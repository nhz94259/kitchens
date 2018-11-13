package ant.kitchens.respository;

import ant.kitchens.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by wolf   2018/10/23
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>{

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
