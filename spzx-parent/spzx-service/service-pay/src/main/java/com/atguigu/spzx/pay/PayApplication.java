package com.atguigu.spzx.pay;

import com.atguigu.spzx.common.annotation.EnableUserWebMvcConfiguration;
import com.atguigu.spzx.pay.properties.AlipayProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

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
@EnableUserWebMvcConfiguration
@EnableFeignClients(basePackages = {
        "com.atguigu.spzx"

})
@EnableConfigurationProperties(value = { AlipayProperties.class })
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class , args) ;
    }

}