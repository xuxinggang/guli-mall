package com.xxg.eduOnline.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.xxg.eduOnline.service.OssService;
import com.xxg.eduOnline.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/16 18:07
 * @Description:
 * @Params:
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileOssAvatar(MultipartFile file) {
        // 获取阿里云oss中需要的参数值
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        try{
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            /**
             * 需求：由于每次上传同一个头像，会被覆盖
             * 所以我们需要在文件名称里面生成一个随机唯一的值
             */
            String uuid = UUID.randomUUID().toString().replace("-", "");

            //上传文件名称
            String originalFilename = file.getOriginalFilename();
            originalFilename=uuid+originalFilename;
            //按照时间分类,oss会自动生成相应的文件夹
            String dataPath = new DateTime().toString("yyyy/MM/dd");
            originalFilename=dataPath+"/"+originalFilename;
            /*
            * 1、你的阿里云buckNmae名字
            * 2、你的本地文件名:https://proj-file.oss-cn-beijing.aliyuncs.com/888.jpg
            * 3、上传到文件流
            * */
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(bucketName, originalFilename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();
            //将上传至阿里云oss中的路径自己进行拼接返回
            String url="https://"+bucketName+"."+endpoint+"/"+originalFilename;
            return url;
        }catch(Exception e){
             e.printStackTrace();
            return null;
        }
    }
}
