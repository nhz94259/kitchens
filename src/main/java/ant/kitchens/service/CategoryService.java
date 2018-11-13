package ant.kitchens.service;

import ant.kitchens.pojo.ProductCategory;

import java.util.List;

/**
 * 说明:类目操作.
 * Created by wolf   2018/10/23
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
