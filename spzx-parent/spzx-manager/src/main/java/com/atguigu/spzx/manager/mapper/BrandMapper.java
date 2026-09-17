package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: BrandMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 15:12
 * @Version 1.0
 */
@Mapper
public interface BrandMapper {
    List<Brand> findByPage();

    void save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Long id);

    List<Brand> findAll();
}
