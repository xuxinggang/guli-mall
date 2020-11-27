package com.xxg.eduOnline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxg.eduOnline.entity.EduSubject;
import com.xxg.eduOnline.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author xxg.testJava
 * @since 2020-08-18
 */
public interface EduSubjectService extends IService<EduSubject> {
    //添加课程分类
    void saveExcelFile(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> getOneTwoSubject();
}
