package com.xxg.eduOnline.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxg.eduOnline.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author xxg.testJava
 * @since 2020-07-13
 */
public interface EduTeacherService extends IService<EduTeacher> {
    //对名师列表进行分页查询
    Map<String, Object> getPageTeacherFrontList(Page<EduTeacher> teacherPage);
}
