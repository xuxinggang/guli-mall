package com.xxg.msmService.controller;

import com.xxg.eduOnline.R;
import com.xxg.eduOnline.exceptionHandler.DiyException;
import com.xxg.msmService.service.MsmService;
import com.xxg.msmService.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/10 10:00
 * @Description:
 * @Params:
 */
@CrossOrigin
@RestController
@RequestMapping("/eduMsm")
public class msmController {

    @Autowired
    private MsmService msmService;

    //使用redis模板进行短信验证码有效时间验证;先从redis查询有没短信验证码，没有的话，就由阿里云短信服务发送验证码
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("/msm/{phone}")
    public R sendPhoneCode(@PathVariable("phone") String phone){
        //设置验证码的有效时间（使用redis设置）
        String code= (String) redisTemplate.opsForValue().get(phone);//通过手机号，获取redis中的验证码，存在，直接发送
        if (!StringUtils.isEmpty(code)){
            return R.success().message("redis中存在数据验证码");
        }

        //发送给阿里云的验证码，是由我们自己生成的，有四位，八位...,由我们发送到阿里云短信服务
        code= RandomUtil.getSixBitRandom();
        Map<String, Object> parms = new HashMap<>();
        parms.put("code",code);
        //调用短信服务发送短信
        boolean isSend=msmService.sendMsm(phone,parms);
        if (!isSend){
            throw new DiyException(20001,"短信发送失败");
        }else {
            //发送短信成功，则设置验证码有效时间为5分钟
            redisTemplate.opsForValue().set(phone,code,60, TimeUnit.MINUTES);
            return R.success();
        }
    }
}
