package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.mapper.SysMenuMapper;
import com.atguigu.spzx.manager.mapper.SysRoleMenuMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.utils.MenuHelper;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.SysMenuVo;
import com.atguigu.spzx.utils.AuthContextUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * ClassName: SysMenuServiceImpl
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/13 17:21
 * @Version 1.0
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper ;

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Override
    public List<SysMenu> findNodes() {
        //1/查询所有菜单
        List<SysMenu> sysMenuList = sysMenuMapper.selectAll();
        if(CollectionUtils.isEmpty(sysMenuList)) return null;
        //2.调用工具类方法,把list集合封装成树形结构

        List<SysMenu> treeList = MenuHelper.buildTree(sysMenuList);
        return treeList;
    }

    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu) ;

        //新添加的子菜单,把父菜单isHalf改为半开状态 1
        updateSysRoleMenu(sysMenu);
    }
    //新添加的子菜单,把父菜单isHalf改为半开状态 1
    private void updateSysRoleMenu(SysMenu sysMenu) {
        //获取当前添加菜单的父菜单
        SysMenu parentMenu = sysMenuMapper.selectParentMenu(sysMenu.getParentId());
        if(parentMenu != null){
            //把父菜单isHalf改为半开状态 1
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId());

            //递归调用
            updateSysRoleMenu(parentMenu);
        }
    }

    @Override
    public void updateById(SysMenu sysMenu) {
        sysMenuMapper.updateById(sysMenu) ;
    }

    @Override
    public void removeById(Long id) {
        int count = sysMenuMapper.countByParentId(id) ;// 先查询是否存在子菜单，如果存在不允许进行删除
        if(count > 0) throw new GuiguException(ResultCodeEnum.NODE_ERROR);
        sysMenuMapper.deleteById(id);		// 不存在子菜单直接删除

    }

    @Override
    public List<SysMenuVo> findUserMenuList() {
        //获取当前用户id
        Long userId = AuthContextUtil.get().getId();
        //根据userId查询可以操作的菜单
        List<SysMenu> sysMenuList = sysMenuMapper.findMenuByUserId(userId);

        //封装要求数据格式
        sysMenuList = MenuHelper.buildTree(sysMenuList);
        List<SysMenuVo> sysMenuVos = this.buildMenus(sysMenuList);
        return sysMenuVos;
    }

    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {

        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }
}
