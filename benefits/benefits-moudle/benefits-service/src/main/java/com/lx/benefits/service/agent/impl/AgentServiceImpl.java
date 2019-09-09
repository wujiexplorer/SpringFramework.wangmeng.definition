package com.lx.benefits.service.agent.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.druid.util.StringUtils;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.constants.AcountStatus;
import com.lx.benefits.bean.constants.AgentProfitType;
import com.lx.benefits.bean.constants.AgentRebateType;
import com.lx.benefits.bean.constants.AgentType;
import com.lx.benefits.bean.constants.BindStatus;
import com.lx.benefits.bean.constants.CheckStatus;
import com.lx.benefits.bean.constants.RegisterType;
import com.lx.benefits.bean.entity.agent.AgentAccountCheckingRecorder;
import com.lx.benefits.bean.entity.agent.AgentAccountCheckingRecorderCondition;
import com.lx.benefits.bean.entity.agent.AgentAccountInfo;
import com.lx.benefits.bean.entity.agent.AgentAccountInfoCondition;
import com.lx.benefits.bean.entity.agent.AgentAccountInfoCondition.Criteria;
import com.lx.benefits.bean.entity.agent.AgentBindRecorder;
import com.lx.benefits.bean.entity.agent.AgentBindRecorderCondition;
import com.lx.benefits.bean.entity.agent.AgentLevel;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfoCondition;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.vo.agent.AgentAccountInfoBean;
import com.lx.benefits.bean.vo.agent.AgentBindInfoBean;
import com.lx.benefits.bean.vo.agent.AgentCheckingBean;
import com.lx.benefits.bean.vo.agent.AgentUniqueInfoBean;
import com.lx.benefits.mapper.agent.AgentAccountCheckingRecorderMapper;
import com.lx.benefits.mapper.agent.AgentAccountInfoMapper;
import com.lx.benefits.mapper.agent.AgentBindRecorderMapper;
import com.lx.benefits.mapper.agent.AgentLevelMapper;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.service.agent.AgentService;

@Service
public class AgentServiceImpl implements AgentService {

	@Autowired
	private AgentLevelMapper agentLevelMapper;
	@Autowired
	private AgentAccountInfoMapper accountInfoMapper;
	@Autowired
	private AgentAccountCheckingRecorderMapper checkingRecorderMapper;
	@Autowired
	private AgentBindRecorderMapper agentBindRecorderMapper;
	@Autowired
	private EnterprUserInfoMapper enterprUserInfoMapper;

