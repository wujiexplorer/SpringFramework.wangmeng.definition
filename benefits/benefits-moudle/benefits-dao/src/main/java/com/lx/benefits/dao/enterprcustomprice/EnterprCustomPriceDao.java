package com.lx.benefits.dao.enterprcustomprice;



import com.lx.benefits.bean.entity.enterprcustomprice.EnterprCustomPrice;
import com.lx.benefits.bean.entity.enterprcustomprice.EnterprCustomPriceExample;

import java.util.List;

/**
 * @author unknow on 2018-12-25 23:52.
 */
public interface EnterprCustomPriceDao {
    Long insertSelective(EnterprCustomPrice record);

    List<EnterprCustomPrice> selectByExample(EnterprCustomPriceExample example);

    int countByExample(EnterprCustomPriceExample example);
    
    EnterprCustomPrice selectByPrimaryKey(Long customId);

	/**
	 * 获取企业商品定制价格
	 * 
	 * @param enterprId
	 *            企业ID
	 * @param skuIds
	 *            skuID列表，null表示获取该企业下所有定制价格
	 * @return
	 */
	List<EnterprCustomPrice> selectByEnterprId(Long enterprId, List<Long> skuIds);

    Integer updateByPrimaryKeySelective(EnterprCustomPrice record);

    int insertBatch(List<EnterprCustomPrice> record);

    int updateBatch(List<EnterprCustomPrice> record);

    Integer deleteByPrimaryKey(Long customId);

    EnterprCustomPrice selectByParam(EnterprCustomPriceExample example);

}
