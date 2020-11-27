package com.xxg.eduOnline.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxg.eduMember.entity.UcenterMember;
import com.xxg.eduMember.service.UcenterMemberService;
import com.xxg.eduOnline.R;
import com.xxg.eduOnline.client.UcenterClient;
import com.xxg.eduOnline.client.impl.UcenterClientImpl;
import com.xxg.eduOnline.entity.EduComment;
import com.xxg.eduOnline.service.EduCommentService;
import com.xxg.eduOnline.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author xxg.testJava
 * @since 2020-11-17
 */
@RestController
@RequestMapping("/eduOnline/eduComment")
@CrossOrigin
public class EduCommentController {
     @Autowired
     private EduCommentService commentService;
     @Autowired
    private UcenterMemberService memberService;

    //根据课程id查询评论列表
    @ApiOperation(value = "评论分页列表")
    @GetMapping("{page}/{limit}")
    public R index(
                    @ApiParam(name = "page", value = "当前页码", required = true)
                            @PathVariable Long page,
                    @ApiParam(name = "limit", value = "每页记录数", required = true)
                            @PathVariable Long limit,
                    @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                            String id){
        Page<EduComment> eduCommentPage = new Page<>(page,limit);
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        commentService.page(eduCommentPage,wrapper);//调用分页插件，使评论分页
        List<EduComment> commentList = eduCommentPage.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", eduCommentPage.getCurrent());
        map.put("pages", eduCommentPage.getPages());
        map.put("size", eduCommentPage.getSize());
        map.put("total", eduCommentPage.getTotal());
        map.put("hasNext", eduCommentPage.hasNext());
        map.put("hasPrevious", eduCommentPage.hasPrevious());
        return R.success().data(map);
    }
    @PostMapping("addCommit")
    public R addCommit(@RequestBody EduComment comment, HttpServletRequest request){
        String id = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println("12312312"+id);
        if (StringUtils.isEmpty(id)){
            return R.error().message("请先登录").code(20001);
        }
        comment.setMemberId(id);
        UcenterClientImpl ucenterClient = new UcenterClientImpl();
        UcenterMember ucenterInfo = ucenterClient.getMemberInfoById(id);
        comment.setNickname(ucenterInfo.getNickname());
        comment.setAvatar(ucenterInfo.getAvatar());
        commentService.save(comment);
        return R.success();
    }
}

