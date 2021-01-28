package com.xxg.eduMember.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxg.eduMember.entity.UcenterMember;
import com.xxg.eduMember.entity.vo.LoginVo;
import com.xxg.eduMember.entity.vo.RegisterVo;
import com.xxg.eduMember.mapper.UcenterMemberMapper;
import com.xxg.eduMember.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxg.eduOnline.exceptionHandler.DiyException;
import com.xxg.eduOnline.utils.JwtUtils;
import com.xxg.eduOnline.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *  使用jwt的token令牌来实现登录
 * @author xxg.testJava
 * @since 2020-09-10
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public String login(LoginVo loginVo) {
        //判断登录的账号和密码一个为空，则终止登录
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new DiyException(20001,"手机号或者密码为空，请检查后重试");
        }
        //判断手机号，是否存在
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        if (ucenterMember==null){
            throw new DiyException(20001,"手机号不存在，请检查后重试");
        }
        //因为存储到数据库的密码是加密的，把输入的密码加密，再比较
        if (!MD5.encrypt(password).equals(ucenterMember.getPassword())){
            throw new DiyException(20001,"密码不正确，请检查后重试");
        }
        if (ucenterMember.getIsDisabled()){
            throw new DiyException(20001,"改用户已经被禁用，请修改后重试");
        }
        //登录成功,使用jwt正常token
        String token = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取需要注册的值
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        //对注册信息判空
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
                                        ||
            StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)
        ){
            throw new DiyException(20001,"注册信息为空，请检查后重试");
        }
        //判断验证码是否和redis中的验证码一致
//        String redisCode = redisTemplate.opsForValue().get(mobile);
//        if (!code.equals(redisCode)){
//            throw new DiyException(20001,"验证码错误，请检查后重试");
//        }
        //每个手机号只能注册一次
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count>0){
            throw new DiyException(20001,"该手机号已经注册，请更换后重试");
        }

        //添加数据到数据库中
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(registerVo.getMobile());
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setIsDisabled(false);
        ucenterMember.setAge(99);
        ucenterMember.setAvatar("https://www.baidu.com/img/dong_8e531e8c4c040acdb0c085da1a79179e.gif");
        ucenterMember.setNickname(nickname);
        baseMapper.insert(ucenterMember);//插入数据库中
    }

    @Override
    public UcenterMember getMemberOpenId(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        if (!(ucenterMember==null)){
            throw new DiyException(20001,"该用户已登录");
        }
        return ucenterMember;
    }

    @Override
    public UcenterMember getMemberById(String memberId) {
        UcenterMember member = baseMapper.selectById(memberId);
        return member;
    }
}
