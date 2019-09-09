package com.lx.benefits.web.controller.admin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.bo.product.EsProductBean;
import com.lx.benefits.essearch.entity.EsProduct;
import com.lx.benefits.essearch.service.EsProductService;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.task.EsProductUpdateTask;

@RestController
@RequestMapping("/admin/esproduct/search")
public class ProductEsSearchController {

	@Autowired
	private EsProductService esProductService;
	@Autowired
	private ProductService productService;
	@Autowired
	private EsProductUpdateTask esProductUpdateTask;

	@GetMapping("/importAll")
	public Object importAll() {
		int count=esProductUpdateTask.esProductUpdateTask();
		return Collections.singletonMap("importCount", count);
	}

	@GetMapping("/import/{spuCode}")
	public Object test(@PathVariable Long spuCode) {
		List<EsProductBean> esProductBeans =  productService.getEsProductBeansByIds(Arrays.asList(spuCode));
		for (EsProductBean item : esProductBeans) {
			EsProduct esProduct = new EsProduct();
			BeanUtils.copyProperties(item, esProduct);
			esProductService.create(esProduct);
		}
		return Collections.singletonMap("importCount", esProductBeans.size());
	}

	@PostMapping("/batchImport")
	public Object add(@RequestBody List<Long> spuCodes) {
		int importCount = 0;
		if (!CollectionUtils.isEmpty(spuCodes)) {
			List<EsProductBean> esProductBeans = productService.getEsProductBeansByIds(spuCodes);
			for (EsProductBean item : esProductBeans) {
				EsProduct esProduct = new EsProduct();
				BeanUtils.copyProperties(item, esProduct);
				esProductService.create(esProduct);
			}
			importCount = esProductBeans.size();
		}
		return Collections.singletonMap("importCount", importCount);
	}

}
