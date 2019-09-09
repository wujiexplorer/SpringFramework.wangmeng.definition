package com.lx.benefits.dao.employeeimportinfo.impl;


import com.lx.benefits.bean.entity.employeeimportinfo.EmplyeeImportInfo;
import com.lx.benefits.bean.entity.employeeimportinfo.EmplyeeImportInfoExample;
import com.lx.benefits.dao.employeeimportinfo.EmplyeeImportInfoDao;
import com.lx.benefits.mapper.employeeimportinfo.EmplyeeImportInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author unknow on 2018-12-26 16:48.
 */
@Repository
public class EmplyeeImportInfoDaoImpl implements EmplyeeImportInfoDao {
    
    @Autowired
    private EmplyeeImportInfoMapper emplyeeImportInfoMapper;
    
    @Override
    public Long insertSelective(EmplyeeImportInfo record) {
        int rowCount = emplyeeImportInfoMapper.insertSelective(record);
        if (rowCount > 0) {
            return record.getImportId();
        }
        return null;
    }

    @Override
    public List<EmplyeeImportInfo> selectByExample(EmplyeeImportInfoExample example) {
        return emplyeeImportInfoMapper.selectByExample(example);
    }

    @Override
    public int countByExample(EmplyeeImportInfoExample example) {
        return emplyeeImportInfoMapper.countByExample(example);
    }
}