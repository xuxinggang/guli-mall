package com.xxg.eduOnline.controller;

import com.xxg.eduOnline.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/12 16:56
 * @Description:目前模拟登陆到element-ui
 * @Params:
 */
@RestController
@CrossOrigin//解决跨域问题
@RequestMapping("/eduService/user")
public class EduLoginController {

    //login
    @PostMapping("login")
    public R login(){
        return R.success().data("token","admin");
    }
    //info:获取用户信息
    @GetMapping("info")
    public R info(){
     return R.success().data("roles","[admin]")
             .data("name","admin")  //https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597234396646&di=69ae68d3016e9a7ccaff0eeea800aecd&imgtype=0&src=http%3A%2F%2Fs9.rr.itc.cn%2Fr%2FwapChange%2F20166_5_12%2Fa8yosr34001866812596.jpg
             .data("avatar","https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1091405991,859863778&fm=26&gp=0.jpg");
    }
}
