package com.xxg.eduOnline.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxg.eduOnline.entity.EduCourse;
import com.xxg.eduOnline.entity.frontVo.CourseInfoPageVo;
import com.xxg.eduOnline.entity.frontVo.courseInfoVo;
import com.xxg.eduOnline.entity.vo.CourseInfoVo;
import com.xxg.eduOnline.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/19 17:18
 * @Description:课程管理服务接口,保存课程和课程详情信息
 * @Params:courseInfoForm
 */
public interface EduCourseService extends IService<EduCourse> {
    //添加课程基本信息
    String saveCourseInfo(CourseInfoVo courseInfoVo);
   //根据courseId进行回显
    CourseInfoVo getByCourseId(String courseId);

    void updateCourse(CourseInfoVo courseInfoVo);

    CoursePublishVo getCourseInfoById(String courseId);
    //删除课程
    void removeCourseById(String courseId);

    Map<String, Object> getCoursePageList(Page<EduCourse> coursePage, courseInfoVo courseInfoVo);

    CourseInfoPageVo getCourseInfoByCourseId(String courseId);
}
