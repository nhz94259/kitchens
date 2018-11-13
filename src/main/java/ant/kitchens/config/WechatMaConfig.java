package ant.kitchens.config;

import ant.kitchens.configproperties.wxMaProperties;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by wolf   2018/10/23
 */
@Component
@Slf4j
public class WechatMaConfig {

    @Autowired private wxMaProperties wxMaConfig;


    @Bean
    public WxMaService wxMaService(){
        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(wxMaConfig());
        return wxMaService;
    }

    @Bean
    public WxMaConfig wxMaConfig(){
        WxMaInMemoryConfig config = new WxMaInMemoryConfig();
        if(wxMaConfig.getAppid()!=null&&wxMaConfig.getSecret()!=null){
            config.setAppid(wxMaConfig.getAppid());
            config.setSecret(wxMaConfig.getSecret());
        }else{
            log.error("请检查配置文件 Appid secret 信息！");
        }
        return  config;
    }
}
