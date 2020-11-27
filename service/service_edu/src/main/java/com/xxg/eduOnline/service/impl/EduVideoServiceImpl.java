package com.xxg.eduOnline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxg.eduOnline.client.vodServiceClient;
import com.xxg.eduOnline.entity.EduVideo;
import com.xxg.eduOnline.exceptionHandler.DiyException;
import com.xxg.eduOnline.mapper.EduVideoMapper;
import com.xxg.eduOnline.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author xxg.testJava
 * @since 2020-08-21
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
     //注入服务提供者的方法
    @Autowired
    private vodServiceClient vodServiceClient;
     //根据课程id，删除小节和小节中对应的视频
    @Override
    public void removeVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        //根据课程id，获取对应的所有视频id
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(wrapperVideo);
        //将eduVideos变成我们需要的格式（1,2,3,4）
        List<String> videos = new ArrayList<>();
        for (int i = 0; i < eduVideos.size(); i++) {
            EduVideo eduVideo = eduVideos.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)) {
                videos.add(videoSourceId);
            }
        }
        //如果视频id，一个都没有，就没有必要调用远程服务
        if (videos.size()>0) {
            //先根据多个视频id，删除视频
            vodServiceClient.deleteAllVideo(videos);
        }
        //再删小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
