package com.atguigu.spzx.order;

import com.atguigu.spzx.common.annotation.EnableUserTokenFeignInterceptor;

import com.atguigu.spzx.common.annotation.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * ClassName: ${NAME}
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create ${DATE} ${TIME}
 * @Version 1.0
 */
@SpringBootApplication
@EnableUserTokenFeignInterceptor
@EnableFeignClients(basePackages = {
        "com.atguigu.spzx.feign.cart",
        "com.atguigu.spzx.feign.user",
        "com.atguigu.spzx.feign.product"
})
@EnableUserWebMvcConfiguration
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class , args) ;
    }

}