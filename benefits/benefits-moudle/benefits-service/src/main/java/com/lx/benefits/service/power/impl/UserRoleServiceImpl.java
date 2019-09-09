package com.lx.benefits.service.power.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.power.SysRoleMenuRes;
import com.lx.benefits.bean.dto.power.UserAuthorizeReq;
import com.lx.benefits.bean.entity.power.SysUserRole;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionFuliInfo;
import com.lx.benefits.mapper.power.SysUserRoleMapper;
import com.lx.benefits.service.power.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: fan
 * Date: 2019/03/19
 * Time: 22:55
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private static final String USERPOWER_KEY = "USERPOWER:%s/%s";

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public JSONObject findRole(Long userId) {
        return Response.succ(sysUserRoleMapper.findSysUserRole(userId));
    }

    @Override
    public JSONObject userAuthorize(String userName, UserAuthorizeReq req) {
        // 删除用户所有角色
        sysUserRoleMapper.delete(req.getUserId());
        for (Long roleId : req.getRoleIds()) {
            SysUserRole role = new SysUserRole();
            role.setRoleId(roleId);
            role.setUserId(req.getUserId());
            role.setCreateBy(userName);
            role.setCreateTime(new Date());
            sysUserRoleMapper.insert(role);
        }
        return Response.succ();
    }

    @Override
    public List<SysRoleMenuRes> userPower(SessionFuliInfo userInfo) {
        String key = String.format(USERPOWER_KEY, userInfo.getAdminId(), userInfo.getLoginName());
        List<SysRoleMenuRes> list = sysUserRoleMapper.findSysUserPower(userInfo.getAdminId());
        if (list == null) {
            list = new ArrayList<>();
            redisTemplate.opsForHash().put(key, "/", JSON.toJSONString(list));
            return null;
        }
        for (SysRoleMenuRes res : list) {
            redisTemplate.opsForHash().put(key, res.getMenuUrl(), JSON.toJSONString(res));
        }
        return list;
    }

    @Override
    public JSONObject findUserMenu(Long userId) {
        List<SysRoleMenuRes> userMenu = sysUserRoleMapper.findSysUserMenu(userId, 0L);
        setMenu(userId, userMenu);
        return Response.succ(userMenu);
    }

    private void setMenu(Long userId, List<SysRoleMenuRes> userMenu) {
        if (userMenu == null || userMenu.size() == 0) {
            return;
        }
        for (SysRoleMenuRes menu : userMenu) {
            // 加载子菜单
            List<SysRoleMenuRes> roleMenuRes = sysUserRoleMapper.findSysUserMenu(userId, menu.getId());
            menu.setMenuList(roleMenuRes == null || roleMenuRes.size() == 0 ? null : roleMenuRes);
            setMenu(userId, roleMenuRes);
        }
    }
}
