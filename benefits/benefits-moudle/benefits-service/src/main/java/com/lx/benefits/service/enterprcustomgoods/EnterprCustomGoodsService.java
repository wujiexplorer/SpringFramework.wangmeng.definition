package com.lx.benefits.service.enterprcustomgoods;


import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.customized.GoodsModuleInfoDto;
import com.lx.benefits.bean.dto.order.QueryFreightVO;
import com.lx.benefits.bean.entity.enterprcustomgoods.EnterprCustomGoods;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface EnterprCustomGoodsService {


    Long insertGoods(GoodsModuleInfoDto req);

    GoodsModuleInfoDto findById(Long enterprId);
    
    GoodsModuleInfoDto findByIdWithAgent(Long enterprId);

    GoodsModuleInfoDto findByIdWithAgentNoCache(Long enterprId);

    List<EnterprCustomGoods>  selectListByParam(EnterprCustomGoods record);

    /**
     * 校验是否可购买
     * @param enterprId
     * @param productEntity
     * @return
     */
	boolean checkAvailable(Long enterprId, ProductEntity productEntity, SkuEntity skuEntity);
	
	JSONObject queryStockAndFreight(QueryFreightVO qeueryFreightVO);

	boolean checkAvailable(Long campaginId, Long enterprId, ProductEntity productEntity, SkuEntity skuEntity);
}
