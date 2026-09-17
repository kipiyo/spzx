package com.atguigu.spzx.pay.service;

import com.atguigu.spzx.model.entity.pay.PaymentInfo;

import java.util.Map;

/**
 * ClassName: PaymentInfoService
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/17 15:04
 * @Version 1.0
 */
public interface PaymentInfoService {
    PaymentInfo savePaymentInfo(String orderNo);

    void updatePaymentStatus(Map<String, String> map, Integer payType);
}
