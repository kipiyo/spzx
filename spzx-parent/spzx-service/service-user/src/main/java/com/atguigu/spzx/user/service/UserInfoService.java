package com.atguigu.spzx.user.service;

import com.atguigu.spzx.model.dto.h5.UserLoginDto;
import com.atguigu.spzx.model.dto.h5.UserRegisterDto;
import com.atguigu.spzx.model.vo.h5.UserInfoVo;

/**
 * ClassName: UserInfoService
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/16 19:17
 * @Version 1.0
 */
public interface UserInfoService {
    void register(UserRegisterDto userRegisterDto);

    Object login(UserLoginDto userLoginDto);

    UserInfoVo getCurrentUserInfo(String token);
}
