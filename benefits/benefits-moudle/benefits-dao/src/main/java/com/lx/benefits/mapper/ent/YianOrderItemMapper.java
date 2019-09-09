package com.lx.benefits.mapper.ent;


import com.lx.benefits.bean.entity.ent.YianOrderItem;

import java.util.List;
import java.util.Map;

public interface YianOrderItemMapper {

    void batchInsert(List<YianOrderItem> list);

    int deleteByParam(Map<String,Object> map);

    List<YianOrderItem> queryByObject(YianOrderItem itemq);

}
