package com.lx.benefits.mapper.employeeimportinfo;

import com.lx.benefits.bean.entity.employeeimportinfo.EmplyeeImportInfo;
import com.lx.benefits.bean.entity.employeeimportinfo.EmplyeeImportInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmplyeeImportInfoMapper {
    int countByExample(EmplyeeImportInfoExample example);

    int deleteByExample(EmplyeeImportInfoExample example);

    int deleteByPrimaryKey(Long importId);

    int insert(EmplyeeImportInfo record);

    int insertSelective(EmplyeeImportInfo record);

    List<EmplyeeImportInfo> selectByExample(EmplyeeImportInfoExample example);

    EmplyeeImportInfo selectByPrimaryKey(Long importId);

    int updateByExampleSelective(@Param("record") EmplyeeImportInfo record, @Param("example") EmplyeeImportInfoExample example);

    int updateByExample(@Param("record") EmplyeeImportInfo record, @Param("example") EmplyeeImportInfoExample example);

    int updateByPrimaryKeySelective(EmplyeeImportInfo record);

    int updateByPrimaryKey(EmplyeeImportInfo record);
}