package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ProductDetailsMapper
 * Package:
 * Description:商品详情的持久层代码：
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 20:13
 * @Version 1.0
 */
@Mapper
public interface ProductDetailsMapper {
    void save(ProductDetails productDetails);

    ProductDetails selectByProductId(Long id);

    void updateById(ProductDetails productDetails);

    void deleteByProductId(Long id);
}
