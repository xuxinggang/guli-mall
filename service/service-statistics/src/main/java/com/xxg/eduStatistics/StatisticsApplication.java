package com.xxg.eduStatistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/10 15:20
 * @Description:
 * @Params:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.xxg"})
@MapperScan("com.xxg.eduStatistics.mapper")
@EnableDiscoveryClient //开启服务发现注解
@EnableFeignClients //开启微服务调用注解
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class,args);
    }
}
