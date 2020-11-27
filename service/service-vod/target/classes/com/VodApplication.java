package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/31 17:32
 * @Description:
 * @Params:exclude = DataSourceAutoConfiguration.class:由于application配置文件中，没有加入数据源等数据库配置，所以要排除数据源
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.xxg"})
@EnableDiscoveryClient //开启微服务注册中心nacos
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class, args);
    }
}
