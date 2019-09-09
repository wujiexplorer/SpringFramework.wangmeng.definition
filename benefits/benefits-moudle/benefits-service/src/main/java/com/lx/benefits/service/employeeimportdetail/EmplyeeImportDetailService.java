package com.lx.benefits.service.employeeimportdetail;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeImportDetailQueryDto;

/**
 * @author unknow on 2018-12-26 17:19.
 */
public interface EmplyeeImportDetailService {

    JSONObject selectByExample(EmployeeImportDetailQueryDto req);
}
