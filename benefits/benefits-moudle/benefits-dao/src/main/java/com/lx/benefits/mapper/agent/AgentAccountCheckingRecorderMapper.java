package com.lx.benefits.mapper.agent;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.agent.AgentAccountCheckingRecorder;
import com.lx.benefits.bean.entity.agent.AgentAccountCheckingRecorderCondition;
import com.lx.benefits.bean.vo.agent.AgentCheckingBean;

public interface AgentAccountCheckingRecorderMapper {
	long countByExample(AgentAccountCheckingRecorderCondition example);

	int deleteByExample(AgentAccountCheckingRecorderCondition example);

	int deleteByPrimaryKey(Integer id);

	int insert(AgentAccountCheckingRecorder record);

	int insertSelective(AgentAccountCheckingRecorder record);

	List<AgentAccountCheckingRecorder> selectByExample(AgentAccountCheckingRecorderCondition example);

	AgentAccountCheckingRecorder selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") AgentAccountCheckingRecorder record, @Param("example") AgentAccountCheckingRecorderCondition example);

	int updateByExample(@Param("record") AgentAccountCheckingRecorder record, @Param("example") AgentAccountCheckingRecorderCondition example);

	int updateByPrimaryKeySelective(AgentAccountCheckingRecorder record);

	int updateByPrimaryKey(AgentAccountCheckingRecorder record);

	List<AgentCheckingBean> selectWithParentAgentName(@Param("checked") Integer checked, @Param("agentName") String agentName,
			@Param("checkStatus") Integer checkStatus, @Param("pageBean") PageBean pageBean);

	AgentCheckingBean selectAgentCheckingBean(@Param("agentId")Integer agentId);
}