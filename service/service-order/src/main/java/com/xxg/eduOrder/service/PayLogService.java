package com.xxg.eduOrder.service;

import com.xxg.eduOrder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志记录表 服务类
 * </p>
 *
 * @author xxg.testJava
 * @since 2021-01-23
 */
public interface PayLogService extends IService<PayLog> {

    Map<String, Object> getPatCode(String orderId);

    void updateOrderStatus(Map<String, String> map);

    Map<String, String> queryPayStatus(String orderId);
}
