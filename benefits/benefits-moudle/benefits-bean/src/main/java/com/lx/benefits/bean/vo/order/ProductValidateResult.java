package com.lx.benefits.bean.vo.order;

import java.util.List;

import lombok.Data;

@Data
public class ProductValidateResult {

	private List<ProductMsg> yxRestrictSkus;
	private List<ProductMsg> jdRestrictSkus;
}
