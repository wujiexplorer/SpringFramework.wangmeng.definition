package com.lx.benefits.dao.employeeimportdetail.impl;


import com.lx.benefits.bean.entity.employeeimportdetail.EmplyeeImportDetail;
import com.lx.benefits.bean.entity.employeeimportdetail.EmplyeeImportDetailExample;
import com.lx.benefits.dao.employeeimportdetail.EmplyeeImportDetailDao;
import com.lx.benefits.mapper.employeeimportdetail.EmplyeeImportDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author unknow on 2018-12-26 17:21.
 */
@Repository
public class EmplyeeImportDetailDaoImpl implements EmplyeeImportDetailDao {
    
    @Autowired
    private EmplyeeImportDetailMapper emplyeeImportDetailMapper;

    @Override
    public int countByExample(EmplyeeImportDetailExample example) {
        return emplyeeImportDetailMapper.countByExample(example);
    }

    @Override
    public Long insertSelective(EmplyeeImportDetail record) {
        int rowCount = emplyeeImportDetailMapper.insertSelective(record);
        if (rowCount > 0) {
            return record.getDetailId();
        }
        return null;
    }

    @Override
    public int batchInsert(List<EmplyeeImportDetail> emplyeeImportDetailList) {
        return emplyeeImportDetailMapper.batchInsert(emplyeeImportDetailList);
    }

    @Override
    public List<EmplyeeImportDetail> selectByExample(EmplyeeImportDetailExample example) {
        return emplyeeImportDetailMapper.selectByExample(example);
    }
}
