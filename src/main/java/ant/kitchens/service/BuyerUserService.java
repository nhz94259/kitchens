package ant.kitchens.service;

import ant.kitchens.pojo.BuyerUser;

/**
 * 用户信息操作.
 * Created by wolf   2018/10/24
 */
public interface BuyerUserService {

    //存在
    public BuyerUser exist(BuyerUser purchaser);

    //更新与保存
    public void save(BuyerUser purchaser);
}
