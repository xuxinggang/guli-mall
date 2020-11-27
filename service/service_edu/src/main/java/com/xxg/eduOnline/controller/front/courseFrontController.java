package com.xxg.eduOnline.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxg.eduOnline.R;
import com.xxg.eduOnline.entity.EduCourse;
import com.xxg.eduOnline.entity.EduTeacher;
import com.xxg.eduOnline.entity.chapter.chapterVo;
import com.xxg.eduOnline.entity.frontVo.CourseInfoPageVo;
import com.xxg.eduOnline.entity.frontVo.courseInfoVo;
import com.xxg.eduOnline.service.EduChapterService;
import com.xxg.eduOnline.service.EduCourseService;
import com.xxg.eduOnline.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/14 15:16
 * @Description:
 * @Params:
 */
@RestController
@RequestMapping("/eduService/courseFront")
@CrossOrigin
public class courseFrontController {
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;


    //对课程条件查询分页且带条件
    @PostMapping("coursePageList/{page}/{limit}")
    public R coursePageList(@PathVariable("page") long page,
                            @PathVariable("limit") long limit,
                            @RequestBody(required = false) courseInfoVo courseInfoVo){
        Page<EduCourse> coursePage = new Page<>(page,limit);
        Map<String,Object> map=eduCourseService.getCoursePageList(coursePage,courseInfoVo);
        return R.success().data("map",map);
    }
    //课程详情页
    @GetMapping("frontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId){
        //根据课程id回去课程分类，课程描述，讲师等数据；属于多表，需自己写sql
        CourseInfoPageVo courseInfoPageVo = eduCourseService.getCourseInfoByCourseId(courseId);
        //根据课程id，查询出章节小节
        List<chapterVo> chapterAll = eduChapterService.getChapterAll(courseId);

        return R.success().data("course",courseInfoPageVo).data("chapterAll",chapterAll);
    }
}
