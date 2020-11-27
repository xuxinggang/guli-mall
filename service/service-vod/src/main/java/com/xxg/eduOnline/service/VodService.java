package com.xxg.eduOnline.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/3 14:08
 * @Description:
 * @Params:
 */
public interface VodService {
    String uploadVideo(MultipartFile file);

    void deleteVideoById(String videoId);

    void deleteMoreVideo(List videoList);
}
