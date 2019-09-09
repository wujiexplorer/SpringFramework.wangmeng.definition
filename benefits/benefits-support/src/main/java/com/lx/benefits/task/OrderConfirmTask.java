package com.lx.benefits.task;

import com.alibaba.fastjson.JSON;
import com.apollo.common.bean.bo.BasePageQueryBO;
import com.apollo.common.enums.base.DatePatternEnum;
import com.apollo.common.lock.support.DistributedLock;
import com.apollo.common.utils.base.DateUtil;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.support.order.OrderSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 定时完结超过15天未确认收货订单
 */
@Slf4j
@Component
public class OrderConfirmTask {

    @Resource
    private OrderService orderService;
    @Resource
    private OrderSupport orderSupport;
    @Autowired
    private DistributedLock redisDistributedLock;

    /**
     * 定时完结超过15天未确认收货订单
     */
    @Scheduled(cron = "0 */15 * * * ?")
    public void cancelOvertimePaymentOrder() {
        redisDistributedLock.lock("cancelOvertimePaymentOrder",16*60*1000L,0);
        log.info("确认完结订单任务，任务开始时间：" + DateUtil.getDate(new Date(), DatePatternEnum.Y_M_D_SPACE_H_M_S.getPattern()));

        int maxHandleSize = 2000;
        int hasHandleSize = 0;
        //15天前
        Date currDate = DateUtil.getDateBefore(new Date(),15);
        Long minId = 0L;
        BasePageQueryBO pageQueryBO = new BasePageQueryBO();
        boolean isHasNext = true;
        while (isHasNext){
            List<Order> list =  orderService.listNeedOverOrder(currDate,minId,pageQueryBO.getStartRecord(),pageQueryBO.getPageSize());
            if(CollectionUtils.isEmpty(list) || hasHandleSize>maxHandleSize){
                isHasNext =false;
            }
            for (Order order : list) {
                minId = order.getId();
                try {

                    orderSupport.confirmOverOrder(order.getNumber());
                    log.info(String.format("确认完结订单任务,【确认完结订单成功】，订单编号:%s,订单信息：%s", order.getNumber(), JSON.toJSONString(order)));
                } catch (Exception e) {
                    log.error("确认完结订单任务,【确认完结订单失败】，订单编号:"+order.getNumber(),e);
                    log.info(String.format("确认完结订单任务,【确认完结订单失败】，订单编号:%s,订单信息：%s", order.getNumber(), JSON.toJSONString(order)));
                }

            }
            hasHandleSize +=list.size();
        }

        log.info("确认完结订单任务，任务完成时间：" + DateUtil.getDate(new Date(), DatePatternEnum.Y_M_D_SPACE_H_M_S.getPattern()));
        redisDistributedLock.releaseLock("cancelOvertimePaymentOrder");
    }
}
