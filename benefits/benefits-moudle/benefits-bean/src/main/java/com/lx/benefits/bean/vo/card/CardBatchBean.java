package com.lx.benefits.bean.vo.card;

import java.util.Date;

import lombok.Data;

@Data
public class CardBatchBean {

	private Integer batchId;// 生产批次ID
	private Long customerId;// 客户ID
	private String cutomerName;// 客户名称
	private Date orderCreateTime;// 开单时间
	private Integer totalAmount;// 会员卡总面值
	private Integer totalCount;// 会员卡总数量
	private Integer isCustomCard;// 是否是定制卡
	private Integer status;// 生产批次状态

}
