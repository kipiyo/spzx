package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.dto.h5.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: ProductSkuMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/15 12:27
 * @Version 1.0
 */
@Mapper
public interface ProductSkuMapper {
    List<ProductSku> findProductSkuBySale();

    List<ProductSku> findByPage(ProductSkuDto productSkuDto);


    ProductSku getById(Long skuId);

    List<ProductSku> findByProductId(Long productId);

    void updateSale(@Param("skuId") Long skuId, @Param("num") Integer num);
}
