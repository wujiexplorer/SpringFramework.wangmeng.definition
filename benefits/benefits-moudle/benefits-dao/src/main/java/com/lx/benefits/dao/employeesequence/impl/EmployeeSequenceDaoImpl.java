package com.lx.benefits.dao.employeesequence.impl;

import com.lx.benefits.bean.entity.employeesequence.EmployeeSequence;
import com.lx.benefits.dao.employeesequence.EmployeeSequenceDao;
import com.lx.benefits.mapper.employeesequence.EmployeeSequenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by yingcai on 2018/12/20.
 */
@Service
public class EmployeeSequenceDaoImpl implements EmployeeSequenceDao {

    @Autowired
    private EmployeeSequenceMapper employeeSequenceMapper;

    @Override
    public Long insert(EmployeeSequence employeeSequence) {
        int rowCount = employeeSequenceMapper.insert(employeeSequence);
        if (rowCount == 1) {
            return employeeSequence.getEmployeeId();
        }
        return null;
    }

    @Override
    public List<Long> batchInsert(List<EmployeeSequence> employeeSequenceList) {
        int rowCount = employeeSequenceMapper.batchInsert(employeeSequenceList);
        if (employeeSequenceList.size() == rowCount) {
            List<Long> employeeIdList = new ArrayList<>();
            for (EmployeeSequence employeeSequence: employeeSequenceList) {
                employeeIdList.add(employeeSequence.getEmployeeId());
            }
            return employeeIdList;
        }
        return null;
    }
}
