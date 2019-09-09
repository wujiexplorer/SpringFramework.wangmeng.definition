package com.lx.benefits.mapper.agent;

import com.lx.benefits.bean.entity.agent.AgentWithdrawRecorder;
import com.lx.benefits.bean.entity.agent.AgentWithdrawRecorderCondition;
import com.lx.benefits.bean.vo.agent.AgentWithdrawSearchBean;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgentWithdrawRecorderMapper {
    long countByExample(AgentWithdrawRecorderCondition example);

    int deleteByExample(AgentWithdrawRecorderCondition example);

    int deleteByPrimaryKey(String cashNumber);

    int insert(AgentWithdrawRecorder record);

    int insertSelective(AgentWithdrawRecorder record);

    List<AgentWithdrawRecorder> selectByExample(AgentWithdrawRecorderCondition example);

    AgentWithdrawRecorder selectByPrimaryKey(String cashNumber);

    int updateByExampleSelective(@Param("record") AgentWithdrawRecorder record, @Param("example") AgentWithdrawRecorderCondition example);

    int updateByExample(@Param("record") AgentWithdrawRecorder record, @Param("example") AgentWithdrawRecorderCondition example);

    int updateByPrimaryKeySelective(AgentWithdrawRecorder record);

    int updateByPrimaryKey(AgentWithdrawRecorder record);

	int countByExampleWithAgentName(AgentWithdrawSearchBean searchBean);

	List<AgentWithdrawRecorder> selectByExampleWithAgentName(AgentWithdrawSearchBean searchBean);
}