package com.atguigu.spzx.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.product.mapper.CategoryMapper;
import com.atguigu.spzx.product.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * ClassName: CategoryServiceImpl
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/15 12:25
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;


    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public List<Category> findOneCategory() {
        //查询redis,是否有所有一级分类数据
        String categoryOneJson = stringRedisTemplate.opsForValue().get("category:one");
        //如果redis有，直接返回
        if(StringUtils.hasText(categoryOneJson)){
            //categoryOneJson字符串转为List
            List<Category> existCategoryOne = JSON.parseArray(categoryOneJson, Category.class);
//            System.out.println("从Redis缓存中查询到了所有的一级分类数据");
            return existCategoryOne;
        }
        //如果redis没有，查询数据库，并存入redis
        List<Category> categoryList = categoryMapper.findOneCategory();
        stringRedisTemplate.opsForValue().set("category:one", JSON.toJSONString(categoryList),
                7, TimeUnit.DAYS);

        return categoryList;
    }


    @Override
    @Cacheable(value = "category", key = "'all'") //category::all
    public List<Category> findCategoryTree() {
        List<Category> categoryList = categoryMapper.findAll();

        // 获取一级分类
        List<Category> oneCategoryList = categoryList.stream().
                filter(item -> item.getParentId().longValue() == 0L).collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(oneCategoryList)){
            oneCategoryList.forEach(oneCategory -> {
                List<Category> twoCategoryList = categoryList.stream().filter(item ->
                                item.getParentId().longValue() == oneCategory.getId().longValue())
                        .collect(Collectors.toList());
                oneCategory.setChildren(twoCategoryList);

                if(!CollectionUtils.isEmpty(twoCategoryList)){
                    twoCategoryList.forEach(twoCategory -> {
                        List<Category> threeCategoryList = categoryList.stream().filter(item ->
                                        item.getParentId().longValue() == twoCategory.getId().longValue())
                                .collect(Collectors.toList());
                        twoCategory.setChildren(threeCategoryList);
                    });
                }
            });
        }
        return oneCategoryList;
    }
}
