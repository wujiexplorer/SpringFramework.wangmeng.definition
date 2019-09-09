package com.lx.benefits.dao.employeeuserinfo.impl;

import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfoExample;
import com.lx.benefits.dao.employeeuserinfo.EmployeeUserInfoDao;
import com.lx.benefits.mapper.employeeuserinfo.EmployeeUserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author luojie
 */
@Repository
public class EmployeeUserInfoDaoImpl implements EmployeeUserInfoDao {

    @Autowired
    private EmployeeUserInfoMapper employeeUserInfoMapper;


    @Override
    public Long insert(EmployeeUserInfo employeeUserInfo) {
        int rowCount = employeeUserInfoMapper.insertSelective(employeeUserInfo);
        if (rowCount == 1) {
            return employeeUserInfo.getEmployeeId();
        }
        return null;
    }
    
    @Override
    public int batchInsert(List<EmployeeUserInfo> employeeUserInfoList) {
        return employeeUserInfoMapper.batchInsert(employeeUserInfoList);
    }

    @Override
    public List<EmployeeUserInfo> find(EmployeeUserInfoExample example) {
        return employeeUserInfoMapper.selectByExample(example);
    }

    @Override
    public List<EmployeeUserInfo> selectByExampleList(EmployeeUserInfoExample example) {
        return employeeUserInfoMapper.selectByExampleList(example);
    }

    @Override
    public Integer count(EmployeeUserInfoExample example) {
        return employeeUserInfoMapper.countByExample(example);
    }

    @Override
    public EmployeeUserInfo fetchOne(EmployeeUserInfoExample example) {
        return employeeUserInfoMapper.fetchOneByExample(example);
    }

    @Override
    public int updateByPrimaryKeySelective(EmployeeUserInfo record) {
        return employeeUserInfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据员工编号和企业id查询企业员工信息 实现方法
     *
     * @param employeeNo 员工编号
     * @param enterPrId  企业id
     * @return
     */
    @Override
    public EmployeeUserInfo findOne(String employeeNo, Long enterPrId) {
        EmployeeUserInfoExample example = new EmployeeUserInfoExample();
        example.createCriteria().andEmployeeNoEqualTo(employeeNo);
        example.createCriteria().andEnterprIdEqualTo(enterPrId);
        return employeeUserInfoMapper.fetchOneByExample(example);
    }

    @Override
    public EmployeeUserInfo selectByPrimaryKey(Long employeeId) {
        return employeeUserInfoMapper.selectByPrimaryKey(employeeId);
    }

    /**
     * 根据企业id查询企业员工信息列表 实现方法
     *
     * @param enterPrId 企业id
     * @return
     */
    @Override
    public List<EmployeeUserInfo> findByEnterPrId(Long enterPrId) {
        EmployeeUserInfoExample example = new EmployeeUserInfoExample();
        example.createCriteria().andEnterprIdEqualTo(enterPrId);
        return employeeUserInfoMapper.selectByExampleList(example);
    }

    /**
     * 根据企业id查询企业员工信息列表 实现方法
     *
     * @param enterPrId   企业id
     * @param employeeNos 员工工号列表
     * @return
     */
    @Override
    public List<EmployeeUserInfo> findByEnterPrIdAndEmployeeNos(Long enterPrId, List<String> employeeNos) {
        EmployeeUserInfoExample example = new EmployeeUserInfoExample();
        example.createCriteria().andEnterprIdEqualTo(enterPrId);
        example.createCriteria().andEmployeeNoIn(employeeNos);
        return employeeUserInfoMapper.selectByExampleList(example);
    }

    @Override
    public Integer updateByPrimaryKey(EmployeeUserInfo employeeUserInfo) {
        return employeeUserInfoMapper.updateByPrimaryKeySelective(employeeUserInfo);
    }

    @Override
    public List<EmployeeUserInfo> findEmployeeUserInfosByEnterprId(Long enterprId) {
        return employeeUserInfoMapper.findEmployeeUserInfosByEnterprId(enterprId);
    }

    @Override
    public List<EmployeeUserInfo> findEmployeeUserInfosByName(Map<String,Object> params) {
        return employeeUserInfoMapper.findEmployeeUserInfosByName(params);
    }
}
