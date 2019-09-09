package com.lx.benefits.bean.dto.admin.operation;

import lombok.Data;

/**
 * 专题活动 专题活动表 basis_activity_theme
 * 
 * @author ruoyi
 * @date 2019-02-01
 */
@Data
public class ActivityThemeReqDto {
	
	/** 自增id */
	private Integer id;
	/** 活动id */
	private Long activityId;
	/** 专题活动名称 */
	private String themeName;
	/** 题图 */
	private String themeUrl;
	/** 页内图 */
	private String themeTopUrls;
	/** 绑定专题id */
	private Long topicId;
	/** 展示类型 1 短展位 2长展位 */
	private Integer showType;
	/** 排序 */
	private Integer sortNum;
	/** 状态 状态 0-无效 1-有效 */
	private Integer status;
	/**专栏描述*/
	private String remark;
}
