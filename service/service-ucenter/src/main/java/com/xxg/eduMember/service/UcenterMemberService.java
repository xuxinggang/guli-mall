package com.xxg.eduMember.service;

import com.xxg.eduMember.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxg.eduMember.entity.vo.LoginVo;
import com.xxg.eduMember.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author xxg.testJava
 * @since 2020-09-10
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);
    //根据openid进行查询，是否已经注册好了
    UcenterMember getMemberOpenId(String openid);

    UcenterMember getMemberById(String memberId);
}
