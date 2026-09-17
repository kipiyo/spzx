package com.atguigu.spzx.manager.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.model.entity.base.GlobalConstants;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.utils.AuthContextUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: LoginAuthInterceptor
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/12 16:07
 * @Version 1.0
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * preHandle是进行处理器Controller方法之前的操作
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求方式
        String method = request.getMethod();
        //如果请求方式是options 预检请求 直接通过
        if ("OPTIONS".equals(method)) {
            return true;
        }

        //2.从请求头获取token
        String token = request.getHeader("token");
        //token为空 直接返回
        if (StrUtil.isEmpty(token)) {
            responseNoLoginInfo(response);
            return false;
        }
        //3.从redis中获取token对应的用户信息
        String sysUserInfoJson = (String) redisTemplate.opsForValue().get(GlobalConstants.REDIS_USER_LOGIN_TOKEN_PREFIX + token);

        //用户信息为空 直接返回
        if (StrUtil.isEmpty(sysUserInfoJson)) {
            responseNoLoginInfo(response);
            return false;
        }
        //4.将用户信息存入ThreadLocal
        SysUser sysUser = JSON.parseObject(sysUserInfoJson, SysUser.class);
        AuthContextUtil.set(sysUser);
        //5,更新redis用户信息数据,更新过期时间
        redisTemplate.expire(GlobalConstants.REDIS_USER_LOGIN_TOKEN_PREFIX + token, 30,TimeUnit.MINUTES);
        //6.放行
        return true;
    }

    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        //构建未登录提示的响应结果对象
        Result result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        //设置响应的字符编码
        response.setCharacterEncoding("UTF-8");
        //设置响应的内容类型及编码格式
        response.setContentType("text/html; charset=utf-8");
        try {
            //获取响应字符输出流
            writer = response.getWriter();
            //将结果对象序列化为json字符串并写回给前端
            writer.write(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                //关闭输出流，释放资源
                writer.close();
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除ThreadLocal中的数据
        AuthContextUtil.remove();
    }
}
