package com.lx.benefits.dao.memunioninfo;


import com.lx.benefits.bean.entity.memunioninfo.MemUnionInfo;

import java.util.List;

/**
 * @author unknow on 2019-01-05 21:57.
 */
public interface MemUnionInfoDao {

    int batchInsert(List<MemUnionInfo> memUnionInfoList);

    int updateByPrimaryKeySelective(MemUnionInfo memUnionInfo);

    int updateByMemId(MemUnionInfo memUnionInfo);
}
