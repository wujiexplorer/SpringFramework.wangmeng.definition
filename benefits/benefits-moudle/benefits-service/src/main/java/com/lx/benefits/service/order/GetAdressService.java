package com.lx.benefits.service.order;

import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;

/**
 * User:lz
 * Date:2019/4/24
 * Time:15:35
 * Verision:2.x
 * Description:
 **/
public interface GetAdressService {
	/**
	 * 获得收货地址
	 * @param id
	 * @return
	 */
	public String getAddressById(Long id);
}
