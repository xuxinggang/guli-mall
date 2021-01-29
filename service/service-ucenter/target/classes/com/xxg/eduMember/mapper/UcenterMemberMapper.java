package com.xxg.eduMember.mapper;

import com.xxg.eduMember.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author xxg.testJava
 * @since 2020-09-10
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer dayRegisterCount(@Param("day") String day);
}
