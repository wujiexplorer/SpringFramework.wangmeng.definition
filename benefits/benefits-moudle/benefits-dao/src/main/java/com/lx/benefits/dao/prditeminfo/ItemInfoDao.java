package com.lx.benefits.dao.prditeminfo;



import com.lx.benefits.bean.entity.prditeminfo.ItemInfo;
import com.lx.benefits.bean.entity.prditeminfo.ItemInfoExample;

import java.util.List;

/**
 * @author unknow on 2018-12-26 14:16.
 */
public interface ItemInfoDao {

    ItemInfo selectByPrimaryKey(Long id);

    List<ItemInfo> selectByExample(ItemInfoExample example);
}
