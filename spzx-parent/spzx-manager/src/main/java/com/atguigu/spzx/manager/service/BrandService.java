package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * ClassName: BrandService
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 15:11
 * @Version 1.0
 */
public interface BrandService {
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    void save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Long id);

    List<Brand> findAll();
}
