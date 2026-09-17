package com.atguigu.spzx.common.log.annotation;

import com.atguigu.spzx.common.log.aspect.LogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: EnableLogAspect
 * Package:
 * Description:想让LogAspect这个切面类在其他的业务服务中进行使用，
 * 那么就需要该切面类纳入到Spring容器中。Spring Boot默认会扫描和启动类所在包相同包中的bean以及子包中的bean。
 * 而LogAspect切面类不满足扫描条件，因此无法直接在业务服务中进行使用。那么此时可以通过自定义注解进行实现，
 *
 * @Author SeaUrchin
 * @Create 2026/9/15 10:59
 * @Version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(value = LogAspect.class)// 通过Import注解导入日志切面类到Spring容器中
public @interface EnableLogAspect {
}
