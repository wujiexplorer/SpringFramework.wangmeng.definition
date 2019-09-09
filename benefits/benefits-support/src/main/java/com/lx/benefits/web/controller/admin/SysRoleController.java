package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.power.RoleAuthorizeReq;
import com.lx.benefits.bean.dto.power.SysRoleReq;
import com.lx.benefits.bean.dto.user.AddRoleReq;
import com.lx.benefits.bean.entity.power.SysRole;
import com.lx.benefits.service.power.RoleService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User: fan
 * Date: 2019/03/19
 * Time: 18:38
 */
@RestController
@RequestMapping("/admin/role")
public class SysRoleController extends BaseAdminController {

    @Autowired
    RoleService roleService;

    @ApiOperation(value = "查询角色列表", response = JSONObject.class)
    @RequestMapping(value = "/findRoleList", method = RequestMethod.GET)
    public JSONObject findRoleList(@ModelAttribute SysRoleReq req) {
        return roleService.findSysRole(req);
    }

    @ApiOperation(value = "查询角色菜单列表", response = JSONObject.class)
    @RequestMapping(value = "/findRoleMenu/{id}", method = RequestMethod.GET)
    public JSONObject findRoleMenu(@ApiParam(value = "Request", name = "dto") @PathVariable Long id) {
        return roleService.findRoleMenu(id);
    }

    @ApiOperation(value = "增加角色", response = JSONObject.class)
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public JSONObject addRole(@ApiParam(value = "Request", name = "dto") @RequestBody AddRoleReq dto) {
        return roleService.insert(dto,getSessionFuliInfo().getLoginName());
    }

    @ApiOperation(value = "修改角色", response = JSONObject.class)
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public JSONObject updateRole(@ApiParam(value = "Request", name = "dto") @RequestBody SysRole dto) {
        dto.setUpdateBy(getSessionFuliInfo().getLoginName());
        return roleService.update(dto);
    }

    @ApiOperation(value = "删除角色", response = JSONObject.class)
    @RequestMapping(value = "/delRole/{id}", method = RequestMethod.GET)
    public JSONObject delRole(@ApiParam(value = "Request", name = "dto") @PathVariable Long id) {
        return roleService.delete(id);
    }

    @ApiOperation(value = "角色授权", response = JSONObject.class)
    @RequestMapping(value = "/roleAuthorize", method = RequestMethod.POST)
    public JSONObject roleAuthorize(@ApiParam(value = "Request", name = "dto") @RequestBody RoleAuthorizeReq dto) {
        return roleService.roleAuthorize(dto, getSessionFuliInfo().getLoginName());
    }

}
