package com.lx.benefits.mapper.ent;

import com.lx.benefits.bean.entity.ent.YianOrderInfo;

import java.util.List;
import java.util.Map;

public interface YianOrderInfoMapper{

    void batchUpdate(List<YianOrderInfo> list);

    int batchDelivery(List<YianOrderInfo> list);

    void batchInsert(List<YianOrderInfo> list);

    List<YianOrderInfo> queryByParam(Map<String,Object> map);

    Integer selectCount(Map<String,Object> map);

    List<YianOrderInfo> selectPageList(Map<String, Object> map);

    YianOrderInfo queryUniqueByObject(YianOrderInfo yianOrderInfo);

}
