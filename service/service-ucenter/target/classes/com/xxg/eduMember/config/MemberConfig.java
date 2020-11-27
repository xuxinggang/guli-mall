package com.xxg.eduMember.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/10 15:24
 * @Description:
 * @Params:
 */
@Configuration
@MapperScan(basePackages = "com.xxg.eduMember.mapper")
public class MemberConfig {
}
