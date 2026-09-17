package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: SysMenuController
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/13 17:20
 * @Version 1.0
 */
@RestController
@RequestMapping(value="/admin/system/sysMenu")
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    /**
     * 根据id删除菜单信息
     * @param id
     * @return
     */
    @DeleteMapping("/removeById/{id}")
    public Result removeById(@PathVariable Long id) {
        sysMenuService.removeById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 更新菜单信息
     * @param sysMenu
     * @return
     */
    @PutMapping("/updateById")
    public Result updateById(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateById(sysMenu);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    /**
     * 保存菜单信息
     * @param sysMenu
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 查询所有菜单
     * @return
     */
    @GetMapping("/findNodes")
    public Result<List<SysMenu>> findNodes(){
        List<SysMenu> list =  sysMenuService.findNodes();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }
}
