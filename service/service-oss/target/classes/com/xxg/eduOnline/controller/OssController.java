package com.xxg.eduOnline.controller;

import com.xxg.eduOnline.R;
import com.xxg.eduOnline.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/16 18:06
 * @Description:
 * @Params:
 */
@RestController
@RequestMapping("/eduOss/fileOss")
@CrossOrigin
public class OssController {
    //将具体上传步骤，在业务层中实现
    @Autowired
    private OssService ossService;

    //头像上传方法
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        //设置阿里云头像的访问路径
        String url=ossService.uploadFileOssAvatar(file);
         return R.success().data("url",url);
    }
}
