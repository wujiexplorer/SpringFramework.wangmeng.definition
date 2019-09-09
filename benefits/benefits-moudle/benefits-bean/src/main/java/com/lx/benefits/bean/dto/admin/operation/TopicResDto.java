package com.lx.benefits.bean.dto.admin.operation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 专题 专题表 basis_topic
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
@Data
public class TopicResDto{
	
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
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdTime;
	/** 专题商品数量*/
	private Integer num;

}
