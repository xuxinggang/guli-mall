package com.xxg.eduOnline.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/7/13 19:11
 * @Description:
 * @Params:
 */
@Configuration
@MapperScan("com.xxg.eduOnline.mapper")
public class EduConfig {
    /**
     * SQL 执行性能分析插件
     * 开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长
     */
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(2000);//ms，超过此处设置的ms则sql不执行
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
        /**
         * 逻辑删除插件
         */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
        /**
         * 分页插件
         */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

//    /**
//     * SQL执行效率插件
//     */
//    @Bean
//    @Profile({"dev","test"})// 设置 dev test 环境开启，保证我们的效率
//    public PerformanceInterceptor performanceInterceptor() {
//        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        performanceInterceptor.setMaxTime(800); //ms 设置sql执行的最大时间，如果超过了则不执行
//        performanceInterceptor.setFormat(true);
//        return performanceInterceptor;
//    }
}
