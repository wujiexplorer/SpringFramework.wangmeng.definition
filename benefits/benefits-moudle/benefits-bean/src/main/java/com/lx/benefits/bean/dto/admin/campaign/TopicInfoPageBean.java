package com.lx.benefits.bean.dto.admin.campaign;

import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.product.ProductEntity;

public class TopicInfoPageBean extends PageResultBean<ProductEntity> {
	private Integer topicId;
	private String topicName;

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

}
