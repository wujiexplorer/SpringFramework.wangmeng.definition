package com.lx.benefits.mapper.memberinfo;

import com.lx.benefits.bean.entity.memberinfo.MemberInfo;
import com.lx.benefits.bean.entity.memberinfo.MemberInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberInfoMapper {
    int countByExample(MemberInfoExample example);

    int deleteByExample(MemberInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberInfo record);

    int insertSelective(MemberInfo record);

    int batchInsert(@Param("memberInfoList") List<MemberInfo> memberInfoList);
    
    List<MemberInfo> selectByExample(MemberInfoExample example);

    MemberInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberInfo record, @Param("example") MemberInfoExample example);

    int updateByExample(@Param("record") MemberInfo record, @Param("example") MemberInfoExample example);

    int updateByPrimaryKeySelective(MemberInfo record);

    int updateByPrimaryKey(MemberInfo record);
}