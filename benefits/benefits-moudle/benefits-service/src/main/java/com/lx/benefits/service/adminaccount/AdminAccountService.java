package com.lx.benefits.service.adminaccount;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.account.AdminAccountInfoDto;
import com.lx.benefits.bean.dto.admin.account.ModifyPasswordReqDto;

/**
 * @author unknow on 2018-12-20 02:02.
 */
public interface AdminAccountService {

    /**
     * 根据登录用户名获取用户信息
     * @param loginName       登录用户名
     * @param needPassword    是否需要返回密码信息
     * @return    AdminAccountInfoDto
     */
    AdminAccountInfoDto getAdminAccountInfoByLoginName(String loginName, boolean needPassword);

    JSONObject modifyPassword(ModifyPasswordReqDto req);
}