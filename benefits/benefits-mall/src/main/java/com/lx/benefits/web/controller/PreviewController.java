package com.lx.benefits.web.controller;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.dto.enterprise.GoodsInfoResDto;
import com.lx.benefits.bean.dto.spec.ProductBean;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.product.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/preview/goods")
public class PreviewController {
	@Resource
	private ProductService productService;
	@ApiOperation(value = "预览企业商品详细信息", response = GoodsInfoResDto.class)
	@GetMapping("/detail/{spuCode}")
	public Object detail(@ApiParam(value = "商品标识id", name = "spuCode") @PathVariable Long spuCode) {
		ProductBean productSpecBean = productService.getPCProductSpecBean(spuCode);
		return Response.succ(productSpecBean);
	}
}
