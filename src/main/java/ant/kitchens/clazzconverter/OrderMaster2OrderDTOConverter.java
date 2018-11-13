package ant.kitchens.clazzconverter;

import ant.kitchens.dto.OrderDTO;
import ant.kitchens.pojo.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wolf   2018/10/24
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);

        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream()
                .map(orderMaster -> convert(orderMaster)
        ).collect(Collectors.toList());
    }
}
