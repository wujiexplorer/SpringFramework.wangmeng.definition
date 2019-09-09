package com.lx.benefits.bean.dto.admin.operation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 活动位表 basis_activity
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
@Data
public class ActivityResDto {
	
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
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdTime;
	/** 排序 */
	private Integer sortNum;

}
