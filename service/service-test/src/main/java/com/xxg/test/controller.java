package com.xxg.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/11/27 14:06
 * @Description:
 * @Params:
 */
@RestController
@RequestMapping("/test")
public class controller {
    private String name;
    private String sex;
}
