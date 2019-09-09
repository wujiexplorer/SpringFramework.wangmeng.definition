package com.lx.benefits.mapper.jdPrice;

import com.lx.benefits.bean.dto.jdPrice.JdPriceStrategyReq;
import com.lx.benefits.bean.entity.jdPrice.JdPriceStrategy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JdPriceStrategyMapper {

    int delete(Long id);

    int insert(JdPriceStrategy record);

    JdPriceStrategy getStrategyById(Long id);

    List<JdPriceStrategy> getStrategyList(JdPriceStrategyReq record);

    Integer getStrategyListCount(JdPriceStrategyReq record);

    int update(JdPriceStrategy record);

}