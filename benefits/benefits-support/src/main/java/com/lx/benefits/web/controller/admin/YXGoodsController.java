package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.yx.PrdYxBaseItemReq;
import com.lx.benefits.bean.dto.yx.YxSkuImportReq;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.yx.IYXItemInfoService;
import com.lx.benefits.service.yx.IYXItemService;
import com.lx.benefits.service.yx.PrdYxBaseItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: fan
 * Date: 2019/02/27
 * Time: 18:04
 */
@Api(tags = "网易商品")
@RestController
@RequestMapping("/admin/yx")
public class YXGoodsController {

    @Autowired
    IYXItemService iyxItemService;

    @Autowired
    IYXItemInfoService iyxItemInfoService;

    @Autowired
    PrdYxBaseItemService prdYxBaseItemService;

    @ApiOperation(value = "网易商品列表", response = JSONObject.class)
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.GET)
    public JSONObject getGoodsList(@ApiParam(value = "Request", name = "dto") @ModelAttribute PrdYxBaseItemReq dto) {
        return Response.succ(prdYxBaseItemService.getYxItemList(dto));
    }

    @ApiOperation(value = "网易商品同步", response = JSONObject.class)
    @RequestMapping(value="/synYxData" ,method= RequestMethod.POST)
    public JSONObject synDataFromJd(){
        return iyxItemInfoService.statistics();
    }

    @ApiOperation(value = "网易商品导入", response = JSONObject.class)
    @RequestMapping(value = "/goodsImport", method = RequestMethod.POST)
    public JSONObject goodsImport(@ApiParam(value = "Request", name = "dto") @RequestBody YxSkuImportReq dto) {
        return prdYxBaseItemService.goodsImport(dto);
    }


    @ApiOperation(value = "网易商品测试接口", response = JSONObject.class)
    @RequestMapping(value="/getYxItem" ,method= RequestMethod.POST)
    public JSONObject getYxItem(@RequestBody List<Long> subIds){
        return iyxItemService.getYxItem(subIds);
    }
}
