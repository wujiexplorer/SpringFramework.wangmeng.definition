package com.lx.benefits.bean.dto.admin.category;

import lombok.Data;

import java.util.Date;

/**
 * 分类 商品分类表 basis_category
 * 
 * @author gaosong
 * @date 2019-01-29
 */
@Data
public class CategoryReqDto  {

	/** 分类id */
	private Long id;

	/** 分类名称 */
	private String name;

	/** 分类英文名称 */
	private String nameEn;

	/** 父id */
	private Long parentId;

	/** 级别 */
	private Integer level;

	/** 类型 */
	private Integer type;

	/** 创建时间 */
	private Date createdTime;


}
