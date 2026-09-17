package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: BrandMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/16 14:35
 * @Version 1.0
 */
@Mapper
public interface BrandMapper {
    List<Brand> findAll();
}
