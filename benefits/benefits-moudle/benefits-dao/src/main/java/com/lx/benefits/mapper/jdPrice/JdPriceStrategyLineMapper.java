package com.lx.benefits.mapper.jdPrice;

import com.lx.benefits.bean.entity.jdPrice.JdPriceStrategyLine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JdPriceStrategyLineMapper {

    List<JdPriceStrategyLine> getStrategyLine(@Param("strategyId") Long strategyId);

    int insert(JdPriceStrategyLine record);

    List<Long> getIdList(@Param("strategyId") Long strategyId);

    int delete(Long id);

    int deletes(@Param("strategyId") Long strategyId);

}