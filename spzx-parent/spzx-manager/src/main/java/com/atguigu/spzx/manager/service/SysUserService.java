package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.github.pagehelper.PageInfo;

/**
 * ClassName: SysUserService
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/12 11:23
 * @Version 1.0
 */
public interface SysUserService {
    /**
     * 根据用户名查询用户数据
     * @param loginDto
     * @return
     */
    public abstract LoginVo login(LoginDto loginDto);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    SysUser getUserInfo(String token);

    /**
     * 退出登录
     * @param token
     */
    void logout(String token);

    PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Integer id);

    void doAssign(AssginRoleDto assginRoleDto);

}
