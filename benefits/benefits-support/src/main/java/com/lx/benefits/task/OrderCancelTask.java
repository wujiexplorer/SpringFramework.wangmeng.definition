package com.lx.benefits.task;

import com.alibaba.fastjson.JSON;
import com.apollo.common.bean.bo.BasePageQueryBO;
import com.apollo.common.enums.base.DatePatternEnum;
import com.apollo.common.lock.support.DistributedLock;
import com.apollo.common.utils.base.DateUtil;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.entity.seckill.Seckill;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.service.seckill.SeckillService;
import com.lx.benefits.support.order.OrderSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Slf4j
@Component
public class OrderCancelTask {

    @Resource
    private OrderService orderService;
    @Resource
    private OrderSupport orderSupport;
    @Autowired
    private DistributedLock redisDistributedLock;

    @Autowired
    private SeckillService seckillService;


    /**
     * 定时取消超时待支付订单(包括秒杀订单）
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void cancelOvertimePaymentOrder() {
        redisDistributedLock.lock("cancelOvertimePaymentOrder",6*60*1000L,0);
        log.info("取消未支付超时订单任务，任务开始时间：" + DateUtil.getDate(new Date(), DatePatternEnum.Y_M_D_SPACE_H_M_S.getPattern()));

        int maxHandleSize = 2000;
        int hasHandleSize = 0;
        //15分钟前
        Date currDate = DateUtil.getSomeMinsBefore(-15);
        Long minId = 0L;
        BasePageQueryBO pageQueryBO = new BasePageQueryBO();
        boolean isHasNext = true;
        while (isHasNext){
            List<Order> list =  orderService.listOvertimePaymentOrder(currDate,minId,pageQueryBO.getStartRecord(),pageQueryBO.getPageSize());
            if(CollectionUtils.isEmpty(list) || hasHandleSize>maxHandleSize){
                isHasNext =false;
            }
            for (Order order : list) {
                minId = order.getId();
                try {

                    orderSupport.cancelOrder(order.getNumber(), null, true);
                    log.info(String.format("取消未支付超时订单任务,【取消订单成功】，订单编号:%s,订单信息：%s", order.getNumber(), JSON.toJSONString(order)));
                } catch (Exception e) {
                    log.error("取消未支付超时订单任务,【取消订单失败】，订单编号:"+order.getNumber(),e);
                    log.info(String.format("取消未支付超时订单任务,【取消订单失败】，订单编号:%s,订单信息：%s", order.getNumber(), JSON.toJSONString(order)));
                }

            }
            hasHandleSize +=list.size();
        }
        maxHandleSize = 2000;
        hasHandleSize = 0;
        isHasNext = true;
        minId = 0L;
        currDate = DateUtil.getSomeMinsBefore(-5);
        while (isHasNext){
            List<Order> list =  orderService.listOvertimePaymentSeckillOrder(currDate,minId,pageQueryBO.getStartRecord(),pageQueryBO.getPageSize());
            if(CollectionUtils.isEmpty(list) || hasHandleSize>maxHandleSize){
                isHasNext =false;
            }
            for (Order order : list) {
                minId = order.getId();
                    try {
                        orderSupport.cancelOrder(order.getNumber(), null, true);
                        log.info(String.format("取消未支付超时订单任务,【取消订单成功】，订单编号:%s,订单信息：%s", order.getNumber(), JSON.toJSONString(order)));
                    } catch (Exception e) {
                        log.error("取消未支付超时订单任务,【取消订单失败】，订单编号:"+order.getNumber(),e);
                        log.info(String.format("取消未支付超时订单任务,【取消订单失败】，订单编号:%s,订单信息：%s", order.getNumber(), JSON.toJSONString(order)));
                    }
            }
            hasHandleSize +=list.size();
        }

        log.info("取消未支付超时订单任务，任务完成时间：" + DateUtil.getDate(new Date(), DatePatternEnum.Y_M_D_SPACE_H_M_S.getPattern()));
        redisDistributedLock.releaseLock("cancelOvertimePaymentOrder");
    }
}
