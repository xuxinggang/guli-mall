package com.xxg.eduStatistics.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxg.eduOnline.R;
import com.xxg.eduStatistics.client.StatisticsClient;
import com.xxg.eduStatistics.entity.StatisticsDaily;
import com.xxg.eduStatistics.mapper.StatisticsDailyMapper;
import com.xxg.eduStatistics.service.StatisticsDailyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *  业务：统计用户一天内注册的人数，生成对应的统计图表
 * @author xxg
 * @since 2021-01-28
 */
@RestController
@RequestMapping("/eduStatistics/statisticsDaily")
@CrossOrigin
public class StatisticsDailyController {
    
    @Resource
    private StatisticsDailyService statisticsDailyService;
    
    /**
     * 统计某月用户注册人数，生成相应统计数据
     * @param day
     * @return
     */
      @GetMapping("/statistics/{day}")
    public R getStatisticsByDay(@PathVariable String day){
          //先判断统计表中是否有这一天的数据，如果有，就先删除此数据
          QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
          wrapper.lambda().eq(StatisticsDaily::getDateCalculated,day);
          statisticsDailyService.remove(wrapper);

          statisticsDailyService.getStatisticsByDay(day);
          return R.success().message("统计数据查询成功");
      }

}

