package com.lx.benefits.mapper.order;

import com.lx.benefits.bean.vo.order.OrderOverviewReq;
import com.lx.benefits.bean.vo.order.OrderOverviewVO;

import java.util.Date;
import java.util.List;

/**
 * User:wangmeng
 * Date:2019/7/12
 * Time:14:02
 * Version:2.x
 * Description:TODO
 **/
public interface OrderOverviewMapper {

    public OrderOverviewVO getTodayOrderStatistic();

    public OrderOverviewVO getTodayRefundOrderStatistic();

    public OrderOverviewVO getPreDay7OrderStatistic();

    public OrderOverviewVO getPreDay7RefundOrderStatistic();

    public Integer getOrderCountStatistic();

    public Integer getRefundOrderCountStatistic();

    public OrderOverviewVO getYesterdayOrderStatistic();

    public OrderOverviewVO getYesterdayRefundOrderStatistic();

    public List<OrderOverviewVO> getOrderHourStatistic();
}
