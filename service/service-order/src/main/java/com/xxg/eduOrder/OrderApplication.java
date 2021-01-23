package com.xxg.eduOrder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xxg
 * @version 1.0
 * @date 2021/1/23 10:30
 * @Description:
 * @Params:
 */
@EnableDiscoveryClient //服务发现
@EnableFeignClients //服务远程调用注解
@SpringBootApplication
@ComponentScan("com.xxg")
@MapperScan("com.xxg.eduOrder.mapper") //用于服务之间进行远程调用
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
