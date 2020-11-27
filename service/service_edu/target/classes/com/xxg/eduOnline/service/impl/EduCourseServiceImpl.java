package com.xxg.eduOnline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxg.eduOnline.entity.EduCourse;
import com.xxg.eduOnline.entity.EduCourseDescription;
import com.xxg.eduOnline.entity.EduTeacher;
import com.xxg.eduOnline.entity.frontVo.CourseInfoPageVo;
import com.xxg.eduOnline.entity.frontVo.courseInfoVo;
import com.xxg.eduOnline.entity.vo.CourseInfoVo;
import com.xxg.eduOnline.entity.vo.CoursePublishVo;
import com.xxg.eduOnline.exceptionHandler.DiyException;
import com.xxg.eduOnline.mapper.EduCourseMapper;
import com.xxg.eduOnline.service.EduChapterService;
import com.xxg.eduOnline.service.EduCourseDescriptionService;
import com.xxg.eduOnline.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxg.eduOnline.service.EduVideoService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *  添加课程基本信息
 * @author xxg.testJava
 * @since 2020-08-21
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
         //向课程表添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        //eduCourse.setSubjectParentId("123456789");
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        boolean save = this.save(eduCourse);
        if (!save){
            throw new DiyException(20001,"课程数据添加失败，请修改");
        }
        //获取添加到课程表的课程id
        String id = eduCourse.getId();
        //向课程简介表添加课程简介基本信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(id);//设置课程id为描述id，主键相同，有利于维护和优化性能
        boolean b = eduCourseDescriptionService.save(eduCourseDescription);
        if (!b){
            throw new DiyException(20001,"课程描述数据添加失败，请修改");
        }
           return id;
    }

    @Override
    public CourseInfoVo getByCourseId(String courseId) {
        //查询课程表信息
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        //查询描述表信息
        EduCourseDescription descriptionServiceById = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(descriptionServiceById.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourse(CourseInfoVo courseInfoVo) {
        //先修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int byId = baseMapper.updateById(eduCourse);
        if (byId==0) {
            throw new DiyException(20001,"更新课程信息失败");
        }
        //再修改描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(courseInfoVo.getId());
        boolean b = eduCourseDescriptionService.updateById(eduCourseDescription);
        if (!b) {
            throw new DiyException(20001,"更新课程信息失败");
        }
    }

    @Override
    public CoursePublishVo getCourseInfoById(String courseId) {
        CoursePublishVo coursePublishVo = baseMapper.getCoursePublishVo(courseId);
        return coursePublishVo;
    }
    //删除课程
    @Override
    public void removeCourseById(String courseId) {
        //根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);
        //根据课程id删除章节
        eduChapterService.removeChapterByCourseId(courseId);
        //根据课程id删除描述
        eduCourseDescriptionService.removeById(courseId);
        //根据课程id删除课程本身
        baseMapper.deleteById(courseId);
    }

    @Override
    public Map<String, Object> getCoursePageList(Page<EduCourse> coursePage, courseInfoVo courseInfoVo) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //对需要进行条件查询的数据，进行判空；在进行拼接
        if (!StringUtils.isEmpty(courseInfoVo.getSubjectParentId())){//一级分类
            wrapper.eq("subject_parent_id",courseInfoVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseInfoVo.getSubjectId())){//二级分类
            wrapper.eq("subject_id",courseInfoVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseInfoVo.getBuyCountSort())){//购买量
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseInfoVo.getPriceSort())){//价格
            wrapper.orderByDesc("price");
        }
        if (!StringUtils.isEmpty(courseInfoVo.getGmtCreateSort())){//最新时间
            wrapper.orderByDesc("gmt_create");
        }
        baseMapper.selectPage(coursePage,wrapper);
        List<EduCourse> records = coursePage.getRecords();
        long size = coursePage.getSize();
        long pages = coursePage.getPages();
        long total = coursePage.getTotal();
        long current = coursePage.getCurrent();
        boolean hasNext = coursePage.hasNext();
        boolean hasPrevious = coursePage.hasPrevious();
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    @Override
    public CourseInfoPageVo getCourseInfoByCourseId(String courseId) {

        return baseMapper.getCourseInfoByCourseId(courseId);
    }
}
