package com.wangmeng.test.mapper.auto;

import com.wangmeng.test.model.auto.TsysUser;
import com.wangmeng.test.model.auto.TsysUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TsysUserMapper {
    int countByExample(TsysUserExample example);

    int deleteByExample(TsysUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(TsysUser record);

    int insertSelective(TsysUser record);

    List<TsysUser> selectByExample(TsysUserExample example);

    TsysUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TsysUser record, @Param("example") TsysUserExample example);

    int updateByExample(@Param("record") TsysUser record, @Param("example") TsysUserExample example);

    int updateByPrimaryKeySelective(TsysUser record);

    int updateByPrimaryKey(TsysUser record);
}