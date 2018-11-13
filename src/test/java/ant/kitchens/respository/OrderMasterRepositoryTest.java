package ant.kitchens.respository;

import ant.kitchens.enums.ResultEnum;
import ant.kitchens.exception.KitcException;
import ant.kitchens.pojo.OrderMaster;
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
 * Created by wolf   2018/10/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterRepositoryTest {

    @Autowired private OrderMasterRepository orderMasterRepository;
    @Test
    public void findByBuyerOpenid() {
    }

    @Test
    public void findByid(){
        Optional<OrderMaster>  oderMasterOp=  orderMasterRepository.findById("1") ;

     }
}