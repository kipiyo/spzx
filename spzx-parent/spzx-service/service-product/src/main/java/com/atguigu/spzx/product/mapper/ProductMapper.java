package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ProductMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/16 15:23
 * @Version 1.0
 */
@Mapper
public interface ProductMapper {
    Product getById(Long productId);
}
