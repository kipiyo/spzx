package com.atguigu.spzx.feign.cart;

import com.atguigu.spzx.model.entity.h5.CartInfo;
import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * ClassName: CartFeignClient
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/17 11:50
 * @Version 1.0
 */
@FeignClient(value = "service-cart")
public interface CartFeignClient {
    @GetMapping(value = "/api/order/cart/auth/deleteChecked")
    public abstract Result deleteChecked() ;
    @GetMapping(value = "/api/order/cart/auth/getAllChecked")
    public abstract List<CartInfo> getAllChecked() ;

}