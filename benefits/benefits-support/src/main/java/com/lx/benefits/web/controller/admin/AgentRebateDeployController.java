package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.entity.agent.AgentRebateDeploy;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.agent.AgentRebateDeployService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User:wangmeng
 * Date:2019/7/10
 * Time:15:39
 * Version:2.x
 * Description:TODO
 **/
@RestController()
@RequestMapping("/admin/agents")
@Slf4j
public class AgentRebateDeployController {

    @Autowired
    private AgentRebateDeployService agentRebateDeployService;

    /**
     * 获取代理商配置信息
     * @return
     */
    @GetMapping("/findAgentRebateDeploys")
    public JSONObject findAgentRebateDeploys(){
        try{
            AgentRebateDeploy agentRebateDeploy  = agentRebateDeployService.findAgentRebateDeploys();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("agentRebateDeploy",agentRebateDeploy);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 修改代理商返点配置信息
     * @param agentRebateDeploy
     * @return
     */
    @PostMapping("/updateAgentRebateDeploy")
    public JSONObject updateAgentRebateDeploy(AgentRebateDeploy agentRebateDeploy){
        try{
            Integer count  = agentRebateDeployService.updateAgentRebateDeploy(agentRebateDeploy);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("count",count);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }
}
