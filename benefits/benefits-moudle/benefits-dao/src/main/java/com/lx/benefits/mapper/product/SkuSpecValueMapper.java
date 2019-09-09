package com.lx.benefits.mapper.product;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.lx.benefits.bean.dto.spec.SkuSpectValueBean;
import com.lx.benefits.bean.entity.product.SkuSpecValue;
import com.lx.benefits.bean.entity.product.SkuSpecValueCondition;

public interface SkuSpecValueMapper {
    long countByExample(SkuSpecValueCondition example);

    int deleteByExample(SkuSpecValueCondition example);

    int deleteByPrimaryKey(Integer id);

    int insert(SkuSpecValue record);

    int insertSelective(SkuSpecValue record);

    List<SkuSpecValue> selectByExample(SkuSpecValueCondition example);

    SkuSpecValue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SkuSpecValue record, @Param("example") SkuSpecValueCondition example);

    int updateByExample(@Param("record") SkuSpecValue record, @Param("example") SkuSpecValueCondition example);

    int updateByPrimaryKeySelective(SkuSpecValue record);

    int updateByPrimaryKey(SkuSpecValue record);
    
    List<SkuSpectValueBean> seleSkuOneSpecValue(@Param("skuIds")List<Long> skuIds, @Param("specId")Integer specId);

   	List<SkuSpectValueBean> selectSkuTwoSpecValue(@Param("skuIds")List<Long> skuIds, @Param("firstSpecId")Integer firstSpecId, @Param("secondSpecId")Integer secondSpecId);

	List<SkuSpecValue> getSkuSpecValue(@Param("skuIds")List<Long> skuIds);

}