package com.lx.benefits.bean.entity.operation;

import lombok.Data;

import java.util.Date;

/**
 * 专题 专题表 basis_topic
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
@Data
public class TopicEntity{
	
	/** 自增id */
	private Long id;
	/** 专题名称 */
	private String name;
	/** 排序 */
	private Integer sortNum;
	/** 专题描述 */
	private String description;
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

	private Integer num;

}
