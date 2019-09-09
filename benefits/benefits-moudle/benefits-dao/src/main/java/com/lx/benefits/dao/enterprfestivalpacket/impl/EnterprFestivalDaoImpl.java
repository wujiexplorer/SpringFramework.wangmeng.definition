package com.lx.benefits.dao.enterprfestivalpacket.impl;

import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacket;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacketExample;
import com.lx.benefits.dao.enterprfestivalpacket.EnterprFestivalDao;
import com.lx.benefits.mapper.enterprfestivalpacket.EnterprFestivalPacketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yingcai on 2018/12/26.
 */
@Service
public class EnterprFestivalDaoImpl implements EnterprFestivalDao {

    @Autowired
    private EnterprFestivalPacketMapper enterprFestivalPacketMapper;

    @Override
    public Long insert(EnterprFestivalPacket enterprFestival) {
        enterprFestival.checkBeforeInsert();
        enterprFestivalPacketMapper.insertSelective(enterprFestival);
        return enterprFestival.getCampaignId();
    }

    @Override
    public Integer update(EnterprFestivalPacket enterprFestival, EnterprFestivalPacketExample example) {
        enterprFestival.checkBeforeUpdate();
        return enterprFestivalPacketMapper.updateByExampleSelective(enterprFestival, example);
    }

    @Override
    public List<EnterprFestivalPacket> find(EnterprFestivalPacketExample example) {
        return enterprFestivalPacketMapper.selectByExample(example);
    }

    @Override
    public Integer count(EnterprFestivalPacketExample example) {
        return enterprFestivalPacketMapper.countByExample(example);
    }

    @Override
    public EnterprFestivalPacket selectByPrimaryKey(Long campaignId) {
        return enterprFestivalPacketMapper.selectByPrimaryKey(campaignId);
    }
}
