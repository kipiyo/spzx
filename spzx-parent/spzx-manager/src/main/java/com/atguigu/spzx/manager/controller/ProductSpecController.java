package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.ProductSpecService;
import com.atguigu.spzx.model.entity.product.ProductSpec;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: ProductSpecController
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 19:00
 * @Version 1.0
 */
@RestController
@RequestMapping(value="/admin/product/productSpec")
public class ProductSpecController {

    @Resource
    private ProductSpecService productSpecService ;

    /**
     * 当添加商品的表单对话框展示出来以后，
     * 此时就需要从数据库中查询出来所有的商品规格数据，并将查询到的商品规格数据在商品规格下拉框中进行展示。
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll() {
        List<ProductSpec> productSpecList = productSpecService.findAll();
        return Result.build(productSpecList , ResultCodeEnum.SUCCESS) ;
    }

    @DeleteMapping("/deleteById/{id}")
    public Result removeById(@PathVariable Long id) {
        productSpecService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     *
     * @param productSpec
     * @return
     */
    @PutMapping("/updateById")
    public Result updateById(@RequestBody ProductSpec productSpec) {
        productSpecService.updateById(productSpec) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 保存规格
     * @param productSpec
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody ProductSpec productSpec) {
        productSpecService.save(productSpec) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 分页查询规格
     */
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<ProductSpec>> findByPage(@PathVariable Integer page, @PathVariable Integer limit) {
        PageInfo<ProductSpec> pageInfo = productSpecService.findByPage(page, limit);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }
}
