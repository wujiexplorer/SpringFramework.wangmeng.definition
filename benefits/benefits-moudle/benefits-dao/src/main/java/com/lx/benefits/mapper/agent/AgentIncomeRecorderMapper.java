package com.lx.benefits.mapper.agent;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.agent.AgentIncomeRecorder;
import com.lx.benefits.bean.entity.agent.AgentIncomeRecorderCondition;
import com.lx.benefits.bean.vo.agent.AgentEnterpriseProfitBean;
import com.lx.benefits.bean.vo.agent.AgentOrderProfitBean;
import com.lx.benefits.bean.vo.agent.AgentProfitReport;
import com.lx.benefits.bean.vo.agent.AgentRechargeProfitBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgentIncomeRecorderMapper {
	long countByExample(AgentIncomeRecorderCondition example);

	int deleteByExample(AgentIncomeRecorderCondition example);

	int deleteByPrimaryKey(Integer id);

	int insert(AgentIncomeRecorder record);

	int insertSelective(AgentIncomeRecorder record);

	List<AgentIncomeRecorder> selectByExample(AgentIncomeRecorderCondition example);

	AgentIncomeRecorder selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") AgentIncomeRecorder record, @Param("example") AgentIncomeRecorderCondition example);

	int updateByExample(@Param("record") AgentIncomeRecorder record, @Param("example") AgentIncomeRecorderCondition example);

	int updateByPrimaryKeySelective(AgentIncomeRecorder record);

	int updateByPrimaryKey(AgentIncomeRecorder record);

	BigDecimal sumCashIncomeTotal(@Param("agentId") Integer agentId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

	AgentProfitReport getAgentRechargeProfitReport(@Param("agentId") Integer agentId);

	AgentProfitReport getAgentOrderProfitReport(@Param("agentId") Integer agentId);

	List<AgentEnterpriseProfitBean> getEnterpriseRechargeProfitsView(@Param("agentId") Integer agentId, @Param("enterprName") String enterprName,
			@Param("createStartTime") Date createStartTime, @Param("createEndTime") Date createEndTime, @Param("pageBean") PageBean pageBean);

	List<AgentEnterpriseProfitBean> getEnterpriseOrderProfitsView(@Param("agentId") Integer agentId, @Param("enterprName") String enterprName,
			@Param("createStartTime") Date createStartTime, @Param("createEndTime") Date createEndTime, @Param("pageBean") PageBean pageBean);

	List<AgentRechargeProfitBean> getEnterpriseRechargeProfits(@Param("agentId") Integer agentId, @Param("enterprId") Long enterprId,
			@Param("pageBean") PageBean pageBean);

	int countEnterpriseOrderProfits(@Param("agentId") Integer agentId, @Param("enterprId") Long enterprId, @Param("orderNumber") String orderNumber,
			@Param("employeeName") String employeeName, @Param("orderStartTime") Date orderStartTime, @Param("orderEndTime") Date orderEndTime);

	List<AgentOrderProfitBean> getEnterpriseOrderProfits(@Param("agentId") Integer agentId, @Param("enterprId") Long enterprId,
			@Param("orderNumber") String orderNumber, @Param("employeeName") String employeeName, @Param("orderStartTime") Date orderStartTime,
			@Param("orderEndTime") Date orderEndTime, @Param("pageBean") PageBean pageBean);

	BigDecimal sumCashIncomeByType(@Param("type") Integer type);

	/**
	 * 返回代理商下的企业充值总额和充值返点总额
	 * 
	 * @param agentId
	 *            代理商ID
	 * @return
	 */
	AgentProfitReport sumTotalRecharge(@Param("agentId") Integer agentId);

	/**
	 * 获取代理商下企业的销售额和成本总额(包括正常订单和异常订单)
	 * 
	 * @param agentId
	 *            代理商ID
	 * @return
	 */
	AgentProfitReport sumOrderCostAndSaleAmount(@Param("agentId")Integer agentId);

	/**
	 * 获取订单收益金额
	 * 
	 * @param agentId
	 *            代理商ID
	 * 
	 * @param type
	 *            订单类型（销售额返点/利润额返点）
	 * @return
	 */
	BigDecimal sumCashIncomeTotalByType(@Param("agentId")Integer agentId, @Param("type")Integer type);
}