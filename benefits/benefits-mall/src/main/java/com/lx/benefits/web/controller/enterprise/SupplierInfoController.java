package com.lx.benefits.web.controller.enterprise;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.supplierInfo.SupplierInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User: fan
 * Date: 2019/02/24
 * Time: 01:24
 */
@Api("供应商")
@RestController
@RequestMapping("/enterprise/supplier")
public class SupplierInfoController {

    @Autowired
    SupplierInfoService supplierInfoService;


    @ApiOperation(value = "查询供应商详细", response = JSONObject.class)
    @RequestMapping(value = "/getSupplierInfo/{id}", method = RequestMethod.GET)
    public JSONObject getSupplierInfo(@ApiParam(value = "Request", name = "dto") @PathVariable Long id) {
        if (id == null) {
            return Response.fail("供应商ID不能为空");
        }
        return supplierInfoService.getSupplierInfoById(id);
    }


}
