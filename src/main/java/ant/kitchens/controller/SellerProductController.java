package ant.kitchens.controller;

import ant.kitchens.enums.ResultEnum;
import ant.kitchens.exception.KitcException;
import ant.kitchens.formvalid.ProductForm;
import ant.kitchens.pojo.ProductCategory;
import ant.kitchens.pojo.ProductInfo;
import ant.kitchens.service.CategoryService;
import ant.kitchens.service.ProductService;
import ant.kitchens.utils.KeyUtil;

import ant.kitchens.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by wolf   2018/10/23
 */
@Slf4j
@RestController
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public R list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                  @RequestParam(value = "size", defaultValue = "10") Integer size,
                  Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return R.success().put(map);
    }



    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public R onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (KitcException e) {
            log.error("【上架商品】 productid:{} ,error message:{}",productId,e.getMessage());
            return R.error(ResultEnum.ERROR.getCode(),e.getMessage()) ;

        }
        return R.success();
    }
    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public R offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (KitcException e) {
            log.error("【下架商品】 productid:{} ,error message:{}",productId,e.getMessage());
            return R.error(ResultEnum.ERROR.getCode(),e.getMessage()) ;
        }
        return R.success();
    }

    @GetMapping("/index")
    public R index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        return R.success().put(map);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
//    @Cacheable(cacheNames = "product", key = "123")
    @CacheEvict(cacheNames = "product", allEntries = true, beforeInvocation = true)
    public R save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            log.error("【保存/更新商品】   ,error message:{}"  ,bindingResult.getFieldError().getDefaultMessage());
            return R.error(ResultEnum.ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage()) ;
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空, 说明是新增
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId());
            } else {
                form.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (KitcException e) {
            log.error("【保存/更新商品】   ,error message:{}"  ,bindingResult.getFieldError().getDefaultMessage());
            return R.error(ResultEnum.ERROR.getCode(),e.getMessage()) ;
        }


        return R.success();
    }
}
