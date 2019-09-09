package com.lx.benefits.service.memaddressimport;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.FLPageDto;
import com.lx.benefits.bean.entity.memadressimport.AddressImportInfo;

/**
 * @author unknow on 2018-12-26 17:07.
 */
public interface AddressImportInfoService {

    Long insertSelective(AddressImportInfo record);

    JSONObject selectByExample(FLPageDto req);

    Integer update(AddressImportInfo addressImportInfo);

}