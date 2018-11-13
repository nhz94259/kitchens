package ant.kitchens.service.Impl;

import ant.kitchens.enums.ResultEnum;
import ant.kitchens.exception.KitcException;
import ant.kitchens.pojo.ProductCategory;
import ant.kitchens.pojo.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by wolf   2018/10/23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CategoryServiceImplTest {


    @Autowired private CategoryServiceImpl categoryService;
    @Test
    public void findByCategoryTypeIn() {

        List<ProductCategory> categorys = Optional.ofNullable(categoryService
                .findByCategoryTypeIn(Arrays.asList(1)))
                .orElseThrow(()-> new KitcException(ResultEnum.PARAMETER_ERROR));
    }
}