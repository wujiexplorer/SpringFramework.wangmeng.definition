package com.lx.benefits.bean.vo.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User:wangmeng
 * Date:2019/7/15
 * Time:16:15
 * Version:2.x
 * Description:TODO
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBankVO implements Serializable {

    private static final long serialVersionUID = -7476334054158380653L;
    private Long parentNumber;

    private Long number;

    private String thirdOrderNumber;

    private Integer status;

    private Date createTime;

    private String enterprName;

    private String goodsName;

    private Integer quantity;

    private String supplierName;

    private String categoryName;

    private String employeeName;

    private String serialNumber;


    private BigDecimal price;

    private BigDecimal goodsPrice;

    private BigDecimal goodsCostprice;

    private BigDecimal totalCostPrice;

    private Integer skuCode;

    private BigDecimal shipExpense;

    private BigDecimal payableSum;

    private Long payNumber;

    private BigDecimal pointAmount;

    private BigDecimal cardAmount;

    private BigDecimal cardRefundAmount;

    private BigDecimal thirdCostprice;

    private Integer channel;

    private String settleNumber;

    private Integer reverseStatus;

    private Date successTime;

    private BigDecimal realMoney;

    private BigDecimal returnAccountPoint;

    private String agentName;

    private Integer agentId;

    private String employeeNo;
    //优惠卷优惠的金额（含拆分）
    private BigDecimal voucherBenefit;
}
