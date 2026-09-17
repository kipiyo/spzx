package com.atguigu.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ClassName: SysRoleUserMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/13 16:00
 * @Version 1.0
 */
@Mapper
public interface SysRoleUserMapper {
    void deleteByUserId(Long userId);

    void doAssign(@Param("userId") Long userId,@Param("roleId") Long roleId);

    List<Long> selectRoleIdsByUserId(Long userId);
}
