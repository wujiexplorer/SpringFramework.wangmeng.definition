package com.lx.benefits.dao.memadressimport.impl;

import com.lx.benefits.bean.entity.memadressimport.AddressImportInfo;
import com.lx.benefits.bean.entity.memadressimport.AddressImportInfoExample;
import com.lx.benefits.dao.memadressimport.AddressImportInfoDao;
import com.lx.benefits.mapper.memadressimport.AddressImportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author unknow on 2018-12-26 16:48.
 */
@Repository
public class AddressImportInfoDaoImpl implements AddressImportInfoDao {
    
    @Autowired
    private AddressImportMapper addressImportMapper;
    
    @Override
    public Long insertSelective(AddressImportInfo record) {
        int rowCount = addressImportMapper.insertSelective(record);
        if (rowCount > 0) {
            return record.getImportId();
        }
        return null;
    }

    @Override
    public List<AddressImportInfo> selectByExample(AddressImportInfoExample example) {
        return addressImportMapper.selectByExample(example);
    }

    @Override
    public int countByExample(AddressImportInfoExample example) {
        return addressImportMapper.countByExample(example);
    }

    @Override
    public Integer update(AddressImportInfo record) {
        return addressImportMapper.updateByPrimaryKeySelective(record);
    }
}