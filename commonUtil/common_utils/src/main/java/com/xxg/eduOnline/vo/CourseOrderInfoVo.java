package com.xxg.eduOnline.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/21 10:22
 * @Description:主要用来实现课程和课程描述表数据的显示以及存储到数据库
 * @Params:
 */
@Api(description = "课程描述和课程一对一")
@Data
public class CourseOrderInfoVo {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "课程ID")
    private String id;
    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;
    @ApiModelProperty(value = "课程专业ID")
    private String subjectId;
    @ApiModelProperty(value = "课程标题")
    private String title;
    @ApiModelProperty(value = "课程专业父级ID")
    private String subjectParentId;
    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;//数值精确度为0.01
    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;
    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;
    @ApiModelProperty(value = "课程简介")
    private String description;
}
