package com.lx.benefits.web.controller.enterpriseadmin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.enterprise.EnterpriseService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * User:wangmeng
 * Date:2019/5/22
 * Time:11:33
 * Verision:2.x
 * Description:TODO
 **/
@Api(tags = "企业平台-根据登录名查询颗粒号信息模块")
@RestController
@RequestMapping("/enterpriseadmin/user")
public class EnterpriseUserInfoController {

    private static final Logger log = LoggerFactory.getLogger(EnterpriseUserInfoController.class);

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 根据登录名查询颗粒号信息
     * @param loginName
     * @return
     */
    @RequestMapping(value = "/getEnterpriseInfo", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getEnterpriseInfo(String loginName) {
        try {
            EnterprUserInfo enterprUserInfo = enterpriseService.getEnterprUserInfo(loginName);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("enterprUserInfo",enterprUserInfo);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }
}
