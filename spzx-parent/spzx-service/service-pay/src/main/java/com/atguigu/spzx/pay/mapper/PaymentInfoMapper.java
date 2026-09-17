package com.atguigu.spzx.pay.mapper;

import com.atguigu.spzx.model.entity.pay.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: PaymentInfoMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/17 15:07
 * @Version 1.0
 */
@Mapper
public interface PaymentInfoMapper {
    PaymentInfo getByOrderNo(String orderNo);

    void save(PaymentInfo paymentInfo);

    void updateById(PaymentInfo paymentInfo);
}
