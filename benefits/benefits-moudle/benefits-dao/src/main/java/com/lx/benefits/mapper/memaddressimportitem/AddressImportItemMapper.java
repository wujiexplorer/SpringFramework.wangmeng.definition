package com.lx.benefits.mapper.memaddressimportitem;

import com.lx.benefits.bean.entity.memaddressimportitem.AddressImportItem;
import com.lx.benefits.bean.entity.memaddressimportitem.AddressImportItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressImportItemMapper {
    int countByExample(AddressImportItemExample example);

    int deleteByExample(AddressImportItemExample example);

    int deleteByPrimaryKey(Long importId);

    int insert(AddressImportItem record);

    int insertSelective(AddressImportItem record);

    List<AddressImportItem> selectByExample(AddressImportItemExample example);

    AddressImportItem selectByPrimaryKey(Long importId);

    int updateByExampleSelective(@Param("record") AddressImportItem record, @Param("example") AddressImportItemExample example);

    int updateByExample(@Param("record") AddressImportItem record, @Param("example") AddressImportItemExample example);

    int updateByPrimaryKeySelective(AddressImportItem record);

    int updateByPrimaryKey(AddressImportItem record);
}