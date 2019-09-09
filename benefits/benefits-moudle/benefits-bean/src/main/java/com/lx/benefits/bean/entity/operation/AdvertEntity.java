package com.lx.benefits.bean.entity.operation;

import lombok.Data;

import java.util.Date;

/**
 * 广告位 表 basis_advert
 * 
 * @author ruoyi
 * @date 2019-01-30
 */
@Data
public class AdvertEntity{
	
	/** 自增id */
	private Long id;
	/** 广告名称 */
	private String advertName;
	/** 广告位置 */
	private Integer position;
	/** 开始时间 */
	private Date beginTime;
	/** 到期时间 */
	private Date endTime;
	/** 广告图片 */
	private String advertImage;
	/** 广告链接 */
	private String advertLink;
	/** 状态 状态 0-无效 1-有效 */
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
