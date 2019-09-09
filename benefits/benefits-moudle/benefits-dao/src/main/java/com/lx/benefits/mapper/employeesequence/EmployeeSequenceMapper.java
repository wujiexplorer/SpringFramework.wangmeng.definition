package com.lx.benefits.mapper.employeesequence;

import com.lx.benefits.bean.entity.employeesequence.EmployeeSequence;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeSequenceMapper {
    
    int batchInsert(@Param("employeeSequenceList") List<EmployeeSequence> employeeSequenceList);
    
    int insert(EmployeeSequence employeeSequence);
}