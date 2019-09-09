package com.lx.benefits.web.controller.admin.jdmanagement;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.bo.product.TempProduct;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.jd.JDManagementService;

@RestController
@RequestMapping("/admin/jdManagement")
public class JDManagementController {

	@Autowired
	private JDManagementService jdManagementService;

	@GetMapping("/skuInfo")
	public Object getSkuInfo(@RequestParam String skuCode) {
		List<TempProduct> productDetails = jdManagementService.getSkuDetails(skuCode, false);
		if (CollectionUtils.isEmpty(productDetails)) {
			return Response.fail("未找到商品，请重新输入！");
		}
		TempProduct tempProduct = productDetails.get(0);
		SkuEntity skuEntity = new SkuEntity();
		skuEntity.setGoodsName(tempProduct.getGoodsName());
		skuEntity.setGoodsSpec(tempProduct.getGoodsSpec());
		skuEntity.setGoodsImage(tempProduct.getImagePath());
		skuEntity.setGoodsPrice(tempProduct.getGoodsPrice());
		return Response.succ(skuEntity);
	}

	@GetMapping("/goodsImport")
	public Object goodsImport(@RequestParam String skuCode, @RequestParam(defaultValue = "true") Boolean withSimilar) {
		int goodsImport = jdManagementService.goodsImport(skuCode, withSimilar);
		return Response.succ(Collections.singletonMap("importCount", goodsImport));
	}
}
