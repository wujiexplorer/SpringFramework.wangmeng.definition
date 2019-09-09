package com.lx.benefits.service.employeeimportinfo;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.FLPageDto;
import com.lx.benefits.bean.entity.employeeimportinfo.EmplyeeImportInfo;


/**
 * @author unknow on 2018-12-26 17:07.
 */
public interface EmplyeeImportInfoService {

    Long insertSelective(EmplyeeImportInfo record);

    JSONObject selectByExample(FLPageDto req);
    
}