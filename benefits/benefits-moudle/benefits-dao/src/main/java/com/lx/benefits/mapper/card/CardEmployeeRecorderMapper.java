package com.lx.benefits.mapper.card;

import com.lx.benefits.bean.entity.card.CardEmployeeRecorder;
import com.lx.benefits.bean.entity.card.CardEmployeeRecorderCondition;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardEmployeeRecorderMapper {
    long countByExample(CardEmployeeRecorderCondition example);

    int deleteByExample(CardEmployeeRecorderCondition example);

    int deleteByPrimaryKey(Integer id);

    int insert(CardEmployeeRecorder record);

    int insertSelective(CardEmployeeRecorder record);

    List<CardEmployeeRecorder> selectByExample(CardEmployeeRecorderCondition example);

    CardEmployeeRecorder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CardEmployeeRecorder record, @Param("example") CardEmployeeRecorderCondition example);

    int updateByExample(@Param("record") CardEmployeeRecorder record, @Param("example") CardEmployeeRecorderCondition example);

    int updateByPrimaryKeySelective(CardEmployeeRecorder record);

    int updateByPrimaryKey(CardEmployeeRecorder record);
    
    BigDecimal getAmount(@Param("status")Integer status);
}