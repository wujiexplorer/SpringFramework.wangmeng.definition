package com.lx.benefits.mapper.product;

import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.ProductTopic;
import com.lx.benefits.bean.entity.product.ProductTopicCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductTopicMapper {
    long countByExample(ProductTopicCondition example);

    int deleteByExample(ProductTopicCondition example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductTopic record);

    int insertSelective(ProductTopic record);

    List<ProductTopic> selectByExample(ProductTopicCondition example);

    ProductTopic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductTopic record, @Param("example") ProductTopicCondition example);

    int updateByExample(@Param("record") ProductTopic record, @Param("example") ProductTopicCondition example);

    int updateByPrimaryKeySelective(ProductTopic record);

    int updateByPrimaryKey(ProductTopic record);

	List<Long> selectTopicSpuCodes(@Param("topicIds")List<Integer> topicIds);

	void batchAdd(@Param("topicId")Integer topicId, @Param("spuCodes")List<Long> spuCodes);

	List<ProductEntity> getTopicProducts(Integer topicId);

	void sortProduct(@Param("topicId")Integer topicId, @Param("spuCodes")List<Long> spuCodes);
}