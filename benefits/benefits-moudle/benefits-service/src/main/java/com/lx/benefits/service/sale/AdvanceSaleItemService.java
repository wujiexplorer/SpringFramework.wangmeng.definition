package com.lx.benefits.service.sale;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.entity.sale.AdvanceSaleExample;
import com.lx.benefits.bean.entity.sale.AdvanceSaleItem;

import java.util.List;
import java.util.Map;

/**
 * @author unknow on 2018-12-26 17:07.
 */
public interface AdvanceSaleItemService {

    Long insertSelective(AdvanceSaleItem record);

    Integer update(AdvanceSaleItem orderImportItem, AdvanceSaleExample example);

    List<AdvanceSaleItem> selectListByCampaignId(Long campaignId);

    JSONObject submitOrder(Long campaignId);

    List<AdvanceSaleItem> selectDownExcel(Long campaignId);

    JSONObject submitAdvance(Map<String,Object> params);
}