package com.xxg.eduOnline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.xxg.eduOnline.entity.EduChapter;
import com.xxg.eduOnline.entity.EduVideo;
import com.xxg.eduOnline.entity.chapter.chapterVideo;
import com.xxg.eduOnline.entity.chapter.chapterVo;
import com.xxg.eduOnline.entity.subject.OneSubject;
import com.xxg.eduOnline.exceptionHandler.DiyException;
import com.xxg.eduOnline.mapper.EduChapterMapper;
import com.xxg.eduOnline.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxg.eduOnline.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xxg.testJava
 * @since 2020-08-21
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
   @Autowired
    private EduVideoService eduVideoService;
    @Override
    public List<chapterVo> getChapterAll(String courseId) {
        //1、根据课程id查询出所有的章节
        QueryWrapper<EduChapter> chapterVoQueryWrapper = new QueryWrapper<>();
        chapterVoQueryWrapper.eq("course_id",courseId);
        List<EduChapter>  chapterVoList = this.list(chapterVoQueryWrapper);
        //2、根据课程id查询出章节中所有的小节
        QueryWrapper<EduVideo> videoVoQueryWrapper = new QueryWrapper<>();
        videoVoQueryWrapper.eq("course_id",courseId);
        List<EduVideo> videoVoList = eduVideoService.list(videoVoQueryWrapper);

        //声明最后封装的集合
        List<chapterVo> finalListChapterVo = new ArrayList<>();
        //3、根据查询出来的章节，进行遍历，获取章节id
        for (int i = 0; i < chapterVoList.size(); i++) {
            EduChapter eduChapter = chapterVoList.get(i);
            chapterVo chapterVo = new chapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalListChapterVo.add(chapterVo);

            //因为每一个章节里面包含很多小节
            List<chapterVideo> finalListChapterVideo = new ArrayList<>();
            //4、根据课程id查询出所有的小节，进行遍历
            for (int j = 0; j < videoVoList.size(); j++) {
                    EduVideo eduVideo = videoVoList.get(j);
                if (eduVideo.getChapterId().equals(eduChapter.getId())){
                    chapterVideo chapterVideo = new chapterVideo();
                    BeanUtils.copyProperties(eduVideo,chapterVideo);
                    finalListChapterVideo.add(chapterVideo);
                }
            }
            //将小节封装到章节里面
            chapterVo.setChildren(finalListChapterVideo);
        }

        return finalListChapterVo;
    }
    //根据章节id删除章节
    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        //因为小节中存在章节id，所以通过章节id去查询是否存在小节数据
        eduVideoQueryWrapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count(eduVideoQueryWrapper);
        if (count<=0){
            boolean b = this.removeById(chapterId);
            return b;
        }
        throw new DiyException(20001,"章节下面存在小节，不能删除此章节");
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
