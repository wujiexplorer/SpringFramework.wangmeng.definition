package com.lx.benefits.mapper.yx;

import com.lx.benefits.bean.dto.yx.PrdYxBaseItemReq;
import com.lx.benefits.bean.entity.yx.PrdYxBaseItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PrdYxBaseItemMapper {

    int delete();

    int insert(PrdYxBaseItem record);

    int insertSelective(PrdYxBaseItem record);

    PrdYxBaseItem selectByPrimaryKey(Long spuCode);

    List<PrdYxBaseItem> getYxItemList(PrdYxBaseItemReq record);

    Integer getYxItemListCount(PrdYxBaseItemReq record);

    int updateByPrimaryKeySelective(PrdYxBaseItem record);

    int updateByPrimaryKey(PrdYxBaseItem record);

    List<PrdYxBaseItem> getInfoBySku(@Param("sku") List<String> param);
}