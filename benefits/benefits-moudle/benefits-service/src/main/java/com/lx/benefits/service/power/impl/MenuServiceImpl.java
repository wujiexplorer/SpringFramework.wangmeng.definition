package com.lx.benefits.service.power.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.power.SysMenuRes;
import com.lx.benefits.bean.entity.power.SysMenu;
import com.lx.benefits.bean.util.BeansUtils;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.mapper.power.SysMenuMapper;
import com.lx.benefits.mapper.power.SysRoleMenuMapper;
import com.lx.benefits.service.power.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * User: fan
 * Date: 2019/03/19
 * Time: 17:45
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public JSONObject findSysMenu() {
        //List<SysMenuRes> list = new ArrayList<>();
        // 查询顶级菜单
        List<SysMenu> menuList = sysMenuMapper.findSysMenu(0, 0L);
        List<SysMenuRes> list = BeansUtils.copyArrayProperties(menuList, SysMenuRes.class);
        setMenu(list);
        return Response.succ(list);
    }

    private void setMenu(List<SysMenuRes> menuList) {
        if (menuList == null || menuList.size() == 0) {
            return;
        }
        for (SysMenuRes menu : menuList) {
            SysMenuRes res = BeansUtils.copyProperties(menu, SysMenuRes.class);
            // 加载子菜单
            List<SysMenu> subMenu = sysMenuMapper.findSysMenu((res.getMenuLevel() + 1), menu.getId());
            List<SysMenuRes> subMenuRes = BeansUtils.copyArrayProperties(subMenu, SysMenuRes.class);
            menu.setMenuList(subMenuRes == null || subMenuRes.size() == 0 ? null : subMenuRes);

            setMenu(subMenuRes);
        }
    }

    @Override
    public JSONObject delete(Long id) {
        SysMenu menu = sysMenuMapper.findSysMenuObj(id);
        if (menu == null) {
            return Response.fail("信息不存在");
        }
        if (sysMenuMapper.delete(menu.getId()) > 0) {
            // 父级删除后需要删除子级
            if (menu.getMenuParent() == 0 && menu.getMenuLevel() == 0) {
                sysMenuMapper.deleteSubMenu(menu.getId());
            }
            sysRoleMenuMapper.deleteMenu(id);
            return Response.succ();
        }
        return Response.fail();
    }

    @Override
    public JSONObject insert(SysMenu record) {
        JSONObject object = check(record);
        if (!object.getString("code").equals("1001")) {
            return object;
        }
        if (sysMenuMapper.findSysMenuObjByCode(record.getMenuCode()) != null) {
            return Response.fail("编号已存在");
        }
        record.setCreateTime(new Date());
        if (sysMenuMapper.insert(record) > 0) {
            return Response.succ();
        }
        return Response.fail();
    }

    @Override
    public JSONObject update(SysMenu record) {
        if (record.getId() == null || record.getId() < 1) {
            return Response.fail("ID不正确");
        }
        SysMenu sysMenu = sysMenuMapper.findSysMenuObj(record.getId());
        if (sysMenu == null) {
            return Response.fail("信息不存在");
        }
        SysMenu sysMenu2 = sysMenuMapper.findSysMenuObjByCode(record.getMenuCode());
        if (sysMenu2 != null && !sysMenu2.getId().equals(sysMenu.getId())) {
            return Response.fail("编号已存在");
        }
        record.setUpdateTime(new Date());
        if (sysMenuMapper.update(record) > 0) {
            return Response.succ();
        }
        return Response.fail();
    }

    private JSONObject check(SysMenu record) {
        if (StringUtils.isBlank(record.getMenuCode())) {
            return Response.fail("编号不能为空");
        }
        if (StringUtils.isBlank(record.getMenuName())) {
            return Response.fail("名称不能为空");
        }
        if (StringUtils.isBlank(record.getMenuUrl())) {
            return Response.fail("URL不能为空");
        }
        return Response.succ();
    }
}
