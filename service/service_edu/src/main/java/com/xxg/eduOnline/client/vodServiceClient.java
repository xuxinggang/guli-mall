package com.xxg.eduOnline.client;

import com.alibaba.nacos.api.config.annotation.NacosProperty;
import com.xxg.eduOnline.R;
import com.xxg.eduOnline.client.impl.vodServiceClientFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/4 15:52
 * @Description: feign；服务发现，通过服务名来调用服务方法
 * @Params:fallback:feign调用的服务提供端的接口服务不通时，会调用fallback下的对应的方法
 */
@Component
@FeignClient(name = "service-vod",fallback= vodServiceClientFeign.class) //调用vod提供的方法（消费者）
public interface vodServiceClient {

    //调用需要vod中方法的全局路径
    @DeleteMapping("/eduVod/video/deleteVideoById/{videoId}")
    public R deleteVideoById(@PathVariable("videoId") String videoId);

    //删除课程中的多个视频
    @DeleteMapping("/eduVod/video/deleteAllVideo")
    public R deleteAllVideo(@RequestParam("videoList") List<String> videoList);
}
