package com.xxg.eduOnline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/7/13 19:08
 * @Description:
 * @Params:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.xxg"})//包扫描swagger测试类
@EnableDiscoveryClient //开启微服务Nacos服务注册
@EnableFeignClients //开启微服务调用注解
public class eduServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(eduServiceApplication.class,args);
    }
}
