package com.atguigu.spzx.common.annotation;

import com.atguigu.spzx.common.config.UserWebMvcConfiguration;
import com.atguigu.spzx.common.interceptor.UserLoginAuthInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: EnableUserWebMvcConfiguration
 * Package:
 * Description: 自定义注解，用于开启用户登录拦截器配置
 *
 * @Author SeaUrchin
 * @Create 2026/9/16 20:41
 * @Version 1.0
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(value = { UserLoginAuthInterceptor.class , UserWebMvcConfiguration.class})

public @interface EnableUserWebMvcConfiguration {
}
