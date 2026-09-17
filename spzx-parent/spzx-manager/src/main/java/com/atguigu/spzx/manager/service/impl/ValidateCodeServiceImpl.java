package com.atguigu.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.atguigu.spzx.manager.service.ValidateCodeService;
import com.atguigu.spzx.model.entity.base.GlobalConstants;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: ValidateCodeServiceImpl
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/12 14:19
 * @Version 1.0
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ValidateCodeVo generateValidateCode() {
        // 使用hutool工具包中的工具类生成图片验证码
        //参数：宽  高  验证码位数 干扰线数量
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 2);
        String codeValue = circleCaptcha.getCode(); //获取验证码文本内容
        String imageBase64 = circleCaptcha.getImageBase64();  //获取验证码图片的base64编码

        //生成uuid作为图片验证码的key
        String codeKey = UUID.randomUUID().toString().replace("-", "");

        //将验证码存到redis
        //   业务模块:功能:业务细分:唯一标识   user:login:validateCode:codeKey
        redisTemplate.opsForValue().set(GlobalConstants.REDIS_USER_LOGIN_VALIDATE_CODE_PREFIX + codeKey,
                codeValue,
                5,
                TimeUnit.MINUTES);

        //构建响应结果
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(codeKey);
        validateCodeVo.setCodeValue("data:image/png;base64," + imageBase64);

        return validateCodeVo;
    }
}
