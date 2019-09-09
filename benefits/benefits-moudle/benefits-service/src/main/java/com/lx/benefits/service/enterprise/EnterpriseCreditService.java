package com.lx.benefits.service.enterprise;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.enterprise.EnterprCreditDetailDto;
import com.lx.benefits.bean.dto.enterprise.EnterprCreditInfoBean;
import com.lx.benefits.bean.dto.enterprise.EnterprMoneyCreditDto;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeCreditDetailDto;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.entercreditinfo.CreditChekingRecorder;
import com.lx.benefits.bean.vo.entercreditinfo.CreditChekingRecorderBean;
import com.lx.benefits.bean.vo.entercreditinfo.CreditRechargeRecorder;
import com.lx.benefits.bean.vo.entercreditinfo.CreditRechargerReport;

public interface EnterpriseCreditService {

	List<EnterprCreditInfoBean> getCreditinfo(Long enterprId);

	void creditApply(Long enterprId, CreditChekingRecorder applyInfo, String applyUser);

	PageResultBean<CreditChekingRecorderBean> getUncheckedApplyInfos(Integer applyType, String enterprName, Date applyStartTime, Date applyEndTime,
			PageBean pageBean);

	void checkingCreditApply(String cashNumber, CreditChekingRecorder checkingInfo, String checkUser);

	PageResultBean<CreditChekingRecorder> getEnterpriseCreditList(Long enterprId, Integer applyType, PageBean pageBean);

	CreditRechargerReport getCreditRechargeReport();

	PageResultBean<CreditRechargeRecorder> getCreditRechargeRecorders(Integer type, String enterprName, String agentName, Date rechargeStartTime,
			Date rechargeEndTime, PageBean pageBean,Long enterprId);
	
	EnterprMoneyCreditDto countMoneyCreditinfo(Long enterprId);
	
	PageResultBean<EnterprCreditDetailDto> getCreditInfoDetail(Long enterprId,PageBean pageBean);
	
	PageResultBean<EmployeeCreditDetailDto> getEmployeeCredit(Long enterprId,PageBean pageBean);
	
	int insertReceipt(CreditRechargeRecorder creditRecorder);
	
	PageResultBean<CreditOperateInfo> recycleCreditRecord(Long enterprId,PageBean pageBean);
	
	List<CreditRechargeRecorder> getMoneyDetai(CreditRechargeRecorder creditRecorder);
}
