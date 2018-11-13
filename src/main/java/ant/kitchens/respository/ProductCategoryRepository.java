package ant.kitchens.respository;

import ant.kitchens.pojo.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wolf   2018/10/22
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
