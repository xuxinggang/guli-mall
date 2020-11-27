package com.xxg.eduOnline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxg.eduOnline.entity.EduTeacher;
import com.xxg.eduOnline.mapper.EduTeacherMapper;
import com.xxg.eduOnline.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *  //对名师列表进行分页查询
 * @author xxg.testJava
 * @since 2020-07-13
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    //对分页写的底层一点，所以数据都要返回，通过map集合显示
    @Override
    public Map<String, Object> getPageTeacherFrontList(Page<EduTeacher> teacherPage) {
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();
        eduTeacherQueryWrapper.orderByDesc("gmt_create");
        this.page(teacherPage, eduTeacherQueryWrapper);
        //获取分页所需数据
        List<EduTeacher> records = teacherPage.getRecords();
        long size = teacherPage.getSize();
        long pages = teacherPage.getPages();
        long total = teacherPage.getTotal();
        long current = teacherPage.getCurrent();
        boolean hasNext = teacherPage.hasNext();
        boolean hasPrevious = teacherPage.hasPrevious();
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }
}
