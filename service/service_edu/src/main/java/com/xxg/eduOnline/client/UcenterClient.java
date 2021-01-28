package com.xxg.eduOnline.client;

import com.xxg.eduMember.entity.UcenterMember;
import com.xxg.eduOnline.client.impl.UcenterClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    ////通过token字符串获取用户id获取用户信息
    @PostMapping("/eduMember/member/getMemberInfoById/{id}")
    public UcenterMember getMemberInfoById(@PathVariable("id") String id);
}
