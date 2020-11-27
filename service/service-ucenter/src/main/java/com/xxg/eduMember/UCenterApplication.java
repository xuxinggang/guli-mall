package com.xxg.eduMember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class UCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UCenterApplication.class,args);
    }
}
