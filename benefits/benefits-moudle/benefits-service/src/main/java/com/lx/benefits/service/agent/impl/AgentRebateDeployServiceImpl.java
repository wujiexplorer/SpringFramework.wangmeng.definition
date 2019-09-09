package com.lx.benefits.service.agent.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.agent.AgentRebateDeploy;
import com.lx.benefits.mapper.agent.AgentRebateDeployMapper;
import com.lx.benefits.service.agent.AgentRebateDeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User:wangmeng
 * Date:2019/7/10
 * Time:15:28
 * Version:2.x
 * Description:TODO
 **/

@Service
public class AgentRebateDeployServiceImpl implements AgentRebateDeployService {

    @Autowired
    private AgentRebateDeployMapper agentRebateDeployMapper;

    @Override
    public AgentRebateDeploy findAgentRebateDeploys() {
        try{
            List<AgentRebateDeploy> list = agentRebateDeployMapper.selectByExample(null);
            return list.get(0);
        }catch (Exception e){
            throw new RuntimeException("获取代理商返点配置信息出错！",e);
        }
    }

    @Override
    public Integer updateAgentRebateDeploy(AgentRebateDeploy agentRebateDeploy) {
        if(null == agentRebateDeploy){
            throw new BusinessException("代理商返点配置信息参数不能为空！");
        }
        try{
            Integer count = agentRebateDeployMapper.updateByPrimaryKeySelective(agentRebateDeploy);
            return count;
        }catch (Exception e){
            throw new RuntimeException("修改代理商配置信息出错！",e);
        }
    }
}
