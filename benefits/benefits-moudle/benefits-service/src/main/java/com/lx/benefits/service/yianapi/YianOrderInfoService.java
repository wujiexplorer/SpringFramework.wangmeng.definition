package com.lx.benefits.service.yianapi;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.dto.yianapi.YianImportOrderDto;
import com.lx.benefits.bean.entity.ent.YianOrderInfo;

import java.util.List;
import java.util.Map;

public interface YianOrderInfoService {

    ResultInfo<YianOrderInfo>  queryUniqueByObject(YianOrderInfo infoq);

    JSONObject importOrder(YianImportOrderDto req);

    JSONObject importOrderDelivery(YianImportOrderDto req);

    Integer selectCount(Map<String,Object> query);

    List<YianOrderInfo> selectPageList(Map<String,Object> map);
}
