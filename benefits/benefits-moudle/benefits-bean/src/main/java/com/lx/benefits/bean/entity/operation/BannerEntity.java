package com.lx.benefits.bean.entity.operation;

import lombok.Data;

import java.util.Date;

/**
 * banner表 basis_banner
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
@Data
public class BannerEntity{
	
	/** 自增id */
	private Long id;
	/** banner名称 */
	private String name;
	/** 描述 */
	private String description;
	/** 图片地址 */
	private String picAddress;
	/** 所属专题 */
	private Long topicId;
	/** 排序 */
	private Integer sort;
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
