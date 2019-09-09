package com.lx.benefits.service.seckill;

import com.lx.benefits.bean.entity.seckill.SeckillOrder;
import com.lx.benefits.bean.entity.seckill.SeckillOrderReq;

import java.util.List;

/**
 * User:wangmeng
 * Date:2019/8/23
 * Time:13:14
 * Version:2.x
 * Description:TODO
 **/
public interface SeckillOrderService {
    /**
     * 查询某个秒杀下所有订单
     * @param seckillOrderReq
     * @return
     */
    public List<SeckillOrder> selectOrderBySeckillId(SeckillOrderReq seckillOrderReq);

    /**
     * 统计某个秒杀下所有订单数
     * @param seckillOrderReq
     * @return
     */
    public Integer countOrderBySeckillId(SeckillOrderReq seckillOrderReq);
}
