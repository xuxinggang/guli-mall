package com.xxg.eduOnline.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxg.eduOnline.R;
import com.xxg.eduOnline.entity.EduChapter;
import com.xxg.eduOnline.entity.chapter.chapterVo;
import com.xxg.eduOnline.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xxg.testJava
 * @since 2020-08-21
 */
@RestController
@RequestMapping("/eduService/eduChapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("getChapter/{courseId}")
    public R getChapter(@PathVariable String courseId){
        //大纲是查所有课程里面的课程ID，再查出章节，小节
        List<chapterVo> chapterVoList = eduChapterService.getChapterAll(courseId);
        return R.success().data("list",chapterVoList);
    }
    //添加章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        //大纲是查所有课程里面的课程ID，再查出章节，小节
       eduChapterService.save(eduChapter);
        return R.success();
    }
    //根据id查询章节
    @GetMapping("findChapterById/{chapterId}")
    public R findChapterById(@PathVariable("chapterId") String chapterId){
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        QueryWrapper<EduChapter> wrapper = queryWrapper.orderByAsc(chapterId);
        
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.success().data("eduChapter",eduChapter);
    }
    //修改章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        //大纲是查所有课程里面的课程ID，再查出章节，小节
        eduChapterService.updateById(eduChapter);
        return R.success();
    }
    //删除章节
    @DeleteMapping("deleteChapter/{chapterId}")
    public R deleteChapterById(@PathVariable("chapterId") String chapterId) {
        boolean b = eduChapterService.deleteChapter(chapterId);
        if (b) {
            return R.success();
        } else {
            return R.error();
        }
    }
}

