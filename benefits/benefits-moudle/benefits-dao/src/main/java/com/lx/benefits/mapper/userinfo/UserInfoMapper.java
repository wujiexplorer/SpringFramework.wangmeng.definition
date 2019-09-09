package com.lx.benefits.mapper.userinfo;

import com.lx.benefits.bean.dto.user.UserInfoReq;
import com.lx.benefits.bean.entity.userinfo.UserInfo;
import com.lx.benefits.bean.entity.userinfo.UserInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {
    int countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(Long id);

    int delete(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExample(UserInfoExample example);

    List<UserInfo> findUserList(UserInfoReq req);

    Integer findUserCount(UserInfoReq req);

    UserInfo selectByPrimaryKey(Long id);

    UserInfo fetchOneByExample(UserInfoExample example);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    int existUser(@Param("login_name") String loginName);
}