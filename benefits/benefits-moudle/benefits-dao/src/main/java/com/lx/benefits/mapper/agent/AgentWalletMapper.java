package com.lx.benefits.mapper.agent;

import com.lx.benefits.bean.entity.agent.AgentWallet;
import com.lx.benefits.bean.entity.agent.AgentWalletCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgentWalletMapper {
    long countByExample(AgentWalletCondition example);

    int deleteByExample(AgentWalletCondition example);

    int deleteByPrimaryKey(Integer agentId);

    int insert(AgentWallet record);

    int insertSelective(AgentWallet record);

    List<AgentWallet> selectByExample(AgentWalletCondition example);

    AgentWallet selectByPrimaryKey(Integer agentId);

    int updateByExampleSelective(@Param("record") AgentWallet record, @Param("example") AgentWalletCondition example);

    int updateByExample(@Param("record") AgentWallet record, @Param("example") AgentWalletCondition example);

    int updateByPrimaryKeySelective(AgentWallet record);

    int updateByPrimaryKey(AgentWallet record);
}