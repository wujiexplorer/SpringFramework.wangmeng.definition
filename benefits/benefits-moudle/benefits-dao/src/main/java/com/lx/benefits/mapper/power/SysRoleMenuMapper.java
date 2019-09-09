package com.lx.benefits.mapper.power;

import com.lx.benefits.bean.dto.power.SysRoleMenuRes;
import com.lx.benefits.bean.entity.power.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper {

    List<SysRoleMenuRes> findRoleMenu(@Param("roleId") Long roleId, @Param("menuParent") Long menuParent);

    int delete(@Param("roleId") Long roleId,@Param("menuId") Long menuId);

    int deleteRole(Long id);

    int deleteMenu(Long id);

    int insert(SysRoleMenu record);

    int update(SysRoleMenu record);
}
