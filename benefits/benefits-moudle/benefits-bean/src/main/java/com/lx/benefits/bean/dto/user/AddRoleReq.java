package com.lx.benefits.bean.dto.user;

import lombok.Data;

import java.util.List;

/**
 * User: fan
 * Date: 2019/03/25
 * Time: 22:43
 */
@Data
public class AddRoleReq {

    private String roleCode;

    private String roleName;

    private String roleDesc;

    private List<Long> menuIds;

}
