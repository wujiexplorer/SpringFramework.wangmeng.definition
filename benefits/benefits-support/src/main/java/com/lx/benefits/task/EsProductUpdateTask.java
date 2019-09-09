package com.lx.benefits.task;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.bo.product.EsProductBean;
import com.lx.benefits.essearch.entity.EsProduct;
import com.lx.benefits.essearch.service.EsProductService;
import com.lx.benefits.service.product.ProductService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EsProductUpdateTask {

	@Autowired
	private EsProductService esProductService;
	@Autowired
	private ProductService productService;

	@Scheduled(cron = "0 0 3 ? * SAT")
	public int esProductUpdateTask() {
		log.info("begin flush esProduct at {}", new Date());
		esProductService.clearAll();
		PageBean pageBean = new PageBean() {
			@Override
			public Integer getPageSize() {
				return 1000;
			}
		};
		int result = 0;
		for (int page = 1; page < 500; page++) {// 500*1000=500000（50万），防止死循环
			pageBean.setPage(page);
			List<EsProductBean> esProductBeans = productService.getEsProductBeansByPage(pageBean);
			if (CollectionUtils.isEmpty(esProductBeans)) {
				break;
			}
			result += esProductService.importEsProduct(esProductBeans.parallelStream().map(item -> {
				EsProduct esProduct = new EsProduct();
				BeanUtils.copyProperties(item, esProduct);
				return esProduct;
			}).collect(Collectors.toList()));
		}
		log.info("end flush esProduct at {}", new Date());
		return result;
	}
}
