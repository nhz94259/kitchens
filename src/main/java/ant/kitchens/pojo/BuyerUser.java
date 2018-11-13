package ant.kitchens.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 买家用户信息.
 * Created by wolf   2018/10/22
 */
@Entity
@Data
@Table(name = "user_buyer")
public class BuyerUser {

    @Id
    private String id;

    /* 买家用户的用户名. */
    private String username;

    /* 买家用户的密码. */
    private String password;

    /* 买家用户的微信的openid. */
    private String openId;

    /* 买家用户的微信的unionId. */
    private String unionId;

    /* 买家用户的微信的电话号码. */
    private String phone;

    /* 买家用户的地址. */
    private String address;

}
