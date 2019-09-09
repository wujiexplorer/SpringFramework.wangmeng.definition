package com.lx.benefits.bean.dto.power;

import lombok.Data;

/**
 * User: fan
 * Date: 2019/03/19
 * Time: 16:47
 */
@Data
public class SysRoleSubMenuRes {

    private Long id;

    private String menuCode;

    private String menuName;

    private Integer menuLevel;

    private Long menuParent;

    private String menuUrl;

}
