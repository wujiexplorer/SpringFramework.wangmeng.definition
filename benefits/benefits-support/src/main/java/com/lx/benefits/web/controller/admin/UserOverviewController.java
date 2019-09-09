package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.user.UserOverviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/7/15
 * Time:10:15
 * Version:2.x
 * Description:TODO
 **/
@RestController
@RequestMapping("/admin/user")
@Slf4j
public class UserOverviewController {

    @Autowired
    private UserOverviewService userOverviewService;

    @RequestMapping(value = "/userOverview", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject userOverview() {
        try {
            Map<String,Object> map = userOverviewService.getUserStatistic();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("map",map);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }
}
