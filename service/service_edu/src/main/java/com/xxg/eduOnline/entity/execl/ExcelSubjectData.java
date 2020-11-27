package com.xxg.eduOnline.entity.execl;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/18 14:35
 * @Description:excel课程分类实体类
 * @Params:
 */
@Data
public class ExcelSubjectData {
    @ExcelProperty(index = 0)
     private String oneSubjectName;
    @ExcelProperty(index = 1)
     private String twoSubjectName;
}
