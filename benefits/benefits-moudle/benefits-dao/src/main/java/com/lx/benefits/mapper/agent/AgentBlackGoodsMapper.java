package com.lx.benefits.mapper.agent;

import com.lx.benefits.bean.entity.agent.AgentBlackGoods;
import com.lx.benefits.bean.entity.agent.AgentBlackGoodsCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgentBlackGoodsMapper {
    long countByExample(AgentBlackGoodsCondition example);

    int deleteByExample(AgentBlackGoodsCondition example);

    int insert(AgentBlackGoods record);

    int insertSelective(AgentBlackGoods record);

    List<AgentBlackGoods> selectByExample(AgentBlackGoodsCondition example);

    int updateByExampleSelective(@Param("record") AgentBlackGoods record, @Param("example") AgentBlackGoodsCondition example);

    int updateByExample(@Param("record") AgentBlackGoods record, @Param("example") AgentBlackGoodsCondition example);

	AgentBlackGoods getBlackGoodsByEnterprId(@Param("enterprId")Long enterprId);

	List<Long> getEnterprIdsByRebateType(@Param("rebateType")Integer rebateType);
}