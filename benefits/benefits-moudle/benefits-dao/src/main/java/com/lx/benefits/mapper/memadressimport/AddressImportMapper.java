package com.lx.benefits.mapper.memadressimport;

import com.lx.benefits.bean.entity.memadressimport.AddressImportInfo;
import com.lx.benefits.bean.entity.memadressimport.AddressImportInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AddressImportMapper {
    int countByExample(AddressImportInfoExample example);

    int deleteByExample(AddressImportInfoExample example);

    int deleteByPrimaryKey(Long importId);

    int insert(AddressImportInfo record);

    int insertSelective(AddressImportInfo record);

    List<AddressImportInfo> selectByExample(AddressImportInfoExample example);

    AddressImportInfo selectByPrimaryKey(Long importId);

    int updateByExampleSelective(@Param("record") AddressImportInfo record, @Param("example") AddressImportInfoExample example);

    int updateByExample(@Param("record") AddressImportInfo record, @Param("example") AddressImportInfoExample example);

    int updateByPrimaryKeySelective(AddressImportInfo record);

    int updateByPrimaryKey(AddressImportInfo record);
}