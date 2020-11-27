package com.xxg.eduOnline.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/28 10:19
 * @Description: 课程最终发布
 * @Params:
 */
@Data
public class CoursePublishVo implements Serializable {
    private String id;//课程id
    private String title;//课程分类
    private String cover;//课程封面
    private String price;//只用于显示
    private Integer lessonNum;//
    private String description;
    private String teacherName;
    private String subjectLevelOne;
    private String subjectLevelTwo;
}
