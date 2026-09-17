package com.atguigu.spzx.manager.utils;

import com.atguigu.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: MenuHelper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/13 17:42
 * @Version 1.0
 */
public class MenuHelper {

    //查询所有菜单数据,层层封装,封装到最后一级菜单时,停止递归
    //递归实现封装树形结构
        //1.递归入口
        //2.查找条件
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        //sysMenuList 所有菜单集合
        //创建list集合,存放最终的树形结构
        List<SysMenu> treeList = new ArrayList<>();
        //遍历
        for (SysMenu sysMenu : sysMenuList) {
            //找到递归入口 parent_id=0
            if (sysMenu.getParentId().longValue() == 0) {
                //根据第一层,找下层数据
                treeList.add(findChildren(sysMenu, sysMenuList));
            }
        }
        return treeList;
    }

    /**
     * 递归方法 找下层数据
     * @param sysMenu 当前遍历的菜单对象
     * @param sysMenuList 所有菜单集合
     * @return
     */
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        //Sysmenu中属性children,存放当前菜单的下层数据
        sysMenu.setChildren(new ArrayList<>());
        //遍历所有菜单
        //sysMenu的id值和sysMenuList的parent_id值比较 ,相等则为下层数据
        for (SysMenu menu : sysMenuList) {
            //判断当前菜单的id值和遍历到的菜单对象的parent_id是否相等
            if (sysMenu.getId().longValue() == menu.getParentId().longValue()) {
                //相等,则为下层数据
                sysMenu.getChildren().add(findChildren(menu, sysMenuList));// 递归调用
            }
        }
        return sysMenu;
    }
}
