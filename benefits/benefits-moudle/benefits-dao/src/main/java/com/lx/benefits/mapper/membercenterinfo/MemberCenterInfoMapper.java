package com.lx.benefits.mapper.membercenterinfo;

import com.lx.benefits.bean.entity.membercenterinfo.MemberCenterInfo;
import com.lx.benefits.bean.entity.membercenterinfo.MemberCenterInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberCenterInfoMapper {
    int countByExample(MemberCenterInfoExample example);

    int deleteByExample(MemberCenterInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberCenterInfo record);

    int batchInsert(@Param("memberCenterInfoList") List<MemberCenterInfo> memberCenterInfoList);

    int insertSelective(MemberCenterInfo record);

    List<MemberCenterInfo> selectByExample(MemberCenterInfoExample example);

    MemberCenterInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberCenterInfo record, @Param("example") MemberCenterInfoExample example);

    int updateByExample(@Param("record") MemberCenterInfo record, @Param("example") MemberCenterInfoExample example);

    int updateByPrimaryKeySelective(MemberCenterInfo record);

    int updateByPrimaryKey(MemberCenterInfo record);
}