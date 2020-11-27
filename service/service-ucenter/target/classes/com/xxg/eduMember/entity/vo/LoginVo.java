package com.xxg.eduMember.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/10 16:37
 * @Description:
 * @Params:
 */
@Data
public class LoginVo {
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;
}
