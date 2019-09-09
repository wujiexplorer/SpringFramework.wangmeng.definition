package com.lx.benefits.mapper.enterprfestivalpacket;

import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacket;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacketExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnterprFestivalPacketMapper {
    int countByExample(EnterprFestivalPacketExample example);

    int deleteByExample(EnterprFestivalPacketExample example);

    int deleteByPrimaryKey(Long campaignId);

    int insert(EnterprFestivalPacket record);

    int insertSelective(EnterprFestivalPacket record);

    List<EnterprFestivalPacket> selectByExample(EnterprFestivalPacketExample example);

    EnterprFestivalPacket selectByPrimaryKey(Long campaignId);

    int updateByExampleSelective(@Param("record") EnterprFestivalPacket record, @Param("example") EnterprFestivalPacketExample example);

    int updateByExample(@Param("record") EnterprFestivalPacket record, @Param("example") EnterprFestivalPacketExample example);

    int updateByPrimaryKeySelective(EnterprFestivalPacket record);

    int updateByPrimaryKey(EnterprFestivalPacket record);
}