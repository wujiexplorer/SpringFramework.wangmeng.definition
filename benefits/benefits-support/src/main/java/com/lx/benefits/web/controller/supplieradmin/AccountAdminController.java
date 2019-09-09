package com.lx.benefits.web.controller.supplieradmin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.LoginReqDto;
import com.lx.benefits.bean.dto.admin.account.ModifyPasswordReqDto;
import com.lx.benefits.bean.entity.supplierInfo.SupplierInfo;
import com.lx.benefits.bean.entity.supplieruser.SupplierUser;
import com.lx.benefits.bean.util.*;
import com.lx.benefits.constant.PlatformConstatnts;
import com.lx.benefits.service.supplierInfo.SupplierInfoService;
import com.lx.benefits.service.supplieruser.SupplierUserService;
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
 * @author unknow on 2018-12-03 21:46.
 */
@Api(tags = "供应商后台-管理员用户模块")
@RestController("supplieradminAccountController")
@RequestMapping("/supplieradmin/account")
public class AccountAdminController extends BaseSupplierAdminController {

    private final static Logger logger = LoggerFactory.getLogger(AccountAdminController.class);

    @Autowired
    private SupplierUserService supplierUserService;

    @Autowired
    private SupplierInfoService supplierInfoService;

    @ApiOperation(value = "供应商登录", response = SessionSupplierInfo.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(@ApiParam(value = "Request", name = "req") @RequestBody LoginReqDto req, HttpServletResponse httpServletResponse) {
        if (null == req) {
            return Response.fail("供应商登录信息不能为空!");
        }
        String loginName = null == req.getLoginName() ? null : req.getLoginName().trim();
        String password = null == req.getPassword() ? null : req.getPassword().trim();
        if (null == loginName || loginName.isEmpty()) {
            return Response.fail("供应商登录用户名不能为空!");
        }
        if (null == password || password.isEmpty()) {
            return Response.fail("供应商登录密码不能为空!");
        }
        SupplierUser supplierUser = null;
        try {
            supplierUser = supplierUserService.getSupplierUserByLoginName(req.getLoginName().trim());
            if (null == supplierUser) {
                return Response.fail("获取供应商信息失败!");
            }
            SupplierInfo supplierInfo = supplierInfoService.getSupplierById(supplierUser.getSupplier_id());
            if (null == supplierInfo) {
                return Response.fail("供应商信息不存在，请联系管理员!");
            }
            String encryptPassword = EncryptUtil.encodePassword(password,supplierUser.getSalt_key());
            if (loginName.equals(supplierUser.getLogin_name()) && encryptPassword.equals(supplierUser.getPassword())) {

                SessionSupplierInfo sessionSupplierInfo = new SessionSupplierInfo();
                sessionSupplierInfo.setLoginName(loginName);
                sessionSupplierInfo.setSupplierId(supplierUser.getSupplier_id());
                sessionSupplierInfo.setSupplierName(supplierInfo.getName());

                String secret = EncryptUtil.encryptByMd5(UUID.randomUUID().toString());
                String token = EncryptUtil.aesEncrypt(JsonUtils.getJsonString(sessionSupplierInfo), secret);
                if (null == token || token.isEmpty()) {
                    return Response.fail("生成token信息失败,请联系网站管理员!");
                }
                String viewToken = EncryptUtil.encodeToken(token, secret);

                //写header头信息
                httpServletResponse.setHeader("Access-Control-Expose-Headers", PlatformConstatnts.EXPOSE_HEADERS);
                httpServletResponse.setHeader("Access-Control-Allow-Headers", PlatformConstatnts.ALLOW_HEADERS);
                httpServletResponse.setHeader(PlatformConstatnts.SUPPLIER_TOKEN, viewToken);
                httpServletResponse.setHeader(PlatformConstatnts.SUPPLIER_NAME, loginName);
                return Response.succ(sessionSupplierInfo);
            }
        } catch (Exception e) {
            logger.error("系统异常登录失败,req={} res={}", req, supplierUser, e);
            return Response.fail("系统异常登录失败!");
        }
        return Response.fail("用户名或者密码错误,请重新登录!");
    }

    @ApiOperation(value = "供应商退出登录", response = Boolean.class)
    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    public JSONObject loginOut(HttpServletResponse httpServletResponse) {
        try {
            //remove header
            httpServletResponse.setHeader("Access-Control-Expose-Headers", "");
            httpServletResponse.setHeader(PlatformConstatnts.SUPPLIER_TOKEN, "");
            httpServletResponse.setHeader(PlatformConstatnts.SUPPLIER_NAME, "");
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
            return supplierUserService.modifyPassword(req);
        } catch (Exception e) {
            logger.error("系统异常修改密码失败", e);
            return Response.fail("系统异常修改密码失败!");
        }
    }
}