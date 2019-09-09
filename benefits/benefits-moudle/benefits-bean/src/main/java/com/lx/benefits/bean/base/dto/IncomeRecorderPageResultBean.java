package com.lx.benefits.bean.base.dto;

import java.math.BigDecimal;
import java.util.List;

import com.lx.benefits.bean.entity.agent.AgentIncomeRecorder;

public class IncomeRecorderPageResultBean extends PageResultBean<AgentIncomeRecorder> {

	private BigDecimal cashTotal;

	public IncomeRecorderPageResultBean() {

	}

	public IncomeRecorderPageResultBean(Integer page, Integer pageSize, Integer count, List<AgentIncomeRecorder> list) {
		super(page, pageSize, count, list);
	}

	public BigDecimal getCashTotal() {
		return cashTotal;
	}

	public void setCashTotal(BigDecimal cashTotal) {
		this.cashTotal = cashTotal;
	}

}
