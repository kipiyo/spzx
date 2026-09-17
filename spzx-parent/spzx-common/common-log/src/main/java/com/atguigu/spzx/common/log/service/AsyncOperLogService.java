package com.atguigu.spzx.common.log.service;

import com.atguigu.spzx.model.entity.system.SysOperLog;

/**
 * ClassName: AsyncOperLogService
 * Package:
 * Description:在common-log模块中定义保存日志数据的service接口，然后在具体的业务服务中给出实现。
 *
 * @Author SeaUrchin
 * @Create 2026/9/15 11:10
 * @Version 1.0
 */
public interface AsyncOperLogService {
//    保存日志数据
    public abstract void saveSysOperLog(SysOperLog sysOperLog) ;
}
