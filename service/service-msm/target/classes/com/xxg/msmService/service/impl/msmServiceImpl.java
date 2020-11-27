package com.xxg.msmService.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.xxg.msmService.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Map;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/10 10:09
 * @Description: 调用阿里云短信服务，发送短信验证码
 * @Params:
 */
@Service
public class msmServiceImpl implements MsmService {
    @Override
    public boolean sendMsm(String phone, Map<String, Object> parms) {
        //先对手机号进行判断是否为空
        if (StringUtils.isEmpty(phone)){
            return false;
        }
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI4GEffgH19uMPoLig3D7f", "FhNgsJUYcajP7RRrpxAIk9m93YaY7f");
        //创建服务发送实例
        IAcsClient client = new DefaultAcsClient(profile);
        //设置相关固定参数
        CommonRequest request = new CommonRequest();
        request.setSysAction("SendSms");
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysMethod(MethodType.POST);
        request.setSysVersion("2017-05-25");
        //设置发送相关短信参数
        request.putQueryParameter("PhoneNumbers", phone);//设置发送短信的手机号
        request.putQueryParameter("SignName", "我的在线视频学习网站");//设置签名管理名称
        request.putQueryParameter("TemplateCode","SMS_202350200");//设置模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(parms));//设置json格式的模板参数
        //发送短信
        try {
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
