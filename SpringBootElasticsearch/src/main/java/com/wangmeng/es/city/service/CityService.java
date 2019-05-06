package com.wangmeng.es.city.service;


import com.wangmeng.es.city.entity.City;

public interface CityService {
	/**
	 * 新增城市
	 * @param log
	 * @return
	 */
	void saveCity(City city);
   
	void getNearbyCities(double lat, double lon);
}
