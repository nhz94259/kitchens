package ant.kitchens.respository;

import ant.kitchens.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wolf   2018/10/23
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String>{

    List<OrderDetail> findByOrderId(String orderId);

}
