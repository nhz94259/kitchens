package ant.kitchens.service.Impl;

import ant.kitchens.dto.CartDTO;
import ant.kitchens.enums.ProductStatusEnum;
import ant.kitchens.enums.ResultEnum;
import ant.kitchens.exception.KitcException;
import ant.kitchens.pojo.ProductInfo;
import ant.kitchens.respository.ProductInfoRepository;
import ant.kitchens.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by wolf   2018/10/23
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findOnSaleAll() {
        return repository.findByProductStatus(ProductStatusEnum.ON_SALE.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDto:cartDTOList){
            ProductInfo productInfo =Optional.ofNullable(repository.findById(cartDto.getProductId()).get())
                    .orElseThrow(()->new KitcException(ResultEnum.PRODUCT_NOT_EXIST));
            Integer product_amount = productInfo.getProductStock()+cartDto.getProductQuantity();
            productInfo.setProductStock(product_amount);
            repository.save(productInfo);
        }
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDto:cartDTOList){
            ProductInfo productInfo =  Optional.ofNullable(repository.findById(cartDto.getProductId()).get())
                    .orElseThrow(()->new KitcException(ResultEnum.PRODUCT_NOT_EXIST));
            if(productInfo.getProductStock()<cartDto.getProductQuantity()){
                throw new KitcException(ResultEnum.PRODUCT_STOCK_NOT_ENOUGH);
            }
            Integer product_amount = productInfo.getProductStock()-cartDto.getProductQuantity();
            productInfo.setProductStock(product_amount);
            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
       ProductInfo productInfo=  repository.findById(productId)
               .orElseThrow(()->new KitcException(ResultEnum.PRODUCT_NOT_EXIST)) ;
       if(productInfo.getProductStatusEnum()==ProductStatusEnum.ON_SALE){
           throw new KitcException(ResultEnum.PRODUCT_STATUS_ERROR);
       }
        productInfo.setProductStatus(ProductStatusEnum.ON_SALE.getCode());
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo=  repository.findById(productId)
                .orElseThrow(()->new KitcException(ResultEnum.PRODUCT_NOT_EXIST)) ;
        if(productInfo.getProductStatusEnum()==ProductStatusEnum.OFF_SALE){
            throw new KitcException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.OFF_SALE.getCode());
        return repository.save(productInfo);
    }
}
