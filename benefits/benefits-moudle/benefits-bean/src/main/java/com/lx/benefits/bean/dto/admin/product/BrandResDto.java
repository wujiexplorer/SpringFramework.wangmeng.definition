package com.lx.benefits.bean.dto.admin.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author unknow on 2018-12-26 18:17.
 */
@Data
public class BrandResDto  {

    /** 品牌id */
    private Long id;

    /** 品牌名称 */
    private String name;

    /** 品牌英文 */
    private String nameEn;

    /** 简称 */
    private String shortName;

    /** 官网地址 */
    private String website;

    /** 品牌logo图片 */
    private String logoAddress;

    /** 主图 */
    private String headAddress;

    /** 描述 */
    private String description;

    /** 是否热门;1：是   0：否 */
    private Integer isHost;

    /** 状态;状态,1为可用,0为不可用 */
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;
    
}
