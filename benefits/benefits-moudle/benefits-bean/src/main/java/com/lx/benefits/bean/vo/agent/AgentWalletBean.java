package com.lx.benefits.bean.vo.agent;

import java.math.BigDecimal;

import com.lx.benefits.bean.entity.agent.AgentWallet;

public class AgentWalletBean extends AgentWallet {
	private static final long serialVersionUID = 1L;

	private BigDecimal availableCashWithdraw;

	public BigDecimal getAvailableCashWithdraw() {
		return availableCashWithdraw;
	}

	public void setAvailableCashWithdraw(BigDecimal availableCashWithdraw) {
		this.availableCashWithdraw = availableCashWithdraw;
	}

}
