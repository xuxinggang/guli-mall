package com.xxg.eduOnline.client.impl;

import com.xxg.eduOnline.R;
import com.xxg.eduOnline.client.IsBuyCourse;
import org.springframework.stereotype.Component;

/**
 * @author xxg
 * @version 1.0
 * @date 2021/1/28 15:28
 * @Description: 作为服务熔断机制的fallback
 * @Params: hystrix这个回调方法里应该可以写更多的业务，目前只是模拟使用
 */
@Component
public class IsBuyCourseImpl implements IsBuyCourse {

    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
         return false;
    }
}
