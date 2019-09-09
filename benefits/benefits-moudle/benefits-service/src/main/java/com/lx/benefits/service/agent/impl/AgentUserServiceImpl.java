package com.lx.benefits.service.agent.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.constants.AcountStatus;
import com.lx.benefits.bean.entity.agent.AgentAccountInfo;
import com.lx.benefits.bean.entity.agent.AgentAccountInfoCondition;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.SessionAgentInfo;
import com.lx.benefits.mapper.agent.AgentAccountInfoMapper;
import com.lx.benefits.service.agent.AgentUserService;

@Service
public class AgentUserServiceImpl implements AgentUserService {

	@Autowired
	private AgentAccountInfoMapper accountInfoMapper;

	@Override
	public void resetAgentPassword(Integer agentId, String password) {
		if (password == null || "".equals(password = password.trim())) {
			throw new BusinessException("密码不能为空!");
		}
		AgentAccountInfo record = new AgentAccountInfo();
		record.setId(agentId);
		String secret = EncryptUtil.generateSecret();
		record.setSecret(secret);
		record.setPassword(EncryptUtil.encodePassword(password, secret));
		accountInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SessionAgentInfo login(String loginName, String password) {
		if (loginName == null || "".equals(loginName = loginName.trim())) {
			throw new BusinessException("账号不能为空!");
		}
		if (password == null || "".equals(password = password.trim())) {
			throw new BusinessException("密码不能为空!");
		}

		AgentAccountInfoCondition example = new AgentAccountInfoCondition();
		example.createCriteria().andLoginNameEqualTo(loginName);
		List<AgentAccountInfo> agentUserInfos = accountInfoMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(agentUserInfos)) {
			throw new BusinessException("账号或密码错误!");
		}
		AgentAccountInfo agentUserInfo = agentUserInfos.get(0);
		String encodePassword = EncryptUtil.encodePassword(password, agentUserInfo.getSecret());
		if (!agentUserInfo.getPassword().equals(encodePassword)) {
			throw new BusinessException("账号或密码错误!");
		}
		if (AcountStatus.FREEZE.getStatus().equals(agentUserInfo.getAccountStatus())) {
			throw new BusinessException("您的账号已被冻结,请联系管理员!");
		}
		SessionAgentInfo sessionAgentInfo = new SessionAgentInfo();
		sessionAgentInfo.setLoginName(loginName);
		sessionAgentInfo.setAgentId(agentUserInfo.getId());
		sessionAgentInfo.setAgentName(agentUserInfo.getAgentName());
		return sessionAgentInfo;
	}

	@Override
	public void modifyPassword(String loginName, String oldPassword, String password) {
		if (oldPassword == null || "".equals(oldPassword = oldPassword.trim())) {
			throw new BusinessException("原始密码不能为空!");
		}
		if (password == null || "".equals(password = password.trim())) {
			throw new BusinessException("新密码不能为空!");
		}
		AgentAccountInfoCondition example = new AgentAccountInfoCondition();
		example.createCriteria().andLoginNameEqualTo(loginName);
		List<AgentAccountInfo> agentUserInfos = accountInfoMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(agentUserInfos)) {
			throw new BusinessException("该账号已不存在");
		}
		AgentAccountInfo agentUserInfo = agentUserInfos.get(0);
		String encodePassword = EncryptUtil.encodePassword(oldPassword, agentUserInfo.getSecret());
		if (!encodePassword.equals(agentUserInfo.getPassword())) {
			throw new BusinessException("原始密码错误!");
		}
		String newEncodePassword = EncryptUtil.encodePassword(password, agentUserInfo.getSecret());
		AgentAccountInfo record = new AgentAccountInfo();
		record.setId(agentUserInfo.getId());
		record.setPassword(newEncodePassword);
		accountInfoMapper.updateByPrimaryKeySelective(record);
	}

}
