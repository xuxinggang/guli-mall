package com.xxg.eduOnline.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxg.eduOnline.R;
import com.xxg.eduOnline.entity.CourseInfoForm;
import com.xxg.eduOnline.entity.EduCourse;
import com.xxg.eduOnline.entity.vo.*;
import com.xxg.eduOnline.exceptionHandler.DiyException;
import com.xxg.eduOnline.service.EduCourseService;
import com.xxg.eduOnline.vo.CourseOrderInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xxg.testJava
 * @since 2020-08-21
 */
@Api(description="课程管理")
@RestController
@RequestMapping("/eduService/eduCourse")
@CrossOrigin
@EnableConfigurationProperties(CourseInfoForm.class)
public class EduCourseController {

    @Resource
    private CourseInfoForm courseInfoForm;

    @Autowired
    private EduCourseService eduCourseService;

    @RequestMapping("course")
    public CourseInfoForm courseInfoForm(){
        return courseInfoForm;
    }

    //课程列表实现
    @PostMapping("pageCourseWrapper/{current}/{limit}")
    public R pageTeacherWrapper(@PathVariable("current") long current,
                                @PathVariable("limit") long limit,
                                @RequestBody(required = false) CourseQueryWrapper courseQueryWrapper){
        //获取分页page对象
        Page<EduCourse> eduCoursePage = new Page<>(current,limit);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //多条件组合查询，进行判断，在进行条件拼接
        String twoSubject = courseQueryWrapper.getSubjectId();
        String oneSubject = courseQueryWrapper.getSubjectParentId();
        String teacherId = courseQueryWrapper.getTeacherId();
        String title = courseQueryWrapper.getTitle();
        if (!StringUtils.isEmpty(oneSubject)){
            wrapper.like("subject_parent_id",oneSubject);
        }
        if (!StringUtils.isEmpty(twoSubject)){
            wrapper.eq("subject_id",twoSubject);
        }
        if (!StringUtils.isEmpty(teacherId)){
            wrapper.ge("teacher_id",teacherId);
        }
        if (!StringUtils.isEmpty(title)){
            wrapper.le("title",title);
        }
        //对查出来的数据进行排序
        wrapper.orderByDesc("gmt_create");
        //根据条件进行分页查询
        eduCourseService.page(eduCoursePage,wrapper);
        long total = eduCoursePage.getTotal();
        List<EduCourse> records = eduCoursePage.getRecords();
        return R.success().data("total",total).data("records",records);
    }

    @PostMapping("addCourseInfo")
    public R addCourse(@RequestBody CourseInfoVo courseInfoVo){
        //返回插入时，生成的课程id，在完成课程大纲时使用
        String courseId =eduCourseService.saveCourseInfo(courseInfoVo);
        return R.success().data("courseId",courseId);
    }
    //根据课程id回显数据
    @GetMapping("getByCourseId/{courseId}")
    public R getByCourseId(@PathVariable("courseId") String courseId){
        CourseInfoVo CourseInfoVo=eduCourseService.getByCourseId(courseId);
        return R.success().data("CourseInfoVo",CourseInfoVo);
    }
    //对课程信息修改保存
    @PostMapping("updateCourse")
    public R updateCourse(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourse(courseInfoVo);
        return R.success();
    }
    //课程确认信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getPublishCourseInfo(@PathVariable("courseId") String courseId){
        CoursePublishVo coursePublishVo=eduCourseService.getCourseInfoById(courseId);
        return R.success().data("coursePublishVo",coursePublishVo);
    }
    //课程最终发布（就是修改课程状态status）
    @PostMapping("finalPublish/{courseId}")
    public R finalPublishCourseInfo(@PathVariable("courseId") String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus(Course.COURSE_NORMAL);
        boolean b = eduCourseService.updateById(eduCourse);
        if (!b) {
           throw new DiyException(20001,"课程最终发布错误，请修改后重试");
          }
        return R.success();
    }
    //删除课程信息
    @ApiOperation(value = "根据ID逻辑删除课程")
    @DeleteMapping("deleteCourseById/{courseId}")
    public R deleteCourseById(@PathVariable("courseId")String courseId) {
        eduCourseService.removeCourseById(courseId);
        //eduCourseService.removeById(courseId);
        return R.success();
    }

    /**
     * 根据课程id获取课程信息
     * @param courseId
     * @return
     */
    @GetMapping("{courseId}")
    public CourseOrderInfoVo getCourseById(@PathVariable("courseId") String courseId){
        CoursePublishVo coursePublishVo = eduCourseService.getCourseInfoById(courseId);
        CourseOrderInfoVo vo = new CourseOrderInfoVo();
        BeanUtils.copyProperties(coursePublishVo,vo);
        return vo;
    }
}

