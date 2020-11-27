package com.xxg.eduOnline.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxg.eduOnline.entity.EduSubject;
import com.xxg.eduOnline.entity.execl.ExcelSubjectData;
import com.xxg.eduOnline.entity.subject.OneSubject;
import com.xxg.eduOnline.entity.subject.TwoSubject;
import com.xxg.eduOnline.listener.ExcelSubjectListener;
import com.xxg.eduOnline.mapper.EduSubjectMapper;
import com.xxg.eduOnline.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *  添加课程分类,将传入的数据进行解析（读excel）
 * @author xxg.testJava
 * @since 2020-08-18
 *
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveExcelFile(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class,new ExcelSubjectListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override//解析上传的Excel中的数据进行树形显示
    public List<OneSubject> getOneTwoSubject() {
        QueryWrapper<EduSubject> oneEduSubjectList = new QueryWrapper<>();
        //查询出数据库中的所有一级分类
        oneEduSubjectList.eq("parent_id","0");
        //this和baseMapper一样，都能调用数据持久层
        List<EduSubject> oneSubjectList = this.list(oneEduSubjectList);
        //查询出数据库中的所有二级分类
        QueryWrapper<EduSubject> twoEduSubjectList = new QueryWrapper<>();
        twoEduSubjectList.ne("parent_id","0");
        //this和baseMapper一样，都能调用数据持久层
        List<EduSubject> twoSubjectList = baseMapper.selectList(twoEduSubjectList);

        //声明最终符合数据格式的list集合
        List<OneSubject> finalListSubject = new ArrayList<>();
        //遍历获取到的所有一级分类值，二级分类同理，同时封装到最终的list集合中
        for (int i = 0; i < oneSubjectList.size(); i++) {
            //遍历得到oneSubjectList中的每个eduSubject对象
            EduSubject eduSubject = oneSubjectList.get(i);
            //将获取的值，继续封装到一级分类集合里面去
            OneSubject oneSubject = new OneSubject();
            //意思就是将eduSubject的值，继续封装到一级分类oneSubject
            BeanUtils.copyProperties(eduSubject,oneSubject);
            //然后继续封装到符合前端显示的数据集合中去
            finalListSubject.add(oneSubject);

            //因为一级分类里面存在二级分类，所以需要在一级分类里面遍历二级分类
            List<TwoSubject> twoFinalListSubject = new ArrayList<>();
            for (int n = 0; n < twoSubjectList.size(); n++) {
                EduSubject twoSubject = twoSubjectList.get(n);
                //判断改二级分类是否属于哪一个一级分类,然后继续封装到二级分类中去
                if (twoSubject.getParentId().equals(eduSubject.getId())){
                    TwoSubject tSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoSubject,tSubject);
                    twoFinalListSubject.add(tSubject);
                }
            }
            //最后将所有的二级分类封装到一级分类中去
            oneSubject.setChildren(twoFinalListSubject);
        }
        return finalListSubject;
    }
}
