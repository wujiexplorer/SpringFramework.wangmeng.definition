package com.lx.benefits.mapper.product;

import com.lx.benefits.bean.entity.product.ProductSetting;
import com.lx.benefits.bean.entity.product.ProductSettingCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductSettingMapper {
    long countByExample(ProductSettingCondition example);

    int deleteByExample(ProductSettingCondition example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductSetting record);

    int insertSelective(ProductSetting record);

    List<ProductSetting> selectByExample(ProductSettingCondition example);

    ProductSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductSetting record, @Param("example") ProductSettingCondition example);

    int updateByExample(@Param("record") ProductSetting record, @Param("example") ProductSettingCondition example);

    int updateByPrimaryKeySelective(ProductSetting record);

    int updateByPrimaryKey(ProductSetting record);
}