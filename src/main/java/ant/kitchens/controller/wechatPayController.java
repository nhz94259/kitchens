package ant.kitchens.controller;

import ant.kitchens.dto.OrderDTO;
import ant.kitchens.enums.ResultEnum;
import ant.kitchens.exception.KitcException;
import ant.kitchens.service.OrderService;
import ant.kitchens.service.WeixinMaPayService;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by wolf   2018/10/25
 */
@RestController
@RequestMapping("/weixin/pay")
public class wechatPayController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private WeixinMaPayService payService;



    /*创建订单*/
    @ResponseBody
    @GetMapping("/create")
    public Map<String, Object> create(@RequestParam("orderId") String orderId,
                                      @RequestParam("returnUrl") String returnUrl,
                                      Map<String, Object> map) {
        //1. 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new KitcException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2. 发起支付
        WxPayUnifiedOrderRequest payResponse = payService.create(orderDTO);

        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);

        return   map ;
    }


    /*微信异步支付结果*/
    @PostMapping("/notify")
    public void notify(@RequestBody String notifyData) throws WxPayException {
        payService.notify(notifyData);
    }
}
