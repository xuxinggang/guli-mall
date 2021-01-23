package com.xxg.eduOrder.service.impl;

import com.xxg.eduOrder.entity.Order;
import com.xxg.eduOrder.mapper.OrderMapper;
import com.xxg.eduOrder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author xxg.testJava
 * @since 2021-01-23
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public String createOrderInfo(String courseId, String memberIdByJwtToken) {
        //通过使用nacos实现服务的之间的远程调用；根据以上形参获取对应的课程信息和用户信息
        return null;
    }
}
