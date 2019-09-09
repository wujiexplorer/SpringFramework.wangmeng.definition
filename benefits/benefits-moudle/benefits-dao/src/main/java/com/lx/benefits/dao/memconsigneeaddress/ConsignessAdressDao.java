package com.lx.benefits.dao.memconsigneeaddress;


import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdressExample;

import java.util.List;

/**
 * @author unknow on 2018-12-26 16:48.
 */
public interface ConsignessAdressDao {

    /**
     * 增加收货地址
     * @param record
     * @return
     */
    Long insertSelective(ConsigneeAdress record);

    /**
     * 更新默认状态
     * @param record
     * @param example
     * @return
     */
    Integer updateDefault(ConsigneeAdress record, ConsigneeAdressExample example);

    /**
     * 更新收货地址
     * @param record
     * @return
     */
    int updateByExample(ConsigneeAdress record);


    /**
     * 查询收货地址列表
     * @param userid
     * @return
     */
    List<ConsigneeAdress> getAddressList(Long userid);

    ConsigneeAdress getUserDefaultAddress(Long userid);

    ConsigneeAdress getAddressById(Long id);

    /**
     * 删除收货地址
     * @param id
     * @return
     */
    int delAddressList(Long id);


}
