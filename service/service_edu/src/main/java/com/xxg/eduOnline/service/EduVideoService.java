package com.xxg.eduOnline.service;

import com.xxg.eduOnline.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author xxg.testJava
 * @since 2020-08-21
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideoByCourseId(String courseId);
}
