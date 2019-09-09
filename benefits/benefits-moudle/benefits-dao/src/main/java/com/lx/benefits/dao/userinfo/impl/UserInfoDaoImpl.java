package com.lx.benefits.dao.userinfo.impl;

import com.lx.benefits.bean.dto.user.UserInfoReq;
import com.lx.benefits.bean.entity.userinfo.UserInfo;
import com.lx.benefits.bean.entity.userinfo.UserInfoExample;
import com.lx.benefits.dao.userinfo.UserInfoDao;
import com.lx.benefits.mapper.userinfo.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yingcai on 2018/12/18.
 */
@Service
public class UserInfoDaoImpl implements UserInfoDao {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Long insert(UserInfo userInfo) {
        return Long.valueOf(userInfoMapper.insertSelective(userInfo));
    }

    @Override
    public Long insertSelective(UserInfo userInfo) {
        return Long.valueOf(userInfoMapper.insertSelective(userInfo));
    }

    @Override
    public Integer update(UserInfo userInfo, UserInfoExample example) {
        return userInfoMapper.updateByExampleSelective(userInfo, example);
    }

    @Override
    public Integer update(UserInfo userInfo) {
        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public Integer delete(Long id) {
        return userInfoMapper.delete(id);
    }

    @Override
    public Integer existUser(String loginName) {
        return userInfoMapper.existUser(loginName);
    }

    @Override
    public List<UserInfo> find(UserInfoExample example) {
        return userInfoMapper.selectByExample(example);
    }

    @Override
    public List<UserInfo> findUserList(UserInfoReq req) {
        return userInfoMapper.findUserList(req);
    }

    @Override
    public Integer findUserCount(UserInfoReq req) {
        return userInfoMapper.findUserCount(req);
    }

    @Override
    public UserInfo fetchOne(UserInfoExample example) {
        return userInfoMapper.fetchOneByExample(example);
    }

    @Override
    public UserInfo findUserById(Long id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer count(UserInfoExample example) {
        return userInfoMapper.countByExample(example);
    }
}
