package com.lx.benefits.bean.dto.jd;

import lombok.Data;

import java.io.Serializable;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 18:23
 */
@Data
public class JDItemPool implements Serializable {

    private static final long serialVersionUID = -8680116455628735923L;

    private String name;

    private Integer page_num;

}
