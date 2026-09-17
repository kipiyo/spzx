package com.atguigu.spzx.manager.test;

import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: EasyExcelTest
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 13:17
 * @Version 1.0
 */
public class EasyExcelTest {
    public static void main(String[] args) {
//        readExcel();
        writeExcel();
    }

    //读操作
    public static void readExcel() {
        //[CategoryExcelVo(id=1, name=数码办公, imageUrl=http, parentId=0, status=1, orderNum=1),
        // CategoryExcelVo(id=11, name=华为, imageUrl=http, parentId=1, status=1, orderNum=2)]
        //定义读取文件的位置
        String fileName = "D:\\test.xlsx";
        //调用方法
        ExcelListener excelListener = new ExcelListener();
        EasyExcel.read(fileName, CategoryExcelVo.class,excelListener)
                .sheet().doRead();//从execl表格中的sheet读
        List<CategoryExcelVo> data = excelListener.getData();
        System.out.println(data);
    }

    //写操作
    public static void writeExcel() {
        List<CategoryExcelVo> list = new ArrayList<>();
        list.add(new CategoryExcelVo(1L , "数码办公" , "",0L, 1, 1)) ;
        list.add(new CategoryExcelVo(11L , "华为手机" , "",1L, 1, 2)) ;
        EasyExcel.write("D:\\test.xlsx",CategoryExcelVo.class)
                .sheet().doWrite(list);
    }
}
