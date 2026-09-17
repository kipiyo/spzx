package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName: CategoryController
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 12:21
 * @Version 1.0
 */
@RestController
@RequestMapping(value="/admin/product/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 当用户点击导入按钮的时候，此时会弹出一个对话框，让用户选择要导入的excel文件，
     * 选择完毕以后将文件上传到服务端，服务端通过easyExcel解析文件的内容，然后将解析的结果存储到category表中。
     * @param file
     * @return
     */
    @PostMapping("/importData")
    public Result importData(MultipartFile file) { //MultipartFile file 代表上传的文件
        categoryService.importData(file);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 当用户点击导出按钮的时候，此时将数据库中的所有的分类的数据导出到一个excel文件中
     * @param response
     */
    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse response) {
        categoryService.exportData(response);
    }

    @Operation(summary = "根据parentId获取下级节点")
    @GetMapping("/findByParentId/{parentId}")
    public Result findByParentId(@PathVariable Long parentId) {
        List<Category> list = categoryService.findByParentId(parentId);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
