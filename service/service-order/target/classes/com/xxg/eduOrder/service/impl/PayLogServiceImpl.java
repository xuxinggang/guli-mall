package com.xxg.eduOrder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.xxg.eduOnline.exceptionHandler.DiyException;
import com.xxg.eduOnline.utils.HttpClient;
import com.xxg.eduOnline.utils.ParamUtils;
import com.xxg.eduOrder.entity.Order;
import com.xxg.eduOrder.entity.PayLog;
import com.xxg.eduOrder.mapper.PayLogMapper;
import com.xxg.eduOrder.service.OrderService;
import com.xxg.eduOrder.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志记录表 服务实现类
 * </p>
 *
 * @author xxg.testJava
 * @since 2021-01-23
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Resource
    private OrderService orderService;

    @Override
    public Map<String, Object> getPatCode(String orderId) {
        try {
            //根据订单好获取订单信息
            QueryWrapper<Order> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(Order::getOrderNo,orderId);
            Order order = orderService.getOne(wrapper);
            //通过map获取生成二维码需要的参数
            Map<String, String> m = new HashMap<>();
            m.put("appid", "wx74862e0dfcf69954");//关联的公众号id
            m.put("mch_id", "1558950191");//商户号
            m.put("nonce_str", WXPayUtil.generateNonceStr());//使生成的二维码都不一样的随机数
            m.put("body", order.getCourseTitle());//二维码内容
            m.put("out_trade_no", orderId);//
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
            m.put("spbill_create_ip", "127.0.0.1");//进行支付的服务地址
            m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");//支付成功之后，回调地址
            m.put("trade_type", "NATIVE");//

            //发送httpclient请求，传递参数xml格式，微信支付提供的固定地址;HTTPClient来根据URL访问第三方接口并且传递参数
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            //设置xml格式需要的参数
            httpClient.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            //设置访问请求方式
            httpClient.isHttps(true);
            //设置提交方式
            httpClient.post();
            //得到请求的结果（xml格式），转换成map集合的方式进行返回
            String xmlContent = httpClient.getContent();
            Map<String, String> xmlToMap = WXPayUtil.xmlToMap(xmlContent);
            //将生成订单的二维码以及相关的值，进行封装
            Map map = new HashMap<>();
            map.put("out_trade_no", orderId);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", xmlToMap.get("result_code"));//返回二维码进行操作的状态码
            map.put("code_url", xmlToMap.get("code_url"));//二维码地址

            //微信支付二维码2小时过期，可采取2小时未支付取消订单
            //redisTemplate.opsForValue().set(orderId, map, 120, TimeUnit.MINUTES);

            return map;
        }catch (Exception e){
            throw new DiyException(20001,"生成微信支付二维码地址失败");
        }finally {
            System.out.println("我执行了生成微信支付二维码");
        }
    }

    @Override
    public void updateOrderStatus(Map<String, String> map) {
        String orderId = map.get("out_trade_no");
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        if (order.getStatus().intValue() ==1 ){
           return; //这个地方有疑问，如果支付状态没有成功的话，这里默认更新了支付状态了
        }
        //更新订单状态
        order.setStatus(ParamUtils.PAY_STATUS_1);
        orderService.updateById(order);
        //生存订单记录
        PayLog payLog = new PayLog();
        payLog.setOrderNo(order.getOrderNo());//支付订单号
        payLog.setPayTime(new Date());
        payLog.setPayType(ParamUtils.PAY_TYPE0_WX);//支付类型
        payLog.setTotalFee(order.getTotalFee());//总金额(分)
        payLog.setTradeState(map.get("trade_state"));//支付状态
        payLog.setTransactionId(map.get("transaction_id"));//订单支付流水号
        payLog.setAttr(JSONObject.toJSONString(map));//其他属性
        baseMapper.insert(payLog);//插入到支付日志表

    }

    @Override
    public Map<String, String> queryPayStatus(String orderId) {

        try {
        //1、封装参数
        Map<String,String> m = new HashMap<>();
        m.put("appid", "wx74862e0dfcf69954");
        m.put("mch_id", "1558950191");
        m.put("out_trade_no", orderId);
        m.put("nonce_str", WXPayUtil.generateNonceStr());

        //发送httpclient请求，传递参数xml格式，微信支付提供的固定地址;HTTPClient来根据URL访问第三方接口并且传递参数
        HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
        //设置xml格式需要的参数
        httpClient.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
        //设置访问请求方式
        httpClient.isHttps(true);
        //设置提交方式
        httpClient.post();
        //得到请求的结果（xml格式），转换成map集合的方式进行返回
        String xmlContent = httpClient.getContent();
        Map<String, String> xmlToMap = WXPayUtil.xmlToMap(xmlContent);

        return  xmlToMap;
        } catch (Exception e) {
           throw new DiyException(20001,"订单支付状态查询失败");
        }
    }
}
