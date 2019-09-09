package com.lx.benefits.mapper.prditeminfo;

import com.lx.benefits.bean.entity.prditeminfo.ItemInfo;
import com.lx.benefits.bean.entity.prditeminfo.ItemInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemInfoMapper {
    int countByExample(ItemInfoExample example);

    int deleteByExample(ItemInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ItemInfo record);

    int insertSelective(ItemInfo record);

    List<ItemInfo> selectByExampleWithBLOBs(ItemInfoExample example);

    List<ItemInfo> selectByExample(ItemInfoExample example);

    ItemInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ItemInfo record, @Param("example") ItemInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ItemInfo record, @Param("example") ItemInfoExample example);

    int updateByExample(@Param("record") ItemInfo record, @Param("example") ItemInfoExample example);

    int updateByPrimaryKeySelective(ItemInfo record);

    int updateByPrimaryKeyWithBLOBs(ItemInfo record);

    int updateByPrimaryKey(ItemInfo record);
}