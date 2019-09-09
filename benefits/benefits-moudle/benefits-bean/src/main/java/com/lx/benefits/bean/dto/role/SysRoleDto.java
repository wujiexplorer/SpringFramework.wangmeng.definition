package com.lx.benefits.bean.dto.role;

import lombok.Data;

import java.util.Date;

/**
 * User: fan
 * Date: 2019/03/25
 * Time: 16:43
 */
@Data
public class SysRoleDto {

    private Long id;

    private String roleCode;

    private String roleName;

    private String roleDesc;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Object menuList;

}
