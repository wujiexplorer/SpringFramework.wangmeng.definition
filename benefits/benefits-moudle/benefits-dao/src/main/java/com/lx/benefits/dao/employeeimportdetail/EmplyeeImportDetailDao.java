package com.lx.benefits.dao.employeeimportdetail;


import com.lx.benefits.bean.entity.employeeimportdetail.EmplyeeImportDetail;
import com.lx.benefits.bean.entity.employeeimportdetail.EmplyeeImportDetailExample;

import java.util.List;

/**
 * @author unknow on 2018-12-26 17:21.
 */
public interface EmplyeeImportDetailDao {

    Long insertSelective(EmplyeeImportDetail record);
    
    int batchInsert(List<EmplyeeImportDetail> emplyeeImportDetailList);

    List<EmplyeeImportDetail> selectByExample(EmplyeeImportDetailExample example);

    int countByExample(EmplyeeImportDetailExample example);
}