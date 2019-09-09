package com.lx.benefits.essearch.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import com.lx.benefits.essearch.entity.EsProduct;

public interface EsProductService {

	void clearAll();

	EsProduct getById(Long spuCode);

	int importEsProduct(List<EsProduct> esProducts);

	// 创建商品
	EsProduct create(EsProduct esProduct);

	// 根据id删除商品
	void delete(Long spuCode);

	// 批量删除商品
	void delete(List<Long> ids);

	// 根据关键字搜索
	Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);

	Page<EsProduct> search(NativeSearchQueryBuilder queryBuilder, Integer pageNum, Integer pageSize);
}
