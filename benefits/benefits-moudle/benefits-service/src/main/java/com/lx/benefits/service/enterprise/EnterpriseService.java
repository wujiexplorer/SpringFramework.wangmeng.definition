package com.lx.benefits.service.enterprise;

import java.util.Date;
import java.util.List;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.constants.RegisterType;
import com.lx.benefits.bean.dto.admin.account.ModifyPasswordReqDto;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprBindRecorder;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprCheckingRecorder;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfoCondition;
import com.lx.benefits.bean.vo.agent.EnterpriseBindInfoBean;
import com.lx.benefits.bean.vo.agent.EnterpriseUniqueInfoBean;
import com.lx.benefits.bean.vo.enterpr.EmployeeUserInfoBean;
import com.lx.benefits.bean.vo.enterpr.EnterprBindRecorderBean;
import com.lx.benefits.bean.vo.enterpr.EnterprUserInfoBean;
import com.lx.benefits.bean.vo.enterpr.EnterpriseCheckingBean;
import com.lx.benefits.bean.vo.enterpr.EnterpriseOverview;

public interface EnterpriseService {

	/**
	 * 重置企业密码
	 * 
	 * @param enterprId
	 * @param password
	 */
	void resetPassword(Long enterprId, String password);

	/**
	 * 添加企业
	 * 
	 * @param request
	 * @param registerType
	 * @param loginName
	 * @param agentId
	 */
	void addEnterprise(EnterprCheckingRecorder request, RegisterType registerType, String loginName, Integer agentId);

	/**
	 * 
	 * @param agentId
	 * @param enterprId
	 * @return
	 */
	EnterprCheckingRecorder getCheckingInfo(Integer agentId, Long enterprId);

	/**
	 * 对企业添加进行审批
	 * 
	 * @param enterprId
	 * @param checkingBean
	 * @param checkUser
	 */
	void checkingEnterprise(Long enterprId, EnterpriseCheckingBean checkingBean, String checkUser);

	void modifyPassword(Long enterprId, ModifyPasswordReqDto req);

	EnterprUserInfo login(String loginName, String password);

	EnterprUserInfo getEnterprUserInfo(String loginName);

	PageResultBean<EnterprUserInfoBean> getEnterprises(String agentName, String enterprName, PageBean pageBean);

	PageResultBean<EnterpriseCheckingBean> getCheckingList(Integer checked, String enterprName, Integer checkStatus, PageBean pageBean);

	PageResultBean<EnterprUserInfo> getEnterprises(Integer agentId, String enterprName, Date bindStartTime, Date bindEndTime, PageBean pageBean);

	PageResultBean<EnterprCheckingRecorder> getCheckingList(Integer agentId, PageBean pageBean);

	void deleteUnpassedCheckingInfo(Integer agentId, Long enterprId);

	EnterpriseCheckingBean getCheckingInfo(Long enterprId);

	void updateContactinfo(Long enterprId, EnterprUserInfo contactinfo);

	void updateEnterpriseBindInfo(Long enterprId, EnterpriseBindInfoBean bindInfoBean, String operateUser);

	void enterpriseUniqueInfoCheck(EnterpriseUniqueInfoBean uniqueInfoBean);

	void updateEnterpriseAccountStatus(Long enterprId, Integer accountStatus);

	EnterprUserInfoBean getEnterpriseInfoBean(Long enterprId);

	EnterprUserInfo getEnterprise(Long enterprId);
	
	List<EnterprUserInfo> getEnterpriseNames(List<Long> enterprIds);

	EnterpriseOverview getEnterpriseOverview(Long enterprId);

	void updateCreditstrategy(Long enterprId, Integer leaveCredit);

	PageResultBean<EmployeeUserInfoBean> getEnterpriseEmployees(Long enterprId, String employeeNo, String employeeName, Integer leaveStatus, PageBean pageBean);

	/**
	 * 获取代理商下企业历史绑定信息
	 * 
	 * @param agentId
	 *            代理商ID
	 * @param pageBean
	 *            分页策略
	 * @return
	 */
	PageResultBean<EnterprBindRecorderBean> getAgentEnterprisesBindhistory(Integer agentId, PageBean pageBean);

	/**
	 * 获取企业员工的消费信息总览
	 * 
	 * @param agentId
	 * @param enterprName
	 * @param bindStartTime
	 * @param bindEndTime
	 * @param pageBean
	 * @return
	 */
	PageResultBean<? extends EnterprUserInfo> getEnterpriseWithOrderOverview(Integer agentId, String enterprName, Date bindStartTime, Date bindEndTime,
			PageBean pageBean);

	EnterprUserInfo getEnterpriseByEmployeeId(Long buyerUserId);

	PageResultBean<EnterprBindRecorder> getEnterpriseBindRecorders(Long enterprId, PageBean pageBean);

	public List<EnterprUserInfo> findEnterprUserInfosByEnterprName(String enterprName);

	long countByExample(EnterprUserInfoCondition example);

	public Long countCurrentEnterprise();

	EnterprUserInfo getEnterpriseByName(String customerName);


}
