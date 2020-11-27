package com.xxg.eduOnline.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxg.eduOnline.R;
import com.xxg.eduOnline.entity.EduCourse;
import com.xxg.eduOnline.entity.EduTeacher;
import com.xxg.eduOnline.service.EduCourseService;
import com.xxg.eduOnline.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/8 16:30
 * @Description: 前台热门课程和名师显示
 * @Params:
 */
@RestController
@CrossOrigin
@RequestMapping("/eduService/frontIndex")
public class indexBannerController {

    @Autowired//查询前八条热门课程
    private EduCourseService courseService;
    @Autowired//查询前四条热门讲师
    private EduTeacherService teacherService;

    @Cacheable(value = "hotCourseAndTeacher",key = "'courseAndTeacher'")
    @GetMapping("index")
    public R index(){
        QueryWrapper<EduCourse> wrapperCourse = new QueryWrapper<>();
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperCourse.last("limit 8");
        wrapperCourse.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        wrapperTeacher.orderByDesc("id");

        List<EduCourse> courses = courseService.list(wrapperCourse);
        List<EduTeacher> teachers = teacherService.list(wrapperTeacher);
        return R.success().data("courses",courses).data("teachers",teachers);
    }
}
