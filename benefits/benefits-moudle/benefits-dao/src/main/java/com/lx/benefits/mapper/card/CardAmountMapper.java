package com.lx.benefits.mapper.card;

import com.lx.benefits.bean.entity.card.CardAmount;
import com.lx.benefits.bean.entity.card.CardAmountCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardAmountMapper {
    long countByExample(CardAmountCondition example);

    int deleteByExample(CardAmountCondition example);

    int deleteByPrimaryKey(Integer amountId);

    int insert(CardAmount record);

    int insertSelective(CardAmount record);

    List<CardAmount> selectByExample(CardAmountCondition example);

    CardAmount selectByPrimaryKey(Integer amountId);

    int updateByExampleSelective(@Param("record") CardAmount record, @Param("example") CardAmountCondition example);

    int updateByExample(@Param("record") CardAmount record, @Param("example") CardAmountCondition example);

    int updateByPrimaryKeySelective(CardAmount record);

    int updateByPrimaryKey(CardAmount record);
}