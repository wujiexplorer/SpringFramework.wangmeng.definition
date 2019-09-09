package com.lx.benefits.mapper.sale;

import com.lx.benefits.bean.entity.sale.AdvanceSaleExample;
import com.lx.benefits.bean.entity.sale.AdvanceSaleItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvanceSaleItemMapper {
    int countByExample(AdvanceSaleExample example);

    int deleteByExample(AdvanceSaleExample example);

    int deleteByPrimaryKey(Long campaignId);

    int insert(AdvanceSaleItem record);

    Long insertSelective(AdvanceSaleItem record);

    List<AdvanceSaleItem> selectByExample(AdvanceSaleExample example);

    AdvanceSaleItem selectByPrimaryKey(Long campaignId);

    int updateByExampleSelective(@Param("record") AdvanceSaleItem record, @Param("example") AdvanceSaleExample example);

    int updateByExample(@Param("record") AdvanceSaleItem record, @Param("example") AdvanceSaleExample example);

    int updateByPrimaryKeySelective(AdvanceSaleItem record);

    int updateByPrimaryKey(AdvanceSaleItem record);

    List<AdvanceSaleItem> selectDownExcel(AdvanceSaleItem record);

    int updateSelective(AdvanceSaleItem record);
}