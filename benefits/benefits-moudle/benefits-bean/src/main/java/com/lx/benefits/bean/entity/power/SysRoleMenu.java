package com.lx.benefits.bean.entity.power;

import lombok.Data;

import java.util.Date;

@Data
public class SysRoleMenu {
    private Long id;

    private Long roleId;

    private Long menuId;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
}