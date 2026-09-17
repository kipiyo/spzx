package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysRoleMenuService;
import com.atguigu.spzx.model.dto.system.AssginMenuDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName: SysRoleMenuController
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/13 18:15
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/admin/system/sysRoleMenu")
public class SysRoleMenuController {
    @Autowired
    private SysRoleMenuService sysRoleMenuService ;

    /**
     * 前端请求后端接口的时候需要将角色的id和用户所选中的菜单id传递到后端。
     * 后端需要先根据角色的id从sys_role_menu表中删除其所对应的菜单数据，然后添加新的菜单数据到sys_role_menu表中
     * @param assginMenuDto
     * @return
     */
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuDto assginMenuDto) {
        sysRoleMenuService.doAssign(assginMenuDto);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 查询所有菜单和查询角色分配过的菜单id列表
     * @param roleId
     * @return
     */
    @GetMapping(value = "/findSysRoleMenuByRoleId/{roleId}")
    public Result findSysRoleMenuByRoleId(@PathVariable("roleId") Long roleId){
        Map<String,Object> data = sysRoleMenuService.findSysRoleMenuByRoleId(roleId);
        return Result.build(data, ResultCodeEnum.SUCCESS);
    }
}
