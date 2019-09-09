package com.lx.benefits.dao.orderimportinfo;

import com.lx.benefits.bean.entity.orderimportinfo.OrderImportInfo;
import com.lx.benefits.bean.entity.orderimportinfo.OrderImportInfoExample;

import java.util.List;

/**
 * @author unknow on 2018-12-26 16:48.
 */
public interface OrderImportInfoDao {
    Long insertSelective(OrderImportInfo record);

    List<OrderImportInfo> selectByExample(OrderImportInfoExample example);

    int countByExample(OrderImportInfoExample example);

    Integer update(OrderImportInfo record);
}
