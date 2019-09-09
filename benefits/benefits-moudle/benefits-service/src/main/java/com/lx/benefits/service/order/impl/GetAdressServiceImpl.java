package com.lx.benefits.service.order.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.dao.memconsigneeaddress.ConsignessAdressDao;
import com.lx.benefits.service.order.GetAdressService;
/**
 * User:lz
 * Date:2019/4/24
 * Time:15:37
 * Verision:2.x
 * Description:
 **/
@Service
public class GetAdressServiceImpl implements GetAdressService {
	@Resource
	private ConsignessAdressDao consignessAdressDao;
	/**
	 * 获得收货地址
	 * @param id
	 * @return
	 */
	public String getAddressById(Long id) {
		ConsigneeAdress consigneeAdress = consignessAdressDao.getAddressById(id);
		String province = consigneeAdress.getProvince();
		String city = consigneeAdress.getCity();
		String district = consigneeAdress.getDistrict();
		String town = consigneeAdress.getStreet();
		return province+city+district+(town!=null?town:"");
	}
} 
