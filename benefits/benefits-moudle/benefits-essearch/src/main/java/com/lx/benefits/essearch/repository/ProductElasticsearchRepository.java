package com.lx.benefits.essearch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.lx.benefits.essearch.entity.EsProduct;

@Repository
public interface ProductElasticsearchRepository extends ElasticsearchRepository<EsProduct, Long> {

	Page<EsProduct> findByGoodsNameOrKeywords(String goodsName, String keywords, Pageable pageable);
}
