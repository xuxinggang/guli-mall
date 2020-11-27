package com.xxg.eduOnline.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxg.eduOnline.R;
import com.xxg.eduOnline.entity.EduCourse;
import com.xxg.eduOnline.entity.EduTeacher;
import com.xxg.eduOnline.service.EduCourseService;
import com.xxg.eduOnline.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
@RequestMapping("/eduService/teacherFront")
@CrossOrigin
public class teacherFrontController {
    //对名师列表进行分页查询
    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("/pageTeacherList/{page}/{limit}")
    public R pageListTeacher(@PathVariable("page") long page,@PathVariable("limit") long limit){
        //创建分页page对象（这里传的包一定要是mp的包）
        Page<EduTeacher> teacherPage = new Page<>(page,limit);
        //调用分页方法:原理,底层封装，把分页需要的所有数据，全部返回
        Map<String,Object> map = eduTeacherService.getPageTeacherFrontList(teacherPage);
        return R.success().data(map);
    }
    //讲师详情接口
    @GetMapping("/teacherInfo/{teacherId}")
    public R getTeacherInfoPage(@PathVariable String teacherId){
        //1、根据讲师id查出讲师基本信息
        EduTeacher eduTeacher = eduTeacherService.getById(teacherId);
        //2、讲师可能有对应的课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        //可能存在多门课程，所以用list
        List<EduCourse> courseList = eduCourseService.list(wrapper);
        return R.success().data("teacherInfo",eduTeacher).data("courseList",courseList);
    }
}
