package com.lx.benefits.bean.dto.admin.operation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * banner表 basis_banner
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
@Data
public class BannerResDto {
	
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
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdTime;

}
