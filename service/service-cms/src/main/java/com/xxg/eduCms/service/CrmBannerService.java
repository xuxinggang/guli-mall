package com.xxg.eduCms.service;

import com.xxg.eduCms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author xxg.testJava
 * @since 2020-09-08
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectBannerList();
}
