package com.lx.benefits.mapper.power;

import com.lx.benefits.bean.dto.power.SysRoleMenuRes;
import com.lx.benefits.bean.dto.power.SysRoleRes;
import com.lx.benefits.bean.entity.power.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {

    List<SysRoleRes> findSysUserRole(@Param("userId") Long userId);

    List<SysUserRole> findSysUserRoleByRoleId(@Param("roleId") Long roleId);

    List<SysRoleMenuRes> findSysUserMenu(@Param("userId") Long userId, @Param("menuParent") Long menuParent);

    List<SysRoleMenuRes> findSysUserPower(@Param("userId") Long userId);

    int delete(Long id);

    int insert(SysUserRole record);

    int update(SysUserRole record);

}
