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
public class TeacherQueryWrapper {
    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间", example = "2019-11-07 09:18:25")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    @ApiModelProperty(value = "查询结束时间", example = "2019-11-12 13:37:01")
    private String end;
}
