package com.lx.benefits.bean.vo.cardkey;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class CardKeyVO {
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date storeStartTime;//入库起始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date storeEndTime;//入库结束时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deliverStartTime;//发货起始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deliverEndTime;//发货结束时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deadStartTime;//截止开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deadEndTime;//截止结束时间
	private Integer status;//卡密状态
	private Long orderNumber;//商品级订单号
}
