package com.lx.benefits.service.memconsigneeadress.impl;


import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdressExample;
import com.lx.benefits.dao.memconsigneeaddress.ConsignessAdressDao;
import com.lx.benefits.service.memconsigneeadress.ConsigeneeAdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConsigneeAdressServiceImpl implements ConsigeneeAdressService {

    @Autowired
    private ConsignessAdressDao consignessAdressDao;

    /**
     * 添加收货地址
     * @param consigneeAdress
     * @return
     */
    @Override
    public Long insert(ConsigneeAdress consigneeAdress) {
        return  consignessAdressDao.insertSelective(consigneeAdress);
    }

    /**
     * 更新为默认地址
     * @param consigneeAdress
     * @param example
     * @return
     */
    @Override
    public Integer updateDefault(ConsigneeAdress consigneeAdress, ConsigneeAdressExample example) {
        return consignessAdressDao.updateDefault(consigneeAdress, example);
    }


}
