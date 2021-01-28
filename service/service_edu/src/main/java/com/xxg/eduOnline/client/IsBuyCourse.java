package com.xxg.eduOnline.client;

import com.xxg.eduOnline.R;
import com.xxg.eduOnline.client.impl.IsBuyCourseImpl;
import com.xxg.eduOnline.client.impl.vodServiceClientFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xxg
 * @version 1.0
 * @date 2021/1/28 15:25
 * @Description: 远程服务调用order的接口进行判断课程是否已经购买
 * @Params:
 */
@Component
@FeignClient(name = "service-order",fallback= IsBuyCourseImpl.class) //调用vod提供的方法（消费者）
public interface IsBuyCourse {
    //在服务进行远程调用时，路径变量需要补充完整
    @GetMapping("/eduOrder/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