	@Override
	public void updateAgentAccountStatus(Integer agentId, Integer accountStatus) {
		if (!AcountStatus.check(accountStatus)) {
			throw new BusinessException("账号状态不正确");
		}
		AgentAccountInfo record = new AgentAccountInfo();
		record.setId(agentId);
		record.setAccountStatus(accountStatus);
		accountInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void addAgent(AgentAccountInfoBean agentAccountInfoBean, RegisterType registerType, String addUser, Integer parentId) {
		List<String> certifyImages = agentAccountInfoBean.getCertifyImages();
		if (CollectionUtils.isEmpty(certifyImages)) {
			throw new BusinessException("证件图片不能为空!");
		}
		String agentName = agentAccountInfoBean.getAgentName();
		String agentIdentityId = agentAccountInfoBean.getAgentIdentityId();
		String agentRegisterAddress = agentAccountInfoBean.getAgentRegisterAddress();
		if (AgentType.ENTERPRISE.getType().equals(agentAccountInfoBean.getAgentType())) {// 个人
			if (agentName == null || "".equals(agentName = agentName.trim())) {// 企业全称
				throw new BusinessException("企业全称不能为空!");
			}
			if (agentIdentityId == null || "".equals(agentIdentityId.trim())) {// 营业编号
				throw new BusinessException("营业编号不能为空!");
			}
			if (agentRegisterAddress == null || "".equals(agentRegisterAddress.trim())) {// 注册地址
				throw new BusinessException("注册地址不能为空!");
			}
		} else if (AgentType.PERSONAL.getType().equals(agentAccountInfoBean.getAgentType())) {
			if (agentName == null || "".equals(agentName = agentName.trim())) {// 姓名
				throw new BusinessException("姓名不能为空!");
			}
			if (agentIdentityId == null || "".equals(agentIdentityId.trim())) {// 身份证号
				throw new BusinessException("身份证号不能为空!");
			}
			if (agentRegisterAddress == null || "".equals(agentRegisterAddress.trim())) {// 住址
				throw new BusinessException("地址信息不能为空!");
			}
		} else {
			throw new BusinessException("代理商类型错误!");
		}
		AgentAccountCheckingRecorderCondition example = new AgentAccountCheckingRecorderCondition();
		example.createCriteria().andAgentIdentityIdEqualTo(agentAccountInfoBean.getAgentIdentityId())
				.andCheckStatusNotEqualTo(CheckStatus.UNPASSED.getStatus());
		if (AgentType.ENTERPRISE.getType().equals(agentAccountInfoBean.getAgentType())) {// 校验企业名称
			example.or().andAgentNameEqualTo(agentName).andCheckStatusNotEqualTo(CheckStatus.UNPASSED.getStatus());
		}
		List<AgentAccountCheckingRecorder> checkingRecorders = checkingRecorderMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(checkingRecorders)) {
			if (CheckStatus.PASSED.getStatus().equals(checkingRecorders.get(0).getCheckStatus())) {
				throw new BusinessException("该代理商已存在!");
			} else {
				throw new BusinessException("该代理商已在审批中!");
			}
		}
		AgentAccountCheckingRecorder checkRecorder = new AgentAccountCheckingRecorder();
		checkRecorder.setAgentType(agentAccountInfoBean.getAgentType());
		checkRecorder.setAgentName(agentName);
		checkRecorder.setAgentIdentityId(agentIdentityId);
		checkRecorder.setAgentRegisterAddress(agentRegisterAddress);
		String certifyImageUrls = certifyImages.stream().filter(item -> !StringUtils.isEmpty(item)).collect(Collectors.joining(","));
		checkRecorder.setCertifyImageUrls(certifyImageUrls);
		checkRecorder.setContactUser(agentAccountInfoBean.getContactUser());
		checkRecorder.setContactPhone(agentAccountInfoBean.getContactPhone());
		checkRecorder.setContactEmail(agentAccountInfoBean.getContactEmail());
		checkRecorder.setAddUser(addUser);
		checkRecorder.setAddTime(new Date());
		if (parentId != null && parentId != 0) {
			checkRecorder.setParentId(parentId);
			checkRecorder.setBindTime(new Date());
		}
		checkRecorder.setRegisteredType(registerType.getType());
		checkingRecorderMapper.insertSelective(checkRecorder);
	}

	@Override
	public AgentAccountInfoBean getAgentInfoBean(Integer agentId) {
		AgentAccountInfoBean agentInfoBean = accountInfoMapper.getAgentInfoBean(agentId);
		if (agentInfoBean == null) {
			return null;
		}
		String certifyImageUrls = agentInfoBean.getCertifyImageUrls();
		if (!StringUtils.isEmpty(certifyImageUrls)) {
			agentInfoBean.setCertifyImages(Arrays.asList(certifyImageUrls.split(",")));
		}
		return agentInfoBean;
	}

	@Override
	public AgentCheckingBean getAgentCheckingInfo(Integer agentId) {
		AgentCheckingBean checkingBean = checkingRecorderMapper.selectAgentCheckingBean(agentId);
		if (checkingBean == null) {
			throw new BusinessException("该记录不存在!");
		}
		String certifyImageUrls = checkingBean.getCertifyImageUrls();
		if (!StringUtils.isEmpty(certifyImageUrls)) {
			checkingBean.setCertifyImages(Arrays.asList(certifyImageUrls.split(",")));
		}
		return checkingBean;
	}

	@Override
	public List<AgentLevel> getAgentlevels() {
		return agentLevelMapper.selectByExample(null);
	}

