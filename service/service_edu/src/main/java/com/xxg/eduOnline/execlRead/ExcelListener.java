package com.xxg.eduOnline.execlRead;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.xxg.eduOnline.execlWrite.DemoData;

import java.util.Map;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/18 11:25
 * @Description:  easyExcel读操作
 * @Params:
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {

    @Override//对导入的excel进行一行一行的读
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("读取excel数据:"+demoData);

    }
    //读取表头内容
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("读取表头数据："+headMap);
    }
    //读取完Excel数据之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
