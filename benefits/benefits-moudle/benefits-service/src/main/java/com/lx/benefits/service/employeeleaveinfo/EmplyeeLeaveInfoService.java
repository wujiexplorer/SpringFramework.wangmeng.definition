package com.lx.benefits.service.employeeleaveinfo;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeLeaveQueryDto;
import com.lx.benefits.bean.entity.employeeleaveinfo.EmplyeeLeaveInfo;

/**
 * @author unknow on 2018-12-28 23:22.
 */
public interface EmplyeeLeaveInfoService {

    Long insertSelective(EmplyeeLeaveInfo record);
    
    JSONObject selectByExample(EmployeeLeaveQueryDto queryDto);
}
