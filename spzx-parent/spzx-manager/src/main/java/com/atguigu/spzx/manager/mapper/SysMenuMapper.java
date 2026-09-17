package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: SysMenuMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/13 17:21
 * @Version 1.0
 */
@Mapper
public interface SysMenuMapper {
    List<SysMenu> selectAll();

    void insert(SysMenu sysMenu);

    void updateById(SysMenu sysMenu);

    int countByParentId(Long id);

    void deleteById(Long id);

    List<SysMenu> findMenuByUserId(Long userId);

    SysMenu selectParentMenu(Long parentId);
}
