package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.user.AddUserInfoReq;
import com.lx.benefits.bean.dto.user.ResetPwdReq;
import com.lx.benefits.bean.dto.user.UserInfoReq;
import com.lx.benefits.bean.dto.user.UserStatusReq;
import com.lx.benefits.bean.entity.userinfo.UserInfo;
import com.lx.benefits.service.userinfo.UserInfoService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User: fan
 * Date: 2019/03/25
 * Time: 17:37
 */
@RestController
@RequestMapping("/admin/user")
public class UserInfoController extends BaseAdminController {

    @Autowired
    UserInfoService userInfoService;

    @ApiOperation(value = "查询用户列表", response = JSONObject.class)
    @RequestMapping(value = "/findUserList", method = RequestMethod.GET)
    public JSONObject findUserList(@ModelAttribute UserInfoReq req) {
        return userInfoService.findUserList(req);
    }

    @ApiOperation(value = "查询用户", response = JSONObject.class)
    @RequestMapping(value = "/findUser/{id}", method = RequestMethod.GET)
    public JSONObject findUser(@PathVariable Long id) {
        return userInfoService.findUser(id);
    }

    @ApiOperation(value = "增加用户", response = JSONObject.class)
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public JSONObject addUser(@RequestBody AddUserInfoReq req) {
        return userInfoService.insert(req,getSessionFuliInfo().getLoginName());
    }

    @ApiOperation(value = "更新用户", response = JSONObject.class)
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public JSONObject updateUser(@RequestBody UserInfo req) {
        req.setUpdate_user(getSessionFuliInfo().getLoginName());
        return userInfoService.update(req);
    }

    @ApiOperation(value = "删除用户", response = JSONObject.class)
    @RequestMapping(value = "/delUser/{id}", method = RequestMethod.GET)
    public JSONObject delUser(@PathVariable Long id) {
        return userInfoService.delete(id);
    }


    @ApiOperation(value = "重置用户密码", response = JSONObject.class)
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public JSONObject resetPwdReq(@RequestBody ResetPwdReq req) {
        return userInfoService.resetPwd(req, getSessionFuliInfo().getLoginName());
    }


    @ApiOperation(value = "更新用户状态", response = JSONObject.class)
    @RequestMapping(value = "/updateUserStatus", method = RequestMethod.POST)
    public JSONObject updateUserStatus(@RequestBody UserStatusReq req) {
        return userInfoService.updateUserStatus(req, getSessionFuliInfo().getLoginName());
    }


    @ApiOperation(value = "用户是否存在", response = JSONObject.class)
    @RequestMapping(value = "/existUser", method = RequestMethod.GET)
    public JSONObject existUser(@RequestParam String loginName) {
        return userInfoService.existUser(loginName);
    }

}
