package com.xxg.eduStatistics.client.impl;

import com.xxg.eduOnline.R;
import com.xxg.eduStatistics.client.StatisticsClient;
import org.springframework.stereotype.Component;

/**
 * @author xxg
 * @version 1.0
 * @date 2021/1/29 9:11
 * @Description: 当远程调用的服务出错时，此方法就会执行
 * @Params:
 */
@Component
public class StatisticsClientImpl implements StatisticsClient {
    @Override
    public R dayRegisterCount(String day) {
        System.out.println("统计的月份为"+day);
        return R.success().message("hhhhhh,我被执行了，因为原服务报错啦！！！");
    }
}
