package com.xxg.eduOrder.service.impl;

import com.xxg.eduOnline.utils.OrderNoUtil;
import com.xxg.eduOnline.utils.ParamUtils;
import com.xxg.eduOnline.vo.CourseOrderInfoVo;
import com.xxg.eduOnline.vo.UcenterMemberOrderVo;
import com.xxg.eduOrder.client.EduCourseOrderInfo;
import com.xxg.eduOrder.client.EduMemberOrderInfo;
import com.xxg.eduOrder.entity.Order;
import com.xxg.eduOrder.mapper.OrderMapper;
import com.xxg.eduOrder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

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

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private EduCourseOrderInfo eduCourseOrderInfo;

    @Resource
    private EduMemberOrderInfo eduMemberOrderInfo;

    /**
     * //通过使用nacos实现服务的之间的远程调用；根据以上形参获取对应的课程信息和用户信息
     * @param courseId
     * @param memberIdByJwtToken
     * @return
     */
    @Override
    public String createOrderInfo(String courseId, String memberIdByJwtToken) {
       //远程服务调用根据课程id获取课程信息
        CourseOrderInfoVo course = eduCourseOrderInfo.getCourseById(courseId);
        //远程服务调用根据用户id获取用户信息
        UcenterMemberOrderVo member = eduMemberOrderInfo.getMemberById(memberIdByJwtToken);
        //生成订单消息
        Order order = new Order();
//        UUID uuid = UUID.randomUUID();
//        order.setOrderNo(uuid.toString());
        order.setCourseCover(course.getCover());//课程封面
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(course.getTitle());//课程标题
        order.setMemberId(memberIdByJwtToken); //用户id
        order.setMobile(member.getMobile());//会员手机
        order.setTeacherName(course.getTeacherName());//教师名称
        order.setNickname(member.getNickname());//会员昵称
        order.setTotalFee(course.getPrice());//课程价格
        order.setPayType(ParamUtils.PAY_TYPE0_WX);//0:微信、1：支付宝
        order.setStatus(ParamUtils.PAY_STATUS_0);//未支付

        orderMapper.insert(order);
        //返回订单好，生成订单支付记录时需要用到
        return order.getOrderNo();
    }
}
