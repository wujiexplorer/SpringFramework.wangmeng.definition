package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.supplierinfo.SupplierInfoDto;
import com.lx.benefits.bean.dto.admin.supplierinfo.SupplierInfoReq;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.product.impl.ProductServiceImpl;
import com.lx.benefits.service.supplierInfo.SupplierInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User: fan
 * Date: 2019/02/24
 * Time: 01:24
 */
@Api("供应商")
@RestController
@RequestMapping("/admin/supplier")
public class SupplierInfoController {

    private final static Logger logger = LoggerFactory.getLogger(SupplierInfoController.class);

    @Autowired
    SupplierInfoService supplierInfoService;

    @ApiOperation(value = "新增供应商", response = JSONObject.class)
    @RequestMapping(value = "/addSupplierInfo", method = RequestMethod.POST)
    public JSONObject addSupplierInfo(@ApiParam(value = "Request", name = "dto") @RequestBody SupplierInfoDto dto) {
        if (dto == null) {
            return Response.fail("供应商信息不能为空");
        }
        if (StringUtils.isBlank(dto.getCode())) {
            return Response.fail("供应商编号不能为空");
        }
        if (StringUtils.isBlank(dto.getName())) {
            return Response.fail("供应商名称不能为空");
        }
        if (StringUtils.isBlank(dto.getLoginName())) {
            return Response.fail("供应商登录名不能为空");
        }
        try {
            return supplierInfoService.insert(dto);
        } catch (Exception e) {
            logger.error("添加供应商异常{}",e.getMessage());
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "查询供应商列表", response = JSONObject.class)
    @RequestMapping(value = "/getSupplierInfoList", method = RequestMethod.GET)
    public JSONObject getSupplierInfoList(@ApiParam(value = "Request", name = "dto") @ModelAttribute SupplierInfoReq dto) {
        if (dto == null) {
            dto = new SupplierInfoReq();
        }
        if (StringUtils.isBlank(dto.getName())) {
            dto.setName(null);
        }
        return Response.succ(supplierInfoService.getSupplierInfoList(dto));
    }

    @ApiOperation(value = "查询供应商详细", response = JSONObject.class)
    @RequestMapping(value = "/getSupplierInfo/{id}", method = RequestMethod.GET)
    public JSONObject getSupplierInfo(@ApiParam(value = "Request", name = "dto") @PathVariable Long id) {
        if (id == null) {
            return Response.fail("供应商ID不能为空");
        }
        return supplierInfoService.getSupplierInfoById(id);
    }

    @ApiOperation(value = "更新供应商信息", response = JSONObject.class)
    @RequestMapping(value = "/updateSupplierInfo", method = RequestMethod.POST)
    public JSONObject updateSupplierInfo(@ApiParam(value = "Request", name = "dto") @RequestBody SupplierInfoDto dto) {
        if (dto == null || dto.getId() == null) {
            return Response.fail("供应商ID不能为空");
        }
        if (StringUtils.isBlank(dto.getCode())) {
            return Response.fail("供应商编号不能为空");
        }
        if (StringUtils.isBlank(dto.getName())) {
            return Response.fail("供应商名称不能为空");
        }
        try {
            return supplierInfoService.update(dto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.fail("失败");
        }
    }

    @ApiOperation(value = "删除供应商详细", response = JSONObject.class)
    @RequestMapping(value = "/delSupplierInfo/{id}", method = RequestMethod.GET)
    public JSONObject delSupplierInfo(@ApiParam(value = "Request", name = "dto") @PathVariable Long id) {
        if (id == null) {
            return Response.fail("供应商ID不能为空");
        }
        return Response.succ(supplierInfoService.delete(id));
    }


}
