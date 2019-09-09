package com.lx.benefits.bean.dto.admin.operation;

import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.product.ProductEntity;

import lombok.Data;

@Data
public class ActivityThemeProducts {

	private Integer id;
	private String themeName;
	private Integer showType;
	private String themeUrl;
	private Integer topicId;
	PageResultBean<ProductEntity> products;
}
