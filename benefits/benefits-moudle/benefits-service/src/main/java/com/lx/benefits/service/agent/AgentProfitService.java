package com.lx.benefits.service.agent;

import java.util.Date;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.vo.agent.AgentEnterpriseProfitBean;
import com.lx.benefits.bean.vo.agent.AgentProfitReport;

public interface AgentProfitService {

	AgentProfitReport getProfitReport(Integer agentId);

	PageResultBean<AgentEnterpriseProfitBean> getEnterpriseProfitsView(Integer agentId, String enterprName, Date createStartTime, Date createEndTime,
			PageBean pageBean);

	 PageResultBean<?> getEnterpriseProfits(Integer agentId, Long enterprId, String orderNumber, String employeeName, Date orderStartTime, Date orderEndTime,
			PageBean pageBean);

	AgentProfitReport getProfitReportWithRebate(Integer agentId);
}
