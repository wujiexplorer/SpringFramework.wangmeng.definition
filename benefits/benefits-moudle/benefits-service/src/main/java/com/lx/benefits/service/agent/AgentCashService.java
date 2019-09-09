package com.lx.benefits.service.agent;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lx.benefits.bean.base.dto.IncomeRecorderPageResultBean;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.agent.AgentIncomeRecorder;
import com.lx.benefits.bean.entity.agent.AgentWallet;
import com.lx.benefits.bean.entity.agent.AgentWithdrawRecorder;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.entity.order.ProductOrderEx;
import com.lx.benefits.bean.vo.agent.AgentEnterpriseProfitBean;
import com.lx.benefits.bean.vo.agent.AgentIncomeDetail;
import com.lx.benefits.bean.vo.agent.AgentOrderProfitBean;
import com.lx.benefits.bean.vo.agent.AgentProfitReport;
import com.lx.benefits.bean.vo.agent.AgentRechargeProfitBean;
import com.lx.benefits.bean.vo.agent.AgentWalletBean;
import com.lx.benefits.bean.vo.agent.AgentWithdrawSearchBean;
import com.lx.benefits.bean.vo.agent.WithdrawCheckingBean;
import com.lx.benefits.bean.vo.agent.WithdrawRequestBean;

public interface AgentCashService {

	/**
	 * 获取代理商的收入明细
	 * 
	 * @param agentId
	 *            代理商ID
	 * @param type
	 *            收入类型
	 * @param createStartTime
	 *            开始时间
	 * @param createEndTime
	 *            结束时间
	 * @param pageBean
	 *            分页效果
	 * @return
	 */
	PageResultBean<AgentIncomeRecorder> getIncomerecorders(Integer agentId, Integer type, Date createStartTime, Date createEndTime, PageBean pageBean);

	IncomeRecorderPageResultBean getIncomerecordersWithCashTotal(Integer agentId, Integer type, Date createStartTime, Date createEndTime, PageBean pageBean);

	/**
	 * 获取代理商提现记录
	 * 
	 * @param agentId
	 *            代理商ID
	 * @param pageBean
	 *            分页效果
	 * @return
	 */
	PageResultBean<AgentWithdrawRecorder> getWithdrawRecorders(Integer agentId, PageBean pageBean);

	/**
	 * 获取代理商钱包详情
	 * 
	 * @param agentId
	 * @return
	 */
	AgentWalletBean getAvailableWithdraw(Integer agentId);

	/**
	 * 代理商申请提现
	 * 
	 * @param agentId
	 *            代理商ID
	 * @param withdrawRequestBean
	 *            提现请求
	 */
	void withdraw(Integer agentId, WithdrawRequestBean withdrawRequestBean);

	/**
	 * 获取提现审核记录
	 * 
	 * @param searchBean
	 *            查询条件
	 * @param pageBean
	 *            分页效果
	 * @return
	 */
	PageResultBean<AgentWithdrawRecorder> getWithdrawrecorders(AgentWithdrawSearchBean searchBean, PageBean pageBean);

	/**
	 * 
	 * @param cashNumber
	 * @param checkingBean
	 * @param checkUser
	 */
	void checkingWithdraw(String cashNumber, WithdrawCheckingBean checkingBean, String checkUser);

	/**
	 * 校验代理商钱包数据
	 * 
	 * <pre>
	 * 	统计分为：全局校验、局部校验
	 *  全局校验:将代理商的收入明细进行统计;
	 * 	局部校验:将上月的统计结果作为起点，进行统计，统计失败则需做一次全局校验，并作记录（如果上月余额+本月收入!=当前实时统计的余额则代表失败）
	 * </pre>
	 * 
	 * @param agentId
	 *            代理商ID
	 * @param globalCheck
	 *            true全局校验,false局部校验
	 * @return
	 */
	AgentWallet checkAgentWallet(Integer agentId, boolean globalCheck);

	/**
	 * 对代理商的钱包进行校验
	 * 
	 * @param globalCheck
	 */
	void checkAgentWallet(boolean globalCheck);

	/**
	 * 获取代理商收益明细
	 * 
	 * @param agentId
	 *            代理商ID
	 * @param id
	 *            收益明细ID
	 */
	AgentIncomeDetail getIncomeDetail(Integer agentId, Integer id);

	/**
	 * 获取代理商返点收益报告
	 * 
	 * @param agentId
	 *            代理商ID
	 * @param rebateType
	 *            返点类型
	 * @return
	 */
	AgentProfitReport getAgentProfitReport(Integer agentId, Integer rebateType);

	/**
	 * 代理商获取企业收益信息
	 * 
	 * @param rebateType
	 * @param agentId
	 * @param enterprName
	 * @param createStartTime
	 * @param createEndTime
	 * @param pageBean
	 * @return
	 */
	PageResultBean<AgentEnterpriseProfitBean> getEnterpriseProfitsView(Integer rebateType, Integer agentId, String enterprName, Date createStartTime,
			Date createEndTime, PageBean pageBean);

	/**
	 * 获取企业充值收益
	 * 
	 * @param agentId
	 * @param enterprId
	 * @param pageBean
	 * @return
	 */
	PageResultBean<AgentRechargeProfitBean> getEnterpriseRechargeProfits(Integer agentId, Long enterprId, PageBean pageBean);

	PageResultBean<AgentOrderProfitBean> getEnterpriseOrderProfits(Integer agentId, Long enterprId, String orderNumber, String employeeName,
			Date orderStartTime, Date orderEndTime, PageBean pageBean);

	/**
	 * 添加代理商的充值返点收益记录
	 * 
	 * @param voucherNumber
	 *            凭证信息
	 * @param cashAmount
	 *            充值金额
	 * @param enterprId
	 *            企业ID
	 */
	void addRechargeIncomeRecorde(String voucherNumber, BigDecimal cashAmount, Long enterprId);

	/**
	 * 添加代理商异常订单收益记录
	 * 
	 * @param itemOrderNumbers
	 *            商品级别订单号
	 */
	void addAbnormalOrderIncomeRecorde(List<String> itemOrderNumbers);

	/**
	 * 添加代理商订单收益记录
	 * @param productOrderExs 
	 * 
	 * @param productOrders
	 *            商品级别订单信息
	 * @param payOrderNumber
	 *            支付级订单号
	 */
	void addOrderIncomeRecorder(List<ProductOrderEx> productOrderExs, List<Order> productOrders, Long payOrderNumber);

}
