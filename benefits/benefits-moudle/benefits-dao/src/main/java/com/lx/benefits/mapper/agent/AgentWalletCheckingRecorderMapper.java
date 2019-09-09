package com.lx.benefits.mapper.agent;

import com.lx.benefits.bean.entity.agent.AgentWalletCheckingRecorder;
import com.lx.benefits.bean.entity.agent.AgentWalletCheckingRecorderCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgentWalletCheckingRecorderMapper {
    long countByExample(AgentWalletCheckingRecorderCondition example);

    int deleteByExample(AgentWalletCheckingRecorderCondition example);

    int deleteByPrimaryKey(Integer id);

    int insert(AgentWalletCheckingRecorder record);

    int insertSelective(AgentWalletCheckingRecorder record);

    List<AgentWalletCheckingRecorder> selectByExample(AgentWalletCheckingRecorderCondition example);

    AgentWalletCheckingRecorder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AgentWalletCheckingRecorder record, @Param("example") AgentWalletCheckingRecorderCondition example);

    int updateByExample(@Param("record") AgentWalletCheckingRecorder record, @Param("example") AgentWalletCheckingRecorderCondition example);

    int updateByPrimaryKeySelective(AgentWalletCheckingRecorder record);

    int updateByPrimaryKey(AgentWalletCheckingRecorder record);
}