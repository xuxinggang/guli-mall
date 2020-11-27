package com.xxg.eduOnline.execlWrite;

import com.alibaba.excel.EasyExcel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/18 9:57
 * @Description:实现Excel的写操作
 * @Params:
 */
public class testEasyExcel {
    public static void main(String[] args) {
        //设置写入文件夹地址和excel文件名称
        String fileName="F:\\ExcelWrite.xls";
        //调用easyExcel中的方法实现写操作。
        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getExcelData());
    }
    //创建写入excel数据的list集合
    private static List<DemoData> getExcelData(){
        ArrayList<DemoData> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            DemoData demoData = new DemoData();
            demoData.setStuExcelNo(i);
            demoData.setStuExcelName("li"+i);
            list.add(demoData);
        }
        return list;
    }
}
