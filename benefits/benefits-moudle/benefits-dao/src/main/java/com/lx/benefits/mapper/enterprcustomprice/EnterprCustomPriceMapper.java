package com.lx.benefits.mapper.enterprcustomprice;


import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.enterprcustomprice.EnterprCustomPrice;
import com.lx.benefits.bean.entity.enterprcustomprice.EnterprCustomPriceExample;
import com.lx.benefits.bean.entity.product.SkuEntity;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EnterprCustomPriceMapper {
    int countByExample(EnterprCustomPriceExample example);

    int deleteByExample(EnterprCustomPriceExample example);

    int deleteByPrimaryKey(Long customId);

    int insert(EnterprCustomPrice record);

    int insertSelective(EnterprCustomPrice record);

    List<EnterprCustomPrice> selectByExample(EnterprCustomPriceExample example);

    EnterprCustomPrice selectByPrimaryKey(Long customId);

    int updateByPrimaryKeySelective(EnterprCustomPrice record);

    int updateByExampleSelective(@Param("record") EnterprCustomPrice record, @Param("example") EnterprCustomPriceExample example);

    int updateByExample(@Param("record") EnterprCustomPrice record, @Param("example") EnterprCustomPriceExample example);

    int updateByPrimaryKey(EnterprCustomPrice record);

    int insertBatch(List<EnterprCustomPrice> list);

    int updateBatch(List<EnterprCustomPrice> list);

    List<EnterprCustomPrice> selectPageList(EnterprCustomPriceExample example);

    List<EnterprCustomPrice> queryMapByParam(Map<String,Object> query);

    EnterprCustomPrice selectByParam(EnterprCustomPriceExample example);

	int countPCFeatureList(@Param("enterprId")Long enterpriseId);

	List<SkuEntity> getPCFeatureListByPage(@Param("enterprId")Long enterpriseId, @Param("pageBean")PageBean pageBean);
}

