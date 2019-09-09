package com.lx.benefits.dao.enterprcustomprice.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.benefits.bean.entity.enterprcustomprice.EnterprCustomPrice;
import com.lx.benefits.bean.entity.enterprcustomprice.EnterprCustomPriceExample;
import com.lx.benefits.dao.enterprcustomprice.EnterprCustomPriceDao;
import com.lx.benefits.mapper.enterprcustomprice.EnterprCustomPriceMapper;

/**
 * @author unknow on 2018-12-25 23:52.
 */
@Service
public class EnterprCustomPriceDaoImpl implements EnterprCustomPriceDao {
    
    @Autowired
    private EnterprCustomPriceMapper enterprCustomPriceMapper;

    @Override
    public Long insertSelective(EnterprCustomPrice record) {
        if (enterprCustomPriceMapper.insertSelective(record) > 0) {
            return record.getCustomId();
        }
        return null;
    }

    @Override
    public List<EnterprCustomPrice> selectByExample(EnterprCustomPriceExample example) {
        return enterprCustomPriceMapper.selectByExample(example);
    }

    @Override
    public int countByExample(EnterprCustomPriceExample example) {
        return enterprCustomPriceMapper.countByExample(example);
    }

    @Override
    public EnterprCustomPrice selectByPrimaryKey(Long customId) {
        return enterprCustomPriceMapper.selectByPrimaryKey(customId);
    }

    @Override
    public  List<EnterprCustomPrice> selectByEnterprId(Long enterprId,List<Long> skuIds) {
    	if(enterprId==null||skuIds!=null&&skuIds.size()==0) {
    		return Collections.emptyList();
    	}
    	EnterprCustomPriceExample example=new EnterprCustomPriceExample();
    	if(skuIds==null) {
    		example.createCriteria().andEnterprIdEqualTo(enterprId);
    	}else {
    		example.createCriteria().andEnterprIdEqualTo(enterprId).andGoodsIdIn(skuIds);
    	}
        return enterprCustomPriceMapper.selectByExample(example);
    }

    @Override
    public Integer updateByPrimaryKeySelective(EnterprCustomPrice record) {
        return enterprCustomPriceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertBatch(List<EnterprCustomPrice> list) {
        return enterprCustomPriceMapper.insertBatch(list);
    }

    @Override
    public int updateBatch(List<EnterprCustomPrice> record) {
        return  enterprCustomPriceMapper.updateBatch(record);
    }

    @Override
    public Integer deleteByPrimaryKey(Long customId) {
        return enterprCustomPriceMapper.deleteByPrimaryKey(customId);
    }

    @Override
    public EnterprCustomPrice selectByParam(EnterprCustomPriceExample example) {
        return enterprCustomPriceMapper.selectByParam(example);
    }

}