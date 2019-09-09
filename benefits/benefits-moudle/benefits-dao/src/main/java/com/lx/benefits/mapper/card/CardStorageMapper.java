package com.lx.benefits.mapper.card;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.card.CardStorage;
import com.lx.benefits.bean.entity.card.CardStorageCondition;
import com.lx.benefits.bean.vo.card.CardAmountStorage;
import com.lx.benefits.bean.vo.card.CardStasticReport;
import com.lx.benefits.bean.vo.card.CardStorageBean;
import com.lx.benefits.bean.vo.card.CardStorageStatistics;
import com.lx.benefits.bean.vo.card.CustomerOrderReport;
import com.lx.benefits.bean.vo.card.MemberCardSearchBean;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardStorageMapper {
	long countByExample(CardStorageCondition example);

	int deleteByExample(CardStorageCondition example);

	int deleteByPrimaryKey(String cardNumber);

	int insert(CardStorage record);

	int insertSelective(CardStorage record);

	List<CardStorage> selectByExample(CardStorageCondition example);

	CardStorage selectByPrimaryKey(String cardNumber);

	int updateByExampleSelective(@Param("record") CardStorage record, @Param("example") CardStorageCondition example);

	int updateByExample(@Param("record") CardStorage record, @Param("example") CardStorageCondition example);

	int updateByPrimaryKeySelective(CardStorage record);

	int updateByPrimaryKey(CardStorage record);

	void batchInsert(@Param("records") List<CardStorage> cardStorages);

	CardStorageStatistics getStorageStatistics(@Param("status") Integer status);

	List<CardAmountStorage> getCardStoreInfo(@Param("status") Integer status);

	List<CardStorageBean> selectBasicInfo(@Param("example")CardStorageCondition example, @Param("withVerifyNumber")boolean withVerifyNumber);

	List<CardStorageBean> getStoredCards(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("cardNumber")String cardNumber, @Param("pageBean")PageBean pageBean);

	int countCustomerCardUseInfo(@Param("customerId")Long customerId, @Param("status")Integer status);

	List<CardStorage> selectCustomerCardUseInfo(@Param("customerId")Long customerId, @Param("status")Integer status, @Param("pageBean")PageBean pageBean);

	CardStorageStatistics statisticsCustomerStorage(@Param("customerId")Long customerId);

	List<CardStasticReport> getCardStasticReportGropuByStatus();

	List<CustomerOrderReport> getUsedAmount(@Param("customerIds")List<Long> customerIds);

	int countBySearchBean(@Param("searchBean")MemberCardSearchBean searchBean);

	List<CardStorageBean> selectBySearchBean(@Param("searchBean")MemberCardSearchBean searchBean, @Param("pageBean")PageBean pageBean);
}