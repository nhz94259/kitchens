package ant.kitchens.service.Impl;

import ant.kitchens.enums.ResultEnum;
import ant.kitchens.exception.KitcException;
import ant.kitchens.pojo.BuyerUser;
import ant.kitchens.respository.UserBuyerRepository;
import ant.kitchens.service.BuyerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by wolf   2018/10/24
 */
@Service
public class BuyerUserServiceImpl implements BuyerUserService {

    @Autowired private UserBuyerRepository buyerRepository;

    @Override
    public BuyerUser exist(BuyerUser buyer) {

        Optional.ofNullable(buyer)
                .map(BuyerUser::getOpenId)
                .orElseThrow(()->new KitcException(ResultEnum.PARAMETER_ERROR));

        BuyerUser  User = buyerRepository.findByOpenId(buyer.getOpenId());

        if (User==null&&User.getUnionId()!=null){
            return  buyerRepository.findByUnionId(buyer.getUnionId());
        }
        return  User;
    }

    @Override
    public void save(BuyerUser buyer) {
         buyerRepository.save(Optional.ofNullable(buyer)
                 .orElseThrow(()->new KitcException(ResultEnum.PARAMETER_ERROR)));
    }
}
