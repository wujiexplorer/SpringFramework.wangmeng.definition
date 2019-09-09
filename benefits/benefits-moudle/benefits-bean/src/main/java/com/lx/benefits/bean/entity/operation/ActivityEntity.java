package com.lx.benefits.bean.entity.operation;

import lombok.Data;

import java.util.Date;

/**
 * 活动位表 basis_activity
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
@Data
public class ActivityEntity{
	
	/** 自增id */
	private Long id;
	/** 活动位名称 */
	private String name;
	/** 活动图标 */
	private String imgUrl;
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
	/** h5跳转链接 */
	private String jumpUrl;
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
	/** 排序 */
	private Integer sortNum;
}
