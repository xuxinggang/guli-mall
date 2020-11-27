package com.xxg.eduCms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxg.eduCms.entity.CrmBanner;
import com.xxg.eduCms.entity.vo.BannerQueryWrapper;
import com.xxg.eduCms.service.CrmBannerService;
import com.xxg.eduOnline.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 * 后台管理员对banner的管理
 * @author xxg.testJava
 * @since 2020-09-08
 */
@RestController
@RequestMapping("/eduCms/crmAdminBanner")
@CrossOrigin
public class CrmBannerAdminController {
    @Autowired
    private CrmBannerService bannerService;
    @GetMapping("BannerPage/{current}/{limit}")
    public R pageBanner(@PathVariable("current") long current,
                        @PathVariable("limit") long limit){
        Page<CrmBanner> crmBannerPage = new Page<>(current, limit);
        bannerService.page(crmBannerPage,null);
        return R.success().data("records",crmBannerPage.getRecords()).data("total",crmBannerPage.getTotal());
    }
    //对banner进行分页管理
    @PostMapping("pageBannerWrapper/{current}/{limit}")
    public R pageBannerWrapper(@PathVariable("current") long current,
                                @PathVariable("limit") long limit,
                                @RequestBody(required = false) BannerQueryWrapper BannerQueryWrapper){
        //获取分页page对象
        Page<CrmBanner> eduTeacherPage = new Page<>(current,limit);
        //构建wrapper条件
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        //多条件组合查询，进行判断，在进行条件拼接
        String title = BannerQueryWrapper.getTitle();
        String gmtCreate = BannerQueryWrapper.getGmtCreate();
        String gmtModified = BannerQueryWrapper.getGmtModified();

        if (!StringUtils.isEmpty(title)){
            wrapper.like("name",title);
        }
        if (!StringUtils.isEmpty(gmtCreate)){
            wrapper.eq("level",gmtCreate);
        }
        if (!StringUtils.isEmpty(gmtModified)){
            wrapper.ge("gmt_create",gmtModified);
        }
        //对查出来的数据进行排序
        wrapper.orderByDesc("gmt_create");
        //根据条件进行分页查询
        bannerService.page(eduTeacherPage,wrapper);
        long total = eduTeacherPage.getTotal();
        List<CrmBanner> records = eduTeacherPage.getRecords();
        return R.success().data("total",total).data("records",records);
    }
    //添加banner
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        bannerService.save(crmBanner);
        return R.success();
    }
    //修改banner
    @PostMapping("bannerUpdate/{id}")
    public R updateBanner(@PathVariable("id") String id,@RequestBody CrmBanner crmBanner){
        boolean b = bannerService.updateById(crmBanner);
        return R.success();
    }
    //根据id查询banner
    @GetMapping("QueryBanner/{id}")
    public R QueryBannerById(@PathVariable("id") String id){
        CrmBanner byId = bannerService.getById(id);
        return R.success().data("bannerList",byId);
    }
    @DeleteMapping("deleteBanner/{id}")
    public R deleteBannerById(@PathVariable("id") String id){
        bannerService.removeById(id);
        return R.success();
    }
}

