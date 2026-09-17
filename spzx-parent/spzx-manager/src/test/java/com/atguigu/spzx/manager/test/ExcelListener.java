package com.atguigu.spzx.manager.test;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ExcelListener
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 13:18
 * @Version 1.0
 */
public class ExcelListener<T> extends AnalysisEventListener {
    private List<T> data =new ArrayList<>();
    /**
     * 一行一行的读取数据,从第二行开始读取(跳过表头) 每行数据封装到o对象
     * @param o
     * @param analysisContext
     */
    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        data.add((T)o);
    }
    public List<T> getData() {
        return data;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
