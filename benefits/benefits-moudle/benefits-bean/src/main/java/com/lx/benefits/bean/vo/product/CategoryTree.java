package com.lx.benefits.bean.vo.product;

import java.util.List;

import lombok.Data;

@Data
public class CategoryTree {
	private Integer categoryId;
	private String categoryName;
	private String nameEn;
	private Integer parentId;
	private List<CategoryTree> childs;
}
