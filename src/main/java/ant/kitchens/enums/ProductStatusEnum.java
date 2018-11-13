package ant.kitchens.enums;

import lombok.Getter;

/**
 * Created by wolf   2018/10/22
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {

    ON_SALE(0,"售卖"),
    OFF_SALE(1,"下架"),

    ;

    private  Integer code;

    private String message;

    ProductStatusEnum(Integer code,String message){
        this.code = code ;
        this.message= message;
    }

}
