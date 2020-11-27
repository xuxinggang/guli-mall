package com.xxg.eduCms.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/8 15:12
 * @Description:
 * @Params:
 */
@ApiModel(value = "Banner条件查询对象", description = "banner分页条件查询对象封装")
@Data
public class BannerQueryWrapper {
    @ApiModelProperty(value = "banner标题名称,模糊查询")
    private String title;
    @ApiModelProperty(value = "查询开始时间", example = "2019-11-07 09:18:25")
    private String gmtCreate;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    @ApiModelProperty(value = "查询结束时间", example = "2019-11-12 13:37:01")
    private String gmtModified;
}
