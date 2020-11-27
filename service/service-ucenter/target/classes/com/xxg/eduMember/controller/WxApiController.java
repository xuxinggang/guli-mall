package com.xxg.eduMember.controller;

import com.google.gson.Gson;
import com.sun.deploy.net.URLEncoder;
import com.xxg.eduMember.entity.UcenterMember;
import com.xxg.eduMember.service.UcenterMemberService;
import com.xxg.eduMember.utils.ConstantPropertiesUtil;
import com.xxg.eduMember.utils.HttpClientUtils;
import com.xxg.eduOnline.exceptionHandler.DiyException;
import com.xxg.eduOnline.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/13 15:47
 * @Description: 实现生成二维码
 * @Params:
 */
@Controller
@RequestMapping("api/ucenter/wx")
@CrossOrigin
public class WxApiController {
    String jwtToken=null;
     @Autowired
     private UcenterMemberService memberService;
    //http://localhost:8150/api/ucenter/wx/callback?code=0917xJll2XdUA54ajWkl2WWLJB37xJl5&state=onlineEdu
    @GetMapping("callback")  //获取扫码人的基本信息
    public String callback(String code,String state){
        //1、获取code值，类似于临时票据（验证码）

        //2、拿着code请求微信固定的地址，得到俩个值access_token，openid
        String baseUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        //对所需地址进行拼接
        String url=String.format(
                baseUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                ConstantPropertiesUtil.WX_OPEN_APP_SECRET,
                code
                );
        //对拼接好的地址，使用httpClient进行请求，获取access_token，openid
        try {
            String accessInfo = HttpClientUtils.get(url);
            //System.out.println("获取access_token_info:"+accessInfo);
            //从accessInfo获取值：access_token，openid；将accessInfo转换为map集合，通过key-value的形式转换
            //使用gson来转换
            Gson gson = new Gson();
            HashMap hashMap = gson.fromJson(accessInfo, HashMap.class);
            String access_token = hashMap.get("access_token").toString();
            String openid = hashMap.get("openid").toString();
            //通过已经得到的access_token和openid，再去请求微信扫描登录的固定地址，获取用户信息，返回给界面显示
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
            "?access_token=%s" +
            "&openid=%s";
            //格式化请求路径，对参数进行拼接
            String userInfoUrl = String.format(baseUserInfoUrl, access_token, openid);
            //通过httpclient模拟浏览器发送get请求
            String userInfo = HttpClientUtils.get(userInfoUrl);
            System.out.println("用户信息为："+userInfo);
            //使用gson来转换(和以上同理)
            HashMap mapUserInfo = gson.fromJson(userInfo, HashMap.class);
            String headimgurl = mapUserInfo.get("headimgurl").toString();
            String nickname = mapUserInfo.get("nickname").toString();
            //将用户信息插入数据库中，判断是否已经注册了
            UcenterMember ucenterMember = memberService.getMemberOpenId(openid);
                ucenterMember = new UcenterMember();
                ucenterMember.setNickname(nickname);
                ucenterMember.setAvatar(headimgurl);
                ucenterMember.setAge(99);
                ucenterMember.setIsDisabled(false);
                ucenterMember.setOpenid(openid);//微信id
                memberService.save(ucenterMember);
            /**
             * 怎么样见信息携带至首页，由于我们在本地localhost，可以用cookie，但是在真实的项目中，需要跨域，cookie不能实现跨域，
             * 所以需要使用jwt生成令牌token，通过路径携带至首页
             */
            jwtToken = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
            //将获取的扫码人信息显示到页面,解决跨域问题

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:http://localhost:3000?token="+jwtToken;
    }


    @GetMapping("login") //返回一个固定地址生成二维码;%s：类似于以前的？，占位符
    public String getWxCode(){
         // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
        "?appid=%s" +
        "&redirect_uri=%s" +
        "&response_type=code" +   //请使用urlEncode对链接进行处理
        "&scope=snsapi_login" +  //应用授权作用域
        "&state=%s" +   //原样传递
        "#wechat_redirect";
        //请使用urlEncode对链接进行处理
       String redirect=ConstantPropertiesUtil.WX_OPEN_REDIRECT_URL;
        try {
            redirect = URLEncoder.encode(redirect, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //使用gson来转换格式
        String onlineEduUrl = String.format(
                baseUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                redirect,//请使用urlEncode对链接进行处理
                "onlineEdu"
        );
        return "redirect:"+onlineEduUrl;
    }

}
