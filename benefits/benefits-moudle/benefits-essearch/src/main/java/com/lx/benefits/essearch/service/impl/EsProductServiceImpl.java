package com.lx.benefits.essearch.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lx.benefits.essearch.entity.EsProduct;
import com.lx.benefits.essearch.repository.ProductElasticsearchRepository;
import com.lx.benefits.essearch.service.EsProductService;

@Service
public class EsProductServiceImpl implements EsProductService {

	@Autowired
	private ProductElasticsearchRepository esProductRepository;

	@Override
	public void clearAll() {
		esProductRepository.deleteAll();
	}

	@Override
	public EsProduct getById(Long spuCode) {
		Optional<EsProduct> result = esProductRepository.findById(spuCode);
		return result.orElse(null);
	}

	@SuppressWarnings("unused")
	@Override
	public int importEsProduct(List<EsProduct> esProducts) {
		Iterable<EsProduct> results = esProductRepository.saveAll(esProducts);
		int count = 0;
		for (EsProduct item : results) {
			count++;
		}
		return count;
	}

	@Override
	public EsProduct create(EsProduct esProduct) {
		return esProductRepository.save(esProduct);
	}

	@Override
	public void delete(Long spuCode) {
		esProductRepository.deleteById(spuCode);
	}

	@Override
	public void delete(List<Long> ids) {
		if (!CollectionUtils.isEmpty(ids)) {
			esProductRepository.deleteAll(ids.stream().map(item -> new EsProduct(item)).collect(Collectors.toList()));
		}
	}

	@Override
	public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		return esProductRepository.findByGoodsNameOrKeywords(keyword, keyword, pageable);
	}

	@Override
	public Page<EsProduct> search(NativeSearchQueryBuilder queryBuilder, Integer pageNum, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		// Caused by:
		// org.elasticsearch.search.query.QueryPhaseExecutionException: Result
		// window is too large, from + size must be less than or equal to:
		// [10000] but was [100000]. See the scroll api for a more efficient way
		// to request large data sets. This limit can be set by changing the
		// [index.max_result_window] index level setting.
		if (pageable.getOffset() > 10000) {
			return Page.empty();
		}
		queryBuilder.withPageable(pageable);
		return esProductRepository.search(queryBuilder.build());
	}

}
