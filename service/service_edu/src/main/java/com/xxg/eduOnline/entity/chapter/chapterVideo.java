package com.xxg.eduOnline.entity.chapter;

import lombok.Data;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/24 10:18
 * @Description:  章节中的小节（video）
 * @Params:
 */
@Data
public class chapterVideo {
    private String id;
    private String title;
    private String videoSourceId;//为了在实现视频点播时，获取视频id作为动态路由
}
