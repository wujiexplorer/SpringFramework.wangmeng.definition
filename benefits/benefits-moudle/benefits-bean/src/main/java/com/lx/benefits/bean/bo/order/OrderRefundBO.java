package com.lx.benefits.bean.bo.order;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 支付参数
 */
@Data
public class OrderRefundBO {

    /**订单支付时传入的商户订单号,不能和 trade_no同时为空*/
    private String payMark;
    /**需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数*/
    private BigDecimal refundAmount;
    /**退款的原因说明*/
    private String refundReason;
    /**标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传*/
    private String outRequestNo;
    /**商户的操作员编号*/
    private String operatorId;
    /**退款申请编号*/
    private Long refundApplyNumber;
    /**支付级订单支付总金额*/
    private BigDecimal totalFee;
    /**收款账号*/
    private String payAccount;

}
