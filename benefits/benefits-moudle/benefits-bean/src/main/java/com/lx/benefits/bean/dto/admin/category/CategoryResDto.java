package com.lx.benefits.bean.dto.admin.category;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 分类 商品分类表 basis_category
 * 
 * @author gaosong
 * @date 2019-01-29
 */
@Data
public class CategoryResDto {

	/** 分类id */
	private Long id;

	/** 分类名称 */
	private String name;

	private String nameEn;

	/** 父id */
	private Long parentId;


	/** 状态;状态,1为可用,0为不可用 */
	private Integer status;

	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdTime;

	private Integer type;

}
