package com.lx.benefits.mapper.card;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.card.CardSaleOrder;
import com.lx.benefits.bean.entity.card.CardSaleOrderCondition;
import com.lx.benefits.bean.vo.card.CardSaleOrderDetail;
import com.lx.benefits.bean.vo.card.CustomerOrderReport;

public interface CardSaleOrderMapper {
	long countByExample(CardSaleOrderCondition example);

	int deleteByExample(CardSaleOrderCondition example);

	int deleteByPrimaryKey(Integer saleOrderId);

	int insert(CardSaleOrder record);

	int insertSelective(CardSaleOrder record);

	List<CardSaleOrder> selectByExample(CardSaleOrderCondition example);

	CardSaleOrder selectByPrimaryKey(Integer saleOrderId);

	int updateByExampleSelective(@Param("record") CardSaleOrder record, @Param("example") CardSaleOrderCondition example);

	int updateByExample(@Param("record") CardSaleOrder record, @Param("example") CardSaleOrderCondition example);

	int updateByPrimaryKeySelective(CardSaleOrder record);

	int updateByPrimaryKey(CardSaleOrder record);

	CardSaleOrderDetail getSaleOrderDetail(Integer saleOrderId);

	List<CardSaleOrderDetail> getSaleOrdersByPage(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("customerId") Long customerId,
			@Param("status") Integer status, @Param("pageBean") PageBean pageBean);

	List<CardSaleOrderDetail> getVerifyOrdersByPage(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("customerId") Long customerId,
			@Param("status") Integer status, @Param("pageBean") PageBean pageBean);

	List<CustomerOrderReport> getCustomerOrderReports(@Param("customerId")Long customerId, @Param("pageBean") PageBean pageBean);

	int countOrderCustomers(@Param("customerId")Long customerId);

	List<CardSaleOrderDetail> getCustomerCompleteOrderInfos(@Param("customerId")Long customerId, @Param("pageBean")PageBean pageBean);

}