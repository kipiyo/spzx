package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.AssginMenuDto;

import java.util.Map;

/**
 * ClassName: SysRoleMenuService
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/13 18:15
 * @Version 1.0
 */
public interface SysRoleMenuService {
    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    void doAssign(AssginMenuDto assginMenuDto);
}
