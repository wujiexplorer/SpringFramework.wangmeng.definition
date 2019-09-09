package com.lx.benefits.service.yianapi;

import java.util.List;

import com.apollo.starter.web.utils.PageResult;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.entity.ent.ClientOrder;
import com.lx.benefits.bean.entity.ent.ClientOrderCondition;
import com.lx.benefits.bean.entity.ent.ClientOrderItem;

public interface ClientOrderService {

    ResultInfo<ClientOrder> submit(ClientOrder order, List<ClientOrderItem> itemList);

    ResultInfo<Integer> cancel(ClientOrder order);

    ClientOrder queryUniqueByObject(ClientOrder orderQuery);

    Integer queryByObjectCount(ClientOrder orderQuery);

    Integer updateById(ClientOrder orderQuery);

    ClientOrder queryById(Long id);

    ClientOrder insert(ClientOrder orderQuery);

	PageResult<ClientOrder> getOrderListByExample(Integer pageNo, Integer pageSize, ClientOrderCondition clientOrderCondition);
}
