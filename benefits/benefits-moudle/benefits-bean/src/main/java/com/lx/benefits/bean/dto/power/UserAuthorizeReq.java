package com.lx.benefits.bean.dto.power;

import lombok.Data;

import java.util.List;

/**
 * User: fan
 * Date: 2019/03/19
 * Time: 22:57
 */
@Data
public class UserAuthorizeReq {

    private Long userId;

    private List<Long> roleIds;

}
