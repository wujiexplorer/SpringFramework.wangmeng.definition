package com.lx.benefits.service.enterprise.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.lx.benefits.bean.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.constants.AgentIncomeType;
import com.lx.benefits.bean.constants.CheckStatus;
import com.lx.benefits.bean.constants.CreditApplyType;
import com.lx.benefits.bean.dto.enterprise.EnterprCreditDetailDto;
import com.lx.benefits.bean.dto.enterprise.EnterprCreditInfoBean;
import com.lx.benefits.bean.dto.enterprise.EnterprMoneyCreditDto;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeCreditDetailDto;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.entercreditinfo.CreditChekingRecorder;
import com.lx.benefits.bean.entity.entercreditinfo.CreditChekingRecorderCondition;
import com.lx.benefits.bean.entity.entercreditinfo.CreditChekingRecorderCondition.Criteria;
import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfo;
import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfoExample;
import com.lx.benefits.bean.enums.CreditType;
import com.lx.benefits.bean.param.employee.EmployeeUserInfoParam;
import com.lx.benefits.bean.vo.entercreditinfo.CreditChekingRecorderBean;
import com.lx.benefits.bean.vo.entercreditinfo.CreditRechargeRecorder;
import com.lx.benefits.bean.vo.entercreditinfo.CreditRechargerReport;
import com.lx.benefits.mapper.agent.AgentIncomeRecorderMapper;
import com.lx.benefits.mapper.creditoperateinfo.CreditOperateInfoMapper;
import com.lx.benefits.mapper.employeecreditinfo.EmployeeCreditInfoMapper;
import com.lx.benefits.mapper.employeeuserinfo.EmployeeUserInfoMapper;
import com.lx.benefits.mapper.entercreditinfo.CreditChekingRecorderMapper;
import com.lx.benefits.mapper.entercreditinfo.EnterprCreditInfoMapper;
import com.lx.benefits.service.agent.AgentCashService;
import com.lx.benefits.service.employeeuserinfo.impl.EmployeeUserInfoServiceImpl;
import com.lx.benefits.service.enterprise.EnterpriseCreditService;

@Service
public class EnterpriseCreditServiceImpl implements EnterpriseCreditService {

	@Autowired
	private CreditChekingRecorderMapper creditChekingMapper;
	@Autowired
	private EnterprCreditInfoMapper enterprCreditInfoMapper;
	@Autowired
	private AgentCashService agentCashService;
	@Autowired
	private AgentIncomeRecorderMapper agentIncomeRecorderMapper;
	@Autowired
	private EmployeeUserInfoServiceImpl employeeUserInfoServiceImpl;
	@Autowired
	private CreditOperateInfoMapper creditOperateInfoMapper;
	@Autowired
	private EmployeeCreditInfoMapper employeeCreditInfoMapper;
	@Autowired
	private EmployeeUserInfoMapper employeeUserInfoMapper;
	
	private final String dateFormatPattern = "yyMMddHHmmssSSS";

	@Override
	public List<EnterprCreditInfoBean> getCreditinfo(Long enterprId) {
		EnterprCreditInfoBean normal = new EnterprCreditInfoBean(CreditType.normal.getCode());// 普通积分
		EnterprCreditInfoBean festivel = new EnterprCreditInfoBean(CreditType.festive.getCode());// 节日积分
		EnterprCreditInfoBean encourage = new EnterprCreditInfoBean(CreditType.encourage.getCode());// 激励积分
		EnterprCreditInfoExample example = new EnterprCreditInfoExample();
		example.createCriteria().andEnterprIdEqualTo(enterprId);
		List<EnterprCreditInfo> enterprCreditInfos = enterprCreditInfoMapper.selectByExample(example);
		for (EnterprCreditInfo enterprCreditInfo : enterprCreditInfos) {
			if (CreditType.encourage.getCode() == enterprCreditInfo.getCreditType()) {
				encourage.setLockCredit(enterprCreditInfo.getLockCredit());
				encourage.setValidCredit(enterprCreditInfo.getValidCredit());
				BigDecimal rechargeAmount = creditChekingMapper.sumRechargeAmount(enterprId, CreditType.encourage.getCode(),
						CreditApplyType.RECHARGE.getType());
				BigDecimal refundAmount = creditChekingMapper.sumRechargeAmount(enterprId, CreditType.encourage.getCode(), CreditApplyType.REFUND.getType());
				encourage.setHistoryTotalCredit(rechargeAmount.subtract(refundAmount));
			} else if (CreditType.normal.getCode() == enterprCreditInfo.getCreditType()) {
				normal.setLockCredit(enterprCreditInfo.getLockCredit());
				normal.setValidCredit(enterprCreditInfo.getValidCredit());
				BigDecimal rechargeAmount = creditChekingMapper.sumRechargeAmount(enterprId, CreditType.normal.getCode(), CreditApplyType.RECHARGE.getType());
				BigDecimal refundAmount = creditChekingMapper.sumRechargeAmount(enterprId, CreditType.normal.getCode(), CreditApplyType.REFUND.getType());
				normal.setHistoryTotalCredit(rechargeAmount.subtract(refundAmount));
			} else if (CreditType.festive.getCode() == enterprCreditInfo.getCreditType()) {
				festivel.setLockCredit(enterprCreditInfo.getLockCredit());
				festivel.setValidCredit(enterprCreditInfo.getValidCredit());
			}
		}
		return Arrays.asList(normal, festivel, encourage);
	}

