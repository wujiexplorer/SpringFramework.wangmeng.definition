package com.lx.benefits.dao.orderimportitem;


import com.lx.benefits.bean.entity.orderimportitem.OrderImportItem;
import com.lx.benefits.bean.entity.orderimportitem.OrderImportItemExample;

import java.util.List;

/**
 * @author unknow on 2018-12-26 16:48.
 */
public interface OrderImportItemDao {
    Long insertSelective(OrderImportItem record);

    List<OrderImportItem> selectByExample(OrderImportItemExample example);

    int countByExample(OrderImportItemExample example);

    Integer update(OrderImportItem record, OrderImportItemExample example);
}
