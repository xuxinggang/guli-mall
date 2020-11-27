package com.xxg.eduOnline.mapper;

import com.xxg.eduOnline.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxg.eduOnline.entity.frontVo.CourseInfoPageVo;
import com.xxg.eduOnline.entity.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author xxg.testJava
 * @since 2020-08-21
 */
@Repository
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {
//<!--根据课程id，查询课程确认信息，最终发布-->
    public CoursePublishVo getCoursePublishVo(String courseId);

    CourseInfoPageVo getCourseInfoByCourseId(String courseId);
}
