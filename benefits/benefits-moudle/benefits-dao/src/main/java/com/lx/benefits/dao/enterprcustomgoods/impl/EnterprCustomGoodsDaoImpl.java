package com.lx.benefits.dao.enterprcustomgoods.impl;


import com.lx.benefits.bean.entity.enterprcustomgoods.EnterprCustomGoods;
import com.lx.benefits.bean.entity.enterprcustomgoods.EnterprCustomGoodsExample;
import com.lx.benefits.dao.enterprcustomgoods.EnterprCustomGoodsDao;
import com.lx.benefits.mapper.enterprcustomgoods.EnterprCustomGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yingcai on 2018/12/19.
 */
@Service
public class EnterprCustomGoodsDaoImpl implements EnterprCustomGoodsDao {

    @Autowired
    private EnterprCustomGoodsMapper enterprCustomGoodsMapper;

    @Override
    public Long insert(EnterprCustomGoods enterprCustomGoods) {
        enterprCustomGoods.checkBeforeInsert();
        int insert = enterprCustomGoodsMapper.insertSelective(enterprCustomGoods);
        if (insert > 0) {
            return enterprCustomGoods.getCustomId();
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(EnterprCustomGoods record) {
        return enterprCustomGoodsMapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public Integer update(EnterprCustomGoods enterprCustomGoods, EnterprCustomGoodsExample example) {
        enterprCustomGoods.checkBeforeUpdate();
        return enterprCustomGoodsMapper.updateByExample(enterprCustomGoods, example);
    }

    @Override
    public List<EnterprCustomGoods> find(EnterprCustomGoodsExample example) {
        return enterprCustomGoodsMapper.selectByExample(example);
    }

    @Override
    public EnterprCustomGoods selectByPrimaryKey(Long customId) {
        return enterprCustomGoodsMapper.selectByPrimaryKey(customId);
    }

    @Override
    public Integer count(EnterprCustomGoodsExample example) {
        return enterprCustomGoodsMapper.countByExample(example);
    }
}
