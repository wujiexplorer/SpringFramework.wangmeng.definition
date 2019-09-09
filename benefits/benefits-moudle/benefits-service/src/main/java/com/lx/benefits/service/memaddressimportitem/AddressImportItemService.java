package com.lx.benefits.service.memaddressimportitem;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.address.AddressImportItemQueryDto;
import com.lx.benefits.bean.entity.memaddressimportitem.AddressImportItem;
import com.lx.benefits.bean.entity.memaddressimportitem.AddressImportItemExample;

/**
 * @author unknow on 2018-12-26 17:07.
 */
public interface AddressImportItemService {

    Long insertSelective(AddressImportItem record);

    JSONObject selectByExample(AddressImportItemQueryDto req);

    Integer update(AddressImportItem addressImportItem, AddressImportItemExample example);

}