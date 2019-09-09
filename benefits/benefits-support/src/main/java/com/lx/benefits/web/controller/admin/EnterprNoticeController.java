package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.campaign.FestivalPacketInfoDto;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.enterprfestival.EnterprFestivalService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author unknow on 2018-12-11 00:27.
 */
@Api(tags = "运营后台-企业提醒设置")
@RestController("enterprNoticeController")
@RequestMapping("/admin/enterprNotice")
public class EnterprNoticeController extends BaseAdminController {

    private final static Logger logger = LoggerFactory.getLogger(EnterprNoticeController.class);

    @Autowired
    private EnterprFestivalService enterprFestivalService;

    @Autowired
    private EmployeeCreditInfoService employeeCreditInfoService;

    @ApiOperation(value = "设置提醒", response = Boolean.class)
    @RequestMapping(value = "/addfestivalpacket", method = RequestMethod.POST)
    public JSONObject addFestivalPacket(@ApiParam(value = "Request", name = "req") @RequestBody FestivalPacketInfoDto req) {
        if (null == req) {
            return Response.fail("节日礼金信息不能为空");
        }
        try {
            return enterprFestivalService.insert(req);
        } catch (Exception e) {
            logger.error("新建节日礼金失败：{}", e);
            return Response.fail(e.getMessage());
        }
    }
}