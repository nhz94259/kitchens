package ant.kitchens.service.Impl;

import ant.kitchens.clazzconverter.OrderMaster2OrderDTOConverter;
import ant.kitchens.dto.CartDTO;
import ant.kitchens.dto.OrderDTO;
import ant.kitchens.enums.OrderStatusEnum;
import ant.kitchens.enums.PayStatusEnum;
import ant.kitchens.enums.ResultEnum;
import ant.kitchens.exception.KitcException;
import ant.kitchens.pojo.OrderDetail;
import ant.kitchens.pojo.OrderMaster;
import ant.kitchens.pojo.ProductInfo;
import ant.kitchens.respository.OrderDetailRepository;
import ant.kitchens.respository.OrderMasterRepository;
import ant.kitchens.service.OrderService;
import ant.kitchens.service.ProductService;
import ant.kitchens.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by wolf   2018/10/23
 */
@Service
@Slf4j
public class OrderServiceImpl  implements OrderService{

    @Autowired private OrderMasterRepository orderMasterRepository;
    @Autowired private ProductService productService;
    @Autowired private OrderDetailRepository orderDetailRepository;


    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1. 查询商品
        for(OrderDetail orderDetail :orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            Optional.ofNullable(productInfo)
                      .orElseThrow(()->new KitcException(ResultEnum.PRODUCT_NOT_EXIST));
        //2.计算总价

            ProductInfo Pi = productService.findOne(orderDetail.getProductId());

            orderAmount =Pi.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
        //3.订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);

            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
            }
        //4.订单信息入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);

        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //5. 扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                .stream().map(orderDetail -> new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        Optional<OrderMaster>  oderMasterOp=  orderMasterRepository.findById(orderId) ;
        if(!oderMasterOp.isPresent())
            throw  new KitcException( ResultEnum.ORDER_NOT_EXIST);

        OrderMaster orderMaster = oderMasterOp.get();

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);

        if(CollectionUtils.isEmpty(orderDetailList))
            throw new KitcException(ResultEnum.ORDER_DETAIL_NOT_EXIST);

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage =orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        Optional<OrderMaster>  oderMasterOp=  orderMasterRepository.findById(orderDTO.getOrderId()) ;
        if(!oderMasterOp.isPresent())
            throw  new KitcException( ResultEnum.ORDER_NOT_EXIST);
        OrderMaster  oderMaster = oderMasterOp.get();
        //只有新订单才可取消 已完结不能操作
        if(!oderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))
            throw new KitcException(ResultEnum.ORDER_STATUS_ERROR);
        //更新订单信息 修改状态为已取消
        BeanUtils.copyProperties(orderDTO,oderMaster);
        oderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster updateResult = orderMasterRepository.save(oderMaster);
       if( updateResult==null){
            log.error("【完结订单】更新失败, orderMaster={}", oderMaster);
            throw new KitcException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //todo 消息推送
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new KitcException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new KitcException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //todo 消息推送
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付完成】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new KitcException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付完成】订单支付状态不正确, orderDTO={}", orderDTO);
            throw new KitcException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
            throw new KitcException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable){

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }
}
