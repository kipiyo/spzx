package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: ProductSkuMapper
 * Package:
 * Description:商品SKU的持久层代码：
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 20:12
 * @Version 1.0
 */
@Mapper
public interface ProductSkuMapper {
    void save(ProductSku productSku);

    List<ProductSku> selectByProductId(Long id);

    void updateById(ProductSku productSku);

    void deleteByProductId(Long id);
}
