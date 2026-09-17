package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.log.annotation.Log;
import com.atguigu.spzx.common.log.enums.OperatorType;
import com.atguigu.spzx.manager.service.BrandService;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: BrandController
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 15:11
 * @Version 1.0
 */
@RestController
@RequestMapping(value="/admin/product/brand")
public class BrandController {

    @Resource
    private BrandService brandService ;

    @GetMapping("/findAll")
    public Result findAll() {
        List<Brand> list = brandService.findAll();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 当点击删除按钮的时候此时需要弹出一个提示框，询问是否需要删除数据？
     * 如果用户点击是，那么此时向后端发送请求传递id参数，后端接收id参数进行逻辑删除。
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 当用户点击修改按钮的时候，那么此时就弹出对话框，在该对话框中需要将当前行所对应的品牌数据在该表单页面进行展示。
     * 当用户在该表单中点击提交按钮的时候那么此时就需要将表单进行提交，在后端需要提交过来的表单数据修改数据库中的即可。
     * @param brand
     * @return
     */
    @PutMapping("updateById")
    public Result updateById(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 用户点击添加按钮，此时需要展示一个添加数据的表单对话框，
     * 用户填写表单数据，点击提交按钮，请求后端接口完成数据的保存操作。
     * @param brand
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody Brand brand) {
        brandService.save(brand);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }



    /**
     * 需求说明：当品牌管理页面加载完毕以后就向后端发送分页查询请求，后端进行分页查询，返回分页结果数据
     */
    @Log(title = "品牌管理:列表",businessType = 0,operatorType = OperatorType.MANAGE)
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<Brand>> findByPage(@PathVariable Integer page,@PathVariable Integer limit) {
        PageInfo<Brand> pageInfo = brandService.findByPage(page,limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
}
