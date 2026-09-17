package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ProductDetailsMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/16 15:24
 * @Version 1.0
 */
@Mapper
public interface ProductDetailsMapper {
    ProductDetails getByProductId(Long productId);
}
