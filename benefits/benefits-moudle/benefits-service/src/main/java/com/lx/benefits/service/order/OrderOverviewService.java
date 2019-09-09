package com.lx.benefits.service.order;

import com.lx.benefits.bean.vo.order.OrderOverviewReq;
import com.lx.benefits.bean.vo.order.OrderOverviewVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/7/12
 * Time:15:10
 * Version:2.x
 * Description:TODO
 **/
public interface OrderOverviewService {

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
