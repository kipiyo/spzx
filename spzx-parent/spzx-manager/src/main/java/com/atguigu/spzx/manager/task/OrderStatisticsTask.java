package com.atguigu.spzx.manager.task;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.atguigu.spzx.manager.mapper.OrderInfoMapper;
import com.atguigu.spzx.manager.mapper.OrderStatisticsMapper;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: OrderStatisticsTask
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/14 22:17
 * @Version 1.0
 */
@Component
@Slf4j
public class OrderStatisticsTask {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;


    //每天凌晨两点 查询前一天统计数据,把数据添加到统计表中
//    @Scheduled(cron = "0/10 * * * * ?")
    @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics(){
        String createTime = DateUtil.offsetDay(new Date(), -1).toString(new SimpleDateFormat("yyyy-MM-dd"));
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(createTime);
        if(orderStatistics != null){
            orderStatisticsMapper.insert(orderStatistics);
        }
    }

//    /**
//     *  @Scheduled + cron表达式
//     *  cron表达式设置执行规则
//     */
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void testH() {
////        System.out.println(new Date().toInstant());
//    }
}
