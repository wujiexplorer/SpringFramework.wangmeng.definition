package com.lx.benefits.mapper.entercreditinfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.entercreditinfo.CreditChekingRecorder;
import com.lx.benefits.bean.entity.entercreditinfo.CreditChekingRecorderCondition;
import com.lx.benefits.bean.vo.entercreditinfo.CreditChekingRecorderBean;
import com.lx.benefits.bean.vo.entercreditinfo.CreditRechargeRecorder;

public interface CreditChekingRecorderMapper {
	long countByExample(CreditChekingRecorderCondition example);

	int deleteByExample(CreditChekingRecorderCondition example);

	int deleteByPrimaryKey(String cashNumber);

	int insert(CreditChekingRecorder record);

	int insertSelective(CreditChekingRecorder record);

	List<CreditChekingRecorder> selectByExample(CreditChekingRecorderCondition example);

	CreditChekingRecorder selectByPrimaryKey(String cashNumber);

	int updateByExampleSelective(@Param("record") CreditChekingRecorder record, @Param("example") CreditChekingRecorderCondition example);

	int updateByExample(@Param("record") CreditChekingRecorder record, @Param("example") CreditChekingRecorderCondition example);

	int updateByPrimaryKeySelective(CreditChekingRecorder record);

	int updateByPrimaryKey(CreditChekingRecorder record);

	Integer countWithEnterprName(@Param("applyType") Integer applyType, @Param("enterprName") String enterprName, @Param("checkStatus") Integer checkStatus,
			@Param("applyStartTime") Date applyStartTime, @Param("applyEndTime") Date applyEndTime);

	List<CreditChekingRecorderBean> selectWithAgentName(@Param("applyType") Integer applyType, @Param("enterprName") String enterprName,
			@Param("checkStatus") Integer checkStatus, @Param("applyStartTime") Date applyStartTime, @Param("applyEndTime") Date applyEndTime,
			@Param("pageBean") PageBean pageBean);

	BigDecimal sumRechargeAmount(@Param("enterprId") Long enterprId, @Param("creditType") Integer creditType, @Param("applyType") Integer applyType);

	int countCreditRecharge(@Param("applyType") Integer applyType, @Param("enterprName") String enterprName, @Param("agentName") String agentName,
			@Param("rechargeStartTime") Date rechargeStartTime, @Param("rechargeEndTime") Date rechargeEndTime,@Param("enterprId") Long enterprId);

	List<CreditRechargeRecorder> getCreditRechargeRecorders(@Param("applyType") Integer applyType, @Param("enterprName") String enterprName,
			@Param("agentName") String agentName, @Param("rechargeStartTime") Date rechargeStartTime, @Param("rechargeEndTime") Date rechargeEndTime,
			@Param("pageBean") PageBean pageBean, @Param("enterprId") Long enterprId);
	
	BigDecimal countMoneyCreditinfo(@Param("enterprId") Long enterprId);
	
	BigDecimal countMoneyReturnedMoneyRecorder(@Param("enterprId") Long enterprId);
	
	int insertReceipt(CreditRechargeRecorder creditRecorder);
	
	List<CreditRechargeRecorder> getMoneyDetailCreditCheck(@Param("record") CreditRechargeRecorder record);
	
	List<CreditRechargeRecorder> getMoneyDetailReturnedMoney(@Param("record") CreditRechargeRecorder record);
	
	List<CreditRechargeRecorder> getMoneyTotal(@Param(value = "cashNumbers") List<String> cashNumbers) ;
}