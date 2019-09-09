package com.lx.benefits.dao.entercreditinfo.impl;

import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfo;
import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfoExample;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.dao.entercreditinfo.EnterprCreditInfoDao;
import com.lx.benefits.mapper.entercreditinfo.EnterprCreditInfoMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author by yingcai on 2018/12/13.
 */
@Service
public class EnterprCreditInfoDaoImpl implements EnterprCreditInfoDao {

    @Autowired
    private EnterprCreditInfoMapper enterprCreditInfoMapper;

    @Override
    public Long insert(EnterprCreditInfo enterprCreditInfo) {
        enterprCreditInfo.checkBeforeInsert();
        int rowCount = enterprCreditInfoMapper.insertSelective(enterprCreditInfo);
        if (rowCount > 0) {
            return enterprCreditInfo.getCreditId();
        }
        return null;
    }

    @Override
    public Integer update(EnterprCreditInfo enterprCreditInfo, EnterprCreditInfoExample example) {
        enterprCreditInfo.checkBeforeUpdate();
        return enterprCreditInfoMapper.updateByExampleSelective(enterprCreditInfo, example);
    }

    @Override
    public List<EnterprCreditInfo> find(EnterprCreditInfoExample example) {
        return enterprCreditInfoMapper.selectByExample(example);
    }

    @Override
    public Integer count(EnterprCreditInfoExample example) {
        return enterprCreditInfoMapper.countByExample(example);
    }

    @Override
    public int updateByPrimaryKeySelective(EnterprCreditInfo record) {
        return enterprCreditInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateEmployeeCreditInfoReduceCredit(EnterprCreditInfo enterprCreditInfo) {
        enterprCreditInfo.setUpdated(DateUtil.getNowTimestamp10());
        return enterprCreditInfoMapper.updateEmployeeCreditInfoReduceCredit(enterprCreditInfo);
    }

    @Override
    public EnterprCreditInfo findByEnterprIdOne(EnterprCreditInfoExample example) {
        List<EnterprCreditInfo> creditInfoList = enterprCreditInfoMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(creditInfoList)) {
            return creditInfoList.get(0);
        }
        return null;
    }

    @Override
    public int updateEmployeeCreditInfoAddCredit(EnterprCreditInfo enterprCreditInfo) {
        enterprCreditInfo.setUpdated(DateUtil.getNowTimestamp10());
        return enterprCreditInfoMapper.updateEmployeeCreditInfoAddCredit(enterprCreditInfo);
    }

    @Override
    public int updateValidCreditByEnterprId(Map<String, Object> params) {
        return enterprCreditInfoMapper.updateValidCreditByEnterprId(params);
    }
}
