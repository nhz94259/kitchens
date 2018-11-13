package ant.kitchens.service.Impl;

import ant.kitchens.enums.ResultEnum;
import ant.kitchens.exception.KitcException;
import ant.kitchens.pojo.BuyerUser;
import ant.kitchens.respository.UserBuyerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by wolf   2018/10/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BuyerUserServiceImplTest {
    @Autowired private UserBuyerRepository buyerRepository;
    @Test
    public void exist() {

        BuyerUser buyer= new BuyerUser();
        buyer.setUnionId("1111");
        Optional.ofNullable(buyer)
                .map(BuyerUser::getUnionId)
                .orElseThrow(()->new KitcException(ResultEnum.PARAMETER_ERROR));

         Assert.assertNotNull(buyerRepository.findByUnionId(buyer.getUnionId()));
    }

    @Test
    public void save() {

    }
}