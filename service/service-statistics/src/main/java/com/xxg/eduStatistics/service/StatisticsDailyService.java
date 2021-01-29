package com.xxg.eduStatistics.service;

import com.xxg.eduStatistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author xxg
 * @since 2021-01-28
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void getStatisticsByDay(String day);
}
