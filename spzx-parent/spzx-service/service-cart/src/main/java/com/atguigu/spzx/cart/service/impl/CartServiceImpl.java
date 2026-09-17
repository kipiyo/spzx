package com.atguigu.spzx.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.cart.service.CartService;
import com.atguigu.spzx.feign.product.ProductFeignClient;
import com.atguigu.spzx.model.entity.h5.CartInfo;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.utils.AuthContextUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * ClassName: CartServiceImpl
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/16 21:10
 * @Version 1.0
 */
@Service
public class CartServiceImpl implements CartService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProductFeignClient productFeignClient;

    private String getCartKey(Long userId) {
        //定义key user:cart:userId
        return "user:cart:" + userId;
    }

    @Override
    public void addToCart(Long skuId, Integer skuNum) {

        // 获取当前登录用户的id 从ThreadLocal中获取
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        //获取缓存对象\
        //hash类型 key:userId  field:skuId  value:{cartInfo}
        Object cartInfoObj = stringRedisTemplate.opsForHash().get(cartKey, String.valueOf(skuId));
        CartInfo cartInfo = null ;
        if(cartInfoObj != null) {       //  如果购物车中有该商品，则商品数量 相加！
            cartInfo = JSON.parseObject(cartInfoObj.toString() , CartInfo.class) ;
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());
        }else {

            // 当购物车中没用该商品的时候，则直接添加到购物车！
            cartInfo = new CartInfo();

            // 购物车数据是从商品详情得到 {skuInfo}
            ProductSku productSku = productFeignClient.getBySkuId(skuId) ;
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setSkuId(skuId);
            cartInfo.setUserId(userId);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());

        }

        // 将商品数据存储到购物车中
        stringRedisTemplate.opsForHash().put(cartKey , String.valueOf(skuId) , JSON.toJSONString(cartInfo));
    }

    @Override
    public List<CartInfo> getCartList() {
        //获取当前登录用户的id 从ThreadLocal中获取
        Long userId = AuthContextUtil.getUserInfo().getId();
        //远程调用获取购物车数据
        String cartKey = this.getCartKey(userId);

        //获取数据
        List<Object> cartInfoList = stringRedisTemplate.opsForHash().values(cartKey);
        System.out.println("====从redis取出原始字符串===" + cartInfoList);

        if(!CollectionUtils.isEmpty(cartInfoList)) {
            List<CartInfo> infoList = cartInfoList.stream().
                    map(cartInfoJson ->
                            JSON.parseObject(cartInfoJson.toString(), CartInfo.class))
                    .sorted((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()))
                    .collect(Collectors.toList());
            return infoList;
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteCart(Long skuId) {

        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        //获取缓存对象
        stringRedisTemplate.opsForHash().delete(cartKey  ,String.valueOf(skuId)) ;
    }

    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        Boolean hasKey = stringRedisTemplate.opsForHash().hasKey(cartKey, String.valueOf(skuId));
        if(hasKey) {
            String cartInfoJSON = stringRedisTemplate.opsForHash().get(cartKey, String.valueOf(skuId)).toString();
            CartInfo cartInfo = JSON.parseObject(cartInfoJSON, CartInfo.class);
            cartInfo.setIsChecked(isChecked);
            stringRedisTemplate.opsForHash().put(cartKey , String.valueOf(skuId) , JSON.toJSONString(cartInfo));
        }
    }

    @Override
    public void allCheckCart(Integer isChecked) {
        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);

        // 获取购物车数据
        List<Object> cartInfoList = stringRedisTemplate.opsForHash().values(cartKey);
        if(!CollectionUtils.isEmpty(cartInfoList)) {
            for(Object cartInfoJson : cartInfoList) {
                CartInfo cartInfo = JSON.parseObject(cartInfoJson.toString(), CartInfo.class);
                cartInfo.setIsChecked(isChecked);
                stringRedisTemplate.opsForHash().put(cartKey, String.valueOf(cartInfo.getSkuId()), JSON.toJSONString(cartInfo));
            }
//            cartInfoList.stream().map(cartInfoJson ->{
//                CartInfo cartInfo = JSON.parseObject(cartInfoJson.toString(), CartInfo.class);
//                cartInfo.setIsChecked(isChecked);
//                return cartInfo;
//            }).forEach(cartInfo -> {
//                stringRedisTemplate.opsForHash().put(cartKey, String.valueOf(cartInfo.getSkuId()), JSON.toJSONString(cartInfo));
//            });
        }
    }

    @Override
    public void clearCart() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        stringRedisTemplate.delete(cartKey);
    }

    @Override
    public List<CartInfo> getAllChecked() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);
        // 获取购物车数据
        List<Object> objectList = stringRedisTemplate.opsForHash().values(cartKey);
        if(!CollectionUtils.isEmpty(objectList)) {
            List<CartInfo> cartInfoList = objectList.stream().map(cartInfoJSON -> JSON.parseObject(cartInfoJSON.toString(), CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                    .collect(Collectors.toList());
            return cartInfoList ;
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteChecked() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);
        // 获取购物车数据
        List<Object> objectList = stringRedisTemplate.opsForHash().values(cartKey);
        if (!CollectionUtils.isEmpty(objectList)) {
            objectList.stream().map(cartInfoJSON -> JSON.parseObject(cartInfoJSON.toString(), CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                    .forEach(cartInfo -> stringRedisTemplate.opsForHash().delete(cartKey, String.valueOf(cartInfo.getSkuId())));
        }
    }

}