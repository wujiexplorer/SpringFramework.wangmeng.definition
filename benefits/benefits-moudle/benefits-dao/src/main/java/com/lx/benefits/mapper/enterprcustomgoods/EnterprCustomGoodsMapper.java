package com.lx.benefits.mapper.enterprcustomgoods;


import com.lx.benefits.bean.entity.enterprcustomgoods.EnterprCustomGoods;
import com.lx.benefits.bean.entity.enterprcustomgoods.EnterprCustomGoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnterprCustomGoodsMapper {
    int countByExample(EnterprCustomGoodsExample example);

    int deleteByExample(EnterprCustomGoodsExample example);

    int deleteByPrimaryKey(Long customId);

    int insert(EnterprCustomGoods record);

    int insertSelective(EnterprCustomGoods record);

    List<EnterprCustomGoods> selectByExample(EnterprCustomGoodsExample example);

    EnterprCustomGoods selectByPrimaryKey(Long customId);

    int updateByExampleSelective(@Param("record") EnterprCustomGoods record, @Param("example") EnterprCustomGoodsExample example);

    int updateByExample(@Param("record") EnterprCustomGoods record, @Param("example") EnterprCustomGoodsExample example);

    int updateByPrimaryKeySelective(EnterprCustomGoods record);

    int updateByPrimaryKey(EnterprCustomGoods record);

    List<EnterprCustomGoods> selectListByParam(EnterprCustomGoods record);

    EnterprCustomGoods selectByEnterprId(Long enterprId);
}