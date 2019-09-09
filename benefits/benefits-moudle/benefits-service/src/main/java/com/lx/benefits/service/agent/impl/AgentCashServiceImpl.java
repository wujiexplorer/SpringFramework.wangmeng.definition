package com.lx.benefits.service.agent.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.lx.benefits.bean.constants.*;
import com.lx.benefits.bean.entity.agent.*;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.service.agent.AgentRebateDeployService;
import com.lx.benefits.service.product.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.IncomeRecorderPageResultBean;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeInfoDto;
import com.lx.benefits.bean.entity.agent.AgentIncomeRecorderCondition.Criteria;
import com.lx.benefits.bean.entity.entercreditinfo.CreditChekingRecorder;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfoCondition;
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
import com.lx.benefits.mapper.agent.AgentIncomeRecorderMapper;
import com.lx.benefits.mapper.agent.AgentWalletCheckingRecorderMapper;
import com.lx.benefits.mapper.agent.AgentWalletMapper;
import com.lx.benefits.mapper.agent.AgentWithdrawRecorderMapper;
import com.lx.benefits.mapper.entercreditinfo.CreditChekingRecorderMapper;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.service.agent.AgentCashService;
import com.lx.benefits.service.agent.AgentService;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.enterprise.EnterpriseService;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.service.order.ProductOrderExService;

@Service
public class AgentCashServiceImpl implements AgentCashService {

	@Autowired
	private AgentIncomeRecorderMapper incomeRecorderMapper;
	@Autowired
	private AgentWithdrawRecorderMapper withdrawRecorderMapper;
	@Autowired
	private AgentIncomeRecorderMapper agentIncomeRecorderMapper;
	@Autowired
	private AgentWalletMapper agentWalletMapper;
	@Autowired
	private AgentWalletCheckingRecorderMapper walletCheckingMapper;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private EmployeeUserInfoService employeeUserInfoService;
	@Autowired
	private CreditChekingRecorderMapper creditChekingRecorderMapper;
	@Autowired
	private ProductOrderExService productOrderExService;
	@Autowired
	private EnterprUserInfoMapper enterprUserInfoMapper;
	@Autowired
	private AgentRebateDeployService agentRebateDeployService;
	@Autowired
	private ProductService productService;

	@Value("${agent.cash.withdrawDay:15}")
	private Integer availableWithdrawDay;
	private final String dateFormatPattern = "yyMMddHHmmssSSS";

