package ant.kitchens.service;

import ant.kitchens.dto.OrderDTO;

/**
 * 用户操作.
 * Created by wolf   2018/10/23
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
