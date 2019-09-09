package com.lx.benefits.dao.employeecreditinfo.impl;

import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfoExample;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.dao.employeecreditinfo.EmployeeCreditInfoDao;
import com.lx.benefits.mapper.employeecreditinfo.EmployeeCreditInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工积分
 *
 * @author luojie
 */
@Repository
public class EmployeeCreditInfoDaoImpl implements EmployeeCreditInfoDao {

    @Autowired
    private EmployeeCreditInfoMapper employeeCreditInfoMapper;

    @Override
    public int updateEmployeeCreditInfoAddCredit(EmployeeCreditInfo info) {
        info.setUpdated((int) DateUtil.getNowTimestamp10());
        return employeeCreditInfoMapper.updateEmployeeCreditInfoAddCredit(info);
    }

    @Override
    public EmployeeCreditInfo getByEmployeeIdAndGrainId(EmployeeCreditInfo employeeCreditInfo) {
        return employeeCreditInfoMapper.getByEmployeeIdAndGrainId(employeeCreditInfo);
    }

    @Override
    public int updateByEmployeeIdSelective(EmployeeCreditInfo record) {
        return employeeCreditInfoMapper.updateByEmployeeIdSelective(record);
    }

    @Override
    public int updateGrainIdByEmployeeIdSelective(EmployeeCreditInfo employeeCreditInfo) {
        return employeeCreditInfoMapper.updateGrainIdByEmployeeIdSelective(employeeCreditInfo);
    }

    @Override
    public List<EmployeeCreditInfo> select(EmployeeCreditInfoExample example) {
        return employeeCreditInfoMapper.selectByExample(example);
    }

    @Override
    public int update(EmployeeCreditInfo info, EmployeeCreditInfoExample example) {
        info.setUpdated((int) DateUtil.getNowTimestamp10());
        return employeeCreditInfoMapper.updateByExampleSelective(info, example);
    }

    @Override
    public List<EmployeeCreditInfo> selectByExample(EmployeeCreditInfoExample example) {
        return employeeCreditInfoMapper.selectByExample(example);
    }

    @Override
    public int countByExample(EmployeeCreditInfoExample example) {
        return employeeCreditInfoMapper.countByExample(example);
    }

    @Override
    public int insert(EmployeeCreditInfo record) {
        record.setCreated((int) DateUtil.getNowTimestamp10());
        record.setUpdated((int) DateUtil.getNowTimestamp10());
        return employeeCreditInfoMapper.insertSelective(record);
    }

    @Override
    public List<EmployeeCreditInfo> findEmployeeCreditInfoListByIds(List<Long> employeeIdList, Integer creditType) {
        return employeeCreditInfoMapper.listByEmployeeIdListAndCreditType(employeeIdList, creditType);
    }

    @Override
    public List<EmployeeCreditInfo> findCreditInfoListByIds(List<Long> employeeIdList, Long campaignId) {
        return employeeCreditInfoMapper.listByEmployeeIdListAndCampaignId(employeeIdList, campaignId);
    }

    @Override
    public int updateEmployeeCreditInfoReduceCredit(EmployeeCreditInfo info) {
        info.setUpdated((int) DateUtil.getNowTimestamp10());
        return employeeCreditInfoMapper.updateEmployeeCreditInfoReduceCredit(info);
    }

    @Override
    public int updateEmployeeCreditInfo4Order(EmployeeCreditInfo info) {
        info.setUpdated((int) DateUtil.getNowTimestamp10());
        return employeeCreditInfoMapper.updateEmployeeCreditInfo4Order(info);
    }

    @Override
    public EmployeeCreditInfo selectEmployeeCreditInfo(Long employeeId, Long campaignId) {
        return employeeCreditInfoMapper.selectEmployeeCreditInfo(employeeId,campaignId);
    }

    @Override
    public int updateEmployeeCredit(EmployeeCreditInfo info) {
        return employeeCreditInfoMapper.updateEmployeeCredit(info);
    }
}
