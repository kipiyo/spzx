package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: SysOperLogMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/15 11:12
 * @Version 1.0
 */
@Mapper
public interface SysOperLogMapper {
    void insert(SysOperLog sysOperLog);
}
