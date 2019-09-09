package com.lx.benefits.service.userinfo;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.user.AddUserInfoReq;
import com.lx.benefits.bean.dto.user.ResetPwdReq;
import com.lx.benefits.bean.dto.user.UserInfoReq;
import com.lx.benefits.bean.dto.user.UserStatusReq;
import com.lx.benefits.bean.entity.userinfo.UserInfo;
import com.lx.benefits.bean.entity.userinfo.UserInfoExample;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface UserInfoService {

    JSONObject insert(AddUserInfoReq userInfo, String userName);

    JSONObject update(UserInfo userInfo);

    JSONObject resetPwd(ResetPwdReq req, String userName);

    JSONObject updateUserStatus(UserStatusReq req, String userName);

    JSONObject delete(Long id);

    JSONObject existUser(String loginName);

    List<UserInfo> find(UserInfoExample example);

    Integer count(UserInfoExample example);

    JSONObject findUserList(UserInfoReq req);

    JSONObject findUser(Long id);

}
