package com.lx.benefits.service.userinfo.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.power.UserAuthorizeReq;
import com.lx.benefits.bean.dto.user.AddUserInfoReq;
import com.lx.benefits.bean.dto.user.ResetPwdReq;
import com.lx.benefits.bean.dto.user.UserInfoReq;
import com.lx.benefits.bean.dto.user.UserStatusReq;
import com.lx.benefits.bean.entity.userinfo.UserInfo;
import com.lx.benefits.bean.entity.userinfo.UserInfoExample;
import com.lx.benefits.bean.util.BeansUtils;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.dao.userinfo.UserInfoDao;
import com.lx.benefits.mapper.power.SysUserRoleMapper;
import com.lx.benefits.service.power.UserRoleService;
import com.lx.benefits.service.userinfo.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author by yingcai on 2018/12/27.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public JSONObject insert(AddUserInfoReq req, String userName) {
        if (StringUtils.isBlank(req.getLogin_name())) {
            return Response.fail("登录名不能为空");
        }
        if (userInfoDao.existUser(req.getLogin_name()) > 0) {
            return Response.fail("登录名已存在");
        }
        if (StringUtils.isBlank(req.getUser_name())) {
            return Response.fail("用户名不能为空");
        }
        if (req.getDepartment_id() == null) {
            return Response.fail("部门ID不能为空");
        }
        if (StringUtils.isBlank(req.getPassword())) {
            return Response.fail("密码不能为空");
        }
        UserInfo userInfo = BeansUtils.copyProperties(req, UserInfo.class);
        userInfo.setPassword(EncryptUtil.encryptByMd5(userInfo.getPassword()));
        userInfo.setSalt(EncryptUtil.encryptByMd5(userInfo.getLogin_name()));
        userInfo.setStatus(true);
        userInfo.setCreate_time(new Date());
        userInfo.setUpdate_time(new Date());
        userInfo.setUpdate_user(userName);
        userInfo.setCreate_user(userName);
        Long userid = userInfoDao.insertSelective(userInfo);
        if (userid != null && userid > 0) {
            if (req.getRoleIds() != null && req.getRoleIds().size() > 0) {
                UserAuthorizeReq authorizeReq = new UserAuthorizeReq();
                authorizeReq.setUserId(userInfo.getId());
                authorizeReq.setRoleIds(req.getRoleIds());
                userRoleService.userAuthorize(userName, authorizeReq);
            }
            return Response.succ();
        }
        return Response.fail();
    }

    @Override
    public JSONObject update(UserInfo userInfo) {
        if (userInfo.getId() == null) {
            return Response.fail("用户ID不能为空");
        }
        userInfo.setPassword(null);
        userInfo.setSalt(null);
        userInfo.setUpdate_time(new Date());
        if (StringUtils.isNotBlank(userInfo.getLogin_name())) {
            UserInfoExample userInfoExample = new UserInfoExample();
            userInfoExample.createCriteria().andLogin_nameEqualTo(userInfo.getLogin_name()).andIdNotEqualTo(userInfo.getId());
            if (userInfoDao.fetchOne(userInfoExample) != null) {
                return Response.fail("登录名已存在");
            }
        }
        if (userInfoDao.update(userInfo) > 0) {
            return Response.succ();
        }
        return Response.fail();
    }

    @Override
    public JSONObject resetPwd(ResetPwdReq req, String userName) {
        if (req.getUserId() == null) {
            return Response.fail("用户ID不能为空");
        }
        if (StringUtils.isBlank(req.getPwd())) {
            req.setPwd("123456");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(req.getUserId());
        userInfo.setPassword(EncryptUtil.encryptByMd5(req.getPwd()));
        userInfo.setUpdate_time(new Date());
        if (userInfoDao.update(userInfo) > 0) {
            return Response.succ();
        }
        return Response.fail();
    }

    @Override
    public JSONObject updateUserStatus(UserStatusReq req, String userName) {
        if (req.getUserId() == null) {
            return Response.fail("用户ID不能为空");
        }
        if (req.getStatus() == null) {
            return Response.fail("用户状态不能为空");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(req.getUserId());
        userInfo.setStatus(req.getStatus() == 1 ? true : false);
        userInfo.setUpdate_time(new Date());
        if (userInfoDao.update(userInfo) > 0) {
            return Response.succ();
        }
        return Response.fail();
    }

    @Override
    public JSONObject delete(Long id) {
        if (userInfoDao.delete(id) > 0) {
            return Response.succ();
        }
        return Response.fail();
    }

    @Override
    public JSONObject existUser(String loginName) {
        return Response.succ(userInfoDao.existUser(loginName) > 0 ? true : false);
    }

    @Override
    public List<UserInfo> find(UserInfoExample example) {
        return userInfoDao.find(example);
    }

    @Override
    public Integer count(UserInfoExample example) {
        return userInfoDao.count(example);
    }

    @Override
    public JSONObject findUserList(UserInfoReq req) {
        req = req == null ? new UserInfoReq() : req;
        req.setPage(req.getPage() > 0 ? (req.getPage() - 1) * req.getPageSize() : 0);
        JSONObject jsonObject = new JSONObject();
        List<UserInfo> list = userInfoDao.findUserList(req);
        if (list != null) {
            for (UserInfo u : list) {
                u.setRoleList(sysUserRoleMapper.findSysUserRole(u.getId()));
            }
        }
        jsonObject.put("info", list);
        jsonObject.put("count", userInfoDao.findUserCount(req));
        return Response.succ(jsonObject);
    }

    @Override
    public JSONObject findUser(Long id) {
        UserInfo userInfo = userInfoDao.findUserById(id);
        if (userInfo != null) {
            userInfo.setRoleList(sysUserRoleMapper.findSysUserRole(userInfo.getId()));
        }
        return Response.succ(userInfo);
    }

}
