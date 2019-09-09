package com.lx.benefits.dao.orderimportitem.impl;

import com.lx.benefits.bean.entity.orderimportitem.OrderImportItem;
import com.lx.benefits.bean.entity.orderimportitem.OrderImportItemExample;
import com.lx.benefits.dao.orderimportitem.OrderImportItemDao;
import com.lx.benefits.mapper.orderimportitem.OrderImportItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author unknow on 2018-12-26 16:48.
 */
@Repository
public class OrderImportItemDaoImpl implements OrderImportItemDao {
    
    @Autowired
    private OrderImportItemMapper orderImportItemMapper;
    
    @Override
    public Long insertSelective(OrderImportItem record) {
        int rowCount = orderImportItemMapper.insertSelective(record);
        if (rowCount > 0) {
            return record.getDetailId();
        }
        return null;
    }

    @Override
    public List<OrderImportItem> selectByExample(OrderImportItemExample example) {
        return orderImportItemMapper.selectByExample(example);
    }

    @Override
    public int countByExample(OrderImportItemExample example) {
        return orderImportItemMapper.countByExample(example);
    }

    @Override
    public Integer update(OrderImportItem record, OrderImportItemExample example) {
        return orderImportItemMapper.updateByExampleSelective(record,example);
    }
}