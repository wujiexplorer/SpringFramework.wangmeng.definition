package com.lx.benefits.service.agent;

import com.lx.benefits.bean.entity.agent.AgentBlackGoods;
import com.lx.benefits.bean.vo.agent.AgentBlackGoodsBean;

public interface AgentBlackGoodService {

	AgentBlackGoods getBlackGoods(Integer rebateType);

	AgentBlackGoodsBean getBlackGoodsWithName(Integer rebateType);

	void updateBlackGoods(Integer rebateType, AgentBlackGoodsBean agentBlackGoodsBean);

	AgentBlackGoods getBlackGoodsByEnterprId(Long enterprId);

}
