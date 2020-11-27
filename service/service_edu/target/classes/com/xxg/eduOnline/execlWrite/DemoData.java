package com.xxg.eduOnline.execlWrite;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/18 9:51
 * @Description:  用于写Excel实体类
 * @Params:
 */
@Data
public class DemoData {
    //设置Excel表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer stuExcelNo;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String stuExcelName;
}
