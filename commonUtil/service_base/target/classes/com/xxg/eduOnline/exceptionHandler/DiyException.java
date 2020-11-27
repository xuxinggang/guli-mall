package com.xxg.eduOnline.exceptionHandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/7/15 17:40
 * @Description: 自定义异常类：需要自己手动抛出，因为系统不能识别改异常
 * @Params:    需要手动try catch抛出自定义异常
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiyException extends RuntimeException{
    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "异常处理信息")
    private String msg;
}
