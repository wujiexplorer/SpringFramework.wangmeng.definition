package com.lx.benefits.dao.prditeminfo.impl;

import com.lx.benefits.bean.entity.prditeminfo.ItemInfo;
import com.lx.benefits.bean.entity.prditeminfo.ItemInfoExample;
import com.lx.benefits.dao.prditeminfo.ItemInfoDao;
import com.lx.benefits.mapper.prditeminfo.ItemInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author unknow on 2018-12-26 14:17.
 */
@Repository
public class ItemInfoDaoImpl implements ItemInfoDao {
    
    @Autowired
    private ItemInfoMapper itemInfoMapper;

    @Override
    public ItemInfo selectByPrimaryKey(Long id) {
        return itemInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItemInfo> selectByExample(ItemInfoExample example) {
        return itemInfoMapper.selectByExample(example);
    }
}