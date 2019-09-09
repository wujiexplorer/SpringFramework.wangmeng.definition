package com.lx.benefits.dao.memunioninfo.impl;


import com.lx.benefits.bean.entity.memunioninfo.MemUnionInfo;
import com.lx.benefits.dao.memunioninfo.MemUnionInfoDao;
import com.lx.benefits.mapper.memunioninfo.MemUnionInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author unknow on 2019-01-05 21:57.
 */
@Repository
public class MemUnionInfoDaoImpl implements MemUnionInfoDao {
    
    @Autowired
    private MemUnionInfoMapper memUnionInfoMapper;
    
    @Override
    public int batchInsert(List<MemUnionInfo> memUnionInfoList) {
        return memUnionInfoMapper.batchInsert(memUnionInfoList);
    }

    @Override
    public int updateByPrimaryKeySelective(MemUnionInfo memUnionInfo) {
        return memUnionInfoMapper.updateByPrimaryKeySelective(memUnionInfo);
    }

    @Override
    public int updateByMemId(MemUnionInfo memUnionInfo) {
        return memUnionInfoMapper.updateByMemId(memUnionInfo);
    }
}
