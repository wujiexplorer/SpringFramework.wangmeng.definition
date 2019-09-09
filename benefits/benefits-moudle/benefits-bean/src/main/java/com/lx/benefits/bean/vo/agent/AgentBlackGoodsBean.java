package com.lx.benefits.bean.vo.agent;

import java.util.List;

import com.lx.benefits.bean.entity.operation.TopicEntity;
import com.lx.benefits.bean.entity.product.BrandEntity;
import com.lx.benefits.bean.entity.product.CategoryEntity;

import lombok.Data;

@Data
public class AgentBlackGoodsBean {
	private List<Long> categoryIdsList;
	private List<Long> brandIdsList;
	private List<Long> goodIdsList;
	private List<CategoryEntity> categoryList;
	private List<BrandEntity> brandList;
	private List<TopicEntity> goodList;
}
