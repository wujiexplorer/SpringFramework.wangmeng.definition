package com.lx.benefits.bean.entity.power;

import lombok.Data;

import java.util.Date;

@Data
public class SysMenu {
    private Long id;

    private String menuCode;

    private String menuName;

    private Integer menuLevel = 0;

    private Long menuParent = 0L;

    private String menuUrl;

    private Integer menuOrder;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

}