package com.lx.benefits.service.adminaccount.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.account.AdminAccountInfoDto;
import com.lx.benefits.bean.dto.admin.account.ModifyPasswordReqDto;
import com.lx.benefits.bean.entity.userinfo.UserInfo;
import com.lx.benefits.bean.entity.userinfo.UserInfoExample;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.dao.userinfo.UserInfoDao;
import com.lx.benefits.service.adminaccount.AdminAccountService;

/**
 * @author unknow on 2018-12-20 02:12.
 */
@Service
public class AdminAccountServiceImpl implements AdminAccountService {
    
    @Autowired
    private UserInfoDao userInfoDao;
    
    /**
     * 根据登录用户名获取用户信息
     * @param loginName       登录用户名
     * @param needPassword    是否需要返回密码信息
     * @return    AdminAccountInfoDto
     */
    @Override
    public AdminAccountInfoDto getAdminAccountInfoByLoginName(String loginName, boolean needPassword) {
        if (null == loginName || loginName.trim().isEmpty()) {
            return null;
        }
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andLogin_nameEqualTo(loginName.trim()).andStatusEqualTo(true);
        UserInfo userInfo = userInfoDao.fetchOne(userInfoExample);
        if (null != userInfo && loginName.equals(userInfo.getLogin_name())) {
            AdminAccountInfoDto adminAccountInfoDto = new AdminAccountInfoDto();
            adminAccountInfoDto.setAdminId(userInfo.getId());
            adminAccountInfoDto.setDepartmentId(userInfo.getDepartment_id());
            adminAccountInfoDto.setEmail(userInfo.getEmail());
            adminAccountInfoDto.setLastLoginIp(userInfo.getLast_login_ip());
            adminAccountInfoDto.setLastLoginTime(DateUtil.date2String(userInfo.getLast_login_time()));
            adminAccountInfoDto.setLoginName(userInfo.getLogin_name());
            adminAccountInfoDto.setMobile(userInfo.getMobile());
            if (needPassword) {
                adminAccountInfoDto.setPassword(userInfo.getPassword());
                adminAccountInfoDto.setSalt(userInfo.getSalt());
            }
            adminAccountInfoDto.setRoleId(userInfo.getRole_id());
            adminAccountInfoDto.setStatus(null);
            adminAccountInfoDto.setUserName(userInfo.getUser_name());
            return adminAccountInfoDto;
        }
        return null;
    }

    @Override
    public JSONObject modifyPassword(ModifyPasswordReqDto req) {
        Long adminId = SessionContextHolder.getSessionFuliInfo().getAdminId();
        UserInfo userInfo = userInfoDao.findUserById(adminId);
        if (null == userInfo) {
            return Response.fail("用户不存在，请求重新登录");
        }
        String oldPassword = req.getOldPassword();
        if (null == oldPassword || StringUtil.isEmpty(oldPassword)) {
            return Response.fail("老密码不能为空");
        }
        String password = req.getPassword();
        if (null == password || StringUtil.isEmpty(password)) {
            return Response.fail("新密码不能为空");
        }
        String repeatPassword = req.getRepeatPassword();
        if (null == repeatPassword || StringUtil.isEmpty(repeatPassword)) {
            return Response.fail("确认密码不能为空");
        }
        if (!password.equals(repeatPassword)) {
            return Response.fail("两次密码不一致");
        }
        String loginName = SessionContextHolder.getSessionFuliInfo().getLoginName();
        String encryptPassword = EncryptUtil.encryptByMd5(oldPassword);
        if (loginName.equals(userInfo.getLogin_name()) && encryptPassword.equals(userInfo.getPassword())) {
            userInfo.setPassword(EncryptUtil.encryptByMd5(password));
            UserInfoExample example = new UserInfoExample();
            example.createCriteria().andIdEqualTo(adminId);
            int update = userInfoDao.update(userInfo,example);
            if ( update > 0) {
                return  Response.succ("密码修改成功");
            } else {
                return Response.fail("密码修改失败");
            }
        } else {
            return Response.fail("老密码错误");
        }
    }
}
