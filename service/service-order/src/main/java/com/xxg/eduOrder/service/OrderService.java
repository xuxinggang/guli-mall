package com.xxg.eduOrder.service;

import com.xxg.eduOrder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author xxg.testJava
 * @since 2021-01-23
 */
public interface OrderService extends IService<Order> {


    String createOrderInfo(String courseId, String memberIdByJwtToken);
}
