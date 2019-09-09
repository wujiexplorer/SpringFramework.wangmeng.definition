package com.lx.benefits.dao.memaddressimportitem.impl;

import com.lx.benefits.bean.entity.memaddressimportitem.AddressImportItem;
import com.lx.benefits.bean.entity.memaddressimportitem.AddressImportItemExample;
import com.lx.benefits.dao.memaddressimportitem.AddressImportItemDao;
import com.lx.benefits.mapper.memaddressimportitem.AddressImportItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author unknow on 2018-12-26 16:48.
 */
@Repository
public class AddressImportItemDaoImpl implements AddressImportItemDao {
    
    @Autowired
    private AddressImportItemMapper addressImportItemMapper;
    
    @Override
    public Long insertSelective(AddressImportItem record) {
        int rowCount = addressImportItemMapper.insertSelective(record);
        if (rowCount > 0) {
            return record.getDetailId();
        }
        return null;
    }

    @Override
    public List<AddressImportItem> selectByExample(AddressImportItemExample example) {
        return addressImportItemMapper.selectByExample(example);
    }

    @Override
    public int countByExample(AddressImportItemExample example) {
        return addressImportItemMapper.countByExample(example);
    }

    @Override
    public Integer update(AddressImportItem record, AddressImportItemExample example) {
        return addressImportItemMapper.updateByExampleSelective(record,example);
    }
}