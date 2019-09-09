package com.lx.benefits.mapper.card;

import com.lx.benefits.bean.entity.card.CardPayRecord;
import com.lx.benefits.bean.entity.card.CardPayRecordCondition;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardPayRecordMapper {
    long countByExample(CardPayRecordCondition example);

    int deleteByExample(CardPayRecordCondition example);

    int deleteByPrimaryKey(Integer id);

    int insert(CardPayRecord record);

    int insertSelective(CardPayRecord record);

    List<CardPayRecord> selectByExample(CardPayRecordCondition example);

    CardPayRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CardPayRecord record, @Param("example") CardPayRecordCondition example);

    int updateByExample(@Param("record") CardPayRecord record, @Param("example") CardPayRecordCondition example);

    int updateByPrimaryKeySelective(CardPayRecord record);

    int updateByPrimaryKey(CardPayRecord record);

	List<CardPayRecord> selectBasicInfoByExample(CardPayRecordCondition example);

	BigDecimal getTotalPayAmount();

}