package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: OrderInfoMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 22:27
 * @Version 1.0
 */
@Mapper
public interface OrderInfoMapper {
    OrderStatistics selectOrderStatistics(String createTime);
}
