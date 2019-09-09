package com.lx.benefits.mapper.seckill;

import com.lx.benefits.bean.entity.seckill.SeckillOrder;
import com.lx.benefits.bean.entity.seckill.SeckillOrderReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User:wangmeng
 * Date:2019/8/23
 * Time:11:02
 * Version:2.x
 * Description:TODO
 **/
public interface SeckillOrderMapper {

    public List<SeckillOrder> selectOrderBySeckillId(SeckillOrderReq seckillOrderReq);

    public Integer countOrderBySeckillId(SeckillOrderReq seckillOrderReq);
}
