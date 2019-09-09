package com.lx.benefits.mapper.orderimportitem;

import com.lx.benefits.bean.entity.orderimportitem.OrderImportItem;
import com.lx.benefits.bean.entity.orderimportitem.OrderImportItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderImportItemMapper {
    int countByExample(OrderImportItemExample example);

    int deleteByExample(OrderImportItemExample example);

    int deleteByPrimaryKey(Long importId);

    int insert(OrderImportItem record);

    int insertSelective(OrderImportItem record);

    List<OrderImportItem> selectByExample(OrderImportItemExample example);

    OrderImportItem selectByPrimaryKey(Long importId);

    int updateByExampleSelective(@Param("record") OrderImportItem record, @Param("example") OrderImportItemExample example);

    int updateByExample(@Param("record") OrderImportItem record, @Param("example") OrderImportItemExample example);

    int updateByPrimaryKeySelective(OrderImportItem record);

    int updateByPrimaryKey(OrderImportItem record);

    Double selectTotalPrice(Long importId);
}