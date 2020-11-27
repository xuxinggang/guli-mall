package com.xxg.eduOnline.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/16 18:06
 * @Description:
 * @Params:
 */
public interface OssService {
    //上传文件到阿里云oss
    String uploadFileOssAvatar(MultipartFile file);
}
