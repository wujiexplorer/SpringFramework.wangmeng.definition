package com.lx.benefits.service.power.impl;

import com.alibaba.fastjson.JSONObject;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.dto.power.RoleAuthorizeReq;
import com.lx.benefits.bean.dto.power.SysRoleMenuRes;
import com.lx.benefits.bean.dto.power.SysRoleReq;
import com.lx.benefits.bean.dto.role.SysRoleDto;
import com.lx.benefits.bean.dto.user.AddRoleReq;
import com.lx.benefits.bean.entity.power.SysMenu;
import com.lx.benefits.bean.entity.power.SysRole;
import com.lx.benefits.bean.entity.power.SysRoleMenu;
import com.lx.benefits.bean.entity.power.SysUserRole;
import com.lx.benefits.bean.util.BeansUtils;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.mapper.power.SysMenuMapper;
import com.lx.benefits.mapper.power.SysRoleMapper;
import com.lx.benefits.mapper.power.SysRoleMenuMapper;
import com.lx.benefits.mapper.power.SysUserRoleMapper;
import com.lx.benefits.service.power.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: fan
 * Date: 2019/03/19
 * Time: 18:40
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public JSONObject findSysRole(SysRoleReq req) {
        req = req == null ? new SysRoleReq() : req;
        List<SysRole> list = sysRoleMapper.findSysRole(req);
        List<SysRoleDto> roleDtos = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (SysRole role : list) {
                SysRoleDto dto = BeansUtils.copyProperties(role, SysRoleDto.class);
                dto.setMenuList(findRoleMenuList(role.getId()));
                roleDtos.add(dto);
            }
        }
        return Response.succ(roleDtos);
    }

    @Override
    public JSONObject findRoleMenu(Long id) {
        return Response.succ(findRoleMenuList(id));
    }

    public List<SysRoleMenuRes> findRoleMenuList(Long id) {
        // 查顶级菜单
        List<SysRoleMenuRes> list = sysRoleMenuMapper.findRoleMenu(id, 0L);
        if (list == null || list.size() == 0) {
            return null;
        }
        setMenu(id, list);
        return list;
    }

    private void setMenu(Long id, List<SysRoleMenuRes> menuList) {
        if (menuList == null || menuList.size() == 0) {
            return;
        }
        for (SysRoleMenuRes menu : menuList) {
            // 加载子菜单
            List<SysRoleMenuRes> roleMenuRes = sysRoleMenuMapper.findRoleMenu(id, menu.getId());
            menu.setMenuList(roleMenuRes == null || roleMenuRes.size() == 0 ? null : roleMenuRes);
            setMenu(id, roleMenuRes);
        }
    }

    @Override
    public JSONObject roleAuthorize(RoleAuthorizeReq req, String userName) {
        if (req.getRoleId() == null || req.getRoleId() < 1) {
            return Response.fail("角色ID不能为空");
        }
        SysRole role = sysRoleMapper.findSysRoleObj(req.getRoleId());
        if (role == null) {
            return Response.fail("角色ID不正确");
        }
        // 清除角色所有权限
        sysRoleMenuMapper.deleteRole(req.getRoleId());
        if (req.getMenuIds() != null) {
            // 增加权限
            for (Long mid : req.getMenuIds()) {
                SysMenu menu = sysMenuMapper.findSysMenuObj(mid);
                // 没有这个菜单
                if (menu == null) {
                    continue;
                }
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setMenuId(mid);
                roleMenu.setRoleId(req.getRoleId());
                roleMenu.setCreateTime(new Date());
                roleMenu.setCreateBy(userName);
                sysRoleMenuMapper.insert(roleMenu);
            }
            return Response.succ();
        }
        return Response.fail();
    }

    @Override
    public JSONObject delete(Long id) {
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.findSysUserRoleByRoleId(id);
        if(!sysUserRoles.isEmpty()){
            throw new BusinessException("该角色下有用户，不能删除！");
        }
        if (sysRoleMapper.delete(id) > 0) {
            // 角色删除所有对应关系
            sysRoleMenuMapper.deleteRole(id);
            return Response.succ();
        }
        return Response.fail();
    }

    @Override
    public JSONObject insert(AddRoleReq req, String userName) {
        SysRole record = BeansUtils.copyProperties(req, SysRole.class);
        record.setCreateBy(userName);
        record.setCreateTime(new Date());
        JSONObject object = check(record);
        if (!object.getString("code").equals("1001")) {
            return object;
        }
        if (sysRoleMapper.findSysRoleObjByCode(record.getRoleCode()) != null) {
            return Response.fail("编号已存在");
        }
        Long roleId = sysRoleMapper.insert(record);
        if (roleId != null && roleId > 0) {
            if (req.getMenuIds() != null && req.getMenuIds().size() > 0) {
                RoleAuthorizeReq authorizeReq = new RoleAuthorizeReq();
                authorizeReq.setRoleId(record.getId());
                authorizeReq.setMenuIds(req.getMenuIds());
                roleAuthorize(authorizeReq, userName);
            }
            return Response.succ();
        }
        return Response.fail();
    }

    @Override
    public JSONObject update(SysRole record) {
        record.setUpdateTime(new Date());
        if (record.getId() == null || record.getId() < 1) {
            return Response.fail("ID不正确");
        }
        SysRole sysRole = sysRoleMapper.findSysRoleObj(record.getId());
        if (sysRole == null) {
            return Response.fail("信息不存在");
        }
        SysRole sysRole2 = sysRoleMapper.findSysRoleObjByCode(record.getRoleCode());
        if (sysRole2 != null && !sysRole2.getId().equals(sysRole.getId())) {
            return Response.fail("编号已存在");
        }
        if (sysRoleMapper.update(record) > 0) {
            return Response.succ();
        }
        return Response.fail();
    }

    private JSONObject check(SysRole record) {
        if (StringUtils.isBlank(record.getRoleCode())) {
            return Response.fail("编号不能为空");
        }
        if (StringUtils.isBlank(record.getRoleName())) {
            return Response.fail("名称不能为空");
        }
        return Response.succ();
    }
}
