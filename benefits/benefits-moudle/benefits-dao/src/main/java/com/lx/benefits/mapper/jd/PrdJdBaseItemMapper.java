package com.lx.benefits.mapper.jd;

import com.lx.benefits.bean.dto.jd.PrdJdBaseItemReq;
import com.lx.benefits.bean.entity.jd.PrdJdBaseItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PrdJdBaseItemMapper {

    int batchInsert(List<PrdJdBaseItem> record);

    int insertSelective(PrdJdBaseItem record);

    List<PrdJdBaseItem> getJdItemList(PrdJdBaseItemReq record);

    int getJdItemListCount(PrdJdBaseItemReq record);

    int updateByPrimaryKeySelective(PrdJdBaseItem record);

    int updateByPrimaryKey(PrdJdBaseItem record);

    List<PrdJdBaseItem> queryByStatusAndRate(Map<String,Object> param);

    List<PrdJdBaseItem> getInfoBySku(@Param("sku") List<String> param);

    int delete();

    int deleteBySku(@Param("sku") String sku);

}