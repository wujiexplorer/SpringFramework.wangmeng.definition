package com.lx.benefits.dao.employeeleaveinfo.impl;


import com.lx.benefits.bean.entity.employeeleaveinfo.EmplyeeLeaveInfo;
import com.lx.benefits.bean.entity.employeeleaveinfo.EmplyeeLeaveInfoExample;
import com.lx.benefits.dao.employeeleaveinfo.EmplyeeLeaveInfoDao;
import com.lx.benefits.mapper.employeeleaveinfo.EmplyeeLeaveInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author unknow on 2018-12-28 23:11.
 */
@Repository
public class EmplyeeLeaveInfoDaoImpl implements EmplyeeLeaveInfoDao {
    
    @Autowired
    private EmplyeeLeaveInfoMapper emplyeeLeaveInfoMapper;

    @Override
    public Long insertSelective(EmplyeeLeaveInfo record) {
        int rowCount = emplyeeLeaveInfoMapper.insertSelective(record);
        if (rowCount > 0) {
            return record.getLeaveId();
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(EmplyeeLeaveInfo record) {
        return emplyeeLeaveInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<EmplyeeLeaveInfo> selectByExample(EmplyeeLeaveInfoExample example) {
        return emplyeeLeaveInfoMapper.selectByExample(example);
    }

    @Override
    public int countByExample(EmplyeeLeaveInfoExample example) {
        return emplyeeLeaveInfoMapper.countByExample(example);
    }
}