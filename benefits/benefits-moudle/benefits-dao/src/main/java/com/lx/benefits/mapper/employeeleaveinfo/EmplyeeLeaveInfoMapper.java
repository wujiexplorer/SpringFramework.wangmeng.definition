package com.lx.benefits.mapper.employeeleaveinfo;

import com.lx.benefits.bean.entity.employeeleaveinfo.EmplyeeLeaveInfo;
import com.lx.benefits.bean.entity.employeeleaveinfo.EmplyeeLeaveInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmplyeeLeaveInfoMapper {
    int countByExample(EmplyeeLeaveInfoExample example);

    int deleteByExample(EmplyeeLeaveInfoExample example);

    int deleteByPrimaryKey(Long leaveId);

    int insert(EmplyeeLeaveInfo record);

    int insertSelective(EmplyeeLeaveInfo record);

    List<EmplyeeLeaveInfo> selectByExample(EmplyeeLeaveInfoExample example);

    EmplyeeLeaveInfo selectByPrimaryKey(Long leaveId);

    int updateByExampleSelective(@Param("record") EmplyeeLeaveInfo record, @Param("example") EmplyeeLeaveInfoExample example);

    int updateByExample(@Param("record") EmplyeeLeaveInfo record, @Param("example") EmplyeeLeaveInfoExample example);

    int updateByPrimaryKeySelective(EmplyeeLeaveInfo record);

    int updateByPrimaryKey(EmplyeeLeaveInfo record);
}