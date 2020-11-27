package com.xxg.eduCms.controller;



import com.xxg.eduCms.entity.CrmBanner;
import com.xxg.eduCms.service.CrmBannerService;
import com.xxg.eduOnline.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *前台用户对banner的管理
 * @author xxg.testJava
 * @since 2020-09-08
 */
@RestController
@RequestMapping("/eduCms/crmFrontBanner")
@CrossOrigin
public class CrmBannerFrontController {

    @Autowired
    private CrmBannerService crmBannerService;

    //查询所有的banner
    @GetMapping("getAllBanner")
    public R QueryBannerAll(){
        List<CrmBanner> list=crmBannerService.selectBannerList();
        return R.success().data("list",list);
    }

}

