package ant.kitchens.respository;

import ant.kitchens.pojo.BuyerUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wolf   2018/10/23
 */
public interface UserBuyerRepository extends JpaRepository<BuyerUser,String>{

    BuyerUser findByOpenId(String openid);

    BuyerUser findByUnionId(String unionid);
}