	@Override
	public PageResultBean<AgentIncomeRecorder> getIncomerecorders(Integer agentId, Integer type, Date createStartTime, Date createEndTime, PageBean pageBean) {
		AgentIncomeRecorderCondition example = new AgentIncomeRecorderCondition();
		Criteria criteria = example.createCriteria().andAgentIdEqualTo(agentId);
		if (type != null) {
			criteria.andTypeEqualTo(type);
		}
		if (createStartTime != null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(createStartTime);
		}
		if (createEndTime != null) {
			criteria.andCreateTimeLessThanOrEqualTo(createEndTime);
		}
		int count = (int) incomeRecorderMapper.countByExample(example);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<AgentIncomeRecorder> list;
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			example.setOffset((page - 1) * pageSize);
			example.setLimit(pageSize);
			example.setOrderByClause("create_time DESC");
			list = incomeRecorderMapper.selectByExample(example);
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public IncomeRecorderPageResultBean getIncomerecordersWithCashTotal(Integer agentId, Integer type, Date createStartTime, Date createEndTime,
			PageBean pageBean) {
		PageResultBean<AgentIncomeRecorder> incomerecorders = this.getIncomerecorders(agentId, type, createStartTime, createEndTime, pageBean);
		IncomeRecorderPageResultBean result = new IncomeRecorderPageResultBean(incomerecorders.getPage(), incomerecorders.getPageSize(),
				incomerecorders.getCount(), incomerecorders.getList());
		AgentWalletCondition agentWalletCondition = new AgentWalletCondition();
		agentWalletCondition.createCriteria().andAgentIdEqualTo(agentId);
		List<AgentWallet> agentWallets = agentWalletMapper.selectByExample(agentWalletCondition);
		if (!CollectionUtils.isEmpty(agentWallets)) {
			result.setCashTotal(agentWallets.get(0).getCashTotal());
		} else {
			result.setCashTotal(BigDecimal.valueOf(0));
		}
		return result;
	}

	@Override
	public PageResultBean<AgentWithdrawRecorder> getWithdrawRecorders(Integer agentId, PageBean pageBean) {
		AgentWithdrawRecorderCondition example = new AgentWithdrawRecorderCondition();
		example.createCriteria().andAgentIdEqualTo(agentId);
		int count = (int) withdrawRecorderMapper.countByExample(example);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<AgentWithdrawRecorder> list;
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			example.setOffset((page - 1) * pageSize);
			example.setLimit(pageSize);
			example.setOrderByClause("apply_time DESC");
			list = withdrawRecorderMapper.selectByExample(example);
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public AgentWalletBean getAvailableWithdraw(Integer agentId) {
		AgentWallet agentWallet = this.getAgentWallet(agentId);
		if (!AgentWalletStatus.NORMAL.getStatus().equals(agentWallet.getStatus())) {// 非正常状态
			throw new BusinessException("余额出现异常，已被冻结，请联系客服!");
		}
		AgentWalletBean walletBean = new AgentWalletBean();
		walletBean.setCashTotal(agentWallet.getCashTotal());
		// 根据当月提现记录获得当月剩余可提现金额
		Date currentMonthFirstTime = this.getMonthFirstTime(Calendar.getInstance()); // 获取当月的第一天的00:00:00,
		AgentIncomeRecorderCondition example = new AgentIncomeRecorderCondition();
		example.createCriteria().andAgentIdEqualTo(agentId).andTypeEqualTo(AgentIncomeType.WITHDRAW.getType())
				.andCreateTimeGreaterThanOrEqualTo(currentMonthFirstTime);
		List<AgentIncomeRecorder> withdrawRecorders = agentIncomeRecorderMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(withdrawRecorders)) {
			walletBean.setAvailableCashWithdraw(agentWallet.getHistoryCashTotal());
		} else {
			Optional<BigDecimal> withdrawedCash = withdrawRecorders.stream().map(AgentIncomeRecorder::getCashAmount).reduce(BigDecimal::add);
			walletBean.setAvailableCashWithdraw(agentWallet.getHistoryCashTotal().add(withdrawedCash.get()));
		}
		return walletBean;
	}

	// 统计截止到上月的收入总额，和截止到当天的收入总额
	@Override
	public AgentWallet checkAgentWallet(Integer agentId, boolean globalCheck) {
		if (globalCheck) {// 全局统计
			BigDecimal cashTotal = incomeRecorderMapper.sumCashIncomeTotal(agentId, null, null);// 截止到当天收入总额
			if (cashTotal == null) {
				cashTotal = BigDecimal.valueOf(0);
			}
			Date currentMonthFirstTime = this.getMonthFirstTime(Calendar.getInstance());// 获取当月的第一天的00:00:00,
			BigDecimal historyCashTotal = incomeRecorderMapper.sumCashIncomeTotal(agentId, null, currentMonthFirstTime);// 截止到上月月末收益总额
			if (historyCashTotal == null) {
				historyCashTotal = BigDecimal.valueOf(0);
			}
			AgentWallet agentWallet = new AgentWallet();
			agentWallet.setAgentId(agentId);
			agentWallet.setCashTotal(cashTotal);
			agentWallet.setHistoryCashTotal(historyCashTotal);
			agentWallet.setCheckTime(new Date());
			agentWalletMapper.updateByPrimaryKeySelective(agentWallet);
			return agentWalletMapper.selectByPrimaryKey(agentId);
		}
		// 仅仅统计未统计的部分
		AgentWallet dbagentWallet = agentWalletMapper.selectByPrimaryKey(agentId);
		if (dbagentWallet == null) {
			return null;
		}
		Date checkTime = dbagentWallet.getCheckTime();
		Calendar instance = Calendar.getInstance();
		instance.setTime(checkTime);
		Date monthFirstTime = this.getMonthFirstTime(instance);
		BigDecimal historyCashTotal = dbagentWallet.getHistoryCashTotal();
		BigDecimal sumCashIncomeToTal = incomeRecorderMapper.sumCashIncomeTotal(agentId, monthFirstTime, null);
		if (sumCashIncomeToTal == null) {
			sumCashIncomeToTal = BigDecimal.valueOf(0);
		}
		if (historyCashTotal.add(sumCashIncomeToTal).equals(dbagentWallet.getCashTotal())) {// 校对成功
			BigDecimal sum = incomeRecorderMapper.sumCashIncomeTotal(agentId, monthFirstTime, this.getMonthFirstTime(Calendar.getInstance()));
			if (sum == null) {
				sum = BigDecimal.valueOf(0);
			}
			AgentWallet record = new AgentWallet();
			record.setAgentId(agentId);
			BigDecimal updatedHistory = historyCashTotal.add(sum);
			record.setHistoryCashTotal(updatedHistory);
			record.setCheckTime(new Date());
			agentWalletMapper.updateByPrimaryKeySelective(record);
			dbagentWallet.setHistoryCashTotal(updatedHistory);
			return dbagentWallet;
		} else {// 校验失败,进行全局校验，并做好校验失败记录
			AgentWalletCheckingRecorder checkingRecorder = new AgentWalletCheckingRecorder();
			BeanUtils.copyProperties(dbagentWallet, checkingRecorder);
			walletCheckingMapper.insertSelective(checkingRecorder);
			return this.checkAgentWallet(agentId, true);
		}

	}

	private AgentWallet getAgentWallet(Integer agentId) {
		AgentWallet agentWallet = agentWalletMapper.selectByPrimaryKey(agentId);
		if (agentWallet == null) {// 初始化钱包数据
			agentWallet = new AgentWallet();
			agentWallet.setHistoryCashTotal(BigDecimal.valueOf(0));
			agentWallet.setCashTotal(BigDecimal.valueOf(0));
			agentWallet.setAgentId(agentId);
			agentWallet.setStatus(AcountStatus.NORMAL.getStatus());
			agentWallet.setCheckTime(new Date());// 校验时间
			try {
				agentWalletMapper.insertSelective(agentWallet);
			} catch (DuplicateKeyException e) {
				agentWallet = agentWalletMapper.selectByPrimaryKey(agentId);
			}
		}
		return agentWallet;
	}

	@Override
	public void withdraw(Integer agentId, WithdrawRequestBean withdrawRequestBean) {
		BigDecimal withdrawCashAmount = withdrawRequestBean.getCashAmount();
		if (withdrawCashAmount == null || withdrawCashAmount.doubleValue() <= 0) {
			throw new BusinessException("请输入正确的提现金额!");
		}
		Calendar instance = Calendar.getInstance();
		int day = instance.get(Calendar.DAY_OF_MONTH);
		if (availableWithdrawDay.compareTo(day) > 0) {// 未到达提现时间
			throw new BusinessException("申请失败,请在每月" + availableWithdrawDay + "日以后申请提现!");
		}
		AgentWithdrawRecorderCondition example = new AgentWithdrawRecorderCondition();
		example.createCriteria().andAgentIdEqualTo(agentId).andCheckStatusEqualTo(CheckStatus.INIT.getStatus());
		long unCheckCount = withdrawRecorderMapper.countByExample(example);
		if (unCheckCount > 0) {
			throw new BusinessException("申请失败,您还有待审批的申请未处理!");
		}
		AgentWalletBean availableWithdraw = this.getAvailableWithdraw(agentId);
		if (availableWithdraw.getAvailableCashWithdraw().compareTo(withdrawRequestBean.getCashAmount()) < 0) {
			throw new BusinessException("申请失败,提现金额不能大于可提现金额!");
		}
		AgentWithdrawRecorder record = new AgentWithdrawRecorder();
		record.setAgentId(agentId);
		record.setApplyTime(new Date());
		record.setCashAmount(withdrawRequestBean.getCashAmount());
		record.setBankCardNumber(withdrawRequestBean.getBankCardNumber());
		record.setBankName(withdrawRequestBean.getBankName());
		record.setCashNumber("AC" + getnerateCashNumber());
		for (int i = 0; i < 3; i++) {// 流水号冲突 重试3次
			try {
				withdrawRecorderMapper.insertSelective(record);
				break;
			} catch (DuplicateKeyException e) {
				try {
					Thread.sleep(1);
					record.setCashNumber("AC" + getnerateCashNumber());
				} catch (InterruptedException e1) {
				}
			}
		}
	}

	// 生成流水号,时间戳+[0-9]的随机数，借助数据库的唯一索引来避免重复
	private String getnerateCashNumber() {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
		return dateFormat.format(new Date()) + ThreadLocalRandom.current().nextInt(10);
	}

	@Override
	public PageResultBean<AgentWithdrawRecorder> getWithdrawrecorders(AgentWithdrawSearchBean searchBean, PageBean pageBean) {
		searchBean.setPageBean(pageBean);
		int count = withdrawRecorderMapper.countByExampleWithAgentName(searchBean);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<AgentWithdrawRecorder> list;
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			list = withdrawRecorderMapper.selectByExampleWithAgentName(searchBean);
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Transactional
	@Override
	public void checkingWithdraw(String cashNumber, WithdrawCheckingBean checkingBean, String checkUser) {
		AgentWithdrawRecorder record = new AgentWithdrawRecorder();
		if (CheckStatus.PASSED.getStatus().equals(checkingBean.getCheckStatus())) {// 审核通过
			String payVoucher = checkingBean.getPayVoucher();
			if (payVoucher == null || "".equals(payVoucher = payVoucher.trim())) {
				throw new BusinessException("打款凭证不能为空!");
			}
			record.setPayVoucher(payVoucher);
		} else if (CheckStatus.UNPASSED.getStatus().equals(checkingBean.getCheckStatus())) {// 审核未通过
			String checkRemark = checkingBean.getCheckRemark();
			if (checkRemark == null || "".equals(checkRemark = checkRemark.trim())) {
				throw new BusinessException("备注信息不能为空!");
			}
			record.setCheckRemark(checkRemark);
		} else {
			throw new BusinessException("无效的审批状态!");
		}
		AgentWithdrawRecorder dbRecorder = withdrawRecorderMapper.selectByPrimaryKey(cashNumber);
		if (dbRecorder == null) {
			throw new BusinessException("申请记录不存在");
		}
		if (!CheckStatus.INIT.getStatus().equals(dbRecorder.getCheckStatus())) {
			throw new BusinessException("请勿重复审批");
		}
		record.setCheckStatus(checkingBean.getCheckStatus());
		record.setCheckTime(new Date());
		record.setCheckUser(checkUser);
		AgentWithdrawRecorderCondition example = new AgentWithdrawRecorderCondition();
		example.createCriteria().andCashNumberEqualTo(cashNumber).andCheckStatusEqualTo(CheckStatus.INIT.getStatus());
		int updateWithdrawCount = withdrawRecorderMapper.updateByExampleSelective(record, example);
		if (updateWithdrawCount == 0) {
			throw new BusinessException("请勿重复审批");// 进到这个逻辑里面说明同一时刻有两个请求同时发起
		}
		// 审核通过后扣减相应的代理商余额，并产生一条提现记录
		if (CheckStatus.PASSED.getStatus().equals(checkingBean.getCheckStatus())) {
			BigDecimal cashAmount = dbRecorder.getCashAmount();
			if (cashAmount.doubleValue() < 0) {
				throw new BusinessException("提现金额必须大于零");
			}
			// 添加一条提现记录,并扣更新余额信息
			BigDecimal updateAmount = cashAmount.multiply(BigDecimal.valueOf(-1));
			AgentIncomeRecorder incomeRecorder = new AgentIncomeRecorder();
			incomeRecorder.setAgentId(dbRecorder.getAgentId());
			incomeRecorder.setCashAmount(updateAmount);// 提现将金额设置为负数
			incomeRecorder.setType(AgentIncomeType.WITHDRAW.getType());
			incomeRecorder.setVoucherNumber(cashNumber);
			agentIncomeRecorderMapper.insertSelective(incomeRecorder);
			this.updateAgentCash(dbRecorder.getAgentId(), updateAmount, true);
		}
	}

	// 因为余额发生变化比较频繁，通过CAS方式尝试三次
	private int updateAgentCash(Integer agentId, BigDecimal updateAmout, boolean withCheck) {
		AgentWallet agentWallet = this.getAgentWallet(agentId);
		BigDecimal oldCashTotal = agentWallet.getCashTotal();
		BigDecimal newCashTotal = oldCashTotal.add(updateAmout);
		if (withCheck && newCashTotal.doubleValue() < 0) {
			throw new BusinessException("余额不足,处理失败!");
		}
		AgentWalletCondition condition = new AgentWalletCondition();
		condition.createCriteria().andCashTotalEqualTo(oldCashTotal).andAgentIdEqualTo(agentId);
		AgentWallet udpatedRecord = new AgentWallet();
		udpatedRecord.setCashTotal(newCashTotal);
		int updateCount = agentWalletMapper.updateByExampleSelective(udpatedRecord, condition);
		for (int i = 0; updateCount == 0 && i < 3; i++) {// 更新失败尝试三次
			updateCount = updateAgentCash(agentId, updateAmout, withCheck);
		}
		return updateCount;
	}

	// 获取当月的第一天的00:00:00
	private Date getMonthFirstTime(Calendar calendar) {
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	// 100为单位进行校验
	@Override
	public void checkAgentWallet(boolean globalCheck) {
		int count = (int) agentWalletMapper.countByExample(null);
		if (count == 0) {
			return;
		}
		int page = (count - 1) / 100 + 1;
		for (int i = 0; i < page; i++) {
			AgentWalletCondition example = new AgentWalletCondition();
			example.setOffset(i * 100);
			example.setLimit(100);
			List<AgentWallet> agentWallets = agentWalletMapper.selectByExample(example);
			for (AgentWallet agentWallet : agentWallets) {
				this.checkAgentWallet(agentWallet.getAgentId(), globalCheck);
			}
		}
	}

	@Transactional(propagation = Propagation.MANDATORY) // 必须在事务中执行
	@Override
	// 充值收益
	public void addRechargeIncomeRecorde(String voucherNumber, BigDecimal cashAmount, Long enterprId) {
		EnterprUserInfo enterpr = enterpriseService.getEnterprise(enterprId);
		Integer agentId;
		if (StringUtils.isEmpty(voucherNumber) || enterpr == null || (agentId = enterpr.getAgentId()) == 0) {
			return;
		}
		AgentLevel agentLevel = agentService.getAgentlevel(agentId);
		Integer rebateType = agentLevel.getRebateType();
		if (!AgentRebateType.RECHARGE.getType().equals(rebateType)) {// 非充值返点模式
			return;
		}
		AgentIncomeRecorder incomeRecorder = new AgentIncomeRecorder();
		incomeRecorder.setAgentId(agentId);
		incomeRecorder.setType(AgentIncomeType.RECHARGE.getType());
		incomeRecorder.setVoucherNumber(voucherNumber);
		BigDecimal profitProportion = agentLevel.getProfitProportion();
		incomeRecorder.setProfitProportion(profitProportion);
		BigDecimal updateAmount = cashAmount.multiply(profitProportion.divide(BigDecimal.valueOf(100)));// 金额*收益比例
		incomeRecorder.setCashAmount(updateAmount);
		incomeRecorder.setEnterprId(enterprId);
		agentIncomeRecorderMapper.insertSelective(incomeRecorder);
		this.updateAgentCash(agentId, updateAmount, false);
	}

	// 销售额/利润额收益
	@Override
	public void addOrderIncomeRecorder(List<ProductOrderEx> productOrderExs, List<Order> productOrders, Long payOrderNumber) {
		Long buyerUserId;
		if (CollectionUtils.isEmpty(productOrders) || CollectionUtils.isEmpty(productOrderExs) || payOrderNumber == null
				|| (buyerUserId = productOrders.get(0).getBuyerUserId()) == null) {
			return;
		}
		EnterprUserInfo enterpr = enterpriseService.getEnterpriseByEmployeeId(buyerUserId);
		Integer agentId;
		AgentLevel agentLevel;
		if (enterpr == null || (agentId = enterpr.getAgentId()) == 0 || (agentLevel = agentService.getAgentlevel(agentId)) == null) {
			return;
		}
		Integer rebateType = agentLevel.getRebateType();
		Map<Long, ProductOrderEx> productMap = null;
		if (AgentRebateType.PROFIT.getType().equals(rebateType)|| AgentRebateType.OLDPROFIT.getType().equals(rebateType)) {
			productMap = productOrderExs.stream().collect(Collectors.toMap(ProductOrderEx::getOrderNumber, Function.identity(), (item1, item2) -> item2));
		}
		for (Order order : productOrders) {
			BigDecimal cashAmount = null;
			BigDecimal managementFee = null;
			Integer incomeType;
			BigDecimal saleTax = BigDecimal.ONE;
			Long cardAmount = order.getCardAmount();
			if (cardAmount == null) {
				cardAmount = 0L;
			}
			Long salePrice = order.getPrice() + order.getPointAmount() - cardAmount;// 积分+金额-会员卡积分
			if (salePrice < 0) {
				continue;
			}
			if (AgentRebateType.SALES.getType().equals(rebateType)) {// 销售额返点
				cashAmount = BigDecimal.valueOf(salePrice).divide(BigDecimal.valueOf(100));
				incomeType = AgentIncomeType.SALE_ORDER.getType();
			} else if (AgentRebateType.PROFIT.getType().equals(rebateType)) {// 利润额返点(新)
				salePrice = order.getPrice() + order.getPointAmount();
				ProductOrderEx productOrderEx = productMap.get(order.getNumber());
				Long costPrice;
				if (productOrderEx == null || (costPrice = productOrderEx.getCostPrice()) == null || costPrice <= 0 || order.getQuantity() <= 0) {
					continue;
				}
				AgentRebateDeploy agentRebateDeploy = agentRebateDeployService.findAgentRebateDeploys();
				ProductEntity product = productService.selectById(order.getSpuCode());
				BigDecimal ratio = null;
				if(product.getGoodsType() == 0){
					ratio = agentRebateDeploy.getMatterTax();
				}else if(product.getGoodsType() == 1){
					ratio = agentRebateDeploy.getVirtualTax();
				}
				if(cardAmount == 0) {//不带会员卡积分支付的订单
					BigDecimal saleIncome = BigDecimal.valueOf(salePrice - order.getShipExpense()).divide(BigDecimal.valueOf(100));
					saleTax = BigDecimal.valueOf(salePrice-costPrice * order.getQuantity()-order.getShipExpense()).divide(BigDecimal.valueOf(100)).multiply(ratio.divide(BigDecimal.valueOf(100)));
					managementFee = saleIncome.multiply(agentRebateDeploy.getManagementFeeRatio().divide(BigDecimal.valueOf(100)));
					cashAmount = BigDecimal.valueOf(salePrice - costPrice * order.getQuantity()-order.getShipExpense()).divide(BigDecimal.valueOf(100));
				}
                if(cardAmount > 0) {//会员卡积分支付订单
                    Long orderPrice = salePrice + order.getShipExpense() + order.getShipExpensePointAmount();//订单金额
                    Long orderCostPrice = costPrice * order.getQuantity() + order.getShipExpense() + order.getShipExpensePointAmount();//总成本 = 商品成本 + 运费 
                    Long exceptCardPrice = orderPrice - cardAmount;//排除会员卡积分支付
                    if(exceptCardPrice <= 0) {
                        continue;
                    }
                    BigDecimal agentScale = BigDecimal.valueOf(exceptCardPrice).divide(BigDecimal.valueOf(orderPrice),4,RoundingMode.HALF_UP);//代理商佣金核算比例
                    Long profit = orderPrice - orderCostPrice;//利润
                    if(profit <= 0) {
                        continue;
                    }
                    BigDecimal antipointsProfit = BigDecimal.valueOf(profit).divide(BigDecimal.valueOf(100)).multiply(agentScale);//代理商返点计算利润
                    saleTax = antipointsProfit.multiply(ratio.divide(BigDecimal.valueOf(100)));//销售税金
                    managementFee = (BigDecimal.valueOf(salePrice).divide(BigDecimal.valueOf(100))).multiply(agentRebateDeploy.getManagementFeeRatio().divide(BigDecimal.valueOf(100)));//综合管理费
                    cashAmount = antipointsProfit.subtract(saleTax).subtract(managementFee);
                }
				incomeType = AgentIncomeType.PROFIT_ORDER.getType();
			}else if (AgentRebateType.OLDPROFIT.getType().equals(rebateType)) {// 利润额返点(旧)
				ProductOrderEx productOrderEx = productMap.get(order.getNumber());
				Long costPrice;
				if (productOrderEx == null || (costPrice = productOrderEx.getCostPrice()) == null || costPrice <= 0 || order.getQuantity() <= 0) {
					continue;
				}
				cashAmount = BigDecimal.valueOf(salePrice - costPrice * order.getQuantity()).divide(BigDecimal.valueOf(100));
				incomeType = AgentIncomeType.PROFIT_ORDER.getType();
			} else {
				continue;
			}
			if (cashAmount.compareTo(BigDecimal.ZERO) <= 0 || saleTax.compareTo(BigDecimal.ZERO)<=0) {
				continue;
			}
			BigDecimal profitProportion = agentLevel.getProfitProportion();
			BigDecimal updateAmount = cashAmount.multiply(profitProportion.divide(BigDecimal.valueOf(100)));// 金额*收益比例
			if (AgentRebateType.PROFIT.getType().equals(rebateType))
			{// 利润额返点
				if(agentLevel.getIsProxyOperative().equals(AgentOpreativeType.NO_AGENT_OPREATIVE.getCode())){
					profitProportion = agentLevel.getProfitProportion().add(agentLevel.getBrandBuildingFee()).
							add(agentLevel.getMarketFee()).add(agentLevel.getTeamBuildingFee());
				}else if(agentLevel.getIsProxyOperative().equals(AgentOpreativeType.YES_AGENT_OPREATIVE.getCode())){
					profitProportion = (agentLevel.getProfitProportion().divide(BigDecimal.valueOf(2))).add(agentLevel.getBrandBuildingFee()).
							add(agentLevel.getMarketFee()).add(agentLevel.getTeamBuildingFee());
				}
				BigDecimal award = (cashAmount.subtract(saleTax).subtract(managementFee)).multiply(agentLevel.getIntroducerAward().divide(BigDecimal.valueOf(100)));
				award = award.setScale(2, RoundingMode.HALF_UP);
				updateAmount = (cashAmount.subtract(saleTax).subtract(managementFee)).multiply(profitProportion.divide(BigDecimal.valueOf(100)));
                if(cardAmount > 0) {//会员卡支付订单
                	award = cashAmount.multiply(agentLevel.getIntroducerAward().divide(BigDecimal.valueOf(100))).setScale(2, RoundingMode.HALF_UP);
                	updateAmount = cashAmount.multiply(profitProportion.divide(BigDecimal.valueOf(100)));
                }
                if(award.compareTo(BigDecimal.ZERO) <= 0){
                	continue;
                }
				Integer incomeAwardType = AgentIncomeType.INTRODUCER_AWARD.getType();
				AgentIncomeRecorder incomeRecorder = new AgentIncomeRecorder();
				incomeRecorder.setAgentId(agentId);
				incomeRecorder.setType(incomeAwardType);
				incomeRecorder.setVoucherNumber(order.getNumber().toString());
				incomeRecorder.setCashAmount(award);
				incomeRecorder.setEnterprId(enterpr.getEnterprId());
				incomeRecorder.setProfitProportion(profitProportion);
				incomeRecorder.setPayOrderNumber(payOrderNumber.toString());
				agentIncomeRecorderMapper.insertSelective(incomeRecorder);
				this.updateAgentCash(agentId, award, false);
			}
			updateAmount = updateAmount.setScale(2, RoundingMode.HALF_UP);
			if (updateAmount.doubleValue() <= 0) {// 亏本卖出
				continue;
			}
			AgentIncomeRecorder incomeRecorder = new AgentIncomeRecorder();
			incomeRecorder.setAgentId(agentId);
			incomeRecorder.setType(incomeType);
			incomeRecorder.setVoucherNumber(order.getNumber().toString());
			incomeRecorder.setCashAmount(updateAmount);
			incomeRecorder.setEnterprId(enterpr.getEnterprId());
			incomeRecorder.setProfitProportion(profitProportion);
			incomeRecorder.setPayOrderNumber(payOrderNumber.toString());
			agentIncomeRecorderMapper.insertSelective(incomeRecorder);
			this.updateAgentCash(agentId, updateAmount, false);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY) // 必须在事务中执行
	@Override
	// 异常订单扣减
	public void addAbnormalOrderIncomeRecorde(List<String> itemOrderNumbers) {
		AgentIncomeRecorderCondition example = new AgentIncomeRecorderCondition();
		example.createCriteria().andVoucherNumberIn(itemOrderNumbers);
		List<AgentIncomeRecorder> agentIncomeRecorders = agentIncomeRecorderMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(agentIncomeRecorders)) {
			return;
		}
		for (AgentIncomeRecorder agentIncomeRecorder : agentIncomeRecorders) {
			AgentIncomeRecorder incomeRecorder = new AgentIncomeRecorder();
			BigDecimal updateAmount = agentIncomeRecorder.getCashAmount().multiply(BigDecimal.valueOf(-1));
			BeanUtils.copyProperties(agentIncomeRecorder, incomeRecorder, "id", "createTime", "type");
			incomeRecorder.setCashAmount(updateAmount);
			incomeRecorder.setType(AgentIncomeType.ABNORMAL_ORDER.getType());
			try {
				agentIncomeRecorderMapper.insertSelective(incomeRecorder);
				this.updateAgentCash(agentIncomeRecorder.getAgentId(), updateAmount, false);
			} catch (DuplicateKeyException e) {
			}
		}
	}

	@Override
	public AgentIncomeDetail getIncomeDetail(Integer agentId, Integer id) {
		AgentIncomeRecorder agentIncomeRecorder = agentIncomeRecorderMapper.selectByPrimaryKey(id);
		if (agentIncomeRecorder == null || !agentIncomeRecorder.getAgentId().equals(agentId)) {
			throw new BusinessException("该收支明细不存在!");
		}
		String voucherNumber = agentIncomeRecorder.getVoucherNumber();
		Integer type = agentIncomeRecorder.getType();
		AgentIncomeDetail agentIncomeDetail = new AgentIncomeDetail(type, voucherNumber, agentIncomeRecorder.getCashAmount());
		if (AgentIncomeType.WITHDRAW.getType().equals(type)) {// 提现
			AgentWithdrawRecorder withdrawRecorder = withdrawRecorderMapper.selectByPrimaryKey(voucherNumber);
			agentIncomeDetail.setCheckStatus(agentIncomeDetail.getCheckStatus());
			agentIncomeDetail.setApplyTime(withdrawRecorder.getApplyTime());
			agentIncomeDetail.setCheckRemark(withdrawRecorder.getCheckRemark());
			agentIncomeDetail.setCheckTime(withdrawRecorder.getCheckTime());
			agentIncomeDetail.setCheckStatus(withdrawRecorder.getCheckStatus());
		} else if (AgentIncomeType.RECHARGE.getType().equals(type)) {// 充值返点
			CreditChekingRecorder creditRecorder = creditChekingRecorderMapper.selectByPrimaryKey(voucherNumber);
			agentIncomeDetail.setCreditType(creditRecorder.getCreditType());
			EnterprUserInfo enterprise = enterpriseService.getEnterprise(creditRecorder.getEnterprId());
			agentIncomeDetail.setEnterprName(enterprise.getEnterprName());
		} else if (AgentIncomeType.SALE_ORDER.getType().equals(type) || AgentIncomeType.PROFIT_ORDER.getType().equals(type)
				|| AgentIncomeType.ABNORMAL_ORDER.getType().equals(type) || AgentIncomeType.INTRODUCER_AWARD.getType().equals(type)) {// 订单收益
			Order order = orderService.getOrderByNumber(Long.valueOf(voucherNumber));
			agentIncomeDetail.setCreateTime(order.getCreateTime());
			agentIncomeDetail.setOrderStatus(order.getStatus());
			agentIncomeDetail.setQuantity(order.getQuantity());
			EmployeeInfoDto employeeInfoDto = employeeUserInfoService.findByEmployeeId(order.getBuyerUserId(), true);
			EnterprUserInfo enterprUserInfo = enterpriseService.getEnterprise(employeeInfoDto.getEnterprId());
			if (employeeInfoDto != null) {
				agentIncomeDetail.setEmployeeName(employeeInfoDto.getEmployeeName());
				agentIncomeDetail.setEmployeeNo(employeeInfoDto.getEmployeeNo());
				agentIncomeDetail.setEnterprName(enterprUserInfo.getEnterprName());
			}
			// 订单收益相关
			ProductOrderEx productOrderEx = productOrderExService.selectByOrderNumber(order.getNumber());
			if (productOrderEx != null) {
				agentIncomeDetail.setProductName(productOrderEx.getTitle());
			}
		} else {
			throw new BusinessException("获取明细失败!");
		}
		return agentIncomeDetail;
	}

	@Override
	public AgentProfitReport getAgentProfitReport(Integer agentId, Integer rebateType) {
		AgentProfitReport agentProfitReport = null;
		if (AgentRebateType.RECHARGE.getType().equals(rebateType)) {// 充值返点
			agentProfitReport = agentIncomeRecorderMapper.getAgentRechargeProfitReport(agentId);
			if (agentProfitReport == null) {
				agentProfitReport = new AgentProfitReport();
				agentProfitReport.setRechargeCount(0);
				agentProfitReport.setTotalRecharge(BigDecimal.valueOf(0));
				agentProfitReport.setTotalIncome(BigDecimal.valueOf(0));
			}
		} else if (AgentRebateType.SALES.getType().equals(rebateType) // 销售额返点
				|| AgentRebateType.PROFIT.getType().equals(rebateType)) {// 利润额返点
			agentProfitReport = agentIncomeRecorderMapper.getAgentOrderProfitReport(agentId);
			if (agentProfitReport == null) {
				agentProfitReport = new AgentProfitReport();
				agentProfitReport.setOrderCount(0);
				agentProfitReport.setTotalIncome(BigDecimal.valueOf(0));
			}
		} else {
			agentProfitReport = new AgentProfitReport();
		}
		agentProfitReport.setAgentId(agentId);
		agentProfitReport.setRebateType(rebateType);// 设置收益方式
		return agentProfitReport;
	}

	@Override
	public PageResultBean<AgentEnterpriseProfitBean> getEnterpriseProfitsView(Integer rebateType, Integer agentId, String enterprName, Date createStartTime,
			Date createEndTime, PageBean pageBean) {
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<AgentEnterpriseProfitBean> list;
		EnterprUserInfoCondition example = new EnterprUserInfoCondition();
		EnterprUserInfoCondition.Criteria criteria = example.createCriteria().andAgentIdEqualTo(agentId);
		if (enterprName != null) {
			criteria.andEnterprNameLike("%" + enterprName + "%");
		}
		if (createStartTime != null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(createStartTime);
		}
		if (createEndTime != null) {
			criteria.andCreateTimeLessThan(createEndTime);
		}
		int count = (int) enterprUserInfoMapper.countByExample(example);
		if (count == 0) {
			return new PageResultBean<>(page, pageSize, count, Collections.emptyList());
		}

		if (AgentRebateType.RECHARGE.getType().equals(rebateType)) {// 充值返点
			list = agentIncomeRecorderMapper.getEnterpriseRechargeProfitsView(agentId, enterprName, createStartTime, createEndTime, pageBean);
		} else if (AgentRebateType.SALES.getType().equals(rebateType) // 销售额返点
				|| AgentRebateType.PROFIT.getType().equals(rebateType)) {// 利润额返点
			list = agentIncomeRecorderMapper.getEnterpriseOrderProfitsView(agentId, enterprName, createStartTime, createEndTime, pageBean);
		} else {
			list = Collections.emptyList();
		}
		for(AgentEnterpriseProfitBean agentEnterpriseProfitBean : list){
		    agentEnterpriseProfitBean.setRebateType(rebateType);
        }
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public PageResultBean<AgentRechargeProfitBean> getEnterpriseRechargeProfits(Integer agentId, Long enterprId, PageBean pageBean) {
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		EnterprUserInfo enterprise = enterpriseService.getEnterprise(enterprId);
		if (enterprise == null || !agentId.equals(enterprise.getAgentId())) {
			return new PageResultBean<>(page, pageSize, 0, Collections.emptyList());
		}
		AgentIncomeRecorderCondition example = new AgentIncomeRecorderCondition();
		example.createCriteria().andAgentIdEqualTo(agentId).andEnterprIdEqualTo(enterprId).andTypeEqualTo(AgentIncomeType.RECHARGE.getType());
		int count = (int) agentIncomeRecorderMapper.countByExample(example);
		List<AgentRechargeProfitBean> list;
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			list = agentIncomeRecorderMapper.getEnterpriseRechargeProfits(agentId, enterprId, pageBean);
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public PageResultBean<AgentOrderProfitBean> getEnterpriseOrderProfits(Integer agentId, Long enterprId, String orderNumber, String employeeName,
			Date orderStartTime, Date orderEndTime, PageBean pageBean) {
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		EnterprUserInfo enterprise = enterpriseService.getEnterprise(enterprId);
		if (enterprise == null || !agentId.equals(enterprise.getAgentId())) {
			return new PageResultBean<>(page, pageSize, 0, Collections.emptyList());
		}
		int count = agentIncomeRecorderMapper.countEnterpriseOrderProfits(agentId, enterprId, orderNumber, employeeName, orderStartTime, orderEndTime);
		List<AgentOrderProfitBean> list;
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			list = agentIncomeRecorderMapper.getEnterpriseOrderProfits(agentId, enterprId, orderNumber, employeeName, orderStartTime, orderEndTime, pageBean);
		}
		for(AgentOrderProfitBean agentOrderProfitBean : list){
		    agentOrderProfitBean.setEnterprId(enterprId);
        }
		return new PageResultBean<>(page, pageSize, count, list);
	}

}
