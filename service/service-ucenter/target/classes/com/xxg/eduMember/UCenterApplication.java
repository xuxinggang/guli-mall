package com.xxg.eduMember;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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
@EnableDiscoveryClient //开启服务发现注解
public class UCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UCenterApplication.class,args);
    }
}
