package com.lx.benefits.service.orderimport;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.enterprise.EnterpriseQueryReqDto;
import com.lx.benefits.bean.entity.orderimportinfo.OrderImportInfo;

/**
 * @author unknow on 2018-12-26 17:07.
 */
public interface OrderImportInfoService {

    Long insertSelective(OrderImportInfo record);

    JSONObject selectByExample(EnterpriseQueryReqDto req);

    Integer update(OrderImportInfo orderImportInfo);

}