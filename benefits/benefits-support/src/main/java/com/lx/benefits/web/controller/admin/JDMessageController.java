package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.jd.JdMessageLineReq;
import com.lx.benefits.bean.dto.jdOrder.JdMerchantOrderReq;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.jd.IJDMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User: fan
 * Date: 2019/03/18
 * Time: 02:15
 */
@Api(tags = "京东消息")
@RestController
@RequestMapping("/admin/jd/message")
public class JDMessageController {

    @Autowired
    IJDMessageService ijdMessageService;

    @ApiOperation(value = "查询 jd 消息列表", response = JSONObject.class)
    @RequestMapping(value = "/getMessageList", method = RequestMethod.GET)
    JSONObject getMessageList(@ApiParam(value = "Request", name = "dto") @ModelAttribute JdMessageLineReq req) {
        return ijdMessageService.getMessageList(req);
    }

    @ApiOperation(value = "处理消息", response = JSONObject.class)
    @RequestMapping(value = "/process/{id}", method = RequestMethod.GET)
    JSONObject process(@ApiParam(value = "Request", name = "dto")  @PathVariable Long id) {
        return ijdMessageService.process(id);
    }

    @ApiOperation(value = "拉取京东消息", response = JSONObject.class)
    @RequestMapping(value = "/doMessageProcess", method = RequestMethod.GET)
    JSONObject doMessageProcess() {
        return Response.succ(ijdMessageService.doMessageProcess());
    }

}
