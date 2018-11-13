package ant.kitchens.enums;

import lombok.Getter;

/**
 * Created by wolf   2018/11/8
 */
@Getter
public enum ProductLocationTypeEnum {
    DEFAULT(0,"默认"),
    HOME_HOT(1,"首页热门"),
    RECOMMEND(2,"活动推荐"),
    ;

    private Integer code;

    private String message;


    ProductLocationTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