	@Override
	public void creditApply(Long enterprId, CreditChekingRecorder applyInfo, String applyUser) {
		BigDecimal cashAmount = applyInfo.getCashAmount();// 充值/退款金额
		if (cashAmount == null || cashAmount.doubleValue() <= 0) {
			throw new BusinessException("请输入正确的金额!");
		}
		Integer creditType = applyInfo.getCreditType();// 积分类型
		if (creditType == null || !(CreditType.normal.getCode() == creditType || CreditType.encourage.getCode() == creditType)) {
			throw new BusinessException("请选择正确的积分类型!");
		}
		Integer applyType = applyInfo.getApplyType();// 申请类型
		String cashNumberprefix;
		if (applyType != null) {
			CreditChekingRecorderCondition example = new CreditChekingRecorderCondition();
			example.createCriteria().andEnterprIdEqualTo(enterprId).andApplyTypeEqualTo(applyType).andCheckStatusEqualTo(CheckStatus.INIT.getStatus());
			long count = creditChekingMapper.countByExample(example);
			if (count > 0) {
				throw new BusinessException("申请失败,还有待审批的申请!");
			}
		}
		if (CreditApplyType.RECHARGE.getType().equals(applyType)) {// 充值
			cashNumberprefix = "ER";
		} else if (CreditApplyType.REFUND.getType().equals(applyType)) {// 退款，校验对应的积分是否足够
			EnterprCreditInfo creditinfo = this.getCreditinfo(enterprId, creditType);
			if (creditinfo == null || creditinfo.getValidCredit().compareTo(cashAmount) < 0) {
				throw new BusinessException("可用积分不足,申请失败!");
			}
			cashNumberprefix = "ET";
		} else {
			throw new BusinessException("未知的申请类型!");
		}
		CreditChekingRecorder record = new CreditChekingRecorder();
		record.setCashNumber(cashNumberprefix + this.getnerateCashNumber());
		record.setEnterprId(enterprId);
		record.setCreditType(creditType);
		record.setApplyRemark(applyInfo.getApplyRemark());
		record.setApplyTime(new Date());
		record.setApplyType(applyType);
		record.setCashAmount(cashAmount);
		record.setApplyUser(applyUser);
		for (int i = 0; i < 3; i++) {// 为避免流水号冲突，失败后尝试三次
			try {
				creditChekingMapper.insertSelective(record);
				break;
			} catch (DuplicateKeyException e) {
				try {
					Thread.sleep(1);// 休眠1毫秒
					record.setCashNumber(cashNumberprefix + this.getnerateCashNumber());
				} catch (InterruptedException e1) {
				}
			}
		}
	}

