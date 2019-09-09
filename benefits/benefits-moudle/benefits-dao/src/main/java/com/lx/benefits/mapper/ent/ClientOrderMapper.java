package com.lx.benefits.mapper.ent;

import java.util.List;

import com.lx.benefits.bean.entity.ent.ClientOrder;
import com.lx.benefits.bean.entity.ent.ClientOrderCondition;

public interface ClientOrderMapper {

    Long insert(ClientOrder order);

    Integer updateById(ClientOrder order);

    ClientOrder queryUniqueByObject(ClientOrder orderQuery);

    ClientOrder queryById(Long id);

    Integer queryByObjectCount(ClientOrder orderQuery);
    
    int countByExample(ClientOrderCondition example);
    
    List<ClientOrder> selectByExample(ClientOrderCondition example);
    
}
