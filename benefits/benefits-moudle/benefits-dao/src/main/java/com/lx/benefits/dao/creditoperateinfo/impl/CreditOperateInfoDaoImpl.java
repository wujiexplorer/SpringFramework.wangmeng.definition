package com.lx.benefits.dao.creditoperateinfo.impl;


import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfoExample;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.dao.creditoperateinfo.CreditOperateInfoDao;
import com.lx.benefits.mapper.creditoperateinfo.CreditOperateInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * 积分操作记录
 *
 * @author luojie
 */
@Repository
public class CreditOperateInfoDaoImpl implements CreditOperateInfoDao {

    @Autowired
    private CreditOperateInfoMapper creditOperateInfoMapper;


    @Override
    public List<CreditOperateInfo> pageByMap(HashMap<String, Object> queryMap) {
        return creditOperateInfoMapper.pageByMap(queryMap);
    }

    @Override
    public List<CreditOperateInfo> find(CreditOperateInfoExample example) {
        return creditOperateInfoMapper.selectByExample(example);
    }

    @Override
    public int insertSelective(CreditOperateInfo record) {
        record.setCreated(DateUtil.getNowTimestamp10());
        record.setUpdated(DateUtil.getNowTimestamp10());
        return creditOperateInfoMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CreditOperateInfo record) {
        record.setUpdated(DateUtil.getNowTimestamp10());
        return creditOperateInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int update(CreditOperateInfo record, CreditOperateInfoExample example) {
        record.checkBeforeUpdate();
        return creditOperateInfoMapper.updateByExampleSelective(record, example);
    }

    @Override
    public List<CreditOperateInfo> listByParentOptId(Long parentOptId) {
        return creditOperateInfoMapper.listByParentOptId(parentOptId);
    }

    @Override
    public int countByExample(CreditOperateInfoExample example) {
        return creditOperateInfoMapper.countByExample(example);
    }
}
