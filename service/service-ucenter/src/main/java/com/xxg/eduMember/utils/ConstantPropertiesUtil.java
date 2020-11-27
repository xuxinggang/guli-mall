package com.xxg.eduMember.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/13 15:40
 * @Description: 微信扫码实现工具类
 * @Params:
 */
@Configuration
//@PropertySource("classpath:application.properties")
//@ConfigurationProperties(prefix="wx.open")
//@Data
public class ConstantPropertiesUtil implements InitializingBean {
//    @Value("${wx.open.app_id}")
    private String appID;
//    @Value("${wx.open.app_secret}")
    private String appSecret;
//    @Value("${wx.open.redirect_url}")
    private String redirectUrl;

    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_APP_SECRET;
    public static String WX_OPEN_REDIRECT_URL;
    @Override
    public void afterPropertiesSet() throws Exception {
        WX_OPEN_APP_ID = appID;
        WX_OPEN_APP_SECRET = appSecret;
        WX_OPEN_REDIRECT_URL = redirectUrl;
    }
}
