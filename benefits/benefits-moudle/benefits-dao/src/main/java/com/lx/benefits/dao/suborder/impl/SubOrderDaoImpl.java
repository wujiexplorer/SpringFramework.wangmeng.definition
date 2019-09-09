package com.lx.benefits.dao.suborder.impl;


import com.lx.benefits.bean.entity.suborder.SubOrder;
import com.lx.benefits.bean.entity.suborder.SubOrderExample;
import com.lx.benefits.dao.suborder.SubOrderDao;
import com.lx.benefits.mapper.suborder.SubOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yingcai on 2018/12/22.
 */
@Service
public class SubOrderDaoImpl implements SubOrderDao {

    @Autowired
    private SubOrderMapper subOrderMapper;

    @Override
    public Long insert(SubOrder subOrder) {
        return Long.valueOf(subOrderMapper.insertSelective(subOrder));
    }

    @Override
    public Integer update(SubOrder subOrder, SubOrderExample example) {
        return subOrderMapper.updateByExampleSelective(subOrder, example);
    }

    @Override
    public List<SubOrder> find(SubOrderExample example) {
        return subOrderMapper.selectByExample(example);
    }

    @Override
    public Integer count(SubOrderExample example) {
        return subOrderMapper.countByExample(example);
    }
}
