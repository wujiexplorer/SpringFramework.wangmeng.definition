package com.lx.benefits.dao.enterprmoduleinfo.impl;


import com.lx.benefits.bean.entity.enterprmoduleinfo.EnterprModuleInfo;
import com.lx.benefits.bean.entity.enterprmoduleinfo.EnterprModuleInfoExample;
import com.lx.benefits.dao.enterprmoduleinfo.EnterprModuleInfoDao;
import com.lx.benefits.mapper.enterprmoduleinfo.EnterprModuleInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yingcai on 2018/12/18.
 */
@Service
public class EnterprModuleInfoDaoImpl implements EnterprModuleInfoDao {

    @Autowired
    private EnterprModuleInfoMapper enterprModuleInfoMapper;

    @Override
    public Long insert(EnterprModuleInfo enterprModuleInfo) {
        enterprModuleInfo.checkBeforeInsert();
        int rowCount = enterprModuleInfoMapper.insertSelective(enterprModuleInfo);
        if (rowCount > 0) {
            return enterprModuleInfo.getModuleId();
        }
        return null;
    }

    @Override
    public Integer updateByPrimaryKeySelective(EnterprModuleInfo enterprModuleInfo) {
        return enterprModuleInfoMapper.updateByPrimaryKeySelective(enterprModuleInfo);
    }

    @Override
    public List<EnterprModuleInfo> find(EnterprModuleInfoExample example) {
        return enterprModuleInfoMapper.selectByExample(example);
    }

    @Override
    public Integer count(EnterprModuleInfoExample example) {
        return null;
    }
}
