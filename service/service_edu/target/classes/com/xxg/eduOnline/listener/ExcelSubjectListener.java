package com.xxg.eduOnline.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxg.eduOnline.entity.EduSubject;
import com.xxg.eduOnline.entity.execl.ExcelSubjectData;
import com.xxg.eduOnline.exceptionHandler.DiyException;
import com.xxg.eduOnline.service.EduSubjectService;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/18 14:43
 * @Description:对上传的excel课程分类进行读取
 * @Params:
 * 由于监听器不能注入到spring中，不能执行数据库操作，只能本身new出来
 * 所以可以通过服务层来代进行数据库操作
 */
public class ExcelSubjectListener extends AnalysisEventListener<ExcelSubjectData> {

    public EduSubjectService eduSubjectService;

    public ExcelSubjectListener() {}

    public ExcelSubjectListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        //规则：对读取的excel问件中，一个值为一级分类，第二个值为二级分类
         if (excelSubjectData==null){
             throw new DiyException(20001,"上传的Excel课程分类文件不存在");
         }
         //对上传的excel课程分类进行一行一行的读取
        EduSubject oneSubject = this.existOneSubject(eduSubjectService, excelSubjectData.getOneSubjectName());
         if (oneSubject==null){//如果一级分类不存在，则添加至数据库中
             oneSubject=new EduSubject();
             oneSubject.setParentId("0");
             oneSubject.setTitle(excelSubjectData.getOneSubjectName());//一级分类名称
             eduSubjectService.save(oneSubject);
         }
         //添加二级分类
        String pId=oneSubject.getId();//获取一级分类的id值
        EduSubject twoSubject = this.existTwoSubject(eduSubjectService, excelSubjectData.getTwoSubjectName(),pId);
        if (twoSubject==null){
            twoSubject = new EduSubject();
            twoSubject.setTitle(excelSubjectData.getTwoSubjectName());//二级分类名称
            twoSubject.setParentId(pId);
            eduSubjectService.save(twoSubject);
        }
    }
    //判断一级分类不能重复添加（重名）
    public EduSubject existOneSubject(EduSubjectService eduSubjectService,String titleName){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",titleName);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = eduSubjectService.getOne(wrapper);
        return oneSubject;
    }
    //判断二级分类不能重复添加（重名）
    public EduSubject existTwoSubject(EduSubjectService eduSubjectService,String titleName,String pId){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",titleName);
        wrapper.eq("parent_id",pId);
        EduSubject twoSubject = eduSubjectService.getOne(wrapper);
        return twoSubject;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
