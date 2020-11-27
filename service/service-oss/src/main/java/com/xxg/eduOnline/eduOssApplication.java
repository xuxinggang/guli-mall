package com.xxg.eduOnline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/7/13 19:08
 * @Description:
 * @Params:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.xxg"})//包扫描swagger测试类
@EnableDiscoveryClient
@EnableFeignClients
public class eduOssApplication {
    public static void main(String[] args) {
        SpringApplication.run(eduOssApplication.class,args);
    }
}
