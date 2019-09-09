package com.lx.benefits.service.seckill;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.entity.seckill.Seckill;
import com.lx.benefits.bean.entity.seckill.SeckillOrder;
import com.lx.benefits.bean.entity.seckill.SeckillOrderReq;
import com.lx.benefits.constant.MemberInfoConstant;

import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/8/20
 * Time:11:46
 * Version:2.x
 * Description:TODO
 **/
public interface SeckillService {
    /**
     * 新建秒杀
     * @param seckill
     * @return
     */
    public Seckill insertSeckill(Seckill seckill);

    /**
     * 查看详情
     * @param seckillId
     * @return
     */
    public Seckill getSeckill(Long seckillId);

    /**
     * 停止秒杀
     * @param seckill
     * @return
     */
    public Integer updateSeckill(Seckill seckill);

    /**
     * 秒杀管理列表
     * @param seckillName
     * @param status
     * @return
     */
    public PageResultBean<Seckill> findSeckills(String seckillName, Integer status, PageBean pageBean);

    /**
     * 查询参与秒杀的商品
     * @param params
     * @return
     */
    public PageResultBean<SkuEntity> findSkusBySeckillId(Map<String,Object> params);

    /**
     * 验证秒杀的有效性
     * @param seckill
     * @param flag
     * @return
     */
    public Boolean validateSeckillEffective(Seckill seckill, Boolean flag);

    /**
     * 查询秒杀有关的订单
     * @param seckillOrderReq
     * @return
     */
    public PageResultBean<SeckillOrder> selectOrderBySeckillId(SeckillOrderReq seckillOrderReq);

    /**
     * 秒杀结束后，回Sku库存
     * @param num
     */
    public void returnSkuStorageBySeckillEnd(Seckill seckill,Integer num);
}
