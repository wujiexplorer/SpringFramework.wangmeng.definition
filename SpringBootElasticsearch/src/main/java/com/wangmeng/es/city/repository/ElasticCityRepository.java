package com.wangmeng.es.city.repository;

import com.wangmeng.es.city.entity.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface  ElasticCityRepository extends ElasticsearchRepository<City,Long> {

}
