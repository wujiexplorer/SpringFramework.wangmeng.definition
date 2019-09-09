package com.lx.benefits.service.seckill.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.seckill.SeckillOrder;
import com.lx.benefits.bean.entity.seckill.SeckillOrderReq;
import com.lx.benefits.mapper.seckill.SeckillOrderMapper;
import com.lx.benefits.service.seckill.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User:wangmeng
 * Date:2019/8/23
 * Time:13:15
 * Version:2.x
 * Description:TODO
 **/
@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;


    @Override
    public List<SeckillOrder> selectOrderBySeckillId(SeckillOrderReq seckillOrderReq) {
        if(seckillOrderReq == null){
            throw new BusinessException("秒杀参数信息不能为空！");
        }
        return seckillOrderMapper.selectOrderBySeckillId(seckillOrderReq);
    }

    @Override
    public Integer countOrderBySeckillId(SeckillOrderReq seckillOrderReq) {
        if(seckillOrderReq == null){
            throw new BusinessException("秒杀参数信息不能为空！");
        }
        return seckillOrderMapper.countOrderBySeckillId(seckillOrderReq);
    }
}
