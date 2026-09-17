package com.atguigu.spzx.manager.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * ClassName: ExcelListener
 * Package:
 * Description:监听器
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 14:39
 * @Version 1.0
 */
public class ExcelListener<T> implements ReadListener<T> {
    /**
     每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    //通过构造传递参数
    private CategoryMapper categoryMapper;
    public ExcelListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        //把每行数据对象t存到cachedDataList集合
        cachedDataList.add(t);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存最后一次的缓存数据，因为最后一次的缓存数据比BATCH_COUNT小
        saveData();
    }


    private void saveData() {
        categoryMapper.insertBatch((List<CategoryExcelVo>) cachedDataList);
    }
}
