package com.lx.benefits.bean.bo.order;

import lombok.Data;

@Data
public class SellerOrderReverseOverviewBO {

    /**全部订单*/
    private int allCount;
    /**退款成功订单（逆向状态2，4）*/
    private int overCount;
}
