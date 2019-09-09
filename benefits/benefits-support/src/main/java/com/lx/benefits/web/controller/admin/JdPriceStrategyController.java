package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.jdPrice.JdPriceStrategyDto;
import com.lx.benefits.bean.dto.jdPrice.JdPriceStrategyReq;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.jdPrice.JdPriceStrategyService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User: fan
 * Date: 2019/03/05
 * Time: 00:18
 */
@Api(tags = "京东订单")
@RestController
@RequestMapping("/admin/jd/pricestrategy")
public class JdPriceStrategyController extends BaseAdminController {

    @Autowired
    JdPriceStrategyService strategyService;

    @ApiOperation(value = "查询 jd 价格策略列表", response = JSONObject.class)
    @RequestMapping(value = "/getPriceStrategyList", method = RequestMethod.GET)
    JSONObject getPriceStrategyList(@ApiParam(value = "Request", name = "dto") @ModelAttribute JdPriceStrategyReq record) {
        return strategyService.getStrategyList(record);
    }

    @ApiOperation(value = "查询 jd 价格策略详情", response = JSONObject.class)
    @RequestMapping(value = "/getStrategyDetail/{id}", method = RequestMethod.GET)
    JSONObject getStrategyDetail(@ApiParam(value = "Request", name = "dto") @PathVariable Long id) {
        if (id == null) {
            return Response.fail("价格策略ID不能为空");
        }
        return strategyService.getStrategyById(id);
    }

    @ApiOperation(value = "jd 价格策略删除", response = JSONObject.class)
    @RequestMapping(value = "/delPriceStrategy/{id}", method = RequestMethod.GET)
    JSONObject delPriceStrategy(@ApiParam(value = "Request", name = "dto") @PathVariable Long id) {
        if (id == null) {
            return Response.fail("价格策略ID不能为空");
        }
        return strategyService.delete(id);
    }

    @ApiOperation(value = "新增 jd 价格策略", response = JSONObject.class)
    @RequestMapping(value = "/addPriceStrategy", method = RequestMethod.POST)
    JSONObject addPriceStrategy(@ApiParam(value = "Request", name = "dto") @RequestBody JdPriceStrategyDto record) {
        if (record.getName() == null) {
            return Response.fail("价格策略名称不能为空");
        }
        if (record.getType() == null) {
            return Response.fail("价格策略类型不能为空");
        }
        if (record.getStrategy() == null || record.getStrategy().size() == 0) {
            return Response.fail("价格策略规则不能为空");
        }
        String username = getSessionFuliInfo().getLoginName();
        return strategyService.insert(record, username);
    }


    @ApiOperation(value = "更新 jd 价格策略", response = JSONObject.class)
    @RequestMapping(value = "/updatePriceStrategy", method = RequestMethod.POST)
    JSONObject updatePriceStrategy(@ApiParam(value = "Request", name = "dto") @RequestBody JdPriceStrategyDto record) {
        if (record.getId() == null) {
            return Response.fail("价格策略ID不能为空");
        }
        if (record.getName() == null) {
            return Response.fail("价格策略名称不能为空");
        }
        if (record.getType() == null) {
            return Response.fail("价格策略类型不能为空");
        }
        if (record.getStrategy() == null || record.getStrategy().size() == 0) {
            return Response.fail("价格策略规则不能为空");
        }
        String username = getSessionFuliInfo().getLoginName();
        return strategyService.update(record, username);
    }

}
