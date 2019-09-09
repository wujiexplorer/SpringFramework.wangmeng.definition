package com.lx.benefits.service.yianapi;

import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.entity.ent.YianOrderItem;

import java.util.List;

public interface YianOrderItemService {

    ResultInfo<List<YianOrderItem>> queryByObject( YianOrderItem itemq);
}
