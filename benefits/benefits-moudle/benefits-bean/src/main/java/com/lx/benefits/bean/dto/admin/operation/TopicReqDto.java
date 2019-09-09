package com.lx.benefits.bean.dto.admin.operation;

import lombok.Data;

/**
 * 专题 专题表 basis_topic
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
@Data
public class TopicReqDto {
	
	/** 自增id */
	private Long id;
	/** 排序 */
	private Integer sortNum;
	/** 专题名称 */
	private String name;
	/** 专题描述 */
	private String description;
	/** 状态 状态 0-无效 1-有效 */
	private Integer status;

}
