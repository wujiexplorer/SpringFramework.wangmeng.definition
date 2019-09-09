package com.lx.benefits.bean.entity.power;

import lombok.Data;

import java.util.Date;

@Data
public class SysRole {
    private Long id;

    private String roleCode;

    private String roleName;

    private String roleDesc;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
}