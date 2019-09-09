package com.lx.benefits.mapper.yx;

import com.lx.benefits.bean.entity.yx.PrdYxBaseItemSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PrdYxBaseItemSkuMapper {
    int delete();

    int insert(PrdYxBaseItemSku record);

    int insertSelective(PrdYxBaseItemSku record);

    PrdYxBaseItemSku selectByPrimaryKey(Integer id);

    List<PrdYxBaseItemSku> getSku(@Param("spu") String spu);

    int updateByPrimaryKeySelective(PrdYxBaseItemSku record);

    int updateByPrimaryKey(PrdYxBaseItemSku record);
}