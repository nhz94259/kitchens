package ant.kitchens.respository;

import ant.kitchens.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wolf   2018/10/22
 */
public interface ProductInfoRepository  extends JpaRepository< ProductInfo, String>{

    List<ProductInfo> findByProductStatus(Integer productStatus);
}
