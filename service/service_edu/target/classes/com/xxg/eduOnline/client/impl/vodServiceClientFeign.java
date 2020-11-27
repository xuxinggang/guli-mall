package com.xxg.eduOnline.client.impl;

import com.xxg.eduOnline.R;
import com.xxg.eduOnline.client.vodServiceClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/6 16:12
 * @Description:
 * @Params:
 */
@Component
public class vodServiceClientFeign implements vodServiceClient {
    @Override
    public R deleteVideoById(String videoId) {
        return R.error().message("执行了hystrix熔断器deleteVideoById");
    }

    @Override
    public R deleteAllVideo(List<String> videoList) {
        return R.error().message("执行了hystrix熔断器的deleteAllVideo");
    }
}
