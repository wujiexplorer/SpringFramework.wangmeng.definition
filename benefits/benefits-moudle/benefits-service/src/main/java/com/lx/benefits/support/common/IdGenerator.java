package com.lx.benefits.support.common;

import com.apollo.common.annotation.RedisLock;
import com.lx.benefits.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Slf4j
@Component
public class IdGenerator {

    /**
     * 生成一个唯一的订单id
     * @param level 0：支付级订单 1：店铺级订单 2：商品级订单
     * @return
     */
    @RedisLock(name = "generateOrderNumber")
    public synchronized Long generateOrderNumber(int level) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
        Calendar curr = Calendar.getInstance();
        StringBuffer suffix = new StringBuffer(
                ((curr.get(Calendar.HOUR_OF_DAY) * 60 * 60 * 1000) + (curr.get(Calendar.MINUTE) * 60 * 1000) + (curr.get(Calendar.SECOND) * 1000) + curr.get(Calendar.MILLISECOND))
                        + "");
        int len = suffix.toString().length();
        if (len >= 0 && len < 8) {
            int size = 8 - len;
            for (int i = 0; i < size; i++)
                suffix.insert(0, "0");
        }
        return Long.valueOf(CommonUtil.date2String(curr.getTime(), "yyMMdd") + suffix + level);
    }


    /**
     * 生成一个唯一的退款id
     */
    @RedisLock(name = "generateRefundNumber")
    public synchronized Long generateRefundNumber() {

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
        Calendar curr = Calendar.getInstance();

        StringBuilder suffix = new StringBuilder(
                ((curr.get(Calendar.HOUR_OF_DAY) * 60 * 60 * 1000) + (curr.get(Calendar.MINUTE) * 60 * 1000) + (curr.get(Calendar.SECOND) * 1000) + curr.get(Calendar.MILLISECOND))
                        + "");
        int len = suffix.toString().length();
        if (len >= 0 && len < 8) {
            int size = 8 - len;
            for (int i = 0; i < size; i++)
                suffix.insert(0, "0");
        }
        return Long.valueOf(CommonUtil.date2String(curr.getTime(), "yyyyMMdd").substring(2) + suffix + "0");
    }
}
