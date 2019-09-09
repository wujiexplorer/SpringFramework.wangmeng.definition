package com.lx.benefits.service.employeesequence;

import java.util.List;

/**
 * @author unknow on 2018-12-21 22:48.
 */
public interface EmployeeSequenceService {
    /**
     * 批量获取num个employeeId
     * @param num  获取employeeId的个数，每次最多生成200个
     * @return  List  List<employeeId>
     */
    List<Long> batchGetEmployeeIdList(int num);

    /**
     * 在Sequence中获取一个employeeId
     * @return   Long   employeeId
     */
    Long getEmployeeId();
}