package com.xxg.eduStatistics.client;

import com.xxg.eduOnline.R;
import com.xxg.eduStatistics.client.impl.StatisticsClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xxg
 * @version 1.0
 * @date 2021/1/29 9:08
 * @Description: 远程服务间调用，模拟公司开发的分库分表
 * @Params:
 */
@Component
@FeignClient(name = "service-ucenter",fallback= StatisticsClientImpl.class)
public interface StatisticsClient {

    /**
     * 统计注册人数，生成相应统计图表
     * @param day
     * @return
     */
    @GetMapping("/eduMember/member/dayRegisterCount/{day}")
    public R dayRegisterCount(@PathVariable("day") String day);
}
