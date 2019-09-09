package com.lx.benefits.mapper.memconsigneeaddress;

import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsigneeAdressMapper {

    int insertSelective(ConsigneeAdress record);

    int updateDefault(@Param("record") ConsigneeAdress record, @Param("example") ConsigneeAdressExample example);

    int updateByExample(ConsigneeAdress record);

    int getAddressCount(@Param("userid") Long userid);

    List<ConsigneeAdress> getAddressList(@Param("userid") Long userid);

    ConsigneeAdress getUserDefaultAddress(@Param("userid") Long userid);

    ConsigneeAdress selectAddressById(Long id);

    int delAddressList(Long id);

}