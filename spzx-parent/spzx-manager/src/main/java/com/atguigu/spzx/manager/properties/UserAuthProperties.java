package com.atguigu.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * ClassName: UserAuthProperties
 * Package:
 * Description:
 * 用户认证属性配置类 读取application-dev
 * 把读取的路径写到配置类中,后续统一修改 ,类似常量
 * spzx:
 *   auth:
 *     noAuthUrls:
 *       - /admin/system/index/login
 *       - /admin/system/index/generateValidateCode
 *
 * @Author SeaUrchin
 * @Create 2026/9/12 16:39
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "spzx.auth")
public class UserAuthProperties {
    private List<String> noAuthUrls;
}
