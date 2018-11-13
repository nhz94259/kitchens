package ant.kitchens.service.Impl;

import ant.kitchens.enums.ResultEnum;
import ant.kitchens.exception.KitcException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.nio.file.OpenOption;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by wolf   2018/10/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImplTest {

    @Test
    public  void throwExcep(){
        Optional.of(3<2).orElseThrow(()->new KitcException(ResultEnum.PRODUCT_NOT_EXIST));
    }

    @Test
    public void findOne() {
    }

    @Test
    public void findOnSaleAll() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() {
    }

    @Test
    public void increaseStock() {
    }

    @Test
    public void decreaseStock() {
    }

    @Test
    public void onSale() {
    }

    @Test
    public void offSale() {
    }
}