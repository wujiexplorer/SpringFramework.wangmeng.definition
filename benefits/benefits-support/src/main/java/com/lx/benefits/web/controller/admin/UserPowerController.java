package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.power.SysRoleMenuRes;
import com.lx.benefits.bean.dto.power.UserAuthorizeReq;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.power.UserRoleService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: fan
 * Date: 2019/03/19
 * Time: 23:10
 */
@RestController
@RequestMapping("/admin/power")
public class UserPowerController extends BaseAdminController {

    @Autowired
    UserRoleService userRoleService;

    @ApiOperation(value = "查询用户角色列表", response = JSONObject.class)
    @RequestMapping(value = "/findUserRole/{userId}", method = RequestMethod.GET)
    public JSONObject findUserRole(@PathVariable Long userId) {
        return userRoleService.findRole(userId);
    }

    @ApiOperation(value = "用户授权", response = JSONObject.class)
    @RequestMapping(value = "/userAuthorize", method = RequestMethod.POST)
    public JSONObject userAuthorize(@ApiParam(value = "Request", name = "dto") @RequestBody UserAuthorizeReq dto) {
        return userRoleService.userAuthorize(getSessionFuliInfo().getLoginName(), dto);
    }

    @ApiOperation(value = "查询用户菜单列表", response = JSONObject.class)
    @RequestMapping(value = "/findUserMenu/{userId}", method = RequestMethod.GET)
    public JSONObject findUserMenu(@PathVariable Long userId) {
        return userRoleService.findUserMenu(userId);
    }

    @ApiOperation(value = "查询用户菜单列表", response = JSONObject.class)
    @RequestMapping(value = "/findUserPower", method = RequestMethod.GET)
    public JSONObject findUserPower() {
        return userRoleService.findUserMenu((getSessionFuliInfo().getAdminId()));
    }

    @ApiOperation(value = "查询用户权限列表", response = JSONObject.class)
    @RequestMapping(value = "/userPower", method = RequestMethod.GET)
    public JSONObject userPower() {
        List<SysRoleMenuRes> roleMenuRes = userRoleService.userPower(getSessionFuliInfo());
        return Response.succ(roleMenuRes);
    }


}
