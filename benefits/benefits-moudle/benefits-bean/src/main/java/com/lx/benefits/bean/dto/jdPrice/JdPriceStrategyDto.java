package com.lx.benefits.bean.dto.jdPrice;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * User: fan
 * Date: 2019/03/04
 * Time: 23:30
 */
@Data
public class JdPriceStrategyDto {

    private Long id;

    private String name;

    private Byte type;

    private Byte deleted;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private List<JdPriceStrategyLineDto> strategy;

}
