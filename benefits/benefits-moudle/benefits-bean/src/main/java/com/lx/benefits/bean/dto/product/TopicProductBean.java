package com.lx.benefits.bean.dto.product;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TopicProductBean {
	@NotNull(message = "专题不能为空")
	private Integer topicId;
	private List<Long> spuCodes;
}
