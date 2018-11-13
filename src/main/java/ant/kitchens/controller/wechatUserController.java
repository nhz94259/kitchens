package ant.kitchens.controller;

import ant.kitchens.enums.ResultEnum;
import ant.kitchens.pojo.BuyerUser;
import ant.kitchens.service.BuyerUserService;
import ant.kitchens.utils.JsonUtil;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by wolf   2018/10/23
 */
@Controller
@Slf4j
@RequestMapping("/weixin")
public class wechatUserController {

    @Autowired private WxMaService wxMaService;
    @Autowired private BuyerUserService uBuyerService;


    /*登陆接口*/
    @ResponseBody
    @GetMapping("/login")
    public String login(@RequestParam("code") String code) {

        if (StringUtils.isBlank(code)) {
            log.warn(ResultEnum.WEIXIN_CODE_EMPTY.getMessage());
            return ResultEnum.WEIXIN_CODE_EMPTY.getMessage();
        }
        try {
            WxMaJscode2SessionResult sessionResult = wxMaService.getUserService().getSessionInfo(code);
           // log.info("session :{}", JsonUtil.toJson(sessionResult));
            return JsonUtil.toJson(sessionResult);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return e.toString();
        }
    }


    /*获取用户信息接口*/
    @ResponseBody
    @GetMapping("/info")
    public String info(@RequestParam("sessionKey")  String sessionKey,
                       @RequestParam("signature")  String signature,
                       @RequestParam("rawData") String rawData,
                       @RequestParam("encryptedData")  String encryptedData,
                       @RequestParam("iv") String iv) {

        // 用户信息校验
        if (!wxMaService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return ResultEnum.WEIXIN_USERCHECK_FAILED.getMessage();
        }
        // 解密用户信息
        WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        BuyerUser newUser = new BuyerUser();
        //对于首次登陆的用户进行存储用户信息
          newUser.setOpenId(userInfo.getOpenId());
          newUser.setUnionId(userInfo.getUnionId());
          newUser.setUsername(userInfo.getNickName());
        if(!Optional.ofNullable(uBuyerService.exist(newUser)).isPresent()){
            newUser.setId(UUID.randomUUID().toString());
            uBuyerService.save(newUser);
        }

        return JsonUtil.toJson(userInfo);
    }


    /* 获取用户绑定手机号信息*/
    @ResponseBody
    @GetMapping("/phone")
    public String phone(@RequestParam("sessionKey") String sessionKey,
                        @RequestParam("signature")  String signature,
                        @RequestParam("rawData")    String rawData,
                        @RequestParam("encryptedData")String encryptedData,
                        @RequestParam("iv")         String iv) {

        // 用户信息校验
        if (!wxMaService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return ResultEnum.WEIXIN_USERCHECK_FAILED.getMessage();
        }
        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxMaService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        return JsonUtil.toJson(phoneNoInfo);
    }
}
