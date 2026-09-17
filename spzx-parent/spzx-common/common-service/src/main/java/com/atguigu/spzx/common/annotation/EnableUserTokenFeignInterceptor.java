package com.atguigu.spzx.common.annotation;

import com.atguigu.spzx.common.interceptor.UserTokenFeignInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: EnableUserTokenFeignInterceptor
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/17 12:21
 * @Version 1.0
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(value = UserTokenFeignInterceptor.class)
public @interface EnableUserTokenFeignInterceptor {

}