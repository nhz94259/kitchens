package ant.kitchens.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 商品类目信息.
 * Created by wolf   2018/10/22
 */
@Data
public class ProductCategory2BuyerVo {

    /** 类目id. */
    @JsonProperty("id")
    private Integer categoryId;

    /** 类目名字. */
    @JsonProperty("name")
    private String categoryName;

    /** 类目编号. */
    @JsonProperty("type")
    private Integer categoryType;

}
