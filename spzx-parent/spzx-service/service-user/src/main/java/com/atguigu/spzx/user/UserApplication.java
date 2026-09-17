package com.atguigu.spzx.user;

import com.atguigu.spzx.common.annotation.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@ComponentScan(basePackages = "com.atguigu.spzx")
@EnableUserWebMvcConfiguration
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}