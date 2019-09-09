package com.lx.benefits.mapper.power;

import com.lx.benefits.bean.entity.power.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuMapper {

    List<SysMenu> findSysMenu(@Param("menuLevel") Integer menuLevel, @Param("menuParent") Long menuParent);

    SysMenu findSysMenuObj(Long id);

    SysMenu findSysMenuObjByCode(@Param("menuCode") String menuCode);

    int delete(Long id);

    int deleteSubMenu(@Param("menuParent") Long menuParent);

    int insert(SysMenu record);

    int update(SysMenu record);
}