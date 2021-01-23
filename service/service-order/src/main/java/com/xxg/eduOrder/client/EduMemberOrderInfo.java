package com.xxg.eduOrder.client;

import com.xxg.eduOnline.vo.UcenterMemberOrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xxg
 * @version 1.0
 * @date 2021/1/23 16:35
 * @Description: 服务远程调用
 * @Params:
 */
@Component
@FeignClient("service-ucenter")
public interface EduMemberOrderInfo {
    /**
     * 服务远程调用，使用feign
     * order--->member
     * @param memberId
     * @Description: 根据用户id获取用户信息
     * @return
     */
    @GetMapping("/eduMember/member/{memberId}")
    public UcenterMemberOrderVo getMemberById(@PathVariable("memberId") String memberId);
}
