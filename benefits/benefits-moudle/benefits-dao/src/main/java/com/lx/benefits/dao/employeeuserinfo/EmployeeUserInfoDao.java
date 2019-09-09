package com.lx.benefits.dao.employeeuserinfo;


import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfoExample;

import java.util.List;
import java.util.Map;

/**
 * @author luojie
 */
public interface EmployeeUserInfoDao {

    Long insert(EmployeeUserInfo employeeUserInfo);

    int batchInsert(List<EmployeeUserInfo> employeeUserInfoList);

    List<EmployeeUserInfo> find(EmployeeUserInfoExample example);

    List<EmployeeUserInfo> selectByExampleList(EmployeeUserInfoExample example);

    Integer count(EmployeeUserInfoExample example);
    
    EmployeeUserInfo fetchOne(EmployeeUserInfoExample example);

    int updateByPrimaryKeySelective(EmployeeUserInfo record);


    /**
     * 根据员工编号和企业id查询企业员工信息
     *
     * @param employeeNo 员工编号
     * @param enterPrId  企业id
     * @return
     */
    EmployeeUserInfo findOne(String employeeNo, Long enterPrId);

    EmployeeUserInfo selectByPrimaryKey(Long employeeId);

    /**
     * 根据企业id查询企业员工信息列表
     *
     * @param enterPrId 企业id
     * @return
     */
    List<EmployeeUserInfo> findByEnterPrId(Long enterPrId);

    /**
     * 根据企业id查询企业员工信息列表
     *
     * @param enterPrId   企业id
     * @param employeeNos 员工工号列表
     * @return
     */
    List<EmployeeUserInfo> findByEnterPrIdAndEmployeeNos(Long enterPrId, List<String> employeeNos);

    Integer updateByPrimaryKey(EmployeeUserInfo employeeUserInfo);

    List<EmployeeUserInfo> findEmployeeUserInfosByEnterprId(Long enterprId);

    List<EmployeeUserInfo> findEmployeeUserInfosByName(Map<String,Object> params);
}
