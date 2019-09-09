package com.lx.benefits.web.controller.enterpriseadmin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.WelcomeModuleInfoDto;
import com.lx.benefits.bean.dto.admin.customized.*;
import com.lx.benefits.bean.entity.enterprnoticeinfo.EnterprNoticeInfo;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.service.enterprnoticeinfo.EnterprNoticeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author unknow on 2018-12-06 01:55.
 */
@Api(tags = "HR平台-通知提醒模块")
@RestController("noticeController")
@RequestMapping("/enterpriseadmin/notice")
public class NoticeController extends BaseEnterpriseAdminController {

    private final static Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private EnterprNoticeInfoService enterprNoticeInfoService;

    @ApiOperation(value = "设置企业提醒信息", response = Boolean.class)
    @RequestMapping(value = "/setNotice", method = RequestMethod.POST)
    public JSONObject setNotice(@ApiParam(value = "Request", name = "req") @RequestBody EnterprNoticeReqDto req) {

        if (null == req) {
            return Response.fail("提醒信息不能为空");
        }

        if (null == req.getIsLoginRemind()) {
            return Response.fail("登录设置不能为空");
        }

        if (null == req.getLoginAttachs() || req.getLoginAttachs().isEmpty()) {
            return Response.fail("登录提醒不能为空");
        }

        if (null == req.getUserAttachs() || req.getUserAttachs().isEmpty()) {
            return Response.fail("用户中心提醒不能为空");
        }
        if (null == req.getIsUserRemind()) {
            return Response.fail("用户设置不能为空");
        }
        req.setLoginAttach(JSON.toJSONString(req.getLoginAttachs()));
        req.setUserAttach(JSON.toJSONString(req.getUserAttachs()));

        Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
        if (null == enterprId || enterprId < 1) {
            return Response.fail("企业id不能为空");
        }
        req.setEnterprId(enterprId);
        try {
            Boolean flag = enterprNoticeInfoService.setNotice(req);
            return Response.succ(flag);
        } catch (Exception e) {
            logger.error("企业提醒失败,req={} msg={}", req, e);
            return Response.fail("企业提醒定制失败");
        }
    }
    @ApiOperation(value = "设置企业邮件提醒信息", response = Boolean.class)
    @RequestMapping(value = "/setEmailNotice", method = RequestMethod.POST)
    public JSONObject setEmailNotice(@ApiParam(value = "Request", name = "req") @RequestBody EnterprNoticeReqDto req) {
    	if (null == req) {
            return Response.fail("提醒信息不能为空");
        }
    	if(req.getIsBirthdayEmailRemind() == null) {
    		return Response.fail("邮件提示不能为空");
    	}
    	if(req.getEmailTitle() == null || req.getEmailTitle().isEmpty()) {
    		return Response.fail("邮件标题不能为空");
    	}
    	if(req.getEmailTitle().length() > 256) {
    		return Response.fail("邮件标题字数不能大于256");
    	}
    	if(req.getEmailContent() == null || req.getEmailContent().isEmpty()) {
    		return Response.fail("邮件内容不能为空");
    	}
    	Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
        if (null == enterprId || enterprId < 1) {
            return Response.fail("企业id不能为空");
        }
        req.setEnterprId(enterprId);
        req.setEmailTitle(req.getEmailTitle().trim());
        req.setEmailContent(req.getEmailContent().trim());
        Boolean flag = enterprNoticeInfoService.setEmail(req);
        return Response.succ(flag);

    }
    @ApiOperation(value = "企业定制提醒详细信息", response = WelcomeModuleInfoDto.class)
    @RequestMapping(value = "/noticeDetail", method = RequestMethod.GET)
    public JSONObject noticeDetail(@RequestParam(value = "type",required = false) Integer type) {
        JSONObject jsonObject = new JSONObject();
        Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();

        if (null == enterprId || enterprId < 1) {
            return Response.fail("企业id不能为空");
        }
        EnterprNoticeInfo enterprNoticeInfo = enterprNoticeInfoService.findById(enterprId,false);
        if (null != enterprNoticeInfo) {
            enterprNoticeInfo.setLoginAttachs(JSONObject.parseArray(enterprNoticeInfo.getLoginAttach(),String.class));
            enterprNoticeInfo.setUserAttachs(JSONObject.parseArray(enterprNoticeInfo.getUserAttach(),String.class));
        }
        jsonObject.put("info",enterprNoticeInfo);
        return Response.succ(jsonObject);
    }
    @ApiOperation(value = "企业邮件通知详细信息")
    @RequestMapping(value = "/EmailDetail", method = RequestMethod.GET)
    public JSONObject EmailDetail() {
        JSONObject jsonObject = new JSONObject();
        Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();

        if (null == enterprId || enterprId < 1) {
            return Response.fail("企业id不能为空");
        }
        EnterprNoticeReqDto enterprEmailInfo = enterprNoticeInfoService.findEmailDetail(enterprId);
        if (null == enterprEmailInfo) {
        	EnterprNoticeReqDto enterprEmail =  new EnterprNoticeReqDto();
        	enterprEmail.setEnterprId(enterprId);
        	enterprEmail.setEmailContent("");
        	enterprEmail.setIsBirthdayEmailRemind(0);
        	jsonObject.put("info",enterprEmail);
            return Response.succ(jsonObject);
        }
        jsonObject.put("info",enterprEmailInfo);
        return Response.succ(jsonObject);
    }


}