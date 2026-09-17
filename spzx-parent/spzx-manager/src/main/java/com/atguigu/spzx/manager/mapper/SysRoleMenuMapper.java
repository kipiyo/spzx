package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.AssginMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: SysRoleMenuMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/13 18:16
 * @Version 1.0
 */
@Mapper
public interface SysRoleMenuMapper {
     void doAssign(AssginMenuDto assginMenuDto);

    List<Long> findSysRoleMenuByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);

    void updateSysRoleMenuIsHalf(Long menuId);
}
