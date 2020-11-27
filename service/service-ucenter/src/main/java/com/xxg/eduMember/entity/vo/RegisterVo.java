package com.xxg.eduMember.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/10 16:36
 * @Description: 用于注册使用
 * @Params:
 */
@Data
public class RegisterVo {
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "验证码")
    private String code;
}
