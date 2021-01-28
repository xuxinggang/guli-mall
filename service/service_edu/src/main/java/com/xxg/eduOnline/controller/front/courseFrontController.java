package com.xxg.eduOnline.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxg.eduOnline.R;
import com.xxg.eduOnline.client.IsBuyCourse;
import com.xxg.eduOnline.entity.EduCourse;
import com.xxg.eduOnline.entity.EduTeacher;
import com.xxg.eduOnline.entity.chapter.chapterVo;
import com.xxg.eduOnline.entity.frontVo.CourseInfoPageVo;
import com.xxg.eduOnline.entity.frontVo.courseInfoVo;
import com.xxg.eduOnline.exceptionHandler.DiyException;
import com.xxg.eduOnline.service.EduChapterService;
import com.xxg.eduOnline.service.EduCourseService;
import com.xxg.eduOnline.service.EduTeacherService;
import com.xxg.eduOnline.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Resource
    private IsBuyCourse isBuyCourse;


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
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){
        //根据课程id获取课程分类，课程描述，讲师等数据；属于多表，需自己写sql
        CourseInfoPageVo courseInfoPageVo = eduCourseService.getCourseInfoByCourseId(courseId);
        //根据课程id，查询出章节小节
        List<chapterVo> chapterAll = eduChapterService.getChapterAll(courseId);
        //根据课程id和用户id查询该课程下面的数据是否已经支付过了

        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        if (memberIdByJwtToken==null){
            throw new DiyException(28004,"请先登录！！！");
        }
        boolean buyCourse = isBuyCourse.isBuyCourse(courseId, memberIdByJwtToken);
        return R.success().data("course",courseInfoPageVo).data("chapterAll",chapterAll).data("buyCourse",buyCourse);
    }
}
