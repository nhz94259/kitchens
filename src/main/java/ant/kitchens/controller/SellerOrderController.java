package ant.kitchens.controller;

import ant.kitchens.dto.OrderDTO;
import ant.kitchens.enums.ResultEnum;
import ant.kitchens.exception.KitcException;
import ant.kitchens.service.OrderService;

import ant.kitchens.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 卖家端订单
 * Created by wolf
 */
@RestController
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param page 第几页, 从1页开始
     * @param size 一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    public R list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                  @RequestParam(value = "size", defaultValue = "10") Integer size,
                  Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        return R.success().put("orderDTOPage", orderDTOPage)
                .put("currentPage", page)
                .put("size", size)   ;
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public R cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (KitcException e) {
            log.error("【卖家端取消订单】发生异常{}", e);
            return R.error(ResultEnum.ERROR.getCode(),e.getMessage());

        }
        return R.success().put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
    }

    /**
     * 订单详情
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public R  detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        }catch (KitcException e) {
            log.error("【卖家端查询订单详情】发生异常{}", e);
            return R.error(ResultEnum.ERROR.getCode(),e.getMessage());
        }
        return R.success().put("orderDTO", orderDTO);
    }

    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public R  finished(@RequestParam("orderId") String orderId,
                                 Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (KitcException e) {
            log.error("【卖家端完结订单】发生异常{}", e);
            return R.error(ResultEnum.ERROR.getCode(),e.getMessage());
        }

        return R.success().put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
    }
}
