package com.lx.benefits.service.enterprise.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.lx.benefits.bean.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.apollo.common.exception.BusinessException;
import com.apollo.common.utils.bean.CollectionExtUtil;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.bo.order.OrderOverview;
import com.lx.benefits.bean.constants.AcountStatus;
import com.lx.benefits.bean.constants.BindStatus;
import com.lx.benefits.bean.constants.CheckStatus;
import com.lx.benefits.bean.constants.RegisterType;
import com.lx.benefits.bean.dto.admin.account.ModifyPasswordReqDto;
import com.lx.benefits.bean.entity.agent.AgentAccountInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfoExample;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfoExample;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprBindRecorder;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprBindRecorderCondition;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprCheckingRecorder;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprCheckingRecorderCondition;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprCheckingRecorderCondition.Criteria;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfoCondition;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.vo.agent.EnterpriseBindInfoBean;
import com.lx.benefits.bean.vo.agent.EnterpriseUniqueInfoBean;
import com.lx.benefits.bean.vo.enterpr.EmployeeUserInfoBean;
import com.lx.benefits.bean.vo.enterpr.EnterprBindRecorderBean;
import com.lx.benefits.bean.vo.enterpr.EnterprUserInfoBean;
import com.lx.benefits.bean.vo.enterpr.EnterpriseCheckingBean;
import com.lx.benefits.bean.vo.enterpr.EnterpriseOverview;
import com.lx.benefits.mapper.agent.AgentAccountInfoMapper;
import com.lx.benefits.mapper.employeecreditinfo.EmployeeCreditInfoMapper;
import com.lx.benefits.mapper.employeeuserinfo.EmployeeUserInfoMapper;
import com.lx.benefits.mapper.enterpruserinfo.EnterprBindRecorderMapper;
import com.lx.benefits.mapper.enterpruserinfo.EnterprCheckingRecorderMapper;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.service.enterprise.EnterpriseService;
import com.lx.benefits.service.order.OrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EnterpriseServiceImpl implements EnterpriseService {

	@Autowired
	private EnterprUserInfoMapper enterprUserInfoMapper;
	@Autowired
	private EnterprCheckingRecorderMapper checkingRecorderMapper;
	@Autowired
	private AgentAccountInfoMapper agentAccountInfoMapper;
	@Autowired
	private EnterprBindRecorderMapper enterprBindRecorderMapper;
	@Autowired
	private EmployeeUserInfoMapper employeeUserInfoMapper;
	@Autowired
	private EmployeeCreditInfoMapper employeeCreditInfoMapper;
	@Autowired
	private OrderService orderService;

	@Override
	public void resetPassword(Long enterprId, String password) {
		if (password == null || "".equals(password = password.trim())) {
			throw new BusinessException("密码不能为空!");
		}
		EnterprUserInfo record = new EnterprUserInfo();
		record.setEnterprId(enterprId);
		String secret = EncryptUtil.generateSecret();
		record.setSecret(secret);
		record.setPassword(EncryptUtil.encodePassword(password, secret));
		enterprUserInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void addEnterprise(EnterprCheckingRecorder request, RegisterType registerType, String loginName, Integer agentId) {
		EnterprCheckingRecorderCondition example = new EnterprCheckingRecorderCondition();
		example.createCriteria().andEnterprIdentityIdEqualTo(request.getEnterprIdentityId()).andCheckStatusNotEqualTo(CheckStatus.UNPASSED.getStatus());
		example.or().andEnterprNameEqualTo(request.getEnterprName()).andCheckStatusNotEqualTo(CheckStatus.UNPASSED.getStatus());
		example.setOffset(0);
		example.setLimit(1);
		List<EnterprCheckingRecorder> checkingRecorder = checkingRecorderMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(checkingRecorder)) {
			String erromessage;
			EnterprCheckingRecorder enterprCheckingRecorder = checkingRecorder.get(0);
			if (CheckStatus.PASSED.getStatus().equals(enterprCheckingRecorder.getCheckStatus())) {// 存在已通过的
				if (enterprCheckingRecorder.getEnterprIdentityId().equals(request.getEnterprIdentityId())) {
					erromessage = "企业营业执照号已存在!";
				} else {
					erromessage = "企业名称已存在!";
				}
			} else {
				if (enterprCheckingRecorder.getEnterprIdentityId().equals(request.getEnterprIdentityId())) {
					erromessage = "该营业执照号对应的企业已在申请中!";
				} else {
					erromessage = "该企业已在申请中!";
				}
			}
			throw new BusinessException(erromessage);
		}
		EnterprCheckingRecorder record = new EnterprCheckingRecorder();
		record.setEnterprName(request.getEnterprName());// 企业明层
		record.setSubEnterprName(request.getSubEnterprName());// 子公司
		String country = request.getCountry();
		if (country != null && !"".equals(country = country.trim())) {
			record.setCountry(country);// 国家
		}
		record.setProvince(request.getProvince());// 省
		record.setCity(request.getCity());// 市
		record.setCounty(request.getCounty());// 县/区
		record.setStreet(request.getStreet());// 街道/镇
		record.setAddress(request.getAddress());// 详细地址
		record.setEnterprIdentityId(request.getEnterprIdentityId());// 企业营业执照编号
		record.setEnterprRegisterAddress(request.getEnterprRegisterAddress());// 企业注册地址
		record.setCertifyImageUrl(request.getCertifyImageUrl());// 营业执照图片
		record.setContactUser(request.getContactUser());// 联系人
		record.setMobile(request.getMobile());// 联系电话
		record.setEmail(request.getEmail());// 邮箱
		record.setAddTime(new Date());
		record.setAddUser(loginName);
		record.setRegisteredType(registerType.getType());
		if (RegisterType.AGENT_RECOMMEND == registerType) {
			record.setAgentId(agentId);
		}
		checkingRecorderMapper.insertSelective(record);
	}

	@Override
	public EnterpriseCheckingBean getCheckingInfo(Long enterprId) {
		EnterprCheckingRecorder checkingRecorder = checkingRecorderMapper.selectByPrimaryKey(enterprId);
		if (checkingRecorder == null) {
			return null;
		}
		EnterpriseCheckingBean checkingBean = new EnterpriseCheckingBean();
		BeanUtils.copyProperties(checkingRecorder, checkingBean);
		if (checkingRecorder.getAgentId() != 0) {
			AgentAccountInfo agentAccountInfo = agentAccountInfoMapper.selectByPrimaryKey(checkingRecorder.getAgentId());
			if (agentAccountInfo != null) {
				checkingBean.setAgentName(agentAccountInfo.getAgentName());
			}
		}
		return checkingBean;
	}

	@Transactional
	@Override
	public void checkingEnterprise(Long enterprId, EnterpriseCheckingBean checkingBean, String checkUser) {
		EnterprCheckingRecorder checkingRecorder = checkingRecorderMapper.selectByPrimaryKey(enterprId);
		if (checkingRecorder == null) {
			throw new BusinessException("该记录不存在!");
		}
		if (!CheckStatus.INIT.getStatus().equals(checkingRecorder.getCheckStatus())) {// 非初始化状态
			throw new BusinessException("该企业已审批，请勿重复审批!");
		}
		Integer checkStatus = checkingBean.getCheckStatus();
		EnterprCheckingRecorder record = new EnterprCheckingRecorder();
		record.setEnterprId(enterprId);
		record.setCheckStatus(checkStatus);// 审批状态
		record.setCheckTime(new Date());// 审批时间
		record.setCheckUser(checkUser);
		if (CheckStatus.PASSED.getStatus().equals(checkStatus)) {// 通过
			String contractNumber = checkingBean.getContractNumber();
			if (contractNumber == null || "".equals(contractNumber = contractNumber.trim())) {// 合同编号为空
				throw new BusinessException("合同编号不能为空!");
			}
			String loginName = checkingBean.getLoginName();
			if (loginName == null || "".equals(loginName = loginName.trim())) {
				throw new BusinessException("账号不能为空!");
			}
			if (!loginName.matches("^[A-Za-z]\\w{5,17}$")) {
				throw new BusinessException("账号格式不正确!");
			}
			String password = checkingBean.getPassword();
			if (password == null || "".equals(password = password.trim())) {
				throw new BusinessException("密码不能为空!");
			}
			if (!password.matches(".{6,16}")) {
				throw new BusinessException("密码6~16个字符");
			}
			record.setContractNumber(contractNumber);
			record.setLoginName(loginName);
			checkingRecorderMapper.updateByPrimaryKeySelective(record);// 更新审批状态
			// 添加企业信息
			try {
				EnterprUserInfo enterprUserInfo = new EnterprUserInfo();
				BeanUtils.copyProperties(checkingRecorder, enterprUserInfo);
				enterprUserInfo.setLoginName(loginName);
				String secret = EncryptUtil.generateSecret();
				enterprUserInfo.setSecret(secret);
				enterprUserInfo.setPassword(EncryptUtil.encodePassword(password, secret));
				// 添加代理商绑定记录
				if (enterprUserInfo.getAgentId() != null && enterprUserInfo.getAgentId() != 0) {// 存在代理商
					EnterprBindRecorder bindRecorder = new EnterprBindRecorder();
					AgentAccountInfo agentAccountInfo = agentAccountInfoMapper.selectByPrimaryKey(enterprUserInfo.getAgentId());
					bindRecorder.setAgentName(agentAccountInfo.getAgentName());
					bindRecorder.setAgentId(enterprUserInfo.getAgentId());
					bindRecorder.setBindTime(checkingRecorder.getAddTime());
					enterprUserInfo.setBindTime(checkingRecorder.getAddTime());
					bindRecorder.setStatus(BindStatus.BIND.getStatus());
					bindRecorder.setEnterprId(checkingRecorder.getEnterprId());
					bindRecorder.setBindRemark("代理商添加绑定");
					bindRecorder.setBindUser(checkingRecorder.getAddUser());
					enterprBindRecorderMapper.insertSelective(bindRecorder);
				}
				enterprUserInfoMapper.insertSelective(enterprUserInfo);
			} catch (DuplicateKeyException e) {
				log.error(e.getMessage());
				throw new BusinessException("该账号已存在!");
			}
		} else if (CheckStatus.UNPASSED.getStatus().equals(checkStatus)) {// 未通过
			String checkRemark = checkingBean.getCheckRemark();
			if (checkRemark == null || "".equals(checkRemark = checkRemark.trim())) {
				throw new BusinessException("请填写未通过原因!");
			}
			record.setCheckRemark(checkRemark);
			checkingRecorderMapper.updateByPrimaryKeySelective(record);
		} else {
			throw new BusinessException("审批状态不正确!");
		}
	}

	@Override
	public void modifyPassword(Long enterprId, ModifyPasswordReqDto req) {
		EnterprUserInfo enterprUserInfo = enterprUserInfoMapper.selectByPrimaryKey(enterprId);
		if (null == enterprUserInfo) {
			throw new BusinessException("用户不存在,请重新登录!");
		}
		String password = req.getPassword();
		if (password == null || "".equals(password = password.trim())) {
			throw new BusinessException("密码不能为空!");
		}
		EnterprUserInfo record = new EnterprUserInfo();
		record.setEnterprId(enterprId);
		String secret = EncryptUtil.generateSecret();
		record.setSecret(secret);
		record.setPassword(EncryptUtil.encodePassword(password, secret));
		enterprUserInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public EnterprUserInfo login(String loginName, String password) {
		if (loginName == null || "".equals(loginName = loginName.trim())) {
			throw new BusinessException("请输入账号!");
		}
		if (password == null || "".equals(password = password.trim())) {
			throw new BusinessException("请输入密码!");
		}
		EnterprUserInfoCondition example = new EnterprUserInfoCondition();
		example.createCriteria().andLoginNameEqualTo(loginName);
		List<EnterprUserInfo> enterprUserInfos = enterprUserInfoMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(enterprUserInfos)) {
			throw new BusinessException("账号或密码错误!");
		}
		EnterprUserInfo enterprUserInfo = enterprUserInfos.get(0);
		String encodePassword = EncryptUtil.encodePassword(password, enterprUserInfo.getSecret());
		if (!encodePassword.equals(enterprUserInfo.getPassword())) {
			throw new BusinessException("账号或密码错误!");
		}
		if (AcountStatus.FREEZE.getStatus().equals(enterprUserInfo.getAccountStatus())) {
			throw new BusinessException("您的账号已被冻结,请联系管理员!");
		}
		return enterprUserInfo;
	}

	@Override
	public EnterprUserInfo getEnterprUserInfo(String loginName) {
		if (StringUtil.isEmpty(loginName)) {
			throw new BusinessException("登录账号不能为空！");
		}
		try {
			EnterprUserInfoCondition example = new EnterprUserInfoCondition();
			example.createCriteria().andLoginNameEqualTo(loginName);
			List<EnterprUserInfo> enterprUserInfos = enterprUserInfoMapper.selectByExample(example);
			EnterprUserInfo enterprUserInfo = enterprUserInfos.get(0);
			return enterprUserInfo;
		} catch (Exception e) {
			throw new RuntimeException("根据登录名查询企业信息出错！", e);
		}
	}

	@Override
	public PageResultBean<EnterprUserInfoBean> getEnterprises(String agentName, String enterprName, PageBean pageBean) {
		if (agentName != null && "".equals(agentName = agentName.trim())) {
			agentName = null;
		}
		if (enterprName != null && "".equals(enterprName = enterprName.trim())) {
			enterprName = null;
		}
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<EnterprUserInfoBean> list;
		int count = enterprUserInfoMapper.countWithAgentName(agentName, enterprName);
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			list = enterprUserInfoMapper.selectWithAgentName(agentName, enterprName, pageBean);
		}
		//员工表中查询员工数
		List<Long> enterprIds = CollectionExtUtil.getPropertyList(list,EnterprUserInfoBean::getEnterprId);
		Long enterprId = 0L;
		for (int i = 0; i < enterprIds.size(); i++) {
			enterprId = enterprIds.get(i);
			EmployeeUserInfoExample example = new EmployeeUserInfoExample();
			example.createCriteria().andEnterprIdEqualTo(enterprId).andLeaveStatusEqualTo(false);
			list.get(i).setEmployeeNum(employeeUserInfoMapper.countByExample(example));
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public PageResultBean<EnterpriseCheckingBean> getCheckingList(Integer checked, String enterprName, Integer checkStatus, PageBean pageBean) {
		EnterprCheckingRecorderCondition example = new EnterprCheckingRecorderCondition();
		Criteria criteria = example.createCriteria();
		if (enterprName != null && !"".equals(enterprName = enterprName.trim())) {
			criteria.andEnterprNameLike("%" + enterprName + "%");
		}
		if (checked != null) {
			if (checked == 0) {
				criteria.andCheckStatusEqualTo(CheckStatus.INIT.getStatus());
			} else {
				criteria.andCheckStatusNotEqualTo(CheckStatus.INIT.getStatus());
			}
		}
		if (checkStatus != null) {
			criteria.andCheckStatusEqualTo(checkStatus);
		}
		int count = (int) checkingRecorderMapper.countByExample(example);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<EnterpriseCheckingBean> list;
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			list = checkingRecorderMapper.selectWithAgentName(checked, enterprName, checkStatus, pageBean);
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public EnterprUserInfoBean getEnterpriseInfoBean(Long enterprId) {
		EnterprUserInfo enterpriseInfo = this.getEnterpriseInfo(enterprId);
		if (enterpriseInfo == null) {
			return null;
		}
		EnterprUserInfoBean enterprUserInfoBean = new EnterprUserInfoBean();
		BeanUtils.copyProperties(enterpriseInfo, enterprUserInfoBean);
		EnterprCheckingRecorder checkingRecorder = checkingRecorderMapper.selectByPrimaryKey(enterprId);
		if (checkingRecorder != null) {
			enterprUserInfoBean.setSubEnterprName(checkingRecorder.getSubEnterprName());
			enterprUserInfoBean.setAddUser(checkingRecorder.getAddUser());
			enterprUserInfoBean.setAddTime(checkingRecorder.getAddTime());
			enterprUserInfoBean.setCheckUser(checkingRecorder.getCheckUser());
			enterprUserInfoBean.setCheckTime(checkingRecorder.getCheckTime());
		} else {// 对于原始数据处理
			enterprUserInfoBean.setAddUser("SYSTEM");
			enterprUserInfoBean.setCheckUser("SYSTEM");
			enterprUserInfoBean.setAddTime(enterpriseInfo.getCreateTime());
			enterprUserInfoBean.setCheckTime(enterpriseInfo.getCreateTime());
		}
		enterprUserInfoBean.setPassword(null);
		enterprUserInfoBean.setSecret(null);
		enterprUserInfoBean.setUpdateTime(null);
		Integer agentId = enterpriseInfo.getAgentId();
		if (agentId != null && agentId != 0) {
			AgentAccountInfo agentInfo = agentAccountInfoMapper.selectByPrimaryKey(agentId);
			if (agentInfo != null) {
				enterprUserInfoBean.setAgentName(agentInfo.getAgentName());
			}
		}
		return enterprUserInfoBean;
	}

	public EnterprUserInfo getEnterpriseInfo(Long enterprId) {
		return enterprUserInfoMapper.selectByPrimaryKey(enterprId);
	}

	@Override
	public PageResultBean<EnterprBindRecorder> getEnterpriseBindRecorders(Long enterprId, PageBean pageBean) {
		EnterprBindRecorderCondition example = new EnterprBindRecorderCondition();
		example.createCriteria().andEnterprIdEqualTo(enterprId);
		int count = (int) enterprBindRecorderMapper.countByExample(example);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<EnterprBindRecorder> list;
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			example.setOffset(pageBean.getOffset());
			example.setLimit(pageSize);
			example.setOrderByClause("id DESC");
			list = enterprBindRecorderMapper.selectByExample(example);
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public List<EnterprUserInfo> findEnterprUserInfosByEnterprName(String enterpeName) {
		try {
			EnterprUserInfoCondition enterprUserInfoCondition = new EnterprUserInfoCondition();
			enterprUserInfoCondition.createCriteria().andEnterprNameEqualTo(enterpeName);
			List<EnterprUserInfo> list = enterprUserInfoMapper.selectByExample(enterprUserInfoCondition);
			return list;
		} catch (Exception e) {
			throw new RuntimeException("根据企业名称查找企业信息出错！", e);
		}
	}

	@Override
	public long countByExample(EnterprUserInfoCondition example) {
		try {
			long count = enterprUserInfoMapper.countByExample(null);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("统计企业总数据出错！", e);
		}
	}

	@Override
	public Long countCurrentEnterprise() {
		try {
			Long count = enterprUserInfoMapper.countCurrentEnterprise();
			return count;
		} catch (Exception e) {
			throw new RuntimeException("统计本月新增企业出错！", e);
		}
	}

	@Override
	public PageResultBean<EnterprUserInfo> getEnterprises(Integer agentId, String enterprName, Date bindStartTime, Date bindEndTime, PageBean pageBean) {
		EnterprUserInfoCondition example = new EnterprUserInfoCondition();
		EnterprUserInfoCondition.Criteria criteria = example.createCriteria().andAgentIdEqualTo(agentId);
		if (enterprName != null && !"".equals(enterprName = enterprName.trim())) {
			criteria.andEnterprNameLike("%" + enterprName + "%");
		}
		if (bindStartTime != null) {
			criteria.andBindTimeGreaterThanOrEqualTo(bindStartTime);
		}
		if (bindEndTime != null) {
			criteria.andBindTimeLessThanOrEqualTo(bindEndTime);
		}
		int count = (int) enterprUserInfoMapper.countByExample(example);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<EnterprUserInfo> list;
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			example.setOffset(pageBean.getOffset());
			example.setLimit(pageSize);
			list = enterprUserInfoMapper.selectByExample(example);
			if (!CollectionUtils.isEmpty(list)) {
				list.forEach(item -> {
					item.setPassword(null);
					item.setSecret(null);
					item.setLoginName(null);
				});
			}
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public PageResultBean<EnterprCheckingRecorder> getCheckingList(Integer agentId, PageBean pageBean) {
		EnterprCheckingRecorderCondition example = new EnterprCheckingRecorderCondition();
		if (agentId == null) {
			example.createCriteria().andDeletedEqualTo(0);
		} else {
			example.createCriteria().andAgentIdEqualTo(agentId).andDeletedEqualTo(0);
		}
		int count = (int) checkingRecorderMapper.countByExample(example);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<EnterprCheckingRecorder> list;
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			example.setOffset(pageBean.getOffset());
			example.setLimit(pageSize);
			example.setOrderByClause("enterpr_id DESC");
			list = checkingRecorderMapper.selectByExample(example);
			if (!CollectionUtils.isEmpty(list)) {
				list.forEach(item -> {
					item.setLoginName(null);// 隐藏登陆账号
				});
			}
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public EnterprCheckingRecorder getCheckingInfo(Integer agentId, Long enterprId) {
		EnterprCheckingRecorder checkingRecorder = checkingRecorderMapper.selectByPrimaryKey(enterprId);
		if (checkingRecorder == null || Integer.valueOf(1).equals(checkingRecorder.getDeleted())) {
			return null;
		}
		if (!agentId.equals(checkingRecorder.getAgentId())) {
			throw new BusinessException("获取失败!");
		}
		checkingRecorder.setLoginName(null);// 隐藏登陆账号
		return checkingRecorder;
	}

	@Override
	public void deleteUnpassedCheckingInfo(Integer agentId, Long enterprId) {
		EnterprCheckingRecorder checkingRecorder = checkingRecorderMapper.selectByPrimaryKey(enterprId);
		if (checkingRecorder == null || Integer.valueOf(1).equals(checkingRecorder.getDeleted())) {
			throw new BusinessException("该记录不存在!");
		}
		if (!agentId.equals(checkingRecorder.getAgentId())) {
			throw new BusinessException("您没有权限删除该记录!");
		}
		Integer checkStatus = checkingRecorder.getCheckStatus();
		if (CheckStatus.UNPASSED.getStatus().equals(checkStatus)) {
			checkingRecorderMapper.deleteByPrimaryKey(enterprId);
		} else {
			throw new BusinessException("删除失败,只能删除审核未通过的记录!");
		}
	}

	@Override
	public void updateContactinfo(Long enterprId, EnterprUserInfo contactinfo) {
		EnterprUserInfo record = new EnterprUserInfo();
		record.setEnterprId(enterprId);
		String contactUser = contactinfo.getContactUser();
		if (contactUser != null && !"".equals(contactUser = contactUser.trim())) {
			record.setContactUser(contactUser);
		}
		String contactPhone = contactinfo.getMobile();
		if (contactPhone != null && !"".equals(contactPhone = contactPhone.trim())) {
			record.setMobile(contactPhone);
		}
		String contactEmail = contactinfo.getEmail();
		if (contactEmail != null && !"".equals(contactEmail = contactEmail.trim())) {
			record.setEmail(contactEmail);
		}
		Long grainId = contactinfo.getGrainId();
		if (null != grainId) {
			record.setGrainId(grainId);
		}
		int affectCount = enterprUserInfoMapper.updateByPrimaryKeySelective(record);
		if (affectCount == 0) {
			throw new BusinessException("该记录不存在!");
		}

	}

	@Override
	public void updateEnterpriseBindInfo(Long enterprId, EnterpriseBindInfoBean bindInfoBean, String operateUser) {
		EnterprUserInfo enterprUserInfo = enterprUserInfoMapper.selectByPrimaryKey(enterprId);
		if (enterprUserInfo == null) {
			throw new BusinessException("该企业不存在!");
		}
		Integer agentId = bindInfoBean.getAgentId();
		EnterprUserInfo record = new EnterprUserInfo();
		record.setEnterprId(enterprId);
		Integer oldAgentId = enterprUserInfo.getAgentId();
		if (agentId == -1) {// 解绑
			if (oldAgentId == 0) {// 本就处于解绑状态
				return;
			}
			record.setAgentId(0);
			// 添加解绑记录
			EnterprBindRecorderCondition example = new EnterprBindRecorderCondition();
			example.createCriteria().andEnterprIdEqualTo(enterprId).andAgentIdEqualTo(oldAgentId).andStatusEqualTo(BindStatus.BIND.getStatus());
			EnterprBindRecorder bindRecorder = new EnterprBindRecorder();
			bindRecorder.setUnbindTime(new Date());
			bindRecorder.setStatus(BindStatus.UNBIND.getStatus());
			bindRecorder.setUnbindRemark(bindInfoBean.getRemark());
			bindRecorder.setUnbindUser(operateUser);
			enterprBindRecorderMapper.updateByExampleSelective(bindRecorder, example);
		} else {
			if (oldAgentId.equals(agentId)) {// 绑定关系没变
				return;
			}
			if (oldAgentId != 0) {
				throw new BusinessException("请先解绑后再次绑定!");
			}
			AgentAccountInfo agent = agentAccountInfoMapper.selectByPrimaryKey(agentId);
			if (agent == null) {
				throw new BusinessException("代理商不存在!");
			}
			record.setAgentId(agentId);
			Date bindTime = new Date();
			record.setBindTime(bindTime);
			// 添加绑定记录
			EnterprBindRecorder bindRecorder = new EnterprBindRecorder();
			bindRecorder.setEnterprId(enterprId);
			bindRecorder.setAgentId(agentId);
			bindRecorder.setBindTime(bindTime);
			bindRecorder.setBindRemark(bindInfoBean.getRemark());
			bindRecorder.setStatus(BindStatus.BIND.getStatus());
			bindRecorder.setAgentName(agent.getAgentName());
			bindRecorder.setBindUser(operateUser);
			enterprBindRecorderMapper.insertSelective(bindRecorder);
		}
		enterprUserInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void enterpriseUniqueInfoCheck(EnterpriseUniqueInfoBean uniqueInfoBean) {
		String info = uniqueInfoBean.getInfo();
		if (info == null || "".equals(info = info.trim())) {
			throw new BusinessException("信息不能为空!");
		}
		EnterprCheckingRecorderCondition example = new EnterprCheckingRecorderCondition();
		example.createCriteria().andEnterprNameEqualTo(info);
		if (checkingRecorderMapper.countByExample(example) > 0) {
			throw new BusinessException("此企业已被绑定，请重新填写!");
		}

	}

	@Override
	public void updateEnterpriseAccountStatus(Long enterprId, Integer accountStatus) {
		if (!AcountStatus.check(accountStatus)) {
			throw new BusinessException("账号状态不正确");
		}
		EnterprUserInfo record = new EnterprUserInfo();
		record.setEnterprId(enterprId);
		record.setAccountStatus(accountStatus);
		enterprUserInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public EnterprUserInfo getEnterprise(Long enterprId) {
		return enterprUserInfoMapper.selectByPrimaryKey(enterprId);
	}

	@Override
	public EnterpriseOverview getEnterpriseOverview(Long enterprId) {
		EnterpriseOverview enterpriseOverview = new EnterpriseOverview();
		EmployeeUserInfoExample example = new EmployeeUserInfoExample();
		example.createCriteria().andEnterprIdEqualTo(enterprId).andLeaveStatusEqualTo(false);
		int workingCount = employeeUserInfoMapper.countByExample(example);
		enterpriseOverview.setWorkingCount(workingCount);
		example.clear();
		example.createCriteria().andEnterprIdEqualTo(enterprId).andLeaveStatusEqualTo(true);
		int leaveCount = employeeUserInfoMapper.countByExample(example);
		enterpriseOverview.setLeaveCount(leaveCount);
		enterpriseOverview.setTotalCount(workingCount + leaveCount);
		EnterprUserInfo enterprise = this.getEnterprise(enterprId);
		enterpriseOverview.setLeaveCredit(enterprise.getLeaveCredit());
		return enterpriseOverview;
	}

	@Override
	public void updateCreditstrategy(Long enterprId, Integer leaveCredit) {
		if (Integer.valueOf(0).equals(leaveCredit) || Integer.valueOf(1).equals(leaveCredit)) {
			EnterprUserInfo record = new EnterprUserInfo();
			record.setEnterprId(enterprId);
			record.setLeaveCredit(leaveCredit);
			enterprUserInfoMapper.updateByPrimaryKeySelective(record);
		} else {
			throw new BusinessException("状态不正确!");
		}

	}

	@Override
	public PageResultBean<EmployeeUserInfoBean> getEnterpriseEmployees(Long enterprId, String employeeNo, String employeeName, Integer leaveStatus,
			PageBean pageBean) {
		EmployeeUserInfoExample example = new EmployeeUserInfoExample();
		EmployeeUserInfoExample.Criteria criteria = example.createCriteria().andEnterprIdEqualTo(enterprId);
		if (employeeNo != null && !"".equals(employeeNo = employeeNo.trim())) {
			criteria.andEmployeeNoLike("%" + employeeNo + "%");
		}
		if (employeeName != null && !"".equals(employeeName = employeeName.trim())) {
			criteria.andEmployeeNameLike("%" + employeeName + "%");
		}
		if (leaveStatus != null) {
			criteria.andLeaveStatusEqualTo(leaveStatus == 0 ? false : true);
		}
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<EmployeeUserInfoBean> list;
		int count = employeeUserInfoMapper.countByExample(example);
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			example.setPageSize(pageSize);
			example.setPage(page);
			example.setOrderByClause("employeeId DESC");
			List<EmployeeUserInfo> employeeUserInfos = employeeUserInfoMapper.selectByExample(example);
			list = employeeUserInfos.parallelStream().map(item -> {
				EmployeeCreditInfoExample creditInfoExample = new EmployeeCreditInfoExample();
				creditInfoExample.createCriteria().andEmployeeIdEqualTo(item.getEmployeeId());
				List<EmployeeCreditInfo> employeeCreditInfo = employeeCreditInfoMapper.selectByExample(creditInfoExample);
				EmployeeUserInfoBean employeeUserInfoBean = new EmployeeUserInfoBean();
				employeeUserInfoBean.setCreditinfo(employeeCreditInfo);
				employeeUserInfoBean.setEmployeeName(item.getEmployeeName());
				employeeUserInfoBean.setEmployeeNo(item.getEmployeeNo());
				employeeUserInfoBean.setLeaveStatus(item.getLeaveStatus());
				employeeUserInfoBean.setLoginName(item.getLoginName());
				if (item.getCreated() != null) {
					employeeUserInfoBean.setCreateTime(new Date(item.getCreated().intValue() * 1000L));
				}
				return employeeUserInfoBean;
			}).collect(Collectors.toList());
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public PageResultBean<EnterprBindRecorderBean> getAgentEnterprisesBindhistory(Integer agentId, PageBean pageBean) {
		int count = enterprBindRecorderMapper.countDistinctAgentUnbind(agentId);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<EnterprBindRecorderBean> list;
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			list = enterprBindRecorderMapper.getAgentEnterprisesBindhistory(agentId, pageBean);
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public PageResultBean<? extends EnterprUserInfo> getEnterpriseWithOrderOverview(Integer agentId, String enterprName, Date bindStartTime, Date bindEndTime,
			PageBean pageBean) {
		PageResultBean<EnterprUserInfo> enterprises = this.getEnterprises(agentId, enterprName, bindStartTime, bindEndTime, pageBean);
		List<EnterprUserInfo> list = enterprises.getList();
		if (CollectionUtils.isEmpty(list)) {
			return enterprises;
		}
		List<EnterprUserInfoBean> collect = list.parallelStream().map(item -> {
			EnterprUserInfoBean enterprUserInfoBean = new EnterprUserInfoBean();
			enterprUserInfoBean.setEnterprId(item.getEnterprId());
			enterprUserInfoBean.setEnterprName(item.getEnterprName());
			enterprUserInfoBean.setEmployeeNum(item.getEmployeeNum());
			enterprUserInfoBean.setBindTime(item.getBindTime());
			OrderOverview enterpriseOrderOverview = orderService.getEnterpriseOrderOverview(item.getEnterprId());
			enterprUserInfoBean.setCostTotal(enterpriseOrderOverview.getPointTotal().add(enterpriseOrderOverview.getPriceTotal()));
			enterprUserInfoBean.setOrderCount(enterpriseOrderOverview.getOrderCount());
			return enterprUserInfoBean;
		}).collect(Collectors.toList());
		return new PageResultBean<>(enterprises.getPage(), enterprises.getPageSize(), enterprises.getCount(), collect);
	}

	@Override
	public EnterprUserInfo getEnterpriseByEmployeeId(Long buyerUserId) {
		EmployeeUserInfo employeeUserInfo = employeeUserInfoMapper.selectByPrimaryKey(buyerUserId);
		if (employeeUserInfo != null) {
			return this.getEnterprise(employeeUserInfo.getEnterprId());
		}
		return null;
	}

	@Override
	public List<EnterprUserInfo> getEnterpriseNames(List<Long> enterprIds) {
		if (CollectionUtils.isEmpty(enterprIds)) {
			return Collections.emptyList();
		}
		return enterprUserInfoMapper.selectEnterpriseNames(enterprIds);
	}

	@Override
	public EnterprUserInfo getEnterpriseByName(String customerName) {
		EnterprUserInfoCondition example = new EnterprUserInfoCondition();
		example.createCriteria().andEnterprNameEqualTo(customerName);
		List<EnterprUserInfo> enterprUserInfos = enterprUserInfoMapper.selectByExample(example);
		return CollectionUtils.isEmpty(enterprUserInfos) ? null : enterprUserInfos.get(0);
	}

}
