package com.lx.benefits.dao.employeesequence;


import com.lx.benefits.bean.entity.employeesequence.EmployeeSequence;

import java.util.List;

/**
 * 员工积分
 *
 * @author luojie
 */
public interface EmployeeSequenceDao {

    Long insert(EmployeeSequence employeeSequence);
    
    /**
     * 批量插入employeeId
     * @param employeeSequenceList   employeeSequenceList
     * @return   List    List<employeeId>
     */
    List<Long> batchInsert(List<EmployeeSequence> employeeSequenceList);

}
