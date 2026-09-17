package com.atguigu.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.Listener.ExcelListener;
import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: CategoryServiceImpl
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 12:21
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> findByParentId(Long parentId) {
        //根据分类id查询下面的子分类
        List<Category> categoryList = categoryMapper.findByParentId(parentId);
        if (!CollectionUtils.isEmpty(categoryList)) {
            //遍历分类集合,获取每个分类数据
            for (Category category : categoryList) {
                //查询该分类下的子类数量
                int count = categoryMapper.countByParentId(category.getId().intValue());
                if(count > 0) {
                    category.setHasChildren(true);
                }else {
                    category.setHasChildren(false);
                }
            }
        }
        return categoryList;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            //设置响应结果类型
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");

            //URLEncoder.encode 是用来解决中文乱码的问题,和easyExcel无关
            String fileName = URLEncoder.encode( "分类数据", "UTF-8");
            //Content-dispositio 固定: 文件以下载方式打开
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            //查询数据库中查询到的Category对象转换成CategoryExcelVo对象
            List<Category> categoryList = categoryMapper.findAll();
            ArrayList<CategoryExcelVo> categoryExcelVoArrayList = new ArrayList<>(categoryList.size());

            //将从数据库中查询到的Category对象转换成CategoryExcelVo对象
            for (Category category : categoryList) {
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                //把category对象中的数据拷贝到categoryExcelVo中
                BeanUtils.copyProperties(category, categoryExcelVo,CategoryExcelVo.class);
                categoryExcelVoArrayList.add(categoryExcelVo);
            }
            //写出数据到浏览器端
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class)
                    .sheet("分类数据").doWrite(categoryExcelVoArrayList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }

    }

    @Override
    public void importData(MultipartFile file) {
        //监听器
        ExcelListener<CategoryExcelVo> excelListener = new ExcelListener(categoryMapper);

        //读取excel文件数据
        try {
            EasyExcel.read(file.getInputStream(),CategoryExcelVo.class,excelListener)
                    .sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
    }
}












