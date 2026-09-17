package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.common.log.service.AsyncOperLogService;
import com.atguigu.spzx.manager.mapper.SysOperLogMapper;
import com.atguigu.spzx.model.entity.system.SysOperLog;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * ClassName: AsyncOperLogServiceimpl
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/15 11:11
 * @Version 1.0
 */
@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {
    @Resource
    private SysOperLogMapper sysOperLogMapper;

    // 保存日志数据
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}
