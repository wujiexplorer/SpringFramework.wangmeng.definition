package com.lx.benefits.bean.bo.order;

import lombok.Data;

/**
 * 支付参数
 */
@Data
public class OrderPayBO {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 支付级订单编号
     */
    private Long payOrderNumber;
    /**
     * 支付payMark
     */
    private String payMark;
    /**
     * 实付金额
     */
    private Long realPrice;
    /**
     * 支付渠道
     */
    private Integer payChannel;
    /**
     * 请求ip
     */
    private String ip;
    /**
     * openID
     */
    private String openId;
    /**
     * 是否web
     */
    private boolean fromWeb;

}
