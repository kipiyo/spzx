package com.atguigu.spzx.user.service;

import com.atguigu.spzx.model.entity.user.UserAddress;

import java.util.List;

/**
 * ClassName: UserAddressService
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/17 11:35
 * @Version 1.0
 */
public interface UserAddressService {
    List<UserAddress> findUserAddressList();

    UserAddress getById(Long id);
}
