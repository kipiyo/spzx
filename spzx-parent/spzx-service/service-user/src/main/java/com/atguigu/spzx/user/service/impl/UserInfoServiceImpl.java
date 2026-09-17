package com.atguigu.spzx.user.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.model.dto.h5.UserLoginDto;
import com.atguigu.spzx.model.dto.h5.UserRegisterDto;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.h5.UserInfoVo;
import com.atguigu.spzx.user.mapper.UserInfoMapper;
import com.atguigu.spzx.user.service.UserInfoService;
import com.atguigu.spzx.utils.AuthContextUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: UserInfoServiceImpl
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/16 19:17
 * @Version 1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserRegisterDto userRegisterDto) {
        //获取数据
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();

        //校验参数
        if(StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(nickName)
                || StringUtils.isEmpty(code)) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }

        //校验验证码
        String codeRedis = stringRedisTemplate.opsForValue().get("phone:code:" + username);
        if(!code.equals(codeRedis)){
            System.out.println("验证码为："+codeRedis);
            throw new GuiguException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        // 校验用户名是否存在
        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if(null != userInfo){
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //不存在,保存用户信息
        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setNickName(nickName);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setSex(0);
        //默认头像
        userInfo.setAvatar("com/atguigu/spzx/user/imgs/doubao.png");

        userInfoMapper.save(userInfo);

        //删除redis数据
        stringRedisTemplate.delete("phone:code:" + username);
    }

    @Override
    public Object login(UserLoginDto userLoginDto) {
        //获取数据
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        //校验参数
        if(StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password)) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        //校验用户名是否存在
        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if(null == userInfo){
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }

        //校验密码
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!md5DigestAsHex.equals(userInfo.getPassword())){
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }
        //校验用户状态
        if(userInfo.getStatus() == 0){
            throw new GuiguException(ResultCodeEnum.ACCOUNT_STOP);
        }

        //生成token 存入 redis
        String token = UUID.randomUUID().toString().replace("-", "");
        stringRedisTemplate.opsForValue().set("user:spzx:" + token, JSON.toJSONString(userInfo),30, TimeUnit.DAYS);
        return token;
    }

    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
//        String userInfoJSON = stringRedisTemplate.opsForValue().get("user:spzx:" + token);
//        if(StringUtils.isEmpty(userInfoJSON)){
//            throw new GuiguException(ResultCodeEnum.LOGIN_AUTH);
//        }
//        UserInfo userInfo = JSON.parseObject(userInfoJSON, UserInfo.class);

        //从TreadLocal获取 UserInfo
        UserInfo userInfo = AuthContextUtil.getUserInfo();

        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return userInfoVo;
    }
}
