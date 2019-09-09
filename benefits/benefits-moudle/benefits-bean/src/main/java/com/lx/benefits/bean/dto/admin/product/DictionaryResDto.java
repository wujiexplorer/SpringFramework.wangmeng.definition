package com.lx.benefits.bean.dto.admin.product;

import lombok.Data;


/**
 * 商品产地表 basis_place_origin
 * 
 * @author ruoyi
 * @date 2019-01-30
 */
@Data
public class DictionaryResDto {
	
	/** 自增id */
	private Long id;
	/** 中文名 */
	private String name;
	/** 英文名 */
	private String nameEn;
//	/** 排序 */
//	private Integer sort;
//	/** 状态 状态 0-无效 1-有效 */
//	private Integer status;
//	/** 更新时间 */
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//	private Date createdTime;


}
