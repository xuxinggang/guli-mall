package com.xxg.eduOnline.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/19 17:08
 * @Description:
 * @Params:
 */
    @ApiModel(value = "课程基本信息", description = "编辑课程基本信息的表单对象")
    @Data
    @ConfigurationProperties(prefix = "course")
//    @ComponentScan
    public class CourseInfoForm implements Serializable {
        private static final long serialVersionUID = 1L;
        @ApiModelProperty(value = "课程ID")
        @TableId(value = "id",type = IdType.INPUT)//修改主键生成策略
        private String id;
        @ApiModelProperty(value = "课程讲师ID")
        private String teacherId;
        @ApiModelProperty(value = "课程专业ID")
        private String subjectId;
        @ApiModelProperty(value = "课程标题")
        private String title;
        @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
        private BigDecimal price;
        @ApiModelProperty(value = "总课时")
        private Integer lessonNum;
        @ApiModelProperty(value = "课程封面图片路径")
        private String cover;
        @ApiModelProperty(value = "课程简介")
        private String description;
    }