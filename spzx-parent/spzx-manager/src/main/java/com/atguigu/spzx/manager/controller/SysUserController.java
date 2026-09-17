package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: SysUserController
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/13 9:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     *用户分配角色
     * @param assginRoleDto
     * @return
     */
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleDto assginRoleDto) {
        sysUserService.doAssign(assginRoleDto) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 根据用户id删除用户信息
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        sysUserService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 当用户点击修改按钮的时候，那么此时就弹出对话框，在该对话框中需要将当前行所对应的用户数据在该表单页面进行展示。
     * 当用户在该表单中点击提交按钮的时候那么此时就需要将表单进行提交，在后端需要提交过来的表单数据修改数据库中的即可。
     * @param sysUser
     * @return
     */
    @PutMapping(value = "/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateSysUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 当用户点击添加按钮的时候，那么此时就弹出对话框，在该对话框中需要展示添加用户表单。
     * 当用户在该表单中点击提交按钮的时候那么此时就需要将表单进行提交，在后端需要提交过来的表单数据保存到数据库中即可。
     * @param sysUser
     * @return
     */
    @PostMapping("/saveSysUser")
    public Result saveSysUser(@RequestBody SysUser sysUser) {
        sysUserService.saveSysUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 1、如果在搜索表单中输入和查询关键字以及创建的开始时间和结束是时间，
     *    那么此时就需要按照查询关键字以及创建的开始时间和结束是时间进行条件查询
     * 2、查询关键字搜索的字段可以是用户名、姓名、手机号码。在查询的时候需要继续按照这些字段进行模糊查询。
     * 2、搜索的时候需要进行分页搜索
     * @param sysUserDto
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(SysUserDto sysUserDto, //GEt请求,使用普通参数传
                             @PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize) {
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(sysUserDto, pageNum, pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
}
