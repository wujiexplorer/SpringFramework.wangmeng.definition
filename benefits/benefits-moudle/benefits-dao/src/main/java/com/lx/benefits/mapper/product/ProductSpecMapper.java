package com.lx.benefits.mapper.product;

import com.lx.benefits.bean.entity.product.ProductSpec;
import com.lx.benefits.bean.entity.product.ProductSpecCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductSpecMapper {
    long countByExample(ProductSpecCondition example);

    int deleteByExample(ProductSpecCondition example);

    int deleteByPrimaryKey(Integer specId);

    int insert(ProductSpec record);

    int insertSelective(ProductSpec record);

    List<ProductSpec> selectByExample(ProductSpecCondition example);

    ProductSpec selectByPrimaryKey(Integer specId);

    int updateByExampleSelective(@Param("record") ProductSpec record, @Param("example") ProductSpecCondition example);

    int updateByExample(@Param("record") ProductSpec record, @Param("example") ProductSpecCondition example);

    int updateByPrimaryKeySelective(ProductSpec record);

    int updateByPrimaryKey(ProductSpec record);
}