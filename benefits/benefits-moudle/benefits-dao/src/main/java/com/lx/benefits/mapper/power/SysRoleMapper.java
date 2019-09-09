package com.lx.benefits.mapper.power;

import com.lx.benefits.bean.dto.power.SysRoleReq;
import com.lx.benefits.bean.entity.power.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper {

    List<SysRole> findSysRole(SysRoleReq req);

    SysRole findSysRoleObj(Long id);

    SysRole findSysRoleObjByCode(@Param("roleCode") String roleCode);

    int delete(Long id);

    Long insert(SysRole record);

    int update(SysRole record);

}