package ant.kitchens.configproperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by wolf   2018/10/23
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat.miniapp.configs")
public class wxMaProperties {
    /**
     * 设置微信小程序的appid
     */
    private String appid;

    /**
     * 设置微信小程序的Secret
     */
    private String secret;

    /**
     * 设置微信小程序消息服务器配置的token
     */
    private String token;

    /**
     * 设置微信小程序消息服务器配置的EncodingAESKey
     */
    private String aesKey;

    /**
     * 消息格式，XML或者JSON
     */
    private String msgDataFormat;

    /**
     * 设置小程序支付  支付的商户号
     */
    private String mchId;
    /**
     * 设置小程序支付 商户密钥
     */
    private String mchkey;
    /**
     * 设置小程序支付 apiclient_cert.p12的绝对路径、 包含了私钥信息的证书文件
     */
    private String KeyPath;
    /**
     * 设置小程序支付
     */
    private String subAppId;
    /**
     * 设置小程序支付
     */
    private String SubMchId;
    /**
     * 设置小程序支付
     */
    private String notifyUrl;
}
