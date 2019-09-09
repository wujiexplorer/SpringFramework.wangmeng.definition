package com.lx.benefits.service.employeesequence.impl;

import com.lx.benefits.bean.entity.employeesequence.EmployeeSequence;
import com.lx.benefits.dao.employeesequence.EmployeeSequenceDao;
import com.lx.benefits.service.employeesequence.EmployeeSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author unknow on 2018-12-21 22:49.
 */
@Service
public class EmployeeSequenceServiceImpl implements EmployeeSequenceService {
    
    @Autowired
    private EmployeeSequenceDao employeeSequenceDao;

    /**
     * 批量获取num个employeeId
     * @param num  获取employeeId的个数，每次最多生成200个
     * @return  List  List<employeeId>
     */
    @Override
    public List<Long> batchGetEmployeeIdList(int num) {
        List<Long> employeeIdList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            employeeIdList.add(employeeSequenceDao.insert(new EmployeeSequence()));
        }
        return employeeIdList;
    }

    /**
     * 在Sequence中获取一个employeeId
     * @return   Long   employeeId
     */
    @Override
    public Long getEmployeeId() {
        return employeeSequenceDao.insert(new EmployeeSequence());
    }
}
