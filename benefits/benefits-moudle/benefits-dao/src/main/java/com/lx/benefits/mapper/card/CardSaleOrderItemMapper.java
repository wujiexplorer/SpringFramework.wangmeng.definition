package com.lx.benefits.mapper.card;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lx.benefits.bean.entity.card.CardSaleOrderItem;
import com.lx.benefits.bean.entity.card.CardSaleOrderItemCondition;
import com.lx.benefits.bean.vo.card.CardSaleOrderDetail;

public interface CardSaleOrderItemMapper {
    long countByExample(CardSaleOrderItemCondition example);

    int deleteByExample(CardSaleOrderItemCondition example);

    int deleteByPrimaryKey(Integer id);

    int insert(CardSaleOrderItem record);

    int insertSelective(CardSaleOrderItem record);

    List<CardSaleOrderItem> selectByExample(CardSaleOrderItemCondition example);

    CardSaleOrderItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CardSaleOrderItem record, @Param("example") CardSaleOrderItemCondition example);

    int updateByExample(@Param("record") CardSaleOrderItem record, @Param("example") CardSaleOrderItemCondition example);

    int updateByPrimaryKeySelective(CardSaleOrderItem record);

    int updateByPrimaryKey(CardSaleOrderItem record);

	int batchInsert(@Param("records")List<CardSaleOrderItem> cardSaleOrderItems, @Param("saleOrderId")Integer saleOrderId);

	List<CardSaleOrderDetail> getTotalAmountByStatus(@Param("customerIds")Collection<Long> customerIds, @Param("status")Integer status);
}