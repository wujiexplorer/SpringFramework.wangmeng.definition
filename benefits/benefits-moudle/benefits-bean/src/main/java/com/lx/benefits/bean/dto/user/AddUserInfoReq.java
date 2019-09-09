package com.lx.benefits.bean.dto.user;

import lombok.Data;

import java.util.List;

/**
 * User: fan
 * Date: 2019/03/25
 * Time: 22:34
 */
@Data
public class AddUserInfoReq {

    /**
     * 登录名
     */
    private String login_name;

    /**
     * 用户名
     */
    private String user_name;

    /**
     * 密码
     */
    private String password;

    /**
     * 部门id
     */
    private Long department_id;

    /**
     * 角色id
     */
    private List<Long> roleIds;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户email
     */
    private String email;

}
