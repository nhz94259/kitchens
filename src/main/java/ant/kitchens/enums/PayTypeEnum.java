package ant.kitchens.enums;

import lombok.Getter;

/**
 * Created by wolf   2018/10/25
 */
@Getter
public enum PayTypeEnum {

    WX_PAY(0,"微信支付");

    private Integer code;

    private String message;



    PayTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
