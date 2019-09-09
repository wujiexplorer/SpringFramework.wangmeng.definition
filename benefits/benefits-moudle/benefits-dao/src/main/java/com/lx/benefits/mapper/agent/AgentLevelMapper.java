package com.lx.benefits.mapper.agent;

import com.lx.benefits.bean.entity.agent.AgentLevel;
import com.lx.benefits.bean.entity.agent.AgentLevelCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgentLevelMapper {
    long countByExample(AgentLevelCondition example);

    int deleteByExample(AgentLevelCondition example);

    int deleteByPrimaryKey(Integer id);

    int insert(AgentLevel record);

    int insertSelective(AgentLevel record);

    List<AgentLevel> selectByExample(AgentLevelCondition example);

    AgentLevel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AgentLevel record, @Param("example") AgentLevelCondition example);

    int updateByExample(@Param("record") AgentLevel record, @Param("example") AgentLevelCondition example);

    int updateByPrimaryKeySelective(AgentLevel record);

    int updateByPrimaryKey(AgentLevel record);
}