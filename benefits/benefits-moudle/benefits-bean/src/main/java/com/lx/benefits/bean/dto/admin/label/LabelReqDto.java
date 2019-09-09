package com.lx.benefits.bean.dto.admin.label;

import lombok.Data;

import java.util.Date;

/**
 * 标签表 basis_label
 * 
 * @author ruoyi
 * @date 2019-01-30
 */
@Data
public class LabelReqDto {
	
	/** 标签id */
	private Long id;
	/** 标签名称 */
	private String name;
	/** 标签描述 */
	private String description;
	/** 标签排序 */
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
