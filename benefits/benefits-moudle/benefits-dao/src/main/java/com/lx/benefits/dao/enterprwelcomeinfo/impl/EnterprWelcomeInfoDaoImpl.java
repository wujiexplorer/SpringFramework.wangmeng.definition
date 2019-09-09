package com.lx.benefits.dao.enterprwelcomeinfo.impl;


import com.lx.benefits.bean.entity.enterprwelcomeinfo.EnterprWelcomeInfo;
import com.lx.benefits.bean.entity.enterprwelcomeinfo.EnterprWelcomeInfoExample;
import com.lx.benefits.dao.enterprwelcomeinfo.EnterprWelcomeInfoDao;
import com.lx.benefits.mapper.enterprwelcomeinfo.EnterprWelcomeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yingcai on 2018/12/18.
 */
@Service
public class EnterprWelcomeInfoDaoImpl implements EnterprWelcomeInfoDao {

    @Autowired
    private EnterprWelcomeInfoMapper enterprWelcomeInfoMapper;

    @Override
    public Long insert(EnterprWelcomeInfo enterprWelcomeInfo) {
        enterprWelcomeInfo.checkBeforeInsert();
        int rowCount = enterprWelcomeInfoMapper.insertSelective(enterprWelcomeInfo);
        if (rowCount > 0) {
            return enterprWelcomeInfo.getCustomId();
        }
        return null;
    }

    @Override
    public Integer update(EnterprWelcomeInfo enterprWelcomeInfo, EnterprWelcomeInfoExample example) {
        enterprWelcomeInfo.checkBeforeUpdate();
        return enterprWelcomeInfoMapper.updateByExampleSelective(enterprWelcomeInfo, example);
    }

    @Override
    public List<EnterprWelcomeInfo> find(EnterprWelcomeInfoExample example) {
        return enterprWelcomeInfoMapper.selectByExample(example);
    }

    @Override
    public Integer count(EnterprWelcomeInfoExample example) {
        return null;
    }
}
