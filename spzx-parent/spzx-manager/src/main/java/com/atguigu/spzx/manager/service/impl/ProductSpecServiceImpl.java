package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.ProductSpecMapper;
import com.atguigu.spzx.manager.service.ProductSpecService;
import com.atguigu.spzx.model.entity.product.ProductSpec;
import com.atguigu.spzx.model.vo.common.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * ClassName: ProductSpecServiceImpl
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 19:00
 * @Version 1.0
 */
@Service
public class ProductSpecServiceImpl implements ProductSpecService {
    @Resource
    private ProductSpecMapper productSpecMapper;



    /**
     * 分页查询规格
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<ProductSpec> productSpecList = productSpecMapper.findByPage() ;
        PageInfo<ProductSpec> productSpecPageInfo = new PageInfo<>(productSpecList);
        return productSpecPageInfo;
    }

    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec) ;
    }

    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.updateById(productSpec) ;
    }

    @Override
    public void deleteById(Long id) {
        productSpecMapper.deleteById(id) ;
    }

    @Override
    public List<ProductSpec> findAll() {
        return productSpecMapper.findAll() ;
    }
}
