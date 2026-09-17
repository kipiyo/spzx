package com.atguigu.spzx.common.log.aspect;

import com.atguigu.spzx.common.log.annotation.Log;
import com.atguigu.spzx.common.log.service.AsyncOperLogService;
import com.atguigu.spzx.common.log.utils.LogUtil;
import com.atguigu.spzx.model.entity.system.SysOperLog;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * ClassName: LogAspect
 * Package:
 * Description:该切面类中提供一个环绕通知方法
 *
 * @Author SeaUrchin
 * @Create 2026/9/15 10:52
 * @Version 1.0
 */
@Aspect
@Component
public class LogAspect {
    @Resource
    private AsyncOperLogService asyncOperLogService;

    /**
     * 环绕通知
     * @param joinPoint
     * @param sysLog
     * @return
     */
    @Around("@annotation(sysLog)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, Log sysLog) {
//        String title = sysLog.title();
//        int businessType = sysLog.businessType();
//        System.out.println("titile = "+title+" businessType = " + businessType);
        //业务方法执行之前,封装数据
        SysOperLog sysOperLog = new SysOperLog();
        LogUtil.beforeHandleLog(sysLog,joinPoint,sysOperLog);
        // 业务方法
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
//            System.out.println("业务方法执行完毕");
            //业务方法执行之后,封装数据
            LogUtil.afterHandlLog(sysLog,proceed,sysOperLog,0,null);
        } catch (Throwable e) {
            //业务方法执行异常,封装数据
            e.printStackTrace();
            LogUtil.afterHandlLog(sysLog,proceed,sysOperLog,1,e.getMessage());
            //抛出异常,让外围的调用者知道业务方法执行失败了
            //自定义了切面类以后，如果不注意异常的处理，那么此时就会出现事务失效的情况。
            throw new RuntimeException();
        }
        //调用service方法,把日志信息添加到数据库
        asyncOperLogService.saveSysOperLog(sysOperLog);
        return proceed;
    }
}
