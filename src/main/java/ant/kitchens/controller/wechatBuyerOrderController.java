package ant.kitchens.controller;

import ant.kitchens.clazzconverter.OrderForm2OrderDTOConverter;
import ant.kitchens.dto.OrderDTO;
import ant.kitchens.enums.ResultEnum;
import ant.kitchens.exception.KitcException;
import ant.kitchens.formvalid.OrderForm;
import ant.kitchens.service.BuyerService;
import ant.kitchens.service.OrderService;
import ant.kitchens.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wolf   2018/10/23
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class wechatBuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public R create(@Valid OrderForm orderForm,
                    BindingResult bindingResult) throws KitcException{
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new KitcException(ResultEnum.PARAMETER_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new KitcException(ResultEnum.PRODUCT_CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return R.success().put(map);
    }

    //订单列表
    @GetMapping("/list")
    public R list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size ) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new KitcException(ResultEnum.PARAMETER_ERROR);
        }

        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);
        return R.success().put("orderDTOPage",orderDTOPage.getContent());
    }


    //订单详情
    @GetMapping("/detail")
    public R detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return R.success().put("orderDTO",orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public R cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return R.success();
    }
}
