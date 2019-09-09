package com.lx.benefits.service.product;

import java.util.List;

import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.ProductTopic;
import com.lx.benefits.bean.entity.product.ProductTopicCondition;

/**
 * 商品专题关系 服务层
 * 
 * @author ruoyi
 * @date 2019-02-13
 */
public interface ProductTopicService {

	/**
	 * 新增 商品专题关系
	 * 
	 * @param topic
	 *            商品专题关系信息
	 * @return 结果
	 */
	int insert(ProductTopic productTopic);

	/**
	 * 
	 * @param topicIdsList 主题ID列表
	 * @return
	 */
	List<ProductTopic> getProductTopic(List<Integer> topicIdsList);

	long countByCondition(ProductTopicCondition productTopicCondition);

	void addProductToTopic(Integer topicId, List<Long> spuCodeList);

	int deleteTopicProduct(Integer topicId, List<Long> spuCodes);

	List<ProductEntity> getTopicProducts(Integer topicId);

	int updateSort(Integer topicId, List<Long> spuCodes);

	List<Long> getTopicSpuCodes(List<Integer> topicIdsList);

}
