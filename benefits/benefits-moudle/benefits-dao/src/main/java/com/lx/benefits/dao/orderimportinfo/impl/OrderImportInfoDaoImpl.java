package com.lx.benefits.dao.orderimportinfo.impl;

import com.lx.benefits.bean.entity.orderimportinfo.OrderImportInfo;
import com.lx.benefits.bean.entity.orderimportinfo.OrderImportInfoExample;
import com.lx.benefits.dao.orderimportinfo.OrderImportInfoDao;
import com.lx.benefits.mapper.orderimportinfo.OrderImportInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author unknow on 2018-12-26 16:48.
 */
@Repository
public class OrderImportInfoDaoImpl implements OrderImportInfoDao {
    
    @Autowired
    private OrderImportInfoMapper orderImportInfoMapper;
    
    @Override
    public Long insertSelective(OrderImportInfo record) {
        int rowCount = orderImportInfoMapper.insertSelective(record);
        if (rowCount > 0) {
            return record.getImportId();
        }
        return null;
    }

    @Override
    public List<OrderImportInfo> selectByExample(OrderImportInfoExample example) {
        return orderImportInfoMapper.selectByExample(example);
    }

    @Override
    public int countByExample(OrderImportInfoExample example) {
        return orderImportInfoMapper.countByExample(example);
    }

    @Override
    public Integer update(OrderImportInfo record) {
        return orderImportInfoMapper.updateByPrimaryKeySelective(record);
    }
}