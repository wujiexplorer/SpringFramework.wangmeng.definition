package com.lx.benefits.mapper.product;

import com.lx.benefits.bean.entity.product.ProductSpecInfo;
import com.lx.benefits.bean.entity.product.ProductSpecInfoCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductSpecInfoMapper {
    long countByExample(ProductSpecInfoCondition example);

    int deleteByExample(ProductSpecInfoCondition example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductSpecInfo record);

    int insertSelective(ProductSpecInfo record);

    List<ProductSpecInfo> selectByExample(ProductSpecInfoCondition example);

    ProductSpecInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductSpecInfo record, @Param("example") ProductSpecInfoCondition example);

    int updateByExample(@Param("record") ProductSpecInfo record, @Param("example") ProductSpecInfoCondition example);

    int updateByPrimaryKeySelective(ProductSpecInfo record);

    int updateByPrimaryKey(ProductSpecInfo record);
}