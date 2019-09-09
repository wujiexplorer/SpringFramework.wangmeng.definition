package com.lx.benefits.bean.dto.user;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * User: fan
 * Date: 2019/03/25
 * Time: 17:04
 */
@Data
public class UserInfoReq {

    /**
     * 登录名
     */
    private String login_name;

    /**
     * 用户名
     */
    private String user_name;

    /**
     * 部门id
     */
    private Long department_id;

    /**
     * 用户手机号
     */
    private String mobile;

    private Integer status;

    /**
     * 用户email
     */
    private String email;

    private Integer page = 1;

    private Integer pageSize = 10;

}
