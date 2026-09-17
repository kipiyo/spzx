package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: CategoryMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 12:23
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper {
    List<Category> findByParentId(Long parentId);

    int countByParentId(int id);

    List<Category> findAll();

    void insertBatch(List<CategoryExcelVo> categoryList);
}
