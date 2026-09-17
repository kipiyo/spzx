package com.atguigu.spzx.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ClassName: UserLoginAuthInterceptor
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/16 20:35
 * @Version 1.0
 */
public class UserLoginAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate redisTemplate ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 如果token不为空，那么此时验证token的合法性
        String userInfoJSON = redisTemplate.opsForValue().get("user:spzx:" + request.getHeader("token"));
        AuthContextUtil.setUserInfo(JSON.parseObject(userInfoJSON , UserInfo.class));
        return true ;

    }
}
