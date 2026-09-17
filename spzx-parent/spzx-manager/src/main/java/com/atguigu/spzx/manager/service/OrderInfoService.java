package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.order.OrderStatisticsDto;
import com.atguigu.spzx.model.vo.order.OrderStatisticsVo;

/**
 * ClassName: OrderInfoService
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/15 9:57
 * @Version 1.0
 */
public interface OrderInfoService {
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
