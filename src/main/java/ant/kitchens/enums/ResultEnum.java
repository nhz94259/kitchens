package ant.kitchens.enums;


import lombok.Data;
import lombok.Getter;

/**
 * Created by wolf   2018/10/22
 */
@Getter
public enum ResultEnum {

    ERROR(-1,"失败"),
    SUCCESS(0,"成功"),
    UNKNOW_ERROR(500,"未知异常，请联系管理员"),



    PARAMETER_ERROR(11,"参数错误"),


    PRODUCT_STATUS_ERROR(101,"商品状态错误"),
    PRODUCT_NOT_EXIST(102,"商品不存在"),
    PRODUCT_STOCK_NOT_ENOUGH(103,"商品库存不足"),
    PRODUCT_CART_EMPTY(104,"购物车为空"),

    ORDER_OWNER_ERROR(151,"订单归属错误"),
    ORDER_NOT_EXIST(152,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(153,"订单详情"),
    ORDER_STATUS_ERROR(154,"订单状态错误"),
    ORDER_UPDATE_FAIL(155,"订单更新失败"),
    ORDER_PAY_STATUS_ERROR(156,"订单支付状态错误"),
    ORDER_CANCEL_SUCCESS(157,"订单取消成功"),
    ORDER_FINISH_SUCCESS(158,"订单完结成功"),




    WEIXIN_CODE_EMPTY(201,"jscode 为空"),
    WEIXIN_USERCHECK_FAILED(202,"用户验证失败"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(211,"异步通知金额错误"),


    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    ResultEnum( ) {

    }
}
