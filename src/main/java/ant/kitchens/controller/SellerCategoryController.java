package ant.kitchens.controller;

import ant.kitchens.enums.ResultEnum;
import ant.kitchens.exception.KitcException;
import ant.kitchens.formvalid.CategoryForm;
import ant.kitchens.pojo.ProductCategory;
import ant.kitchens.service.CategoryService;

import ant.kitchens.utils.R;
import ant.kitchens.vo.ProductCategory2BuyerVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by wolf   2018/10/23
 */
@Slf4j
@RestController
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 类目列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public R list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        return R.success().put("categoryList", categoryList);
    }


    /**
     * 展示
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public R index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null) {
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("category", productCategory);
            return R.success().put(map);
        }else{
            return R.error(ResultEnum.PARAMETER_ERROR.getCode(),ResultEnum.PARAMETER_ERROR.getMessage()+ "【categoryId is null 】 ");
        }

    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/save")
    public R save(@Valid CategoryForm form,
                             BindingResult bindingResult ) throws  KitcException{
        if (bindingResult.hasErrors()) {
            log.error("【更新类目】  ,error message:{}",bindingResult.getFieldError().getDefaultMessage());
            return R.error(bindingResult.getFieldError().getDefaultMessage());
        }

        ProductCategory productCategory = new ProductCategory();
        try {
            if (form.getCategoryId() != null) {
                productCategory = categoryService.findOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form, productCategory);
            categoryService.save(productCategory);
        } catch (KitcException e) {
            log.error("【更新类目】  ,error message:{}", e.getMessage());
            return   R.error( e.getMessage());
        }
         return R.success();
    }
}
