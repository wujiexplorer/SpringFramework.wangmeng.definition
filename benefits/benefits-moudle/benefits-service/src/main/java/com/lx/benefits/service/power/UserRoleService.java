package com.lx.benefits.service.power;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.power.SysRoleMenuRes;
import com.lx.benefits.bean.dto.power.UserAuthorizeReq;
import com.lx.benefits.bean.dto.user.UserInfoReq;
import com.lx.benefits.bean.entity.userinfo.UserInfo;
import com.lx.benefits.bean.util.SessionFuliInfo;

import java.util.List;

/**
 * User: fan
 * Date: 2019/03/19
 * Time: 22:55
 */
public interface UserRoleService {

    JSONObject findRole(Long userId);

    JSONObject userAuthorize(String userName, UserAuthorizeReq req);

    JSONObject findUserMenu(Long userId);

    List<SysRoleMenuRes> userPower(SessionFuliInfo userInfo);

}
