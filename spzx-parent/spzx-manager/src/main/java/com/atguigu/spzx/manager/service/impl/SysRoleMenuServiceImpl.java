package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysRoleMenuMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.service.SysRoleMenuService;
import com.atguigu.spzx.model.dto.system.AssginMenuDto;
import com.atguigu.spzx.model.entity.system.SysMenu;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SysRoleMenuServiceImpl
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/13 18:15
 * @Version 1.0
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Resource
    private SysMenuService sysMenuService;
    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {
        //查询所有菜单
        List<SysMenu> sysMenuList = sysMenuService.findNodes();

        //查询角色分配过的菜单id列表
        List<Long> menuIdList = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);
        Map<String,Object> data = new HashMap();
        data.put("sysMenuList",sysMenuList);
        data.put("roleMenuIds",menuIdList);
        return data;
    }

    @Override
    @Transactional
    public void doAssign(AssginMenuDto assginMenuDto) {
        //先删除角色对应的菜单
        sysRoleMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());
        //保存分配数据
        List<Map<String, Number>> menuInfo = assginMenuDto.getMenuIdList();
        if(menuInfo != null && menuInfo.size() > 0){
            sysRoleMenuMapper.doAssign(assginMenuDto);
        }
    }
}
