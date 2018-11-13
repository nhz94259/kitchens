package ant.kitchens.controller;

import ant.kitchens.enums.ProductLocationTypeEnum;
import ant.kitchens.pojo.ProductCategory;
import ant.kitchens.pojo.ProductInfo;
import ant.kitchens.service.CategoryService;
import ant.kitchens.service.ProductService;
import ant.kitchens.utils.R;
import ant.kitchens.vo.ProductInfoVO;
import ant.kitchens.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by wolf   2018/11/8
 */
@RestController
@Slf4j
@RequestMapping("/buyer/home")
public class HomeShowController {


    @Autowired
    private ProductService productService;

    @Autowired private CategoryService categoryService;

    @GetMapping("/hot")
    public R  ListHotProducts(){
             //查出所有在售商品信息且是首页热门的产品
            List<ProductInfo>productInfoList = productService.findOnSaleAll()
                    .stream().filter(ProductInfo->ProductInfo.getLocationType()
                    .equals(ProductLocationTypeEnum.HOME_HOT.getCode()))
                    .collect(Collectors.toList());


            //收集在售商品类目编号
            List<Integer>  categoryTypeList = productInfoList.stream()
                    .map(a->a.getCategoryType())
                    .distinct()
                    .collect(Collectors.toList());

            //查出所有在售的商品类目
            List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

            List<ProductVO> productVOList = new ArrayList<>();
            //遍历商品类目,封装返回对象
            productCategoryList.forEach(productCategory->{

                ProductVO productVO = new ProductVO();
                productVO.setCategoryType(productCategory.getCategoryType());
                productVO.setCategoryName(productCategory.getCategoryName());

                List<ProductInfoVO> productInfoVOList = new ArrayList<>();
                productInfoList.forEach(productInfo->{
                    if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                        ProductInfoVO productInfoVO = new ProductInfoVO();
                        BeanUtils.copyProperties(productInfo, productInfoVO);
                        productInfoVOList.add(productInfoVO);
                    }
                });

                productVO.setProductInfoVOList(productInfoVOList);
                productVOList.add(productVO);
            });
            return R.success().put("productVOList",productVOList);
        }


    @GetMapping("/recommend")
    public R  recommendProducts(){
        //查出所有在售商品信息且是首页推荐的产品
        List<ProductInfo>productInfoList = productService.findOnSaleAll()
                .stream().filter(ProductInfo->ProductInfo.getLocationType()
                        .equals(ProductLocationTypeEnum.RECOMMEND.getCode()))
                .collect(Collectors.toList());


        //收集在售商品类目编号
        List<Integer>  categoryTypeList = productInfoList.stream()
                .map(a->a.getCategoryType())
                .distinct()
                .collect(Collectors.toList());

        //查出所有在售的商品类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVOList = new ArrayList<>();
        //遍历商品类目,封装返回对象
        productCategoryList.forEach(productCategory->{

            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            productInfoList.forEach(productInfo->{
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            });

            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        });
        return R.success().put("productVOList",productVOList);
    }


}
