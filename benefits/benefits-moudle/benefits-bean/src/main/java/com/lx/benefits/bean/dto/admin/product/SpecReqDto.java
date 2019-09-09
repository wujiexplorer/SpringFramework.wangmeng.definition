package com.lx.benefits.bean.dto.admin.product;

import com.lx.benefits.bean.base.dto.FLPageDto;
import lombok.Data;

/**
 * 规格 表 basis_spec
 * 
 * @author ruoyi
 * @date 2019-01-30
 */
@Data
public class SpecReqDto extends FLPageDto {
	
	/** 规格id */
	private Long specId;
	/** 规格编号 */
	private String specCode;
	/** 规格 */
	private String specName;
	/** 规格值 */
	private String specVal;
	/** 规格分组 */
	private String groupName;
	/** 规格排序 */
	private Integer specSort;
	/** 备注 */
	private String remark;
	/** 状态 状态 0-无效 1-有效 */
	private Integer status;
	/** 库存 */
	private Integer stock;

}
