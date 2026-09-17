package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: CategoryMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/15 12:25
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper {
    List<Category> findOneCategory();

    List<Category> findAll();
}
