package com.xxg.eduOnline.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.netflix.client.IResponse;
import com.xxg.eduOnline.R;
import com.xxg.eduOnline.exceptionHandler.DiyException;
import com.xxg.eduOnline.service.VodService;
import com.xxg.eduOnline.utils.ConstantPropertiesUtils;
import com.xxg.eduOnline.utils.initVodSDK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/3 14:07
 * @Description:
 * @Params:
 */
@RestController
@RequestMapping("/eduVod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadVideo")
    public R uploadVideo(MultipartFile file){
        //把视频上传到阿里云，会得到视频id，我们把它存入数据库中即可
        String videoId=vodService.uploadVideo(file);
        return R.success().data("videoId",videoId);
    }
    //删除小节中的视频
    @DeleteMapping("deleteVideoById/{videoId}")
    public R deleteVideoById(@PathVariable("videoId") String videoId){
        vodService.deleteVideoById(videoId);
        return R.success().message("视频删除成功");
    }
    //删除课程中的多个视频
    @DeleteMapping("deleteAllVideo")
    public R deleteAllVideo(@RequestParam("videoList") List<String> videoList){
        vodService.deleteMoreVideo(videoList);
        return R.success();
    }
    //视频点播功能实现
    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id){

        try {
            //1、根据视频id获取视频播放地址（进行初始化）
//            DefaultAcsClient client = initVodSDK.initVodClient("LTAI4GEffgH19uMPoLig3D7f", "FhNgsJUYcajP7RRrpxAIk9m93YaY7f");
            DefaultAcsClient client = initVodSDK.initVodClient(ConstantPropertiesUtils.KEY_ID, ConstantPropertiesUtils.KEY_SECRET);
            //2、创建获取视频地址request和response
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //3、向request中设置视频id
            request.setVideoId(id);
            //4、调用初始化对象里面的方法，传递request，获取数据
            GetVideoPlayAuthResponse response=client.getAcsResponse(request);
            String playAuth= response.getPlayAuth();
            System.out.println("授权凭证为："+playAuth);
            //VideoMeta信息
            return R.success().data("playAuth",playAuth);
        } catch (ClientException e) {
            throw new DiyException(20001,"視頻播放凭证获取失败");
        }
    }
}