	@Override
	public void addAgentLevel(AgentLevel agentLevel) {
		if (!AgentProfitType.check(agentLevel.getProfitType())) {
			throw new BusinessException("收益方式错误!");
		}
		if (!AgentRebateType.check(agentLevel.getProfitType())) {
			throw new BusinessException("返点模式错误!");
		}
		BigDecimal profitProportion = agentLevel.getProfitProportion();
		if (profitProportion.doubleValue() >= 100 || profitProportion.doubleValue() < 0) {
			throw new BusinessException("收益比例范围须在0%-100%之间!");
		}
		agentLevel.setId(null);
		try {
			agentLevelMapper.insertSelective(agentLevel);
		} catch (DuplicateKeyException e) {
			throw new BusinessException("等级名称已存在!");
		}
	}

	@Override
	public void updateAgentlevelById(Integer id, AgentLevel agentLevel) {
		AgentLevel dbAgentLevel = agentLevelMapper.selectByPrimaryKey(id);
		if (dbAgentLevel == null) {
			throw new BusinessException("该配置不存在!");
		}
		AgentLevel record = new AgentLevel();
		BeanUtils.copyProperties(agentLevel,record);
		record.setId(id);
		String name = agentLevel.getName();
		if (name != null && !"".equals(name = name.trim())) {
			record.setName(name);
		}
		BigDecimal profitProportion = agentLevel.getProfitProportion();
		if (profitProportion != null && !profitProportion.equals(dbAgentLevel.getProfitProportion())) {// 返点比例更新
			if (profitProportion.doubleValue() >= 100 || profitProportion.doubleValue() < 0) {
				throw new BusinessException("收益比例范围须在0%-100%之间!");
			}
			record.setProfitProportion(agentLevel.getProfitProportion());
		}
		try {
			agentLevelMapper.updateByPrimaryKeySelective(record);
		} catch (DuplicateKeyException e) {
			throw new BusinessException("等级名称已存在!");
		}
	}

	@Override
	public void deleteAgentlevelById(Integer id) {
		AgentLevel agentLevel = agentLevelMapper.selectByPrimaryKey(id);
		if (agentLevel == null) {
			return;
		}
		AgentAccountInfoCondition example = new AgentAccountInfoCondition();
		example.createCriteria().andAgentLevelEqualTo(agentLevel.getId());
		long count = accountInfoMapper.countByExample(example);
		if (count == 0) {
			agentLevelMapper.deleteByPrimaryKey(id);
		} else {
			throw new BusinessException("存在该等级的代理商，删除失败!");
		}

	}

	@Override
	public void updateAgentContactinfo(Integer agentId, AgentAccountInfoBean agentAccountInfoBean) {
		AgentAccountInfo record = new AgentAccountInfo();
		record.setId(agentId);
		String contactUser = agentAccountInfoBean.getContactUser();
		if (contactUser != null && !"".equals(contactUser = contactUser.trim())) {
			record.setContactUser(contactUser);
		}
		String contactPhone = agentAccountInfoBean.getContactPhone();
		if (contactPhone != null && !"".equals(contactPhone = contactPhone.trim())) {
			record.setContactPhone(contactPhone);
		}
		String contactEmail = agentAccountInfoBean.getContactEmail();
		if (contactEmail != null && !"".equals(contactEmail = contactEmail.trim())) {
			record.setContactEmail(contactEmail);
		}
		int affectCount = accountInfoMapper.updateByPrimaryKeySelective(record);
		if (affectCount == 0) {
			throw new BusinessException("该记录不存在!");
		}
	}

