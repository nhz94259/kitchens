package ant.kitchens.service.Impl;

import ant.kitchens.pojo.ProductCategory;
import ant.kitchens.respository.ProductCategoryRepository;
import ant.kitchens.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wolf   2018/10/23
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired private ProductCategoryRepository categoryRepository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return categoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return categoryRepository.save(productCategory);
    }
}
