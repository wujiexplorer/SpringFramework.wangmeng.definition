package com.lx.benefits.dao.memconsigneeaddress.impl;


import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdressExample;
import com.lx.benefits.dao.memconsigneeaddress.ConsignessAdressDao;
import com.lx.benefits.mapper.memconsigneeaddress.ConsigneeAdressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author unknow on 2018-12-26 16:48.
 */
@Repository
public class ConsignessAdressDaoImpl implements ConsignessAdressDao {

    @Autowired
    private ConsigneeAdressMapper consigneeAdressMapper;

    @Override
    public Long insertSelective(ConsigneeAdress record) {
        record.setCreateTime(new Date());
        int count = consigneeAdressMapper.getAddressCount(record.getUserid());
        if (count == 0) {
            record.setIsdefault("1");
        } else {
            record.setIsdefault(record.getIsdefault() == null ? "0" : record.getIsdefault());
            // 如果用户有默认地址  先更新掉
            if (record.getIsdefault().equals("1")) {
                ConsigneeAdress adress = new ConsigneeAdress();
                adress.setIsdefault("0");
                adress.setUpdateTime(new Date());
                ConsigneeAdressExample adressExample = new ConsigneeAdressExample();
                adressExample.createCriteria().andIdNotEqualTo(Long.valueOf("0"));
                adressExample.createCriteria().andUserIdEqualTo(record.getUserid());
                consigneeAdressMapper.updateDefault(adress, adressExample);
            }
        }
        int rowCount = consigneeAdressMapper.insertSelective(record);
        if (rowCount > 0) {
            return record.getId();
        }
        return null;
    }


    @Override
    public Integer updateDefault(ConsigneeAdress record, ConsigneeAdressExample example) {
        return consigneeAdressMapper.updateDefault(record, example);
    }

    @Override
    public int updateByExample(ConsigneeAdress record) {
        record.setUpdateTime(new Date());
        return consigneeAdressMapper.updateByExample(record);
    }

    @Override
    public List<ConsigneeAdress> getAddressList(Long userid) {
        return consigneeAdressMapper.getAddressList(userid);
    }

    @Override
    public ConsigneeAdress getUserDefaultAddress(Long userid) {
        return consigneeAdressMapper.getUserDefaultAddress(userid);
    }

    @Override
    public ConsigneeAdress getAddressById(Long id) {
        return consigneeAdressMapper.selectAddressById(id);
    }

    @Override
    public int delAddressList(Long id) {
        return consigneeAdressMapper.delAddressList(id);
    }
}