package com.xxg.eduOnline.service;

import com.xxg.eduOnline.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxg.eduOnline.entity.chapter.chapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xxg.testJava
 * @since 2020-08-21
 */
public interface EduChapterService extends IService<EduChapter> {
     //根据id查询所有的章节，小节
    List<chapterVo> getChapterAll(String courseId);
    //删除章节根据id，如果章节下面存在小节，则不允许删除
    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
