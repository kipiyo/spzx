package com.atguigu.spzx.manager.service.impl;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.common.log.annotation.Log;
import com.atguigu.spzx.manager.mapper.SysRoleUserMapper;
import com.atguigu.spzx.manager.mapper.SysUserMapper;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.base.GlobalConstants;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: SysUserServiceImpl
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/12 11:24
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private SysRoleUserMapper sysRoleUserMapper ;

    @Override
    public LoginVo login(LoginDto loginDto) {
        // 校验验证码是否正确
        String captcha = loginDto.getCaptcha();//验证码
        String codeKey = loginDto.getCodeKey();//redis中验证码的数据key

        // 从Redis中获取验证码
        //redisTemplate.opsForValue().set("user:login:validatecode:" + codeKey,
        String redisCode = (String) redisTemplate.opsForValue().get(GlobalConstants.REDIS_USER_LOGIN_VALIDATE_CODE_PREFIX  + codeKey);
        if(StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(redisCode,captcha)){
            //验证码错误,重新生成验证码,要页面刷新

            throw new GuiguException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        //验证通过删除redis中验证码
        redisTemplate.delete(GlobalConstants.REDIS_USER_LOGIN_VALIDATE_CODE_PREFIX + codeKey);

        // 根据用户名查询用户
        SysUser sysUser = sysUserMapper.selectByUserName(loginDto.getUserName());
        if (sysUser == null) {
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 验证密码是否正确
        String inputPassword = loginDto.getPassword();
        String md5InputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        if (!md5InputPassword.equals(sysUser.getPassword())) {//用户输入密码加密后,与数据库密码比较
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }

        // 生成token,并存入redis
        String token = UUID.randomUUID().toString().replace("-", "");
//        redisTemplate.opsForValue().set("user:login:" + token, JSON.toJSONString(sysUser),7, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(GlobalConstants.REDIS_USER_LOGIN_TOKEN_PREFIX+ token, JSON.toJSONString(sysUser),30, TimeUnit.MINUTES);

        //构建响应结果对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");//暂时不实现刷新token的功能

        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        String userJson = (String) redisTemplate.opsForValue().get(GlobalConstants.REDIS_USER_LOGIN_TOKEN_PREFIX + token);
        return JSON.parseObject(userJson, SysUser.class);
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(GlobalConstants.REDIS_USER_LOGIN_TOKEN_PREFIX + token);
    }

    @Override
    public PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> sysUserList = sysUserMapper.findByPage(sysUserDto);
        PageInfo pageInfo = new PageInfo<>(sysUserList);
        return pageInfo;
    }

    @Override
    public void saveSysUser(SysUser sysUser) {
        //根据输入的用户名查询用户
        SysUser dbSysUser = sysUserMapper.findByUserName(sysUser.getUserName());
        if(dbSysUser != null){
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        //对密码进行加密
        String password = sysUser.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUser.setPassword(md5Password);
        //设置默认状态为启用
        sysUser.setStatus(1);
        sysUserMapper.saveSysUser(sysUser);


    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public void deleteById(Integer id) {
        sysUserMapper.deleteById(id);
    }

    @Log(title = "角色菜单模块" , businessType = 2 )
    @Override
    @Transactional
    public void doAssign(AssginRoleDto assginRoleDto) {

        // 删除之前的所有的用户所对应的角色数据
        sysRoleUserMapper.deleteByUserId(assginRoleDto.getUserId()) ;
        // 手动抛出异常,测试事务回滚
//        int a = 1 / 0 ;
        // 分配新的角色数据
        List<Long> roleIdList = assginRoleDto.getRoleIdList();
        // 遍历角色id集合,将新的用户所对应的角色数据保存到数据库中
        roleIdList.forEach(roleId->{
            sysRoleUserMapper.doAssign(assginRoleDto.getUserId(),roleId);
        });
    }
}
