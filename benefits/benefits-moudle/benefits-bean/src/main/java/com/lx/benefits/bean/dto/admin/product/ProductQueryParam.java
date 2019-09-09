package com.lx.benefits.bean.dto.admin.product;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProductQueryParam {
	private Long spuCode;
	private String goodsName;
	private String goodsNameEn;
	private Long brandId;
	private Long categoryId;
	private Long categoryId2;
	private Long categoryId3;
	private Integer goodsState;
	private Long supplierId;
	private Integer isCross;
	private Integer goodsType;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	private Integer sex;
	private List<Long> spuCodeList;
	private Integer updatedTimeSort;//按更新时间  0.降序排序 1.升序排序
}
