package com.lx.benefits.bean.dto.admin.product;

import lombok.Data;

/**
 * 品牌实体
 * @author gaosong
 * @date 2018-12-26 18:17.
 */
@Data
public class BrandQueryReqDto {

    /** 品牌id */
    private Long id;

    /** 品牌名称 */
    private String name;

    /** 品牌英文 */
    private String nameEn;

    /** 官网地址 */
    private String website;

    /** logo图 */
    private String logoAddress;

    /** 主图 */
    private String headAddress;

    /** 描述信息 */
    private String description;

    /** 热门;是否热门 1：是0否 */
    private String isHot;

    /** 状态;状态,1为可用,0为不可用*/

    private Integer status;

    /** 创建人 */
    private String createdUser;

    /** 创建时间 */
    private String createdTime;

    /** 更新人 */
    private String updatedUser;

    /** 更新时间 */
    private String updatedTime;

}
