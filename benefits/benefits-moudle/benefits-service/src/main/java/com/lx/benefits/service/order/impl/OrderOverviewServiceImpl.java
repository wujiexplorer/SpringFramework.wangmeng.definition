package com.lx.benefits.service.order.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.vo.order.OrderOverviewReq;
import com.lx.benefits.bean.vo.order.OrderOverviewVO;
import com.lx.benefits.mapper.order.OrderOverviewMapper;
import com.lx.benefits.service.order.OrderOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/7/12
 * Time:15:11
 * Version:2.x
 * Description:TODO
 **/

@Service
public class OrderOverviewServiceImpl implements OrderOverviewService {

    @Autowired
    private OrderOverviewMapper orderOverviewMapper;

    @Override
    public OrderOverviewVO getTodayOrderStatistic() {

        try{
            OrderOverviewVO orderOverviewVO = orderOverviewMapper.getTodayOrderStatistic();
            return orderOverviewVO;
        }catch(Exception e){
            throw new RuntimeException("获取今天的订单数据统计出错！",e);
        }
    }

    @Override
    public OrderOverviewVO getTodayRefundOrderStatistic() {
        try{
            return orderOverviewMapper.getTodayRefundOrderStatistic();
        }catch (Exception e){
            throw new RuntimeException("获取今天的退款订单统计出错！",e);
        }
    }

    @Override
    public OrderOverviewVO getPreDay7OrderStatistic() {

        try{
            OrderOverviewVO orderOverviewVO = orderOverviewMapper.getPreDay7OrderStatistic();
            return orderOverviewVO;
        }catch (Exception e){
            throw new RuntimeException("获取前七天的订单数据统计出错！",e);
        }
    }

    @Override
    public OrderOverviewVO getPreDay7RefundOrderStatistic() {
        try{
            return orderOverviewMapper.getPreDay7RefundOrderStatistic();
        }catch (Exception e){
            throw new RuntimeException("获取前七天的退款订单数据出错！",e);
        }
    }

    @Override
    public Integer getOrderCountStatistic() {

        try{
            Integer count = orderOverviewMapper.getOrderCountStatistic();
            return count;
        }catch (Exception e){
            throw new RuntimeException("获取今天订单总数出错！",e);
        }
    }

    @Override
    public Integer getRefundOrderCountStatistic() {
        try{
            return orderOverviewMapper.getRefundOrderCountStatistic();
        }catch (Exception e){
            throw new RuntimeException("获取今天退款订单总数出错！",e);
        }
    }

    @Override
    public OrderOverviewVO getYesterdayOrderStatistic() {
        try{
            OrderOverviewVO orderOverviewVO = orderOverviewMapper.getYesterdayOrderStatistic();
            return orderOverviewVO;
        }catch (Exception e){
            throw new RuntimeException("获取昨天订单数据统计出错！",e);
        }
    }

    @Override
    public OrderOverviewVO getYesterdayRefundOrderStatistic() {
        try{
            return orderOverviewMapper.getYesterdayRefundOrderStatistic();
        }catch (Exception e){
            throw new RuntimeException("获取昨天的退款订单数据统计出错！",e);
        }
    }

    @Override
    public List<OrderOverviewVO> getOrderHourStatistic() {
        try{
            List<OrderOverviewVO> list = orderOverviewMapper.getOrderHourStatistic();
            return list;
        }catch (Exception e){
            throw new RuntimeException("获取今日订单统计出错！",e);
        }
    }

}
