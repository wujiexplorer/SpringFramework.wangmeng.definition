package com.lx.benefits.mapper.card;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.card.CardBatchRecorder;
import com.lx.benefits.bean.entity.card.CardBatchRecorderCondition;
import com.lx.benefits.bean.vo.card.CardBatchBean;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardBatchRecorderMapper {
    long countByExample(CardBatchRecorderCondition example);

    int deleteByExample(CardBatchRecorderCondition example);

    int deleteByPrimaryKey(Integer batchId);

    int insert(CardBatchRecorder record);

    int insertSelective(CardBatchRecorder record);

    List<CardBatchRecorder> selectByExample(CardBatchRecorderCondition example);

    CardBatchRecorder selectByPrimaryKey(Integer batchId);

    int updateByExampleSelective(@Param("record") CardBatchRecorder record, @Param("example") CardBatchRecorderCondition example);

    int updateByExample(@Param("record") CardBatchRecorder record, @Param("example") CardBatchRecorderCondition example);

    int updateByPrimaryKeySelective(CardBatchRecorder record);

    int updateByPrimaryKey(CardBatchRecorder record);

	int seletMaxCustomerBatchId(Long customerId);

	int countCardBatches(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("status")Integer status);

	List<CardBatchBean> selectCardBatches(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("status")Integer status, @Param("pageBean")PageBean pageBean);
}