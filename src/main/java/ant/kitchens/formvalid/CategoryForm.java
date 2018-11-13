package ant.kitchens.formvalid;

import lombok.Data;
import lombok.Getter;

/**
 * Created by wolf   2018/10/25
 */
@Data
public class CategoryForm {
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
