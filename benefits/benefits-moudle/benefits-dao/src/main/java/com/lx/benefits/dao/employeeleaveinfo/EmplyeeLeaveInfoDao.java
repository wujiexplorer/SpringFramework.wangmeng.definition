package com.lx.benefits.dao.employeeleaveinfo;


import com.lx.benefits.bean.entity.employeeleaveinfo.EmplyeeLeaveInfo;
import com.lx.benefits.bean.entity.employeeleaveinfo.EmplyeeLeaveInfoExample;

import java.util.List;

/**
 * @author unknow on 2018-12-28 23:10.
 */
public interface EmplyeeLeaveInfoDao {
    Long insertSelective(EmplyeeLeaveInfo record);

    int updateByPrimaryKeySelective(EmplyeeLeaveInfo record);
    
    List<EmplyeeLeaveInfo> selectByExample(EmplyeeLeaveInfoExample example);

    int countByExample(EmplyeeLeaveInfoExample example);
}