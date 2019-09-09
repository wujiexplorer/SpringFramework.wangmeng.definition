package com.lx.benefits.dao.memaddressimportitem;

import com.lx.benefits.bean.entity.memaddressimportitem.AddressImportItem;
import com.lx.benefits.bean.entity.memaddressimportitem.AddressImportItemExample;

import java.util.List;

/**
 * @author unknow on 2018-12-26 16:48.
 */
public interface AddressImportItemDao {
    Long insertSelective(AddressImportItem record);

    List<AddressImportItem> selectByExample(AddressImportItemExample example);

    int countByExample(AddressImportItemExample example);

    Integer update(AddressImportItem record, AddressImportItemExample example);
}
