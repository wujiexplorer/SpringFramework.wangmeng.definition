package com.lx.benefits.bean.entity.power;

import lombok.Data;

import java.util.Date;

@Data
public class SysUserRole {
    private Long id;

    private Long userId;

    private Long roleId;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
}