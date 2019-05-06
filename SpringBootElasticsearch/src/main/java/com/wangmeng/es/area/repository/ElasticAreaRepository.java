package com.wangmeng.es.area.repository;


import com.wangmeng.es.area.entity.Area;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface  ElasticAreaRepository extends ElasticsearchRepository<Area,Integer	> {

}
