package ant.kitchens.service;

import ant.kitchens.dto.OrderDTO;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * Created by wolf   2018/10/25
 */
public interface WeixinMaPayService {

    WxPayUnifiedOrderRequest create(OrderDTO orderDTO);

    WxPayNotifyResponse notify(String notifyData) throws WxPayException;

    WxPayRefundResult refund(OrderDTO orderDTO);
}
