package com.lx.benefits.service.agent;

import java.util.Date;
import java.util.List;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.constants.RegisterType;
import com.lx.benefits.bean.entity.agent.AgentAccountCheckingRecorder;
import com.lx.benefits.bean.entity.agent.AgentAccountInfo;
import com.lx.benefits.bean.entity.agent.AgentLevel;
import com.lx.benefits.bean.vo.agent.AgentAccountInfoBean;
import com.lx.benefits.bean.vo.agent.AgentBindInfoBean;
import com.lx.benefits.bean.vo.agent.AgentCheckingBean;
import com.lx.benefits.bean.vo.agent.AgentUniqueInfoBean;
import io.swagger.models.auth.In;

public interface AgentService {

	/**
	 * 更新代理商账号状态（冻结/解冻）
	 * 
	 * @param agentId
	 *            代理商ID
	 * @param accountStatus
	 *            更新状态
	 */
	void updateAgentAccountStatus(Integer agentId, Integer accountStatus);

	/**
	 * 提交代理商信息
	 * 
	 * @param parentID
	 * 
	 * @param agentAccountInfo
	 */
	void addAgent(AgentAccountInfoBean agentAccountInfoBean, RegisterType registerType, String addUser, Integer parentId);

	/**
	 * 获取代理商等级列表信息
	 * 
	 * @return
	 */
	List<AgentLevel> getAgentlevels();

	/**
	 * 添加代理商等级
	 * 
	 * @param agentLevel
	 *            代理商等级信息
	 */
	void addAgentLevel(AgentLevel agentLevel);

	/**
	 * 根据ID更新代理商等级
	 * 
	 * @param id
	 *            代理商ID
	 * @param agentLevel
	 *            代理商等级信息
	 */
	void updateAgentlevelById(Integer id, AgentLevel agentLevel);

	/**
	 * 根据ID删除代理商等级
	 * 
	 * @param id
	 *            代理商ID
	 */
	void deleteAgentlevelById(Integer id);

	/**
	 * 获取代理商信息
	 * 
	 * @param agentId
	 * @return
	 */
	AgentAccountInfoBean getAgentInfoBean(Integer agentId);

	/**
	 * 获取代理商审核信息
	 * 
	 * @param agentId
	 * @return
	 */
	AgentCheckingBean getAgentCheckingInfo(Integer agentId);

	/**
	 * 更新代理商联系信息
	 * 
	 * @param agentId
	 * @param agentAccountInfoBean
	 */
	void updateAgentContactinfo(Integer agentId, AgentAccountInfoBean agentAccountInfoBean);

	/**
	 * 更新代理商的上级绑定关系
	 * 
	 * @param agentId
	 * @param bindInfoBean
	 */
	void updateAgentBindInfo(Integer agentId, AgentBindInfoBean bindInfoBean);

	/**
	 * 对代理商进行审批
	 * 
	 * @param agentId
	 * @param agentCheckingBean
	 */
	void checkingAgent(Integer agentId, AgentCheckingBean agentCheckingBean, String checkUser);

	PageResultBean<AgentAccountInfoBean> getAgents(String agentName, String contactUser, String contactPhone, PageBean pageBean);

	PageResultBean<AgentAccountInfoBean> getSubagents(Integer agentId, Integer agenTtype, String agentName, Date bindStartTime, Date bindEndTime,
			PageBean pageBean, boolean needEnterCount);

	/**
	 * 代理商删除审批不通过的下级代理商
	 * 
	 * @param parentAgentId
	 *            父级代理商Id
	 * @param subAgentId
	 *            子级代理商Id
	 */
	void deleteCheckingUnpassData(Integer parentAgentId, Integer subAgentId);

	PageResultBean<AgentCheckingBean> getCheckingList(Integer checked, String agentName, Integer checkStatus, PageBean pageBean);

	PageResultBean<AgentAccountCheckingRecorder> getAgentCheckingList(Integer agentId, PageBean pageBean);

	void agentUniqueInfoCheck(AgentUniqueInfoBean agentUniqueInfoBean);

	AgentAccountInfo getAgentInfo(Integer agentId);

	AgentLevel getAgentlevel(Integer agentId);

	public Integer updateAccountInfoAgentLevel(Integer agentId,Integer agentLevelId);
}
