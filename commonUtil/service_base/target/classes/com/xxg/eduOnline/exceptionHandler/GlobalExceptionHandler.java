package com.xxg.eduOnline.exceptionHandler;

import com.xxg.eduOnline.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/7/15 17:04
 * @Description:统一异常处理接口
 * @Params:拦截所有异常
 * 异常处理机制：指定异常>全局异常
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理...");
    }
    //处理特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了自定义ArithmeticException异常处理..."+e.getMessage());
    }
    //处理自定义类异常
    @ExceptionHandler(DiyException.class)
    @ResponseBody
    public R error(DiyException e){
        log.error(e.getMsg());
        e.printStackTrace();
//        return R.error().message("执行了自定义DiyException异常处理...");
          return R.error().code(e.getCode()).message(e.getMsg());//通用处理
    }
}
