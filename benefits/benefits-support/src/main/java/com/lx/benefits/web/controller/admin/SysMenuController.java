package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.entity.power.SysMenu;
import com.lx.benefits.service.power.MenuService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User: fan
 * Date: 2019/03/19
 * Time: 17:58
 */
@RestController
@RequestMapping("/admin/menu")
public class SysMenuController extends BaseAdminController {

    @Autowired
    MenuService menuService;

    @ApiOperation(value = "查询菜单列表", response = JSONObject.class)
    @RequestMapping(value = "/findMenuList", method = RequestMethod.GET)
    public JSONObject findMenuList() {
        return menuService.findSysMenu();
    }

    @ApiOperation(value = "增加菜单", response = JSONObject.class)
    @RequestMapping(value = "/addMenu", method = RequestMethod.POST)
    public JSONObject addMenu(@ApiParam(value = "Request", name = "dto") @RequestBody SysMenu dto) {
        dto.setCreateBy(getSessionFuliInfo().getLoginName());
        return menuService.insert(dto);
    }

    @ApiOperation(value = "修改菜单", response = JSONObject.class)
    @RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
    public JSONObject updateMenu(@ApiParam(value = "Request", name = "dto") @RequestBody SysMenu dto) {
        dto.setUpdateBy(getSessionFuliInfo().getLoginName());
        return menuService.update(dto);
    }

    @ApiOperation(value = "删除菜单", response = JSONObject.class)
    @RequestMapping(value = "/delMenu/{id}", method = RequestMethod.GET)
    public JSONObject delMenu(@ApiParam(value = "Request", name = "dto") @PathVariable Long id) {
        return menuService.delete(id);
    }

}
