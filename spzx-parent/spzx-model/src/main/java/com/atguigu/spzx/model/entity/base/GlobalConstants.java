package com.atguigu.spzx.model.entity.base;

/**
 * ClassName: GlobalConstants
 * Package:
 * Description: 项目全局常量
 *
 * @Author SeaUrchin
 * @Create 2026/9/12 15:12
 * @Version 1.0
 */
public class GlobalConstants {
    // 私有构造，禁止new对象
    private GlobalConstants() {
        throw new RuntimeException("常量类不允许实例化");
    }
    // ===================== Redis Key 前缀 =====================
    /**
     * 登录验证码key前缀
     * 完整key：user:login:validateCode:{codeKey}
     */
    public static final String REDIS_USER_LOGIN_VALIDATE_CODE_PREFIX = "user:login:validateCode:";

    /**
     * 用户登录token前缀
     */
    public static final String REDIS_USER_LOGIN_TOKEN_PREFIX = "user:login:";
}
