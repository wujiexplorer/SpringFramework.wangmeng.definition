package com.lx.benefits.dao.memadressimport;


import com.lx.benefits.bean.entity.memadressimport.AddressImportInfo;
import com.lx.benefits.bean.entity.memadressimport.AddressImportInfoExample;

import java.util.List;

/**
 * @author unknow on 2018-12-26 16:48.
 */
public interface AddressImportInfoDao {
    Long insertSelective(AddressImportInfo record);

    List<AddressImportInfo> selectByExample(AddressImportInfoExample example);

    int countByExample(AddressImportInfoExample example);

    Integer update(AddressImportInfo record);
}
