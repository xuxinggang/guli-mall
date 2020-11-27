package com.xxg.eduOnline.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/7/15 11:11
 * @Description: 分页条件查询,对需要进行条件查询的参数进行封装
 * @Params:
 */
@ApiModel(value = "Teacher条件查询对象", description = "讲师分页条件查询对象封装")
@Data
public class CourseQueryWrapper {
    @ApiModelProperty(value = "课程名称")
    private String title;
    @ApiModelProperty(value = "讲师id")
    private String teacherId;
    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;
    @ApiModelProperty(value = "二级类别id")
    private String subjectId;
}
