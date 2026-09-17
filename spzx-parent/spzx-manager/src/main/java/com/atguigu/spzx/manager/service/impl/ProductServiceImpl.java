package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.ProductDetailsMapper;
import com.atguigu.spzx.manager.mapper.ProductMapper;
import com.atguigu.spzx.manager.mapper.ProductSkuMapper;
import com.atguigu.spzx.manager.service.ProductService;
import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductDetails;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: ProductServiceImpl
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 19:39
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    @Resource
    private ProductMapper productMapper;
    @Override
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto) {
        PageHelper.startPage(page, limit);
        List<Product> productList = productMapper.findByPage(productDto);
        PageInfo<Product> productPageInfo = new PageInfo<>(productList);
        return productPageInfo;
    }

    @Override
    @Transactional
    public void save(Product product) {
        //保存商品数据
        product.setStatus(0); // 0表示上架
        product.setAuditStatus(0); // 0表示审核通过
        productMapper.save(product);

        //保存商品sku数据
        List<ProductSku> productSkuList = product.getProductSkuList();
        for (int i=0,size=productSkuList.size(); i<size; i++) {
            //获取ProductSku对象
            ProductSku productSku = productSkuList.get(i); // 获取第i个SKU
            productSku.setSkuCode(product.getId() + "_" + i);  // 构建skuCode

            productSku.setProductId(product.getId()); // 设置商品ID
            productSku.setSkuName(product.getName() + productSku.getSkuSpec()); // 设置SKU名称
            productSku.setStatus(0); // 设置SKU状态为上架
            productSku.setSaleNum(0); // 设置销量为0
            productSkuMapper.save(productSku); // 保存SKU数据
        }
        //保存商品详情数据
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.save(productDetails);
    }

    @Override
    public Product getById(Long id) {
        //根据id查询商品数据
        Product product = productMapper.selectById(id);

        //根据商品id查询SKU数据
        List<ProductSku> productSkuList = productSkuMapper.selectByProductId(id);
        product.setProductSkuList(productSkuList);

        //根据商品id查询详情数据
        ProductDetails productDetails = productDetailsMapper.selectByProductId(id);
        product.setDetailsImageUrls(productDetails.getImageUrls());

        return product;
    }

    @Override
    public void updateById(Product product) {
        //更新商品数据
        productMapper.updateById(product);
        //更新SKU数据
        List<ProductSku> productSkuList = product.getProductSkuList();
        for (ProductSku productSku : productSkuList) {
            productSkuMapper.updateById(productSku);
        }
        //更新详情数据
        ProductDetails productDetails = productDetailsMapper.selectByProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.updateById(productDetails);
    }

    @Override
    public void deleteById(Long id) {
        productMapper.deleteById(id); // 删除商品数据
        productSkuMapper.deleteByProductId(id);  // 删除SKU数据
        productDetailsMapper.deleteByProductId(id);  // 删除详情数据
    }

    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);
        if (auditStatus == 1) {
            product.setAuditStatus(1);
            product.setAuditMessage("审核通过");
        }else {
            product.setAuditStatus(-1);
            product.setAuditMessage("审核驳回");
        }
        productMapper.updateById(product);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        if(status == 1) {
            product.setStatus(1);
        }else {
            product.setStatus(-1);
        }
        productMapper.updateById(product);
    }
}
