package com.lx.benefits.service.product.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.dto.admin.product.ProductQueryParam;
import com.lx.benefits.bean.entity.operation.TopicEntity;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.ProductTopic;
import com.lx.benefits.bean.entity.product.ProductTopicCondition;
import com.lx.benefits.mapper.product.ProductMapper;
import com.lx.benefits.mapper.product.ProductTopicMapper;
import com.lx.benefits.service.operation.TopicService;
import com.lx.benefits.service.product.ProductTopicService;

@Service
public class ProductTopicServiceImpl implements ProductTopicService {

	@Autowired
	private ProductTopicMapper productTopicMapper;
	@Autowired
	private TopicService topicService;
	@Autowired
	private ProductMapper productMapper;

	@Override
	public int insert(ProductTopic productTopic) {
		ProductTopic record = new ProductTopic();
		record.setSpuCode(productTopic.getSpuCode());
		record.setTopicId(productTopic.getTopicId());
		record.setCreatedTime(new Date());
		return productTopicMapper.insertSelective(record);
	}

	@Override
	public List<ProductEntity> getTopicProducts(Integer topicId) {
		return productTopicMapper.getTopicProducts(topicId);
	}

	@Override
	public List<ProductTopic> getProductTopic(List<Integer> topicIdsList) {
		if (CollectionUtils.isEmpty(topicIdsList)) {
			return Collections.emptyList();
		}
		ProductTopicCondition example = new ProductTopicCondition();
		example.createCriteria().andTopicIdIn(topicIdsList);
		return productTopicMapper.selectByExample(example);
	}

	@Override
	public long countByCondition(ProductTopicCondition productTopicCondition) {
		return productTopicMapper.countByExample(productTopicCondition);
	}

	@Override
	public void addProductToTopic(Integer topicId, List<Long> spuCodeList) {
		if (topicId == null) {
			throw new BusinessException("专题不能为空!");
		}
		if (CollectionUtils.isEmpty(spuCodeList)) {
			throw new BusinessException("商品列表不能为空!");
		}
		TopicEntity topicEntity = topicService.selectById(topicId);
		if (topicEntity == null) {
			throw new BusinessException("专题不存在!");
		}
		ProductTopicCondition example = new ProductTopicCondition();
		example.createCriteria().andSpuCodeIn(spuCodeList).andTopicIdEqualTo(topicId);
		List<ProductTopic> productTopics = productTopicMapper.selectByExample(example);
		List<Long> distinctSpuCodes;
		if (!CollectionUtils.isEmpty(productTopics)) {
			List<Long> existSpuCodes = productTopics.stream().map(ProductTopic::getSpuCode).collect(Collectors.toList());
			distinctSpuCodes = spuCodeList.parallelStream().distinct().filter(item -> !existSpuCodes.contains(item)).collect(Collectors.toList());
		} else {
			distinctSpuCodes = spuCodeList.parallelStream().distinct().collect(Collectors.toList());
		}
		if (CollectionUtils.isEmpty(distinctSpuCodes)) {
			return;
		}
		ProductQueryParam queryParam = new ProductQueryParam();
		queryParam.setSpuCodeList(distinctSpuCodes);
		int count = productMapper.selectCount(queryParam);
		if (count != distinctSpuCodes.size()) {
			throw new BusinessException("添加失败,存在未知的商品!");
		}
		productTopicMapper.batchAdd(topicId, distinctSpuCodes);
	}

	@Override
	public int deleteTopicProduct(Integer topicId, List<Long> spuCodes) {
		if (topicId == null || CollectionUtils.isEmpty(spuCodes)) {
			return 0;
		}
		ProductTopicCondition example = new ProductTopicCondition();
		example.createCriteria().andTopicIdEqualTo(topicId).andSpuCodeIn(spuCodes);
		return productTopicMapper.deleteByExample(example);
	}

	@Transactional
	@Override
	public int updateSort(Integer topicId, List<Long> spuCodes) {
		Assert.notNull(topicId, "topicId can not be null !");
		if (CollectionUtils.isEmpty(spuCodes)) {
			return 0;
		}
		productTopicMapper.sortProduct(topicId, spuCodes);
		return 1;
	}

	@Override
	public List<Long> getTopicSpuCodes(List<Integer> topicIdsList) {
		if(CollectionUtils.isEmpty(topicIdsList)) {
			return Collections.emptyList();
		}
		return productTopicMapper.selectTopicSpuCodes(topicIdsList);
	}

}
