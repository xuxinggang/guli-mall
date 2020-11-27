package com.xxg.eduOnline.client;

import com.xxg.eduMember.entity.UcenterMember;
import com.xxg.eduOnline.client.impl.UcenterClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/11/17 16:52
 * @Description:
 * @Params:
 */
@Component
@FeignClient(name="service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {

    //根据用户id获取用户信息
    @GetMapping("/eduMember/member/getMemberInfoById/{id}")
    public UcenterMember getMemberInfoById(@PathVariable("id") String id);
}
