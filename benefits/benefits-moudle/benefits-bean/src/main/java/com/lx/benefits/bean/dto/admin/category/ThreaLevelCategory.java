package com.lx.benefits.bean.dto.admin.category;

import lombok.Data;

@Data
public class ThreaLevelCategory {
	/** 一级分类id */
	private Integer categoryId;
	/** 一级分类名称 */
	private String categoryName;
	/** 二级分类id */
	private Integer categoryId2;
	/** 二级分类名称 */
	private String categoryName2;
	/** 三级分类id */
	private Integer categoryId3;
	/** 三级分类名称 */
	private String categoryName3;
}