	private EnterprCreditInfo getCreditinfo(Long enterprId, Integer creditType) {
		EnterprCreditInfoExample example = new EnterprCreditInfoExample();
		example.createCriteria().andEnterprIdEqualTo(enterprId).andCreditTypeEqualTo(creditType.byteValue());
		List<EnterprCreditInfo> enterprCreditInfos = enterprCreditInfoMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(enterprCreditInfos)) {
			return null;
		}
		return enterprCreditInfos.get(0);
	}

	// 生成流水号,时间戳+[0-9]的随机数，借助数据库的唯一索引来避免重复
	private String getnerateCashNumber() {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
		return dateFormat.format(new Date()) + ThreadLocalRandom.current().nextInt(10);
	}

	@Override
	public PageResultBean<CreditChekingRecorderBean> getUncheckedApplyInfos(Integer applyType, String enterprName, Date applyStartTime, Date applyEndTime,
			PageBean pageBean) {
		int count;
		if (enterprName == null || "".equals(enterprName = enterprName.trim())) {
			enterprName = null;
			CreditChekingRecorderCondition example = new CreditChekingRecorderCondition();
			Criteria criteria = example.createCriteria().andCheckStatusEqualTo(CheckStatus.INIT.getStatus());
			if (applyType != null) {
				criteria.andApplyTypeEqualTo(applyType);
			}
			if (applyStartTime != null) {
				criteria.andApplyTimeGreaterThan(applyStartTime);
			}
			if (applyEndTime != null) {
				criteria.andApplyTimeLessThanOrEqualTo(applyEndTime);
			}
			count = (int) creditChekingMapper.countByExample(example);
		} else {
			count = creditChekingMapper.countWithEnterprName(applyType, enterprName, CheckStatus.INIT.getStatus(), applyStartTime, applyEndTime);
		}
		List<CreditChekingRecorderBean> list;
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			list = creditChekingMapper.selectWithAgentName(applyType, enterprName, CheckStatus.INIT.getStatus(), applyStartTime, applyEndTime, pageBean);
		}
		return new PageResultBean<>(page, pageSize, count, list);

	}

	@Transactional
	@Override
	public void checkingCreditApply(String cashNumber, CreditChekingRecorder checkingInfo, String checkUser) {
		CreditChekingRecorder recorder = creditChekingMapper.selectByPrimaryKey(cashNumber);
		if (recorder == null) {
			throw new BusinessException("申请记录不存在!");
		}
		if (!CheckStatus.INIT.getStatus().equals(recorder.getCheckStatus())) {
			throw new BusinessException("该申请已审批,请勿重复审批!");
		}
		CreditChekingRecorder updateRecord = new CreditChekingRecorder();
		updateRecord.setCashNumber(cashNumber);
		Integer checkStatus = checkingInfo.getCheckStatus();
		updateRecord.setCheckStatus(checkStatus);
		boolean flag = false;
		if (CheckStatus.PASSED.getStatus().equals(checkStatus)) {// 审批通过，更新企业积分
			String payVoucher = checkingInfo.getPayVoucher();
			if (payVoucher == null || "".equals(payVoucher = payVoucher.trim())) {
				throw new BusinessException("收款凭证不能为空!");
			}
			updateRecord.setPayVoucher(payVoucher);
			if (CreditApplyType.RECHARGE.getType().equals(recorder.getApplyType())) {// 充值
				BigDecimal payAmount = checkingInfo.getPayAmount();
				if (payAmount == null || payAmount.doubleValue() < 0) {
					throw new BusinessException("请输入正确的收款金额!");
				}
				Date payTime = checkingInfo.getPayTime();
				if (payTime == null) {
					throw new BusinessException("请输入收款日期!");
				}
				updateRecord.setPayAmount(payAmount);
				updateRecord.setPayTime(payTime);
				EnterprCreditInfo creditinfo = this.getCreditinfo(recorder.getEnterprId(), recorder.getCreditType());
				if (creditinfo == null) {
					creditinfo = new EnterprCreditInfo();
					creditinfo.setCreditType(recorder.getCreditType().byteValue());
					creditinfo.setEnterprId(recorder.getEnterprId());
					creditinfo.setValidCredit(recorder.getCashAmount());
					enterprCreditInfoMapper.insertSelective(creditinfo);
				} else {// 更新金额
					BigDecimal oldValidCredit = creditinfo.getValidCredit();
					EnterprCreditInfo record = new EnterprCreditInfo();
					record.setValidCredit(oldValidCredit.add(recorder.getCashAmount()));
					EnterprCreditInfoExample example = new EnterprCreditInfoExample();
					example.createCriteria().andCreditIdEqualTo(creditinfo.getCreditId()).andValidCreditEqualTo(oldValidCredit);
					int updateCount = enterprCreditInfoMapper.updateByExampleSelective(record, example);
					if (updateCount == 0) {// 通过CAS对企业积分进行更新
						throw new BusinessException("因企业积分发生变动,审批失败,请重试!");
					}
				}
				agentCashService.addRechargeIncomeRecorde(cashNumber, recorder.getCashAmount(), recorder.getEnterprId());
			} else if (CreditApplyType.REFUND.getType().equals(recorder.getApplyType())) {// 退款,检测可用积分
				EnterprCreditInfo creditinfo = this.getCreditinfo(recorder.getEnterprId(), recorder.getCreditType());
				BigDecimal validCredit;
				if (creditinfo == null || recorder.getCashAmount().compareTo(validCredit = creditinfo.getValidCredit()) > 0) {
					throw new BusinessException("审批失败,企业积分不足!");
				}
				EnterprCreditInfo record = new EnterprCreditInfo();
				record.setValidCredit(validCredit.subtract(recorder.getCashAmount()));
				EnterprCreditInfoExample example = new EnterprCreditInfoExample();
				example.createCriteria().andCreditIdEqualTo(creditinfo.getCreditId()).andValidCreditEqualTo(validCredit);
				int updateCount = enterprCreditInfoMapper.updateByExampleSelective(record, example);
				if (updateCount == 0) {// 通过CAS对企业积分进行更新
					throw new BusinessException("因企业积分发生变动,审批失败,请重试!");
				}
			} else {
				throw new BusinessException("未知的申请类型!");
			}
			flag = true;
		} else if (CheckStatus.UNPASSED.getStatus().equals(checkStatus)) {// 审批不通过
			String checkRemark = checkingInfo.getCheckRemark();
			if (checkRemark == null || "".equals(checkRemark = checkRemark.trim())) {
				throw new BusinessException("备注信息不能为空!");
			}
			updateRecord.setCheckRemark(checkRemark);
		} else {
			throw new BusinessException("未知的审批状态!");
		}
		updateRecord.setCheckUser(checkUser);
		updateRecord.setCheckTime(new Date());
		creditChekingMapper.updateByPrimaryKeySelective(updateRecord);
		CreditRechargeRecorder creditRecorder = new CreditRechargeRecorder();
		BeanUtils.copyProperties(updateRecord, creditRecorder);
		if(creditRecorder.getPayAmount() == null){
			creditRecorder.setPayAmount(BigDecimal.ZERO);
		}
		if(StringUtil.isEmpty(creditRecorder.getPayVoucher())){
			creditRecorder.setPayVoucher("无回款凭证");
		}
		if(creditRecorder.getPayTime() == null){
			creditRecorder.setPayTime(new Date());
		}
		creditChekingMapper.insertReceipt(creditRecorder);
		if(flag) {
			employeeUserInfoServiceImpl.handlerEmployeeBirthdayHangUp(recorder.getEnterprId());
		}
	}

	@Override
	public PageResultBean<CreditChekingRecorder> getEnterpriseCreditList(Long enterprId, Integer applyType, PageBean pageBean) {
		CreditChekingRecorderCondition example = new CreditChekingRecorderCondition();
		Criteria criteria = example.createCriteria().andEnterprIdEqualTo(enterprId);
		if (applyType != null) {
			criteria.andApplyTypeEqualTo(applyType);
		}
		int count = (int) creditChekingMapper.countByExample(example);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<CreditChekingRecorder> list;
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			example.setOffset(pageBean.getOffset());
			example.setLimit(pageSize);
			example.setOrderByClause("apply_time DESC");
			list = creditChekingMapper.selectByExample(example);
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public CreditRechargerReport getCreditRechargeReport() {
		CreditRechargerReport creditRechargerReport = new CreditRechargerReport();
		BigDecimal totalRecharge = creditChekingMapper.sumRechargeAmount(null, null, CreditApplyType.RECHARGE.getType());
		BigDecimal totalRefund = creditChekingMapper.sumRechargeAmount(null, null, CreditApplyType.REFUND.getType());
		creditRechargerReport.setTotalRecharge(totalRecharge);
		creditRechargerReport.setTotalRefund(totalRefund);
		BigDecimal totalRebate = agentIncomeRecorderMapper.sumCashIncomeByType(AgentIncomeType.RECHARGE.getType());
		creditRechargerReport.setTotalRebate(totalRebate);
		return creditRechargerReport;
	}

	@Override
	public PageResultBean<CreditRechargeRecorder> getCreditRechargeRecorders(Integer type, String enterprName, String agentName, Date rechargeStartTime,
			Date rechargeEndTime, PageBean pageBean,Long enterprId) {
		if (enterprName != null && "".equals(enterprName = enterprName.trim())) {
			enterprName = null;
		}
		if (agentName != null && "".equals(agentName = agentName.trim())) {
			agentName = null;
		}
		int count = creditChekingMapper.countCreditRecharge(type, enterprName, agentName, rechargeStartTime, rechargeEndTime,enterprId);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		if (count == 0) {
			return new PageResultBean<>(page, pageSize, count, Collections.emptyList());
		}
		List<CreditRechargeRecorder> recorders = creditChekingMapper.getCreditRechargeRecorders(type, enterprName, agentName, rechargeStartTime,
				rechargeEndTime, pageBean,enterprId);
		if(type == 1) {
			List<String> cashNumbers = new ArrayList<>();
			for (CreditRechargeRecorder recorder : recorders) {
				cashNumbers.add(recorder.getCashNumber());
			}
			List<CreditRechargeRecorder> moneyRecorders = creditChekingMapper.getMoneyTotal(cashNumbers);
			for (int i = 0; i < recorders.size(); i++) {
				recorders.get(i).setPayAmount(moneyRecorders.get(i).getPayAmount());
				recorders.get(i).setPayTime(moneyRecorders.get(i).getPayTime());
			}
		}
		return new PageResultBean<>(page, pageSize, count, recorders);
	}

	@Override
	public EnterprMoneyCreditDto countMoneyCreditinfo(Long enterprId) {
		EnterprMoneyCreditDto enterprMoneyCredit = new EnterprMoneyCreditDto();
		BigDecimal returnMoney =  creditChekingMapper.countMoneyReturnedMoneyRecorder(enterprId);
		enterprMoneyCredit.setTotleArrivalAmount(returnMoney);//到账总金额
		BigDecimal totleConsumeCredit = creditOperateInfoMapper.paymentCredit(enterprId).subtract(creditOperateInfoMapper.refundCredit(enterprId));
		enterprMoneyCredit.setTotleConsumeCredit(totleConsumeCredit);//消耗总积分
		enterprMoneyCredit.setTotleEmployeeCredit(employeeCreditInfoMapper.inventoryCredit(enterprId));//员工总积分
		enterprMoneyCredit.setTotleEmployeeGeneralCredit(employeeCreditInfoMapper.generalCredit(enterprId));//员工普通总积分
		enterprMoneyCredit.setTotleEmployeeFestivalCredit(employeeCreditInfoMapper.festivalCredit(enterprId));//员工节日总积分
		enterprMoneyCredit.setTotleRecycleCredit(creditOperateInfoMapper.recycleCredit(enterprId));//回收总积分
		return enterprMoneyCredit;
	}

	@Override
	public PageResultBean<EnterprCreditDetailDto> getCreditInfoDetail(Long enterprId, PageBean pageBean) {
		List<EnterprCreditDetailDto> creditPay = creditOperateInfoMapper.getCreditInfoDetailPay(enterprId, pageBean);
		List<EnterprCreditDetailDto> creditRefund = creditOperateInfoMapper.getCreditInfoDetailRefund(enterprId, pageBean);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		int count = creditOperateInfoMapper.creditCount(enterprId);
		if(CollectionUtils.isEmpty(creditPay)) {
			return new PageResultBean<>(page, pageSize, count, Collections.emptyList());
		}
		if(CollectionUtils.isEmpty(creditRefund)) {
			return new PageResultBean<>(page, pageSize, count, creditPay);
		}
		List<EnterprCreditDetailDto> credit = new ArrayList<>();
		int counter = 0;
		for (EnterprCreditDetailDto enterprCreditPay : creditPay) {
			EnterprCreditDetailDto enterprCreditDetail = new EnterprCreditDetailDto();
			enterprCreditDetail.setDate(enterprCreditPay.getDate());
			for (EnterprCreditDetailDto enterprCreditRefund : creditRefund) {
				counter++;
				if(enterprCreditRefund.getDate().equals(enterprCreditPay.getDate())) {
					enterprCreditDetail.setCredit(enterprCreditPay.getCredit().subtract(enterprCreditRefund.getCredit()));
					credit.add(enterprCreditDetail);
					counter = 0;
					break;
				}else if(creditRefund.size() == counter) {
					enterprCreditDetail.setCredit(enterprCreditPay.getCredit());
					credit.add(enterprCreditDetail);
					counter = 0;
				}
			}
		}
		return new PageResultBean<>(page, pageSize, count, credit);
	}

	@Override
	public PageResultBean<EmployeeCreditDetailDto> getEmployeeCredit(Long enterprId, PageBean pageBean) {
		List<EmployeeUserInfoParam> employeeUserInfo = employeeUserInfoMapper.queryEnterprInfo(enterprId,pageBean);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		int count = employeeUserInfoMapper.countEmployeeInfo(enterprId);
		if(CollectionUtils.isEmpty(employeeUserInfo)) {
			return new PageResultBean<>(page, pageSize, count, Collections.emptyList());
		}
		List<Long> employeeIdlist = new ArrayList<>();
		for (EmployeeUserInfoParam employee : employeeUserInfo) {
			employeeIdlist.add(employee.getEmployeeId());	
		}
		List<EmployeeCreditInfo> generalCreditInfo = employeeCreditInfoMapper.queryGeneralCredit(employeeIdlist);
		List<EmployeeCreditInfo> festivalCreditInfo = employeeCreditInfoMapper.queryFestivalCredit(employeeIdlist);
		List<EmployeeCreditDetailDto> EmployeeInfoList = new ArrayList<>();
		for (EmployeeUserInfoParam employeeUser : employeeUserInfo) {			
			EmployeeCreditDetailDto employeeInfoDto = new EmployeeCreditDetailDto();
			employeeInfoDto.setEmployeeId(employeeUser.getEmployeeId());
			employeeInfoDto.setEmployeeName(employeeUser.getEmployeeName());
			employeeInfoDto.setEmployeeNo(employeeUser.getEmployeeNo());
			if(!CollectionUtils.isEmpty(generalCreditInfo)) {//普通积分
				for (EmployeeCreditInfo generalCredit : generalCreditInfo) {
					if(generalCredit.getEmployeeId().equals(employeeUser.getEmployeeId())) {
						employeeInfoDto.setOrdCredit(generalCredit.getCredit());
					}
				}
			}
			if(!CollectionUtils.isEmpty(festivalCreditInfo)) {//节日积分
				for (EmployeeCreditInfo festivalCredit : festivalCreditInfo) {
					if(festivalCredit.getEmployeeId().equals(employeeUser.getEmployeeId())) {
						employeeInfoDto.setFestCredit(festivalCredit.getCredit());
					}
				}
			}
			EmployeeInfoList.add(employeeInfoDto);
		}
		return new PageResultBean<>(page, pageSize, count, EmployeeInfoList);
	}

	@Override
	public int insertReceipt(CreditRechargeRecorder creditRecorder) {	
		return creditChekingMapper.insertReceipt(creditRecorder);
	}

	@Override
	public PageResultBean<CreditOperateInfo> recycleCreditRecord(Long enterprId, PageBean pageBean) {
		List<CreditOperateInfo> creditOperateList = creditOperateInfoMapper.recycleCreditRecord(enterprId, pageBean);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		if(CollectionUtils.isEmpty(creditOperateList)) {
			return new PageResultBean<>(page, pageSize, 0, Collections.emptyList());
		}
		int count = creditOperateInfoMapper.creditRecordCount(enterprId);
		for (CreditOperateInfo creditOperateInfo :creditOperateList) {
            // 1: 普通积分; 2: 节日礼金; 3: 认可激励积分
            if (creditOperateInfo.getCreditType().equals(1)) {
                creditOperateInfo.setCreditTypeDesc("普通积分");
            } else if (creditOperateInfo.getCreditType().equals(2)) {
                creditOperateInfo.setCreditTypeDesc("节日积分");
            } else if (creditOperateInfo.getCreditType().equals(3)) {
                creditOperateInfo.setCreditTypeDesc(" 认可激励");
            }
        }
		return new PageResultBean<>(page, pageSize, count, creditOperateList);
	}

	@Override
	public List<CreditRechargeRecorder> getMoneyDetai(CreditRechargeRecorder creditRecorder) {
		List<CreditRechargeRecorder> moneyDetailReturnedMoney = creditChekingMapper.getMoneyDetailReturnedMoney(creditRecorder);
		return moneyDetailReturnedMoney;
	}
}
