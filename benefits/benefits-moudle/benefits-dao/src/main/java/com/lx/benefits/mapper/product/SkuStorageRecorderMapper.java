package com.lx.benefits.mapper.product;

import com.lx.benefits.bean.entity.product.SkuStorageRecorder;
import com.lx.benefits.bean.entity.product.SkuStorageRecorderCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkuStorageRecorderMapper {
    long countByExample(SkuStorageRecorderCondition example);

    int deleteByExample(SkuStorageRecorderCondition example);

    int deleteByPrimaryKey(Integer id);

    int insert(SkuStorageRecorder record);

    int insertSelective(SkuStorageRecorder record);

    List<SkuStorageRecorder> selectByExample(SkuStorageRecorderCondition example);

    SkuStorageRecorder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SkuStorageRecorder record, @Param("example") SkuStorageRecorderCondition example);

    int updateByExample(@Param("record") SkuStorageRecorder record, @Param("example") SkuStorageRecorderCondition example);

    int updateByPrimaryKeySelective(SkuStorageRecorder record);

    int updateByPrimaryKey(SkuStorageRecorder record);
}