package com.lx.benefits.bean.dto.power;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * User: fan
 * Date: 2019/03/19
 * Time: 17:46
 */
@Data
public class SysMenuRes {

    private Long id;

    private String menuCode;

    private String menuName;

    private Integer menuLevel;

    private Long menuParent;

    private String menuUrl;

    private Integer menuOrder;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Object menuList;

}
