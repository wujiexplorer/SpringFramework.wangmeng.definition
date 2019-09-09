package com.lx.benefits.dao.enterprfestivalpacket;



import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacket;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacketExample;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface EnterprFestivalDao {

    Long insert(EnterprFestivalPacket enterprFestival);


    Integer update(EnterprFestivalPacket enterprFestival, EnterprFestivalPacketExample example);

    List<EnterprFestivalPacket> find(EnterprFestivalPacketExample example);


    Integer count(EnterprFestivalPacketExample example);

    EnterprFestivalPacket selectByPrimaryKey(Long campaignId);
}
