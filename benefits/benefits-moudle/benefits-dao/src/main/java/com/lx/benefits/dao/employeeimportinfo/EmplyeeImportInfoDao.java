package com.lx.benefits.dao.employeeimportinfo;


import com.lx.benefits.bean.entity.employeeimportinfo.EmplyeeImportInfo;
import com.lx.benefits.bean.entity.employeeimportinfo.EmplyeeImportInfoExample;

import java.util.List;

/**
 * @author unknow on 2018-12-26 16:48.
 */
public interface EmplyeeImportInfoDao {
    Long insertSelective(EmplyeeImportInfo record);

    List<EmplyeeImportInfo> selectByExample(EmplyeeImportInfoExample example);

    int countByExample(EmplyeeImportInfoExample example);
}
