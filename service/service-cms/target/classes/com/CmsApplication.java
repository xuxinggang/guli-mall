package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/8 14:36
 * @Description:
 * @Params:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.xxg"})
@MapperScan("com.xxg.eduCms.mapper")
@EnableDiscoveryClient //开启微服务Nacos服务注册
@EnableFeignClients //开启微服务调用注解
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
