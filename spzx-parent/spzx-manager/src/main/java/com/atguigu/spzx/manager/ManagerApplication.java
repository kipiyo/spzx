package com.atguigu.spzx.manager;

import com.atguigu.spzx.common.log.annotation.EnableLogAspect;
import com.atguigu.spzx.manager.properties.MinioProperties;
import com.atguigu.spzx.manager.properties.UserAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

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
@ComponentScan(basePackages = {"com.atguigu.spzx"})
@EnableConfigurationProperties({UserAuthProperties.class, MinioProperties.class})
@EnableScheduling // 开启定时任务
@EnableLogAspect // 开启自定义日志切面类
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class , args) ;
    }

}