package com.lx.benefits.bean.dto.power;

import lombok.Data;

import java.util.List;

/**
 * User: fan
 * Date: 2019/03/19
 * Time: 19:12
 */
@Data
public class RoleAuthorizeReq {

    private Long roleId;

    private List<Long> menuIds;

}
