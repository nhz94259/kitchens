package ant.kitchens.service;

import ant.kitchens.dto.CartDTO;
import ant.kitchens.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品信息操作
 * Created by wolf   2018/10/23
 */
public interface ProductService {

    //返回单个商品信息
    ProductInfo findOne(String productId);

    //返回所有上架商品
    List<ProductInfo> findOnSaleAll();

    //返回所有商品
    Page<ProductInfo> findAll(Pageable pageable);

    //保存新增商品
    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);
}
