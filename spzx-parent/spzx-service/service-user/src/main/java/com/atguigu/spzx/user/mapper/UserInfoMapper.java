package com.atguigu.spzx.user.mapper;

import com.atguigu.spzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserInfoMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/16 19:17
 * @Version 1.0
 */
@Mapper
public interface UserInfoMapper {
    UserInfo getByUsername(String username);

    void save(UserInfo userInfo);
}
