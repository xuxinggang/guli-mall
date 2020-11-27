package com.xxg.eduOnline.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/7/15 14:53
 * @Description:  自动填充类 MyMetaObjectHandler
 * @Params:
 * @Component:将该注解标注的类交给spring管理
 */
@Component
public class MyMateObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //属性名称，不是字段名称
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}
