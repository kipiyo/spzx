package com.atguigu.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.atguigu.spzx.manager.mapper.OrderStatisticsMapper;
import com.atguigu.spzx.manager.service.OrderInfoService;
import com.atguigu.spzx.model.dto.order.OrderStatisticsDto;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import com.atguigu.spzx.model.vo.order.OrderStatisticsVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: OrderInfoServiceImpl
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/15 9:57
 * @Version 1.0
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Resource
    private OrderStatisticsMapper orderStatisticsMapper;
    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        //查询统计结果
        List<OrderStatistics> orderStatisticsList = orderStatisticsMapper.selectList(orderStatisticsDto);
        //遍历list 得到OrderStatistics的日期 用DateUtil变成字符串封装到新的list集合dateList   stream 流式操作
        List<String> dateList = orderStatisticsList.stream().map(OrderStatistics ->
                DateUtil.format(OrderStatistics.getOrderDate(), "yyyy-MM-dd")).collect(Collectors.toList());

        //统计金额列表
        List<BigDecimal> amountList =
                orderStatisticsList.stream().map(OrderStatistics::getTotalAmount).collect(Collectors.toList());

        //创建OrderStaticsVo对象封装响应结果数据
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo();
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);
        return orderStatisticsVo;
    }
}
