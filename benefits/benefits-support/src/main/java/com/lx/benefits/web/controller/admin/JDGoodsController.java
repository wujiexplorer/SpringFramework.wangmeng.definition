package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.jd.JDSkuImportReq;
import com.lx.benefits.bean.dto.jd.PrdJdBaseItemReq;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.jd.PrdJdBaseItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 15:47
 */
@Api(tags = "京东商品")
@RestController
@RequestMapping("/admin/jd")
public class JDGoodsController {

    @Autowired
    PrdJdBaseItemService jdBaseItemService;

    @ApiOperation(value = "京东商品列表", response = JSONObject.class)
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.GET)
    public JSONObject getGoodsList(@ApiParam(value = "Request", name = "dto") @ModelAttribute PrdJdBaseItemReq dto) {
        return Response.succ(jdBaseItemService.getJdItemList(dto));
    }


    @ApiOperation(value = "京东商品同步", response = JSONObject.class)
    @RequestMapping(value="/synJdData" ,method=RequestMethod.POST)
    public JSONObject synDataFromJd(){
        return jdBaseItemService.synItemFromJd();
    }

    @ApiOperation(value = "京东商品导入", response = JSONObject.class)
    @RequestMapping(value = "/goodsImport", method = RequestMethod.POST)
    public JSONObject goodsImport(@ApiParam(value = "Request", name = "dto") @RequestBody JDSkuImportReq dto) {
        return jdBaseItemService.goodsImport(dto);
    }

}
