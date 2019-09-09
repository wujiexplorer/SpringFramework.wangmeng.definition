package com.lx.benefits.service.yianapi;

import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.entity.ent.ClientOrder;
import com.lx.benefits.bean.entity.ent.ClientOrderItem;

import java.util.List;

public interface IClientOrderManagementService {


    ResultInfo<ClientOrder> create(ClientOrder order, List<ClientOrderItem> items);

    ResultInfo<Integer> cancel(ClientOrder order);

    ResultInfo<ClientOrder> detail(ClientOrder order);
}
