package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.log.annotation.Log;
import com.atguigu.spzx.manager.service.SysRoleService;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * ClassName: SysRoleController
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/12 18:11
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 首先需要将系统中所有的角色数据都查询出来，在前端给用户展示出来。
     * @return
     */
    @GetMapping(value = "/findAllRoles/{userId}")
    public Result<Map<String,Object>> findAllRoles(@PathVariable("userId") Long userId) {
        Map<String,Object> data = sysRoleService.findAllRoles(userId);
        return Result.build(data, ResultCodeEnum.SUCCESS);
    }

    /**
     * 当点击删除按钮的时候此时需要弹出一个提示框，询问是否需要删除数据？
     * 如果用户点击是，那么此时向后端发送请求传递id参数，后端接收id参数进行逻辑删除.
     * @param roleId
     * @return
     */
    @DeleteMapping("/deleteById/{roleId}")
    public Result deleteById(@PathVariable("roleId") Long roleId) {
        sysRoleService.deleteById(roleId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 当用户点击修改按钮的时候，那么此时就弹出对话框，在该对话框中需要将当前行所对应的角色数据在该表单页面进行展示。
     * 当用户在该表单中点击提交按钮的时候那么此时就需要将表单进行提交，在后端需要提交过来的表单数据修改数据库中的即可。
     * @param sysRole
     * @return
     */
    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 当用户点击添加按钮的时候，那么此时就弹出对话框，在该对话框中需要展示添加角色表单。
     * 当用户在该表单中点击提交按钮的时候那么此时就需要将表单进行提交，在后端需要提交过来的表单数据保存到数据库中即可
     * @param sysRole
     * @return
     */
    @Log(title = "角色管理:添加", businessType = 1)
    @PostMapping("/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.saveSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 1、前端提交请求参数的时候包含了两部分的参数：搜索条件参数、分页参数。
     * 搜索条件参数可以通过?拼接到请求路径后面，分页参数【当前页码、每页显示的数据条数】
     * 可以让前端通过请求路径传递过来
     *
     * 2、后端查询完毕以后需要给前端返回一个分页对象，
     * 分页对象中就封装了分页相关的参数(当前页数据、总记录数、总页数...)
     *
     * 3、前端进行参数传递的时候，不一定会传递搜索条件，
     * 因此sql语句的编写需要使用到动态sql
     * @param sysRole 条件角色名称对象
     * @param pageNum 当前页码
     * @param pageSize 每页显示的数据条数
     * @return
     */
    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysRole>> findByPage(@RequestBody SysRole sysRole,
                                                @PathVariable("pageNum") Integer pageNum,
                                                @PathVariable("pageSize") Integer pageSize) {
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRole, pageNum, pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
}
