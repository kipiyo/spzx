package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.ProductService;
import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ProductController
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 19:38
 * @Version 1.0
 */
@RestController
@RequestMapping(value="/admin/product/product")
public class ProductController {
    @Resource
    private ProductService productService ;

    /**
     * 当用户点击上架按钮的时候对商品进行上架操作，点击下架按钮的时候对商品进行下架操作。
     *
     * 实现思路：更改商品的上下架状态
     * @param id
     * @param status
     * @return
     */
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        productService.updateStatus(id, status);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 当点击审核按钮的时候此时需要弹出一个对话框，
     * 在该对话框中展示商品的详情信息，用户可以在该对话框中点击通过或者驳回按钮对商品进行审核操作。
     * @param id
     * @param auditStatus
     * @return
     */
    @GetMapping("/updateAuditStatus/{id}/{auditStatus}")
    public Result updateAuditStatus(@PathVariable Long id, @PathVariable Integer auditStatus) {
        productService.updateAuditStatus(id, auditStatus);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 当点击删除按钮的时候此时需要弹出一个提示框，询问是否需要删除数据？
     * 如果用户点击是，那么此时向后端发送请求传递id参数，后端接收id参数进行逻辑删除。
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@Parameter(name = "id", description = "商品id", required = true) @PathVariable Long id) {
        productService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 保存修改数据接口
     * @param product
     * @return
     */
    @PutMapping("/updateById")
    public Result updateById(@Parameter(name = "product", description = "请求参数实体类", required = true) @RequestBody Product product) {
        productService.updateById(product);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 查询商品详情
     * @param id
     * @return
     */
    @GetMapping("/getById/{id}")
    public Result<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return Result.build(product , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 1、前端提交过来的数据，包含了SPU的基本数据，SKU的列表数据，商品详情数据
     * 2、后端可以直接使用Product接收请求参数，但是需要扩展对应的属性
     * 3、保存数据的时候需要操作三张表：product、product_sku、product_detail
     * @param product
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody Product product) {
        productService.save(product);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 分页查询商品列表
     * @param page
     * @param limit
     * @param productDto 商品搜索条件实体类
     * @return
     */
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<Product>> findByPage(
                                                 @PathVariable Integer page,
                                                 @PathVariable Integer limit,
                                                 ProductDto productDto) {
        PageInfo<Product> pageInfo = productService.findByPage(page, limit,productDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
}
