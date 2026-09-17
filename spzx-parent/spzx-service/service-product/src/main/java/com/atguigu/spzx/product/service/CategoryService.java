package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.entity.product.Category;

import java.util.List;

/**
 * ClassName: CategoryService
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/15 12:25
 * @Version 1.0
 */
public interface CategoryService {
    List<Category> findOneCategory();

    List<Category> findCategoryTree();
}
