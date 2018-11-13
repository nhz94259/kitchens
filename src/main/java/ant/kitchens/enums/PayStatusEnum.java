package ant.kitchens.enums;

import lombok.Getter;

/**
 * Created by wolf   2018/10/23
 */
@Getter
public enum PayStatusEnum implements CodeEnum{

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
