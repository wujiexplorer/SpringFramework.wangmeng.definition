package com.lx.benefits.service.memconsigneeadress;


import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdressExample;

public interface ConsigeneeAdressService {

    Long insert(ConsigneeAdress consigneeAdress);


    Integer updateDefault(ConsigneeAdress consigneeAdress, ConsigneeAdressExample example);


}