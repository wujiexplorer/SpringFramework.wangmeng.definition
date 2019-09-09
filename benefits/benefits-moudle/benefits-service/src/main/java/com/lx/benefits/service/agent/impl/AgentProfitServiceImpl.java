package com.lx.benefits.service.agent.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.constants.AgentIncomeType;
import com.lx.benefits.bean.constants.AgentRebateType;
import com.lx.benefits.bean.entity.agent.AgentLevel;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfoCondition;
import com.lx.benefits.bean.vo.agent.AgentEnterpriseProfitBean;
import com.lx.benefits.bean.vo.agent.AgentProfitReport;
import com.lx.benefits.mapper.agent.AgentIncomeRecorderMapper;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.service.agent.AgentCashService;
import com.lx.benefits.service.agent.AgentProfitService;
import com.lx.benefits.service.agent.AgentService;

@Service
public class AgentProfitServiceImpl implements AgentProfitService {

	@Autowired
	private AgentService agentService;
	@Autowired
	private AgentCashService agentCashService;
	@Autowired
	private EnterprUserInfoMapper enterprUserInfoMapper;
	@Autowired
	private AgentIncomeRecorderMapper agentIncomeRecorderMapper;

	@Override
	public AgentProfitReport getProfitReport(Integer agentId) {
		AgentLevel agentlevel = agentService.getAgentlevel(agentId);
		Integer rebateType = agentlevel.getRebateType();
		AgentProfitReport agentProfitReport = agentCashService.getAgentProfitReport(agentId, rebateType);
		// 设置企业数量和企业员工的数量总和
		EnterprUserInfoCondition enterprCondition = new EnterprUserInfoCondition();
		enterprCondition.createCriteria().andAgentIdEqualTo(agentId);
		List<EnterprUserInfo> enterprUserInfos = enterprUserInfoMapper.selectByExample(enterprCondition);
		agentProfitReport.setEnterpriseCount(enterprUserInfos.size());
		if (enterprUserInfos.size() == 0) {
			agentProfitReport.setEmployeeCount(0);
		} else {
			int employeeCount = enterprUserInfos.stream().mapToInt(EnterprUserInfo::getEmployeeNum).sum();
			agentProfitReport.setEmployeeCount(employeeCount);
		}
		return agentProfitReport;
	}

	@Override
	public PageResultBean<AgentEnterpriseProfitBean> getEnterpriseProfitsView(Integer agentId, String enterprName, Date createStartTime, Date createEndTime,
			PageBean pageBean) {
		AgentLevel agentlevel = agentService.getAgentlevel(agentId);
		Integer rebateType = agentlevel.getRebateType();
		return agentCashService.getEnterpriseProfitsView(rebateType, agentId, enterprName, createStartTime, createEndTime, pageBean);
	}

	@Override
	public PageResultBean<?> getEnterpriseProfits(Integer agentId, Long enterprId, String orderNumber, String employeeName, Date orderStartTime,
			Date orderEndTime, PageBean pageBean) {
		AgentLevel agentlevel = agentService.getAgentlevel(agentId);
		Integer rebateType = agentlevel.getRebateType();
		if (AgentRebateType.RECHARGE.getType().equals(rebateType)) {// 充值返点
			return agentCashService.getEnterpriseRechargeProfits(agentId, enterprId, pageBean);
		} else if (AgentRebateType.SALES.getType().equals(rebateType) // 销售额返点
				|| AgentRebateType.PROFIT.getType().equals(rebateType)) {// 利润额返点
			if (orderNumber != null && "".equals(orderNumber = orderNumber.trim())) {
				orderNumber = null;
			}
			if (employeeName != null && "".equals(employeeName = employeeName.trim())) {
				employeeName = null;
			}
			return agentCashService.getEnterpriseOrderProfits(agentId, enterprId, orderNumber, employeeName, orderStartTime, orderEndTime, pageBean);
		}
		return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), 0, Collections.emptyList());
	}

	@Override
	public AgentProfitReport getProfitReportWithRebate(Integer agentId) {
		AgentProfitReport report = new AgentProfitReport();
		// 统计充值总额
		AgentProfitReport rechargeProfitReport = agentIncomeRecorderMapper.sumTotalRecharge(agentId);
		report.setTotalRecharge(rechargeProfitReport.getTotalRecharge());
		report.setRechargeRebate(rechargeProfitReport.getRechargeRebate());
		// 统计订单成本和销售额
		BigDecimal totalOrderSaleAmount = BigDecimal.valueOf(0);// 订单营业总额
		BigDecimal totalOrderCostAmount = BigDecimal.valueOf(0);// 订单成本总额
		AgentProfitReport costAndSaleReport = agentIncomeRecorderMapper.sumOrderCostAndSaleAmount(agentId);
		if (costAndSaleReport!=null) {
			totalOrderSaleAmount=costAndSaleReport.getTotalOrderSaleAmount();
			totalOrderCostAmount=costAndSaleReport.getTotalOrderCostAmount();
		}
		report.setTotalOrderSaleAmount(totalOrderSaleAmount);
		report.setTotalOrderCostAmount(totalOrderCostAmount);
		report.setTotalOrderIncome(totalOrderSaleAmount.subtract(totalOrderCostAmount));// 根据销售额和成本计算利润额
		BigDecimal orderSaleAmountRebate = agentIncomeRecorderMapper.sumCashIncomeTotalByType(agentId, AgentIncomeType.SALE_ORDER.getType());// 获取销售额返点收益
		BigDecimal orderIncomeRebate = agentIncomeRecorderMapper.sumCashIncomeTotalByType(agentId, AgentIncomeType.PROFIT_ORDER.getType())
		.add(agentIncomeRecorderMapper.sumCashIncomeTotalByType(agentId, AgentIncomeType.INTRODUCER_AWARD.getType()));// 获取利润额返点收益
		report.setOrderSaleAmountRebate(orderSaleAmountRebate);
		report.setOrderIncomeRebate(orderIncomeRebate);
		report.setAccountBalance(agentIncomeRecorderMapper.sumCashIncomeTotal(agentId, null, null));// 帐户余额
		return report;
	}
}
