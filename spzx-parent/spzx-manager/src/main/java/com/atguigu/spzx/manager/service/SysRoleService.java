package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * ClassName: SysRoleService
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/12 18:21
 * @Version 1.0
 */
public interface SysRoleService {
    /**
     * 分页查询角色信息
     * @param sysRole
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<SysRole> findByPage(SysRole sysRole, Integer pageNum, Integer pageSize);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);

    Map<String, Object> findAllRoles(Long userId);
}
