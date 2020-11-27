package com.xxg.eduOnline.entity.chapter;

import com.xxg.eduOnline.entity.subject.TwoSubject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/24 10:18
 * @Description:  章节
 * @Params:
 */
@Data
public class chapterVo {
    private String id;
    private String title;
    //一个以及分类里面存在多个二级分类
    private List<chapterVideo> children = new ArrayList<>();
}
