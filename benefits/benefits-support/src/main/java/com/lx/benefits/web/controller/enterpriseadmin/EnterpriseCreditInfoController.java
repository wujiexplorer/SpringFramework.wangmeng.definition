package com.lx.benefits.web.controller.enterpriseadmin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.EnterprCreditInfoDto;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.service.enterprcredit.EnterprCreditInfoService;
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
 * Date:2019/5/23
 * Time:9:24
 * Verision:2.x
 * Description:TODO
 **/
@Api(tags = "企业平台-绑定积分颗粒号企业信息模块")
@RestController
@RequestMapping("/enterpriseadmin/credit")
public class EnterpriseCreditInfoController {

    private static final Logger log = LoggerFactory.getLogger(EnterpriseCreditInfoController.class);

    @Autowired
    private EnterprCreditInfoService enterprCreditInfoService;


    /**
     * 根据企业ID查询积分信息
     * @param
     * @return
     */
    @RequestMapping(value = "/getEnterpriseCreditInfo", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getEnterpriseCreditInfo() {
        try {
            Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
            if(null == enterprId){
                return Response.fail("企业ID不能为空！");
            }
            EnterprCreditInfoDto enterprCreditInfoDto = enterprCreditInfoService.findByEnterprIdOne(enterprId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("enterprCreditInfoDto",enterprCreditInfoDto);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }
}
