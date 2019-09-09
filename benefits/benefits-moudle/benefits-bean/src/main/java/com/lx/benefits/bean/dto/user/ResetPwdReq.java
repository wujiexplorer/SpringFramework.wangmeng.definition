package com.lx.benefits.bean.dto.user;

import lombok.Data;

/**
 * User: fan
 * Date: 2019/03/25
 * Time: 22:25
 */
@Data
public class ResetPwdReq {

    private Long userId;

    private String pwd;
}
