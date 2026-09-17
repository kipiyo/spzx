package com.atguigu.spzx.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: OrderInfoMapper
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/17 14:16
 * @Version 1.0
 */
@Mapper
public interface OrderInfoMapper {
    OrderInfo getByOrderNo(String orderNo) ;
    void save(OrderInfo orderInfo);

    OrderInfo getById(Long orderId);

    List<OrderInfo> findUserPage(Long userId, Integer orderStatus);

    void updateById(OrderInfo orderInfo);
}
