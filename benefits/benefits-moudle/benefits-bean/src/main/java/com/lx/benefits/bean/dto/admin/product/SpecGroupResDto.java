package com.lx.benefits.bean.dto.admin.product;

import lombok.Data;

/**
 * 规格分组 表 basis_spec_group
 * 
 * @author ruoyi
 * @date 2019-01-30
 */
@Data
public class SpecGroupResDto {
	
	/** 规格分组编号 */
	private Long groupId;
	/** 规格组名称 */
	private String groupName;
	/** 备注 */
	private String reamrk;
	/** 状态 状态 0-无效 1-有效 */
	private Integer status;

}
