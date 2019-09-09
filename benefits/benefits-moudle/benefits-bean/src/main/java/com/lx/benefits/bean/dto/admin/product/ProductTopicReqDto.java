package com.lx.benefits.bean.dto.admin.product;

import lombok.Data;

import java.util.Date;

/**
 *  商品专题关系表 product_topic
 * 
 * @author ruoyi
 * @date 2019-02-13
 */
@Data
public class ProductTopicReqDto {
	
	/** 自增id */
	private Long id;
	/** spu编码 */
	private Long spuCode;
	/** 专题id */
	private Long topicId;
	/** 状态 状态 0-无效 1-有效 */
	private Integer status;
	/** 创建时间 */
	private Date createdTime;
	/** 更新时间 */
	private Date updatedTime;

	private String spuCodes;

	private Integer sortNum;

}
