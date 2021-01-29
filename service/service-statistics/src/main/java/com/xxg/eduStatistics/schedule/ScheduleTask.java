package com.xxg.eduStatistics.schedule;

import com.xxg.eduStatistics.service.StatisticsDailyService;
import com.xxg.eduStatistics.utils.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author xxg
 * @version 1.0
 * @date 2021/1/29 13:52
 * @Description: 开启定时任务，corn表达式
 * @Params:
 */
@Component
public class ScheduleTask {

    @Resource
    private StatisticsDailyService statisticsDailyService;

    /**
     * 测试schedule的corn表达式
     * @Scheduled(cron = "0/5 * * * * ?")====>每天七点到二十三点每五秒执行一次
     * cron:七域表达式 ;springboot只支持六位，最后一位默认当前年份
     * 生成网址：https://www.pppet.net/
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void task1(){
        System.out.println("^*^*^*^*^%&^&测试执行了schedule定时任务");
    }

    /**
     * 每天凌晨十二点执行一次定时任务,插入前一天的数据进行添加
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void task2(){
        statisticsDailyService.getStatisticsByDay(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }

}
