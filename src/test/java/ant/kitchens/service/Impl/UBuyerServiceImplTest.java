package ant.kitchens.service.Impl;

import ant.kitchens.pojo.BuyerUser;
import ant.kitchens.service.BuyerUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wolf   2018/10/24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j

public class UBuyerServiceImplTest {

    @Autowired private BuyerUserService ubuyerService;

    @Test
    public void exist() {
        BuyerUser u= new BuyerUser();
        u.setUnionId("1");
        BuyerUser x = ubuyerService.exist(u);
        Assert.assertNotNull(x);
    }

    @Test
    public void save() {
        BuyerUser u= new BuyerUser();
        u.setId("1ll");
        ubuyerService.save(u);
    }
}