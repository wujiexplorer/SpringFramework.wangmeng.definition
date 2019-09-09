package com.lx.benefits.mapper.orderimportinfo;

import com.lx.benefits.bean.entity.orderimportinfo.OrderImportInfo;
import com.lx.benefits.bean.entity.orderimportinfo.OrderImportInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderImportInfoMapper {
    int countByExample(OrderImportInfoExample example);

    int deleteByExample(OrderImportInfoExample example);

    int deleteByPrimaryKey(Long importId);

    int insert(OrderImportInfo record);

    int insertSelective(OrderImportInfo record);

    List<OrderImportInfo> selectByExample(OrderImportInfoExample example);

    OrderImportInfo selectByPrimaryKey(Long importId);

    int updateByExampleSelective(@Param("record") OrderImportInfo record, @Param("example") OrderImportInfoExample example);

    int updateByExample(@Param("record") OrderImportInfo record, @Param("example") OrderImportInfoExample example);

    int updateByPrimaryKeySelective(OrderImportInfo record);

    int updateByPrimaryKey(OrderImportInfo record);
}