package com.lx.benefits.mapper.agent;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.agent.AgentAccountInfo;
import com.lx.benefits.bean.entity.agent.AgentAccountInfoCondition;
import com.lx.benefits.bean.vo.agent.AgentAccountInfoBean;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgentAccountInfoMapper {
	long countByExample(AgentAccountInfoCondition example);

	int deleteByExample(AgentAccountInfoCondition example);

	int deleteByPrimaryKey(Integer id);

	int insert(AgentAccountInfo record);

	int insertSelective(AgentAccountInfo record);

	List<AgentAccountInfo> selectByExample(AgentAccountInfoCondition example);

	AgentAccountInfo selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") AgentAccountInfo record, @Param("example") AgentAccountInfoCondition example);

	int updateByExample(@Param("record") AgentAccountInfo record, @Param("example") AgentAccountInfoCondition example);

	int updateByPrimaryKeySelective(AgentAccountInfo record);

	int updateByPrimaryKey(AgentAccountInfo record);

	List<AgentAccountInfoBean> selectAgentAccountInfoBean(@Param("agentName") String agentName, @Param("contactUser") String contactUser,
			@Param("contactPhone") String contactPhone, @Param("pageBean") PageBean pageBean);

	AgentAccountInfoBean getAgentInfoBean(@Param("agentId") Integer agentId);
}