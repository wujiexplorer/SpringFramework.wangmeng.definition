package com.lx.benefits.bean.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* @ClassName: RefundApplyVO
* @Description:
* @author wind
* @date 2019-3-1
*/
@Data
public class RefundApplyVO implements Serializable{
	
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

	private String refundApplyProblem;
    /**
     *商家级订单编号
     */
	private Long sellerOrderNumber;
    /**
     *商品级订单编号
     */
	private Long productOrderNumber;
    /**
     *商家id
     */
	private Integer sellerId;
    /**
     *真实商家名称
     */
	private String sellerName;
    /**
     *商品实际退款金额（单位：元）
     */
	private BigDecimal realMoney;
    /**
     *实际退的邮费（单位：元）
     */
	private BigDecimal realPostageMoney;
    /**
     *实际返还积分（单位：元）
     */
	private BigDecimal returnAccountPoint;
    /**
     *用户备注
     */
	private String accountRemark;
    /**
     *客服备注
     */
    private String customerRemark;
    /**
     *状态：1、申请中，2、待退货，3、待退款、4.退款拒绝 、5.退款成功、6.退款取消
     */
	private Integer status;

    /**
     *状态：1、申请中，2、待退货，3、待退款、4.退款拒绝 、5.退款成功、6.退款取消
     */
    private String statusDesc;
    /**
     *申请单成功时间
     */
    @JsonFormat(pattern="YYYY-MM-dd HH:mm:ss")
	private Date successTime;
    /**
     *创建时间
     */
    @JsonFormat(pattern="YYYY-MM-dd HH:mm:ss")
	private Date createTime;
	/**************************/
    /**
     * 退款总金额 包含邮费，积分（单位：元）
     */
    private BigDecimal totalPrice;

	/***********/
    /** skuID */
    private Long skuId;
    /** 商品图片 */
    private String goodsImg;
    /** 商品名称 */
    private String title;
    /** 商品英文名称 */
    private String titleEn;
    /** 商品规格 */
    private String goodsSpec;
    /**退款图片*/
    private List<String> picList;

    /**************************/
    /**
     *物流公司
     */
    private String logisticsChannel;
    /**
     *物流编号
     */
    private String logisticsNumber;

}
