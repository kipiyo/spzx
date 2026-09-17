package com.atguigu.spzx.common.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * ClassName: UserTokenFeignInterceptor
 * Package:
 * Description:针对service-cart微服务是获取不到当前登录用户的信息。
 * service-order微服务调用service-cart微服务的时候，
 * 是通过openFeign进行调用，openFeign在调用的时候会丢失请求头
 *
 * @Author SeaUrchin
 * @Create 2026/9/17 12:20
 * @Version 1.0
 */
public class UserTokenFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader("token");
        requestTemplate.header("token" , token) ;
    }

}
