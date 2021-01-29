package com.xxg.eduStatistics.service.impl;

import com.xxg.eduOnline.R;
import com.xxg.eduOnline.utils.RandomUtil;
import com.xxg.eduStatistics.client.StatisticsClient;
import com.xxg.eduStatistics.entity.StatisticsDaily;
import com.xxg.eduStatistics.mapper.StatisticsDailyMapper;
import com.xxg.eduStatistics.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author xxg
 * @since 2021-01-28
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Resource
    private StatisticsClient statisticsClient;

    @Override
    public void getStatisticsByDay(String day) {
        //目前只是统计注册人数；TODO:其他的也可以这样做(现在可以先做模拟)
        Integer registerNum = (Integer) statisticsClient.dayRegisterCount(day).getData().get("dayRegisterCount");
        System.out.println("当月注册人数为："+registerNum);
        Integer videoViewNum =(Integer) RandomUtils.nextInt(100,200);
        Integer loginNum =(Integer) RandomUtils.nextInt(50,200);
        Integer courseNum =(Integer) RandomUtils.nextInt(0,200);
       //生成统计分析数据
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setCourseNum(courseNum);
        statisticsDaily.setVideoViewNum(videoViewNum);
        statisticsDaily.setRegisterNum(registerNum);
        statisticsDaily.setLoginNum(loginNum);
        baseMapper.insert(statisticsDaily);
    }
}
