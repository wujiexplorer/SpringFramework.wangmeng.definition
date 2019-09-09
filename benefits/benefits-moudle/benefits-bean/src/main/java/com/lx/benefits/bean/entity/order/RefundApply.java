package com.lx.benefits.bean.entity.order;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* @ClassName: RefundApply
* @Description:
* @author wind
* @date 2019-3-5
*/
@Data
public class RefundApply implements Serializable{
	
    /**
     *主键
     */
	private Long id;
    /**
     *售后申请编号
     */
	private Long number;
    /**
     *同一批次申请编号
     */
	private Long batchNumber;
    /**
     *申请原因id
     */
	private Integer refundApplyProblemId;
    /**
     *账号id
     */
	private Long accountId;
    /**
     *商家级订单编号
     */
	private Long sellerOrderNumber;
    /**
     *商品级订单编号
     */
	private Long productOrderNumber;
    /**
     *订单类型
     */
	private Integer orderType;
    /**
     *订单支付交易号,order_pay
     */
	private String orderPayMark;
    /**
     *商家id
     */
	private Integer sellerId;
    /**
     *真实商家名称
     */
	private String sellerName;
    /**
     *商品申请金额 （单位：分）
     */
	private Long applyMoney;
    /**
     *商品实际退款金额
     */
	private Long realMoney;
    /**
     *申请退的邮费（单位：分）
     */
	private Long applyPostageMoney;
    /**
     *实际退的邮费（单位：分）
     */
	private Long realPostageMoney;
    /**
     *理论返还积分
     */
	private Integer returnAccountPointApply;
    /**
     *实际返还积分
     */
	private Integer returnAccountPoint;
    /**
     *退款件数
     */
	private Integer count;
    /**
     *清关费用
     */
	private BigDecimal customsClearancePrice;
    /**
     *退款账号类型, 1支付宝,2微信，3原路退回
     */
	private Integer refundPayType;
    /**
     *退款卡号
     */
	private String refundPayNumber;
    /**
     *退款账号人姓名
     */
	private String refundPayName;
    /**
     *订单付款渠道  0.积分，1支付宝,2微信
     */
	private Integer orderPayChannel;
    /**
     *用户备注
     */
	private String accountRemark;
    /**
     *客服备注
     */
	private String customerRemark;
    /**
     *财务备注
     */
	private String financialRemark;
    /**
     *退款方式,1 只退钱; 2 只退积分
     */
	private Integer refundType;
    /**
     *状态：1、申请中，2、待退货，3、待退款、4.退款拒绝 、5.退款成功、6.退款取消
     */
	private Integer status;
    /**
     *状态： 0 初始，1 已打款待确认 2 打款成功 3 打款失败
     */
	private Integer payStatus;
    /**
     *订单付款账户id
     */
	private String payAccoutId;
    /**
     *图片地址：json存储
     */
	private String pics;
    /**
     *是否用户取消, 1是,0否
     */
	private Integer isUserCancel;
    /**
     *申请单成功时间
     */
	private Date successTime;
    /**
     *结算单号
     */
	private Long settleNumber;
    /**
     *是否删除, 0否,1是
     */
	private Integer isDelete;
    /**
     *创建时间
     */
	private Date createTime;
    /**
     *更新时间
     */
	private Date updateTime;


}
