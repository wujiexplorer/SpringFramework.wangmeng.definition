package com.lx.benefits.service.agent;

import com.lx.benefits.bean.entity.agent.AgentRebateDeploy;

import java.util.List;

/**
 * User:wangmeng
 * Date:2019/7/10
 * Time:15:27
 * Version:2.x
 * Description:TODO
 **/
public interface AgentRebateDeployService {

    public AgentRebateDeploy findAgentRebateDeploys();

    public Integer updateAgentRebateDeploy(AgentRebateDeploy agentRebateDeploy);
}
