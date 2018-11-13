package ant.kitchens.service.Impl;

import ant.kitchens.constant.Constants;
import ant.kitchens.dto.OrderDTO;
import ant.kitchens.enums.PayTypeEnum;
import ant.kitchens.enums.ResultEnum;
import ant.kitchens.exception.KitcException;
import ant.kitchens.service.OrderService;
import ant.kitchens.service.WeixinMaPayService;
import ant.kitchens.utils.JsonUtil;
import ant.kitchens.utils.MathUtil;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by wolf   2018/10/25
 */
@Slf4j
@Service
public class weixinMaPayServiceImpl implements WeixinMaPayService {

    @Autowired private WxPayService wxPayService;

    @Autowired private OrderService orderService;


    //todo test
    @Override
    public WxPayUnifiedOrderRequest create(OrderDTO orderDTO) {

        WxPayUnifiedOrderRequest orderRequest  = new WxPayUnifiedOrderRequest();
        try {
            orderRequest.setBody("微信支付");
            orderRequest.setOpenid(orderDTO.getBuyerOpenid());
            //注意：传入的金额参数单位为分
            orderRequest.setTotalFee(Integer.valueOf(orderDTO.getOrderAmount().multiply(new BigDecimal(100)).toString()));
            //outTradeNo  订单号
            orderRequest.setOutTradeNo(orderDTO.getOrderId());
            //tradeType 支付方式
            orderRequest.setTradeType(PayTypeEnum.WX_PAY.getMessage());

            return wxPayService.createOrder(orderRequest);
        } catch (WxPayException e) {
            log.error("【微信支付】支付失败 订单号={} 原因={}", orderDTO.getOrderId(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public WxPayNotifyResponse  notify(String notifyData) throws WxPayException {
        //1. 验证签名
        //2. 支付的状态
        //3. 支付金额
        //4. 支付人(下单人 == 支付人)

        WxPayOrderNotifyResult payResult = wxPayService.parseOrderNotifyResult(notifyData);
        log.info("【微信支付】异步通知, payResponse={}", JsonUtil.toJson(payResult));

        //查询订单
        OrderDTO orderDTO = orderService.findOne(payResult.getOutTradeNo());

        //判断订单是否存在
        if (orderDTO == null) {
            log.error("【微信支付】异步通知, 订单不存在, orderId={}", payResult.getOutTradeNo());
            throw new KitcException(ResultEnum.ORDER_NOT_EXIST);
        }

        //判断金额是否一致(0.10   0.1)
        if (!MathUtil.equals(Double.valueOf(payResult.getTotalFee().toString()), orderDTO.getOrderAmount().doubleValue()*100)) { //元转分 payResult.getTotalFee() 是分单位
            log.error("【微信支付】异步通知, 订单金额不一致, orderId={}, 微信通知金额={}, 系统金额={}", payResult.getOutTradeNo(),payResult.getTotalFee(), orderDTO.getOrderAmount());
            throw new KitcException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }

        //修改订单的支付状态
        orderService.paid(orderDTO);


        WxPayNotifyResponse response = new WxPayNotifyResponse();
        response.setReturnCode(Constants.WEPAY_NOTIFY_SUCCESS);

        return response;

    }

    @Override
    public WxPayRefundResult refund(OrderDTO orderDTO) {

        WxPayRefundRequest refundRequest = new WxPayRefundRequest();

        WxPayRefundResult refundResponse = new WxPayRefundResult();

        try {
            refundRequest.setOutTradeNo( orderDTO.getOrderId());
            refundRequest.setTotalFee( orderDTO.getOrderAmount().intValue()*100);
            //todo
            refundRequest.setRefundFeeType( "weixinzhifu");
            log.info("【微信退款】request={}", JsonUtil.toJson(refundRequest));
            refundResponse = wxPayService.refund(refundRequest);
        } catch (WxPayException e) {
            e.printStackTrace();
        }

        log.info("【微信退款】response={}", JsonUtil.toJson(refundResponse));

        return refundResponse;
    }
}
