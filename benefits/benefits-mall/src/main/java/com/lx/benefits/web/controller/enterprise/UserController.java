package com.lx.benefits.web.controller.enterprise;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.LoginReqDto;
import com.lx.benefits.bean.base.dto.MResultVO;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeInfoDto;
import com.lx.benefits.bean.dto.yianapi.AccountVO;
import com.lx.benefits.bean.exception.MobileException;
import com.lx.benefits.bean.util.*;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.mem.IMessageSendService;
import com.lx.benefits.web.ao.UserAO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户 - 帐户控制器
 *
 * @author zhuss
 * @2016年1月3日 下午2:01:38
 */
@RestController
@RequestMapping("/enterprise/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserAO userAO;

    @Resource
    private EmployeeUserInfoService employeeUserInfoService;

    @Resource
    private IMessageSendService messageSendService;

    /**
     * 获取用户信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public String getUserInfo() {
        try {
            Long employeeId =  SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
            MResultVO<AccountVO> result = userAO.getUser(employeeId);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(result));
            return JsonUtil.convertObjToStr(result);
        } catch (MobileException me) {
            log.error("[API接口 - 获取用信息  MobileException] = {}", me.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(new MResultVO<>(me)));
            return JsonUtil.convertObjToStr(new MResultVO<>(me));
        }
    }

    /**
     * 发送短信
     */
    @ApiOperation(value = "发送短信")
    @PostMapping("/smsCode")
    public Object smsCode(@RequestBody LoginReqDto req) {
        if (null == req) {
            return Response.fail("请求参数不能为空");
        }
        try {
            return messageSendService.smsCode(req);
        } catch (Exception e) {
            log.error("发送短信异常{}",e.getMessage());
        }
        return Response.fail("发送短信失败");
    }


    /**
     * 绑定手机
     */
    @ApiOperation(value = "绑定手机")
    @PostMapping("/bindMobile")
    public JSONObject bindMobile(@RequestBody LoginReqDto req) {
        if (null == req) {
            return Response.fail("请求参数不能为空");
        }
        try {
            return employeeUserInfoService.bindMobile(req);
        } catch (Exception e) {
            log.error("绑定手机异常{}",e.getMessage());
            return Response.fail("绑定手机失败");
        }
    }

    /**
     * 绑定手机(修改绑定手机第一步)
     */
    @ApiOperation(value = "绑定手机")
    @PostMapping("/bindMobileFirst")
    public JSONObject bindMobileFirst(@RequestBody LoginReqDto req) {
        if (null == req) {
            return Response.fail("请求参数不能为空");
        }
        try {
            Long employeeId =  SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
            if (null == employeeId || employeeId < 1) {
                return Response.fail("登录失效，请重新登录");
            }
            String password = null == req.getPassword() ? null : req.getPassword().trim();
            if (null == password || password.isEmpty()) {
                return Response.fail("登录密码不能为空!");
            }


            EmployeeInfoDto employeeInfoDto = employeeUserInfoService.findByEmployeeId(employeeId,true);
            if ( null == employeeInfoDto ) {
                return Response.fail("用户信息不存在");
            }
            String encodePassword = EncryptUtil.encodePassword(password, employeeInfoDto.getSecret());
            if (!encodePassword.equals(employeeInfoDto.getPassword())) {
                return Response.fail("登录密码不正确");
            }
            return Response.succ("密码正确");
        } catch (Exception e) {
            log.error("绑定手机异常{}",e.getMessage());
            return Response.fail("绑定手机失败");
        }
    }

    /**
     * 找回密码
     */
    @ApiOperation(value = "找回密码")
    @PostMapping("/findPasword")
    public JSONObject findPasword(@RequestBody LoginReqDto req) {
        if (null == req) {
            return Response.fail("请求参数不能为空");
        }
        try {
            return employeeUserInfoService.findPasword(req);
        } catch (Exception e) {
            log.error("发送短信异常{}",e.getMessage());
        }
        return Response.fail("发送短信失败");
    }

}
