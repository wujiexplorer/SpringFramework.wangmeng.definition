package com.lx.benefits.dao.enterprcustomgoods;



import com.lx.benefits.bean.entity.enterprcustomgoods.EnterprCustomGoods;
import com.lx.benefits.bean.entity.enterprcustomgoods.EnterprCustomGoodsExample;

import java.util.List;

/**
 * @author by Ginger on 2017/8/23.
 */
public interface EnterprCustomGoodsDao {

    Long insert(EnterprCustomGoods enterprCustomGoods);

    int updateByPrimaryKeySelective(EnterprCustomGoods record);
    
    Integer update(EnterprCustomGoods enterprCustomGoods, EnterprCustomGoodsExample example);

    List<EnterprCustomGoods> find(EnterprCustomGoodsExample example);

    EnterprCustomGoods selectByPrimaryKey(Long customId);
    
    Integer count(EnterprCustomGoodsExample example);
}
