package com.atguigu.spzx.user.service.impl;

import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.user.service.SmsService;
import com.atguigu.spzx.utils.HttpUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: SmsServiceImpl
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/16 16:14
 * @Version 1.0
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void sendValidateCode(String phone) {
        String code = stringRedisTemplate.opsForValue().get(phone);
        if(StringUtils.hasText(code)){ return;}
//        System.out.println("发送验证码");
        //1.生成验证码
        code = RandomStringUtils.randomNumeric(4);
//        System.out.println("验证码为："+code);
        //2.把生成验证码放到redis,设置过期时间
        stringRedisTemplate.opsForValue().set("phone:code:" + phone , code , 5 , TimeUnit.MINUTES);
        //3.向手机发送验证码
//        System.out.println("向手机发送验证码："+phone);
//        sendMessage(phone,code);
//        System.out.println("【测试】手机号："+phone+"，验证码："+code);
    }

    /**
     * 发送验证码
     * @param phone
     * @param code
     */
    public void sendMessage(String phone, String code) {

        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = "xxxxxxxxxxxxxx";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:"+code);
        bodys.put("template_id", "CST_ptdie100");
        bodys.put("phone_number", phone);

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuiguException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }
}

//需要赠送身份证OCR识别，vip优惠券,技术支持。请直接联系客服。
//商品说明可以在商品介绍里查看

/**
 *重要提示：
 *如您的返回结果中，没有我们接口的返回报文，或者连header的信息都打印出来了。可能是您的代码环境未能适配该请求示例。
 *那么，以下两个命令行，您可以二选一，选择一个适合你环境的加入到请求示例中。即可打印我们接口的返回报文。
 *或者直接联系客服  VX 18600814970
 *
 *System.out.println(EntityUtils.toString(response.getEntity()));
 *
 *System.out.println(response.body().string());
 */

