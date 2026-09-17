package com.atguigu.spzx.common.log.annotation;

import com.atguigu.spzx.common.log.enums.OperatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: log
 * Package:
 * Description: 自定义操作日志记录注解
 *
 * @Author SeaUrchin
 * @Create 2026/9/15 10:48
 * @Version 1.0
 */
@Target(ElementType.METHOD) // 指定注解可以使用的位置
@Retention(RetentionPolicy.RUNTIME) // 指定注解在运行时保留
public @interface Log {
    public String title() ;								// 模块名称
    public OperatorType operatorType() default OperatorType.MANAGE;	// 操作人类别
    public int businessType() ;     // 业务类型（0其它 1新增 2修改 3删除）
    public boolean isSaveRequestData() default true;   // 是否保存请求的参数
    public boolean isSaveResponseData() default true;  // 是否保存响应的参数
}
