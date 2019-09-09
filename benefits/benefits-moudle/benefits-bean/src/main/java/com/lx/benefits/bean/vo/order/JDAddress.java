package com.lx.benefits.bean.vo.order;

import lombok.Data;

@Data
public class JDAddress {
	private Long provinceId;
	private Long cityId;
	private Long countyId;
	private Long townId = 0L;// 默认为0

}
