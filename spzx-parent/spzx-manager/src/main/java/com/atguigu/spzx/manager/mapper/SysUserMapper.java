package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: SysUserMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/12 11:24
 * @Version 1.0
 */
@Mapper // 标记为 MyBatis Mapper
public interface SysUserMapper {
    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    SysUser selectByUserName(String userName);

    List<SysUser> findByPage(SysUserDto sysUserDto);

    SysUser findByUserName(String userName);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Integer id);
}
