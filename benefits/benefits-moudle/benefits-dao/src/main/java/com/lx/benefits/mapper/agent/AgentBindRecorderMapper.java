package com.lx.benefits.mapper.agent;

import com.lx.benefits.bean.entity.agent.AgentBindRecorder;
import com.lx.benefits.bean.entity.agent.AgentBindRecorderCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgentBindRecorderMapper {
    long countByExample(AgentBindRecorderCondition example);

    int deleteByExample(AgentBindRecorderCondition example);

    int deleteByPrimaryKey(Integer id);

    int insert(AgentBindRecorder record);

    int insertSelective(AgentBindRecorder record);

    List<AgentBindRecorder> selectByExample(AgentBindRecorderCondition example);

    AgentBindRecorder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AgentBindRecorder record, @Param("example") AgentBindRecorderCondition example);

    int updateByExample(@Param("record") AgentBindRecorder record, @Param("example") AgentBindRecorderCondition example);

    int updateByPrimaryKeySelective(AgentBindRecorder record);

    int updateByPrimaryKey(AgentBindRecorder record);
}