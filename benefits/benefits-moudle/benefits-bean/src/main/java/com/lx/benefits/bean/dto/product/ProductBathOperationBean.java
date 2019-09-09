package com.lx.benefits.bean.dto.product;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.lx.benefits.bean.dto.admin.product.ProductQueryParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductBathOperationBean extends ProductQueryParam{
	@NotNull(message="参数错误")
	private Integer type;
	private String  spuCodes;
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
