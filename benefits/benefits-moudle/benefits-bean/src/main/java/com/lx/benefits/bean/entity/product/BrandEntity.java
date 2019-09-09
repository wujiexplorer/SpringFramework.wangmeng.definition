package com.lx.benefits.bean.entity.product;

import lombok.Data;

import java.util.Date;

/**
 * 品牌 商品品牌表 basis_brand
 * 
 * @author ruoyi
 * @date 2019-01-29
 */
@Data
public class BrandEntity {
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
	/** 是否热门 1：是   0：否 */
	private Integer isHost;
	/** 状态 状态,1为可用,0为不可用 */
	private Integer status;
	/** 创建人 */
	private String createdUser;
	/** 创建时间 */
	private Date createdTime;
	/** 更新人 */
	private String updateUser;
	/** 更新时间 */
	private Date updatedTime;
}
