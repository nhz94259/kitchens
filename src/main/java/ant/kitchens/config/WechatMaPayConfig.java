package ant.kitchens.config;

import ant.kitchens.configproperties.wxMaProperties;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by wolf   2018/10/23
 */
@Component
public class WechatMaPayConfig {
    @Autowired
    private wxMaProperties wxMaConfig;

    @Bean
    public WxPayService wxPayService(WxPayConfig payConfig) {
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }
    @Bean
    public WxPayConfig config() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(wxMaConfig.getAppid());
        payConfig.setMchId(wxMaConfig.getMchId());
        payConfig.setMchKey(wxMaConfig.getAesKey());
        payConfig.setSubAppId(wxMaConfig.getSubAppId());
        payConfig.setSubMchId(wxMaConfig.getSubMchId());
        payConfig.setKeyPath(wxMaConfig.getKeyPath());
        return payConfig;
    }
}
