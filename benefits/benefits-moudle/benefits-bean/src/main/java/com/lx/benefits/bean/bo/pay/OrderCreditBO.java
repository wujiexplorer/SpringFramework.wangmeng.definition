package com.lx.benefits.bean.bo.pay;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCreditBO {

    /**
     * 积分所有者用户id,员工userId
     */
    private Long employeeId;

    /**
     * 积分类型{1: 普通积分; 2: 节日礼金; 3: 认可激励积分}
     */
    private Integer creditType;
    /**
     * 活动id
     */
    private Long campaignId;

    /**
     * 积分数额（包含会员卡积分）
     */
    private BigDecimal reduceCredit;
    /**
     * 会员卡积分数额
     */
    private BigDecimal cardAmount;

    /**
     * 支付级订单编号
     */
    private String payOrderNumber;

    /**
     * 操作类型{1: 运营积分充值; 2: 运营积分退款; 3: HR积分分配扣减企业积分; 4: HR积分回收增加企业积分; 5: HR积分分配员工增加积分; 6: HR积分回收员工扣减积分; 7: 员工下单扣积分; 8: 员工退货退款时退积分; }
     */
    private Integer optType;

    /**
     * 操作备注
     */
    private String remark;
}
