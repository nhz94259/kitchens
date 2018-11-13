package ant.kitchens.respository;

import ant.kitchens.enums.ResultEnum;
import ant.kitchens.exception.KitcException;
import ant.kitchens.pojo.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by wolf   2018/10/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoRepositoryTest {

    @Autowired ProductInfoRepository productInfoRepository;


    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductName("sla");
        productInfo.setProductPrice(new BigDecimal(2.0));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("1111");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        ProductInfo result = productInfoRepository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findById(){
        //springboot 2.0
        ProductInfo product = productInfoRepository.findById("1").get();
        log.info("product is {}",product);
        Assert.assertNotNull(product);
    }

    @Test
    public void option(){
        ProductInfo productInfo1 =null;
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductName("sla");
        productInfo.setProductPrice(new BigDecimal(2.0));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("1111");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        Optional.ofNullable(productInfo1) ;
        ProductInfo  product= Optional.ofNullable(productInfo1).orElse(productInfo);
        Assert.assertEquals("http://xxxxx.jpg",product.getProductIcon());
    }


    @Test
    public void find(){
        ProductInfo x = productInfoRepository.findById("1231").orElseThrow(() -> new KitcException(ResultEnum.PRODUCT_NOT_EXIST));
        Assert.assertNotNull(x);
    }
}