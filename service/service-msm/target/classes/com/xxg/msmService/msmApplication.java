package com.xxg.msmService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/10 10:01
 * @Description:
 * @Params:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.xxg"})
public class msmApplication {
    public static void main(String[] args) {
        SpringApplication.run(msmApplication.class,args);
    }
}
