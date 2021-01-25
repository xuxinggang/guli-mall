package com.xxg.eduOrder.controller;


import com.xxg.eduOnline.R;
import com.xxg.eduOrder.entity.PayLog;
import com.xxg.eduOrder.mapper.PayLogMapper;
import com.xxg.eduOrder.service.OrderService;
import com.xxg.eduOrder.service.PayLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 支付日志记录表 前端控制器
 * </p>
 *
 * @author xxg.testJava
 * @since 2021-01-23
 */
@RestController
@RequestMapping("/eduOrder/payLog")
@CrossOrigin
public class PayLogController {

    @Resource
    private PayLogService payLogService;

    @Resource
    private OrderService orderService;

    /**
     * 获取支付二维码的地址，以及支付需要的值
     * @param orderId
     * @return
     */
    @GetMapping("{orderId}")
    public R getPatCode(@PathVariable String orderId){
        //获取支付生成的二维码地址，以及其他支付需要的信息
        Map<String, Object> map=payLogService.getPatCode(orderId);
        return R.success().data(map);
    }

    @GetMapping("queryPayStatus/{orderId}")
    public R queryPayStatus(@PathVariable String orderId){
       Map<String,String> map = payLogService.queryPayStatus(orderId);
       if (map==null){
           return R.error().message("订单状态查询为空");
       }else{

            if ("SUCCESS".equals(map.get("trade_state"))){
                //生成订单记录日志数据,改变订单状态
                payLogService.updateOrderStatus(map);
                return R.success().message("订单支付成功");
            }
            return R.success().code(25000).message("订单支付中");

       }
    }
}

