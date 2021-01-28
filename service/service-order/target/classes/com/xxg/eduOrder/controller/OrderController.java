package com.xxg.eduOrder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxg.eduOnline.R;
import com.xxg.eduOnline.utils.JwtUtils;
import com.xxg.eduOrder.entity.Order;
import com.xxg.eduOrder.service.OrderService;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *  在课程页面根据课程id，生成对应的订单信息
 * @author xxg.testJava
 * @since 2021-01-23
 */
@RestController
@RequestMapping("/eduOrder/order")
@CrossOrigin
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 根据课程id生成订单信息
     * @param courseId
     * @return
     */
    @PostMapping("createOrder/{courseId}")
    public R createOrderInfo(@PathVariable("courseId") String courseId, HttpServletRequest request){
        //使用JWT获取当前登录用户放在cookie中的用户id;根据订单号进行支付
        String orderId = orderService.createOrderInfo(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.success().data("orderId",orderId);
    }

    /**
     * 根据订单号，查询订单信息
     * @param orderId
     * @return
     */
    @GetMapping("{orderId}")
    public R getOrderById(@PathVariable String orderId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Order::getOrderNo,orderId);
        Order order = orderService.getOne(wrapper);
        return R.success().data("orderInfo",order);
    }

}

