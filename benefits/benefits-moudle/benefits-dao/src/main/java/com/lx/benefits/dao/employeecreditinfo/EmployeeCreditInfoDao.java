package com.lx.benefits.dao.employeecreditinfo;

import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfoExample;

import java.util.List;

/**
 * 员工积分
 *
 * @author luojie
 */
public interface EmployeeCreditInfoDao {

    /**
     * 员工积分增加操作
     *
     * @return
     */
    int updateEmployeeCreditInfoAddCredit(EmployeeCreditInfo info);

    EmployeeCreditInfo getByEmployeeIdAndGrainId(EmployeeCreditInfo employeeCreditInfo);

    int updateByEmployeeIdSelective(EmployeeCreditInfo record);

    int updateGrainIdByEmployeeIdSelective(EmployeeCreditInfo employeeCreditInfo);

    List<EmployeeCreditInfo> select(EmployeeCreditInfoExample example);

    int update(EmployeeCreditInfo info, EmployeeCreditInfoExample example);

    List<EmployeeCreditInfo> selectByExample(EmployeeCreditInfoExample example);

    int countByExample(EmployeeCreditInfoExample example);
    
    /**
     * 插入积分数据
     *
     * @param record
     * @return
     */
    int insert(EmployeeCreditInfo record);


    /**
     * 查询积分数额列表
     *
     * @param employeeIdList ID列表
     * @param creditType     积分类型
     * @return
     */
    List<EmployeeCreditInfo> findEmployeeCreditInfoListByIds(List<Long> employeeIdList, Integer creditType);

    List<EmployeeCreditInfo> findCreditInfoListByIds(List<Long> employeeIdList, Long campaignId);



    /**
     * 员工积分扣减操作
     *
     * @param info 更改数据
     * @return
     */
    int updateEmployeeCreditInfoReduceCredit(EmployeeCreditInfo info);

    /**
     * 用户积分变动 -交易
     * @param info
     * @return
     */
    int updateEmployeeCreditInfo4Order(EmployeeCreditInfo info);

    EmployeeCreditInfo selectEmployeeCreditInfo(Long employeeId, Long campaignId);

    int updateEmployeeCredit(EmployeeCreditInfo info);

}
