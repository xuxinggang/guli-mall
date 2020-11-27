package com.xxg.eduOnline.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/16 17:52
 * @Description:交给spring管理，spring加载之后，执行接口重写的一个方法
 * @Params:
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {

      @Value("${aliyun.vod.file.keyid}")
      private String keyid;
      @Value("${aliyun.vod.file.keysecret}")
      private String keysecret;

      public static String KEY_ID;
      public static String KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
            KEY_ID=keyid;
            KEY_SECRET=keysecret;
    }
}
