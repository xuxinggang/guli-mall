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

}
