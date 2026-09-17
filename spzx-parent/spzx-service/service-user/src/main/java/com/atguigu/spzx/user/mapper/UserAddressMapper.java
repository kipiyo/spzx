package com.atguigu.spzx.user.mapper;

import com.atguigu.spzx.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: UserAddressMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/17 11:36
 * @Version 1.0
 */
@Mapper
public interface UserAddressMapper {
    List<UserAddress> findByUserId(Long userId);

    UserAddress getById(Long id);
}
