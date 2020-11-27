package com.xxg.eduOnline.client.impl;

import com.xxg.eduMember.entity.UcenterMember;
import com.xxg.eduOnline.client.UcenterClient;
import org.springframework.stereotype.Component;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/11/17 17:01
 * @Description:使用nacos微服务调用UCenter接口的方法，获取用户信息
 * @Params:
 */
@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public UcenterMember getMemberInfoById(String id) {
        return null;
    }
}
