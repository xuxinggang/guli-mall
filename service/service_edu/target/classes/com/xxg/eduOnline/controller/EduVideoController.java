package com.xxg.eduOnline.controller;


import com.xxg.eduOnline.R;
import com.xxg.eduOnline.client.vodServiceClient;
import com.xxg.eduOnline.entity.EduChapter;
import com.xxg.eduOnline.entity.EduVideo;
import com.xxg.eduOnline.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author xxg.testJava
 * @since 2020-08-21
 */
@RestController
@RequestMapping("/eduService/eduVideo")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired//调用服务提供方需要的方法
    private vodServiceClient vodServiceClient;

    //添加小节
    @PostMapping("addVideo")
    public R addChapter(@RequestBody EduVideo eduVideo){
        //大纲是查所有课程里面的课程ID，再查出章节，小节
        eduVideoService.save(eduVideo);
        return R.success();
    }
    //删除小节 删除小节的同时，需要删除上传到阿里云中视频内容
    @DeleteMapping("deleteVideo/{videoId}")
    public R deleteChapterById(@PathVariable("videoId") String videoId) {
        //先获取小节id
        EduVideo eduVideo = eduVideoService.getById(videoId);
        //通过视频对象在得到视频id
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断视频id是否存在
        if(!StringUtils.isEmpty(videoSourceId)) {
            //根据视频id，调用vod提供的方法，进行删除小节和中间的视频
            R result = vodServiceClient.deleteVideoById(videoSourceId);
            if (result.getCode()==20001){
                System.out.println("删除视频失败，hystrix熔断器拦截成功");
            }
        }
        //删除小节
        boolean b = eduVideoService.removeById(videoId);
        if (b) {
            return R.success();
        } else {
            return R.error();
        }
    }

    //修改小节
    @PostMapping("updateVideo")
    public R updateChapter(@RequestBody EduVideo eduVideo){
        //大纲是查所有课程里面的课程ID，再查出章节，小节
        eduVideoService.updateById(eduVideo);
        return R.success();
    }
    //根据id查询章节
    @GetMapping("findVideoById/{videoId}")
    public R findChapterById(@PathVariable("videoId") String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return R.success().data("eduVideo",eduVideo);
    }
}

