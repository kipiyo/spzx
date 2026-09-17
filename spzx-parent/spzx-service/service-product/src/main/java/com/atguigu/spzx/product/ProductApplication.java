package com.atguigu.spzx.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

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
@EnableCaching// 开启缓存
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}