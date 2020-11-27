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

      @Value("oss-cn-beijing.aliyuncs.com")
      private String endpoint;
      @Value("LTAI4GEffgH19uMPoLig3D7f")
      private String keyid;
      @Value("FhNgsJUYcajP7RRrpxAIk9m93YaY7f")
      private String keysecret;
      @Value("edu-0816")
      private String bucketname;


      public static String END_POINT;
      public static String KEY_ID;
      public static String KEY_SECRET;
      public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
            END_POINT=endpoint;
            KEY_ID=keyid;
            KEY_SECRET=keysecret;
            BUCKET_NAME= bucketname;
    }
}
