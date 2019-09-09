package com.lx.benefits.dao.userinfo;


import com.lx.benefits.bean.dto.user.UserInfoReq;
import com.lx.benefits.bean.entity.userinfo.UserInfo;
import com.lx.benefits.bean.entity.userinfo.UserInfoExample;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface UserInfoDao {

    Long insert(UserInfo userInfo);

    Long insertSelective(UserInfo userInfo);

    Integer update(UserInfo userInfo, UserInfoExample example);

    Integer update(UserInfo userInfo);

    Integer delete(Long id);

    Integer existUser(String loginName);

    List<UserInfo> find(UserInfoExample example);

    List<UserInfo> findUserList(UserInfoReq req);

    Integer findUserCount(UserInfoReq req);

    UserInfo fetchOne(UserInfoExample example);

    UserInfo findUserById(Long id);

    Integer count(UserInfoExample example);


}
