package com.xxg.eduOnline;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/7/14 18:23
 * @Description:统一以json格式结果返回，有点像单列模式
 * @Params:   使用了链式编程
 */
@Data
public class R {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    private R(){}
    //成功静态方法
    public static R success(){
        R r = new R();
        r.setCode(ResultCode.SUCCESS);
        r.setSuccess(true);
        r.setMessage("成功");
        return r;
    }
    //失败静态方法
    public static R error(){
        R r = new R();
        r.setCode(ResultCode.ERROR);
        r.setSuccess(false);
        r.setMessage("失败");
        return r;
    }
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public R message(String message){
        this.setMessage(message);
        return this;
    }
    public R code(Integer code){
        this.setCode(code);
        return this;
    }
    //键值对形式的
    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    //MAP集合形式的
    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
