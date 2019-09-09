package com.lx.benefits.bean.dto.admin.product;

import lombok.Data;

/**
 * 规格 表 basis_spec
 * 
 * @author ruoyi
 * @date 2019-01-30
 */
@Data
public class SpecResDto{
	
	/** 规格id */
	private Long specId;
	/** 规格编号 */
	private String specCode;
	/** 规格 */
	private String specName;
	/** 状态 状态 0-无效 1-有效 */
	private Integer status;

}
