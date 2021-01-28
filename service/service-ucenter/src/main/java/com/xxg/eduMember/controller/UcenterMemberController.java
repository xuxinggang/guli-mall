package com.xxg.eduMember.controller;


import com.xxg.eduMember.entity.UcenterMember;
import com.xxg.eduMember.entity.vo.LoginVo;
import com.xxg.eduMember.entity.vo.RegisterVo;
import com.xxg.eduMember.service.UcenterMemberService;
import com.xxg.eduOnline.R;
import com.xxg.eduOnline.utils.JwtUtils;
import com.xxg.eduOnline.vo.UcenterMemberOrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *使用jwt的token令牌来实现登录
 * @author xxg.testJava
 * @since 2020-09-10
 */
@RestController
@RequestMapping("/eduMember/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    @PostMapping("login")
    public R OnlineLogin(@RequestBody LoginVo loginVo){
        String token=memberService.login(loginVo);
        return R.success().data("token",token);
    }
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.success();
    }
    //根据token获取用户信息（头像和昵称）
    @GetMapping("memberMessage")
    public R findMessageByToken(HttpServletRequest request){
        //调用jwt工具类，返回token的头信息，返回会员id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //根据memberId，获取会员信息
        UcenterMember member = memberService.getById(memberId);
        return R.success().data("memberInfo",member);
    }
    //通过token字符串获取用户信息
    @GetMapping("getMemberInfoById/{id}")
    public UcenterMember getMemberInfoById(@PathVariable String id){
        UcenterMember member = new UcenterMember();
        UcenterMember memberInfo = memberService.getById(id);
        BeanUtils.copyProperties(member,memberInfo);
        return memberInfo;
    }

    /**
     * 根据用户id获取用户信息
     * @param memberId
     * @return
     */
    @GetMapping("{memberId}")
    public UcenterMemberOrderVo getMemberById(@PathVariable("memberId") String memberId){
        UcenterMember member = memberService.getMemberById(memberId);
        UcenterMemberOrderVo ucenterMemberOrderVo = new UcenterMemberOrderVo();
        BeanUtils.copyProperties(member,ucenterMemberOrderVo);
        return ucenterMemberOrderVo;
    }

}