	@Transactional
	@Override
	public void updateAgentBindInfo(Integer agentId, AgentBindInfoBean bindInfoBean) {
		AgentAccountInfo agentAccountInfo = accountInfoMapper.selectByPrimaryKey(agentId);
		if (agentAccountInfo == null) {
			throw new BusinessException("该代理商不存在!");
		}
		AgentAccountInfo record = new AgentAccountInfo();
		record.setId(agentId);
		Integer oldParentId = agentAccountInfo.getParentId();
		if (bindInfoBean.getParentAgentId() == -1) {// 解绑
			if (oldParentId == 0) {// 本就处于解绑状态
				return;
			}
			record.setParentId(0);
			// 添加解绑记录
			AgentBindRecorderCondition example = new AgentBindRecorderCondition();
			example.createCriteria().andAgentIdEqualTo(agentId).andParentAgentIdEqualTo(agentAccountInfo.getParentId())
					.andStatusEqualTo(BindStatus.BIND.getStatus());
			AgentBindRecorder bindRecorder = new AgentBindRecorder();
			bindRecorder.setUnbindTime(new Date());
			bindRecorder.setStatus(BindStatus.UNBIND.getStatus());
			bindRecorder.setUnbindRemark(bindInfoBean.getRemark());
			agentBindRecorderMapper.updateByExampleSelective(bindRecorder, example);
		} else {
			if (oldParentId.equals(bindInfoBean.getParentAgentId())) {// 绑定关系没变
				return;
			}
			if (oldParentId != 0) {
				throw new BusinessException("请先解绑后再次绑定!");
			}
			if (agentId.equals(bindInfoBean.getParentAgentId())) {
				throw new BusinessException("父级代理商不能是自己!");
			}
			AgentAccountInfo parentAgent = accountInfoMapper.selectByPrimaryKey(bindInfoBean.getParentAgentId());
			if (parentAgent == null) {
				throw new BusinessException("父级代理商不存在!");
			}
			record.setParentId(bindInfoBean.getParentAgentId());
			Date bindTime = new Date();
			record.setBindTime(bindTime);
			// 添加绑定记录
			AgentBindRecorder agentBindRecorder = new AgentBindRecorder();
			agentBindRecorder.setAgentId(agentId);
			agentBindRecorder.setParentAgentId(bindInfoBean.getParentAgentId());
			agentBindRecorder.setParentAgentName(parentAgent.getAgentName());
			agentBindRecorder.setBindTime(bindTime);
			agentBindRecorder.setBindRemark(bindInfoBean.getRemark());
			agentBindRecorder.setStatus(BindStatus.BIND.getStatus());
			agentBindRecorderMapper.insertSelective(agentBindRecorder);
		}
		accountInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Transactional
	@Override
	public void checkingAgent(Integer agentId, AgentCheckingBean agentCheckingBean, String checkUser) {
		AgentAccountCheckingRecorder checkingRecorder = checkingRecorderMapper.selectByPrimaryKey(agentId);
		if (checkingRecorder == null) {
			throw new BusinessException("该记录不存在!");
		}
		if (!CheckStatus.INIT.getStatus().equals(checkingRecorder.getCheckStatus())) {// 非初始化状态
			throw new BusinessException("该代理商已审批，请勿重复审批!");
		}
		Integer checkStatus = agentCheckingBean.getCheckStatus();
		AgentAccountCheckingRecorder record = new AgentAccountCheckingRecorder();
		record.setId(agentId);
		record.setCheckStatus(checkStatus);// 审批状态
		record.setCheckTime(new Date());// 审批时间
		record.setCheckUser(checkUser);
		if (CheckStatus.PASSED.getStatus().equals(checkStatus)) {// 通过
			String contractNumber = agentCheckingBean.getContractNumber();
			if (contractNumber == null || "".equals(contractNumber = contractNumber.trim())) {// 合同编号为空
				throw new BusinessException("合同编号不能为空!");
			}
			Integer agentLevelId = agentCheckingBean.getAgentLevel();
			if (agentLevelId == null || agentLevelMapper.selectByPrimaryKey(agentLevelId) == null) {
				throw new BusinessException("请选择正确的代理商等级!");
			}
			String loginName = agentCheckingBean.getLoginName();
			String password = agentCheckingBean.getPassword();
			if (loginName == null || "".equals(loginName = loginName.trim())) {
				throw new BusinessException("账号不能为空!");
			}
			if (!loginName.matches("^[A-Za-z]\\w{5,17}$")) {
				throw new BusinessException("账号格式不正确!");
			}
			if (password == null || "".equals(password = password.trim())) {
				throw new BusinessException("密码不能为空!");
			}
			if (!password.matches(".{6,16}")) {
				throw new BusinessException("密码6~16个字符");
			}
			record.setContractNumber(contractNumber);
			record.setAgentLevel(agentLevelId);
			record.setLoginName(loginName);
			checkingRecorderMapper.updateByPrimaryKeySelective(record);// 更新审批状态

			// 添加代理商信息
			AgentAccountInfo agentAccountInfo = new AgentAccountInfo();
			BeanUtils.copyProperties(checkingRecorder, agentAccountInfo);
			agentAccountInfo.setContractNumber(contractNumber);
			agentAccountInfo.setAgentLevel(agentLevelId);
			agentAccountInfo.setLoginName(loginName);
			String secret = EncryptUtil.generateSecret();
			agentAccountInfo.setSecret(secret);
			agentAccountInfo.setPassword(EncryptUtil.encodePassword(password, secret));
			try {
				accountInfoMapper.insertSelective(agentAccountInfo);
			} catch (DuplicateKeyException e) {
				throw new BusinessException("该账号已存在!");
			}
			if (checkingRecorder.getParentId() != 0) {// 存在父级代理，添加绑定记录
				AgentBindRecorder bindRecorder = new AgentBindRecorder();
				bindRecorder.setAgentId(checkingRecorder.getId());
				bindRecorder.setBindTime(checkingRecorder.getAddTime());
				bindRecorder.setParentAgentId(checkingRecorder.getParentId());
				AgentAccountInfo parent = accountInfoMapper.selectByPrimaryKey(checkingRecorder.getParentId());
				bindRecorder.setParentAgentName(parent.getAgentName());
				bindRecorder.setBindRemark("父级代理商添加绑定");
				agentBindRecorderMapper.insertSelective(bindRecorder);
			}
		} else if (CheckStatus.UNPASSED.getStatus().equals(checkStatus)) {// 未通过
			String checkRemark = agentCheckingBean.getCheckRemark();
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
	public PageResultBean<AgentAccountInfoBean> getAgents(String agentName, String contactUser, String contactPhone, PageBean pageBean) {
		AgentAccountInfoCondition example = new AgentAccountInfoCondition();
		Criteria criteria = example.createCriteria();
		if (agentName != null && !"".equals(agentName = agentName.trim())) {
			criteria.andAgentNameLike("%" + agentName + "%");
		}
		if (contactUser != null && !"".equals(contactUser.trim())) {
			criteria.andContactUserLike("%" + contactUser + "%");
		}
		if (contactPhone != null && !"".equals(contactPhone = contactPhone.trim())) {
			criteria.andContactPhoneLike("%" + contactPhone + "%");
		}
		int count = (int) accountInfoMapper.countByExample(example);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<AgentAccountInfoBean> list;
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			list = accountInfoMapper.selectAgentAccountInfoBean(agentName, contactUser, contactPhone, pageBean);
			if (!CollectionUtils.isEmpty(list)) {
				list.stream().forEach(item -> {
					// 设置下级代理数量
					AgentAccountInfoCondition condition = new AgentAccountInfoCondition();
					condition.createCriteria().andParentIdEqualTo(item.getId());
					int subAgentCount = (int) accountInfoMapper.countByExample(condition);
					item.setSubAgentCount(subAgentCount);
					// 设置客户数量
					EnterprUserInfoCondition enterprCondition = new EnterprUserInfoCondition();
					enterprCondition.createCriteria().andAgentIdEqualTo(item.getId());
					int enterpriseCount = (int) enterprUserInfoMapper.countByExample(enterprCondition);
					item.setEnterpriseCount(enterpriseCount);
				});
			}
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public PageResultBean<AgentAccountInfoBean> getSubagents(Integer agentId, Integer agenTtype, String agentName, Date bindStartTime, Date bindEndTime,
			PageBean pageBean, boolean needEnterCount) {
		AgentAccountInfoCondition example = new AgentAccountInfoCondition();
		Criteria criteria = example.createCriteria().andParentIdEqualTo(agentId);
		if (agenTtype != null) {
			criteria.andAgentTypeEqualTo(agenTtype);
		}
		if (agentName != null && !"".equals(agentName = agentName.trim())) {
			criteria.andAgentNameLike("%" + agentName + "%");
		}
		if (bindStartTime != null) {
			criteria.andBindTimeGreaterThanOrEqualTo(bindStartTime);
		}
		if (bindEndTime != null) {
			criteria.andBindTimeLessThanOrEqualTo(bindEndTime);
		}
		int count = (int) accountInfoMapper.countByExample(example);
		Integer pageSize = pageBean.getPageSize();
		Integer page = pageBean.getPage();
		if (count == 0) {
			return new PageResultBean<>(page, pageSize, count, Collections.emptyList());
		}
		example.setLimit(pageSize);
		example.setOffset((page - 1) * pageSize);
		example.setOrderByClause("id DESC");
		List<AgentAccountInfo> subagentAccountInfos = accountInfoMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(subagentAccountInfos)) {
			return new PageResultBean<>(page, pageSize, count, Collections.emptyList());
		}
		List<AgentAccountInfoBean> list = subagentAccountInfos.stream().map(item -> {
			AgentAccountInfoBean agentAccountInfoBean = new AgentAccountInfoBean();
			agentAccountInfoBean.setId(item.getId());
			agentAccountInfoBean.setAgentType(item.getAgentType());
			agentAccountInfoBean.setAgentName(item.getAgentName());
			agentAccountInfoBean.setBindTime(item.getBindTime());
			if (needEnterCount) {
				EnterprUserInfoCondition enterprCondition = new EnterprUserInfoCondition();
				enterprCondition.createCriteria().andAgentIdEqualTo(item.getId());
				List<EnterprUserInfo> enterprUserInfos = enterprUserInfoMapper.selectByExample(enterprCondition);
				agentAccountInfoBean.setEnterpriseCount(enterprUserInfos.size());
				if (enterprUserInfos.size() == 0) {
					agentAccountInfoBean.setEmployeeCount(0);
				} else {
					int employeeCount = enterprUserInfos.stream().mapToInt(EnterprUserInfo::getEmployeeNum).sum();
					agentAccountInfoBean.setEmployeeCount(employeeCount);
				}
			}
			return agentAccountInfoBean;
		}).collect(Collectors.toList());
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public void deleteCheckingUnpassData(Integer parentAgentId, Integer subAgentId) {
		AgentAccountCheckingRecorderCondition example = new AgentAccountCheckingRecorderCondition();
		example.createCriteria().andIdEqualTo(subAgentId).andParentIdEqualTo(parentAgentId).andCheckStatusEqualTo(CheckStatus.UNPASSED.getStatus())
				.andDeletedEqualTo(0);
		AgentAccountCheckingRecorder record = new AgentAccountCheckingRecorder();
		record.setDeleted(1);// 逻辑删除
		int deleteCount = checkingRecorderMapper.updateByExampleSelective(record, example);
		if (deleteCount == 0) {
			AgentAccountCheckingRecorder checkingRecorder = checkingRecorderMapper.selectByPrimaryKey(subAgentId);
			if (checkingRecorder == null || Integer.valueOf(1).equals(checkingRecorder.getDeleted())) {
				throw new BusinessException("记录不存在!");
			}
			if (!checkingRecorder.getParentId().equals(parentAgentId)) {
				throw new BusinessException("删除失败,你没有权限删除该记录!");
			}
			if (!CheckStatus.UNPASSED.getStatus().equals(checkingRecorder.getCheckStatus())) {
				throw new BusinessException("删除失败,只能删除审核不通过的记录!");
			}
		}
		return;
	}

	@Override
	public PageResultBean<AgentCheckingBean> getCheckingList(Integer checked, String agentName, Integer checkStatus, PageBean pageBean) {
		AgentAccountCheckingRecorderCondition example = new AgentAccountCheckingRecorderCondition();
		AgentAccountCheckingRecorderCondition.Criteria criteria = example.createCriteria();
		if (!Integer.valueOf(1).equals(checked)) {
			criteria.andCheckStatusEqualTo(CheckStatus.INIT.getStatus());
		} else if (checkStatus != null) {
			criteria.andCheckStatusEqualTo(checkStatus);
		} else {
			criteria.andCheckStatusNotEqualTo(CheckStatus.INIT.getStatus());
		}
		if (agentName != null && !"".equals(agentName = agentName.trim())) {
			criteria.andAgentNameLike("%" + agentName + "%");
		}
		int count = (int) checkingRecorderMapper.countByExample(example);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<AgentCheckingBean> list;
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			list = checkingRecorderMapper.selectWithParentAgentName(checked, agentName, checkStatus, pageBean);
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public PageResultBean<AgentAccountCheckingRecorder> getAgentCheckingList(Integer agentId, PageBean pageBean) {
		AgentAccountCheckingRecorderCondition example = new AgentAccountCheckingRecorderCondition();
		example.createCriteria().andParentIdEqualTo(agentId).andDeletedEqualTo(0);
		int count = (int) checkingRecorderMapper.countByExample(example);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<AgentAccountCheckingRecorder> list;
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			example.setLimit(pageSize);
			example.setOffset((page - 1) * pageSize);
			example.setOrderByClause("id DESC");
			list = checkingRecorderMapper.selectByExample(example);
			if (!CollectionUtils.isEmpty(list)) {
				for (AgentAccountCheckingRecorder item : list) {
					item.setLoginName(null);// 将下级代理商的账号隐藏
				}
			}
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}

	@Override
	public void agentUniqueInfoCheck(AgentUniqueInfoBean agentUniqueInfoBean) {
		String info = agentUniqueInfoBean.getInfo();
		if (info == null || "".equals(info = info.trim())) {
			throw new BusinessException("信息不能为空!");
		}
		AgentAccountCheckingRecorderCondition example = new AgentAccountCheckingRecorderCondition();
		AgentAccountCheckingRecorderCondition.Criteria criteria = example.createCriteria().andCheckStatusNotEqualTo(CheckStatus.UNPASSED.getStatus());
		if (AgentType.PERSONAL.getType().equals(agentUniqueInfoBean.getAgentType())) {// 个人校验身份证
			criteria.andAgentIdentityIdEqualTo(info);
		} else if (AgentType.ENTERPRISE.getType().equals(agentUniqueInfoBean.getAgentType())) {// 校验企业名称
			criteria.andAgentNameEqualTo(info);
		} else {
			throw new BusinessException("代理商类型错误!");
		}
		if (checkingRecorderMapper.countByExample(example) > 0) {
			throw new BusinessException("此代理已被绑定,请重新填写!");
		}
	}

	@Override
	public AgentAccountInfo getAgentInfo(Integer agentId) {
		return accountInfoMapper.selectByPrimaryKey(agentId);
	}

	@Override
	public AgentLevel getAgentlevel(Integer agentId) {
		AgentAccountInfo agentInfo = this.getAgentInfo(agentId);
		if (agentInfo == null) {
			return null;
		}
		return agentLevelMapper.selectByPrimaryKey(agentInfo.getAgentLevel());
	}

	@Override
	public Integer updateAccountInfoAgentLevel(Integer agentId, Integer agentLevelId) {
		if(null == agentId || null == agentLevelId){
			throw new BusinessException("代理商ID或代理等级ID不能为空！");
		}
		try{
			AgentAccountInfo agentAccountInfo = new AgentAccountInfo();
			agentAccountInfo.setId(agentId);
			agentAccountInfo.setAgentLevel(agentLevelId);
			Integer count = accountInfoMapper.updateByPrimaryKeySelective(agentAccountInfo);
			return count;
		}catch (Exception e){
			throw new RuntimeException("修改代理信息中的代理等级出错！",e);
		}
	}

}
