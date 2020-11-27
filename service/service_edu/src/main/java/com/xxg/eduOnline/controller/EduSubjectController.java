package com.xxg.eduOnline.controller;


import com.xxg.eduOnline.R;
import com.xxg.eduOnline.entity.subject.OneSubject;
import com.xxg.eduOnline.service.impl.EduSubjectServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *添加课程分类
 * @author xxg.testJava
 * @since 2020-08-18
 */
@Api(description ="对excel课程分类管理")
@RestController
@RequestMapping("/eduService/subject")
@CrossOrigin //开启跨域注解
public class EduSubjectController {

    @Autowired
   private EduSubjectServiceImpl eduSubjectService;
    //使用easyExcel来解析上传的excel文件
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addExcelSubject")
    public R addExcelFile(MultipartFile file){
        eduSubjectService.saveExcelFile(file,eduSubjectService);
        return R.success();
    }
    //将excel中的数据以树形结构进行分类
    @ApiOperation(value = "对excel数据进行解析显示")
    @GetMapping("getExcelSubject")
    public R getExcelSubject(){
        List<OneSubject> list= eduSubjectService.getOneTwoSubject();
        return R.success().data("list",list);
    }
}

