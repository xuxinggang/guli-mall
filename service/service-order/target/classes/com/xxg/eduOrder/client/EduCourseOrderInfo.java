package com.xxg.eduOrder.client;

import com.xxg.eduOnline.vo.CourseOrderInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xxg
 * @version 1.0
 * @date 2021/1/23 16:34
 * @Description: 服务远程调用
 * @Params:
 */
@Component
@FeignClient("service-edu")
public interface EduCourseOrderInfo {
    /**
     * 服务远程调用，使用feign
     * order--->course
     * @param courseId
     * @Description: 根据课程id获取课程id
     * @return
     */
    @GetMapping("/eduService/eduCourse/{courseId}")
    public CourseOrderInfoVo getCourseById(@PathVariable("courseId") String courseId);
}
