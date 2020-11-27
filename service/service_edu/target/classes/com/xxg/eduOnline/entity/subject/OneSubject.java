package com.xxg.eduOnline.entity.subject;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/20 14:05
 * @Description: 以及分类
 * @Params:
 */
@Data
public class OneSubject {

    private String id;
    private String title;
    //一个以及分类里面存在多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
