package com.lx.benefits.dao.suborder;

import com.lx.benefits.bean.entity.suborder.SubOrder;
import com.lx.benefits.bean.entity.suborder.SubOrderExample;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface SubOrderDao {

    Long insert(SubOrder subOrder);


    Integer update(SubOrder subOrder, SubOrderExample example);

    List<SubOrder> find(SubOrderExample example);


    Integer count(SubOrderExample example);


}
