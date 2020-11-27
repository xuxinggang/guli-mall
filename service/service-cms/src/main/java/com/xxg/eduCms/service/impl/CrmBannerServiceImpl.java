package com.xxg.eduCms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxg.eduCms.entity.CrmBanner;
import com.xxg.eduCms.mapper.CrmBannerMapper;
import com.xxg.eduCms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 * 先查缓存，如果没有再查数据库然后加入缓存中，以后就如此循环
 * @author xxg.testJava
 * @since 2020-09-08
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Cacheable(key = "'selectBannerList'",value = "banner")//开启缓存
    @Override   //在虚拟机显示时：banner::selectBannerList
    public List<CrmBanner> selectBannerList() {
        //对banner进行id显示，显示钱俩条数据
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 2");
        List<CrmBanner> list = baseMapper.selectList(wrapper);
        return list;
    }
}
