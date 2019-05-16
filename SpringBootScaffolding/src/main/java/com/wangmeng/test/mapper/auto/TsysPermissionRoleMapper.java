package com.wangmeng.test.mapper.auto;

import com.wangmeng.test.model.auto.TsysPermissionRole;
import com.wangmeng.test.model.auto.TsysPermissionRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TsysPermissionRoleMapper {
    int countByExample(TsysPermissionRoleExample example);

    int deleteByExample(TsysPermissionRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(TsysPermissionRole record);

    int insertSelective(TsysPermissionRole record);

    List<TsysPermissionRole> selectByExample(TsysPermissionRoleExample example);

    TsysPermissionRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TsysPermissionRole record, @Param("example") TsysPermissionRoleExample example);

    int updateByExample(@Param("record") TsysPermissionRole record, @Param("example") TsysPermissionRoleExample example);

    int updateByPrimaryKeySelective(TsysPermissionRole record);

    int updateByPrimaryKey(TsysPermissionRole record);
}