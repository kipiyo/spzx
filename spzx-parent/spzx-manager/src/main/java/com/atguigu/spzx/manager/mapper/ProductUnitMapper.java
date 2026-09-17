package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.base.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: ProductUnitMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 20:01
 * @Version 1.0
 */
@Mapper
public interface ProductUnitMapper {
    List<ProductUnit> findAll();
}
