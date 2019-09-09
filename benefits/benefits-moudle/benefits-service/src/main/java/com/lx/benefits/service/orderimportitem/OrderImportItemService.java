package com.lx.benefits.service.orderimportitem;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.order.OrderImportItemQueryDto;
import com.lx.benefits.bean.entity.orderimportitem.OrderImportItem;
import com.lx.benefits.bean.entity.orderimportitem.OrderImportItemExample;

/**
 * @author unknow on 2018-12-26 17:07.
 */
public interface OrderImportItemService {

    Long insertSelective(OrderImportItem record);

    JSONObject selectByExample(OrderImportItemQueryDto req);

    Integer update(OrderImportItem orderImportItem, OrderImportItemExample example);

}