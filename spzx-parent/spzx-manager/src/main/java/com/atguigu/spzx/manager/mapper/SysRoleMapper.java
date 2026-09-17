package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: SysRoleMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/12 18:21
 * @Version 1.0
 */
@Mapper
public interface SysRoleMapper {
    List<SysRole> findByPage(SysRole sysRole);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);

    List<SysRole> findAllRoles();
}
