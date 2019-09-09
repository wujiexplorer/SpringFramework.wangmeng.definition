package com.lx.benefits.service.power;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.power.RoleAuthorizeReq;
import com.lx.benefits.bean.dto.power.SysRoleReq;
import com.lx.benefits.bean.dto.user.AddRoleReq;
import com.lx.benefits.bean.entity.power.SysRole;

/**
 * User: fan
 * Date: 2019/03/19
 * Time: 18:39
 */
public interface RoleService {

    JSONObject findSysRole(SysRoleReq req);

    JSONObject findRoleMenu(Long id);

    JSONObject roleAuthorize(RoleAuthorizeReq req, String userName);

    JSONObject delete(Long id);

    JSONObject insert(AddRoleReq record, String userName);

    JSONObject update(SysRole record);

}
