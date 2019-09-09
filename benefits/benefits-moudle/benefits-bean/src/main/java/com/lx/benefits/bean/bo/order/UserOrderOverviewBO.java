package com.lx.benefits.bean.bo.order;

import lombok.Data;

@Data
public class UserOrderOverviewBO {

    /**全部订单*/
    private int allCount;
    /**待支付订单数*/
    private int unPayCount;
    /**待发货订单数*/
    private int payCount;
    /**已发货订单数*/
    private int sendCount;
    /**完成订单数*/
    private int completeCount;
    /**退款数*/
    private int refundCount;
}
