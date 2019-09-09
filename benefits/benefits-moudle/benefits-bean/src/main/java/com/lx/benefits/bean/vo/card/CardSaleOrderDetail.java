package com.lx.benefits.bean.vo.card;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.lx.benefits.bean.entity.card.CardSaleOrderItem;

import lombok.Data;

@Data
public class CardSaleOrderDetail {

	private Integer saleOrderId;
	@NotNull(message = "客户不能为空")
	private Long customerId;
	private String customerName;
	@NotNull(message = "企业不能为空")
	private Long bindEnterprId;
	private String bindEnterprName;
	private Byte isCustomCard;
	@NotNull(message = "付款方式不能为空")
	private Integer payType;
	@Max(value = 1, message = "折扣不能大于100%")
	@Min(value = 0, message = "折扣不能小于0%")
	private BigDecimal discountOnSale;
	private BigDecimal sumPayable;
	private Integer totalCount;
	private Long totalAmount;
	@NotEmpty(message = "会员卡列表不能为空")
	private List<CardSaleOrderItem> cards;
	private String createUser;
	private Date createTime;
	private Integer status;
	private BigDecimal paidAmount;
	private BigDecimal unpaidAmount;
	private String verifyUser;
	private String verifyInfo;
	private Integer batchStatus;//会员卡批次状态
}
