package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.LoginReqDto;
import com.lx.benefits.bean.dto.admin.account.AdminAccountInfoDto;
import com.lx.benefits.bean.dto.admin.account.ModifyPasswordReqDto;
import com.lx.benefits.bean.util.*;
import com.lx.benefits.constant.PlatformConstatnts;
import com.lx.benefits.service.adminaccount.AdminAccountService;
import com.lx.benefits.service.power.UserRoleService;
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

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author unknow on 2018-12-04 23:06.
 */
@Api(tags = "运营后台-管理员用户模块")
@RestController("adminAccountController")
@RequestMapping("/admin/account")
public class AccountController extends BaseAdminController {
    
    @Autowired
    private AdminAccountService adminAccountService;

    @Autowired
    UserRoleService userRoleService;
    
    private final static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @ApiOperation(value = "管理员登录", response = SessionFuliInfo.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(@ApiParam(value = "Request", name = "req") @RequestBody LoginReqDto req, HttpServletResponse httpServletResponse) {
        try {
            if (null == req) {
                return Response.fail("管理员登录信息不能为空!");
            }
            String loginName = null == req.getLoginName() ? null : req.getLoginName().trim();
            String password = null == req.getPassword() ? null : req.getPassword().trim();
            if (null == loginName || loginName.isEmpty()) {
                return Response.fail("管理员登录用户名不能为空!");
            }
            if (null == password || password.isEmpty()) {
                return Response.fail("管理员登录密码不能为空!");
            }
            AdminAccountInfoDto adminAccountInfoDto = adminAccountService.getAdminAccountInfoByLoginName(loginName, true);


            if (null == adminAccountInfoDto) {
                return Response.fail("获取运营后台管理员信息失败!");
            }
            String encryptPassword = EncryptUtil.encryptByMd5(password);
            if (null == encryptPassword || encryptPassword.length() != 32) {
                return Response.fail("运营后台管理员登录密码加密失败!");
            }
            if (loginName.equals(adminAccountInfoDto.getLoginName()) && encryptPassword.equals(adminAccountInfoDto.getPassword())) {

                SessionFuliInfo sessionFuliInfo = new SessionFuliInfo();
                sessionFuliInfo.setLoginName(loginName);
                sessionFuliInfo.setAdminId(adminAccountInfoDto.getAdminId());
                sessionFuliInfo.setDepartmentId(adminAccountInfoDto.getDepartmentId());
                sessionFuliInfo.setRoleId(adminAccountInfoDto.getRoleId());

                logger.info("SessionFuliInfo :{}",sessionFuliInfo);
                String secret = EncryptUtil.encryptByMd5(UUID.randomUUID().toString());
                String token = EncryptUtil.aesEncrypt(JsonUtils.getJsonString(sessionFuliInfo), secret);
                if (null == token || token.isEmpty()) {
                    return Response.fail("生成token信息失败,请联系网站管理员!");
                }
                String viewToken = EncryptUtil.encodeToken(token, secret);
                //写header头信息
                httpServletResponse.setHeader("Access-Control-Expose-Headers", PlatformConstatnts.EXPOSE_HEADERS);
                httpServletResponse.setHeader("Access-Control-Allow-Headers", PlatformConstatnts.ALLOW_HEADERS);
                httpServletResponse.setHeader(PlatformConstatnts.FULI_TOKEN, viewToken);
                httpServletResponse.setHeader(PlatformConstatnts.FULI_NAME, loginName);
                // 加载权限
                userRoleService.userPower(sessionFuliInfo);
                return Response.succ(sessionFuliInfo);
            }
        } catch (Exception e) {
            logger.error("系统异常登录失败,req={} res={}", req, "", e);
            return Response.fail("系统异常登录失败!");
        }
        return Response.fail("用户名或者密码错误,请重新登录!");
    }

    @ApiOperation(value = "管理员退出登录", response = Boolean.class)
    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    public JSONObject loginOut(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setHeader("Access-Control-Expose-Headers", "");
            httpServletResponse.setHeader(PlatformConstatnts.FULI_TOKEN, "");
            httpServletResponse.setHeader(PlatformConstatnts.FULI_NAME, "");
            return Response.succ(true);
        } catch (Exception e) {
            logger.error("系统异常退出登录失败", e);
            return Response.fail("系统异常退出登录失败!");
        }
    }

    @ApiOperation(value = "管理员修改当前登录密码", response = Boolean.class)
    @RequestMapping(value = "/modifypassword", method = RequestMethod.POST)
    public JSONObject modifyPassword(@ApiParam(value = "Request", name = "req") @RequestBody ModifyPasswordReqDto req) {
        if (null == req) {
            return Response.fail("修改密码参数不能为空");
        }
        try {
            return adminAccountService.modifyPassword(req);
        } catch (Exception e) {
            logger.error("系统异常修改密码失败", e);
            return Response.fail("系统异常修改密码失败!");
        }
    }
}
