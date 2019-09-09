package com.lx.benefits.dao.grain;

import com.lx.benefits.bean.dto.grain.GrainOptInfo;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:11:19
 * Verision:2.x
 * Description:TODO
 **/
public interface GrainOptInfoDao {

    int delete(Long id);

    GrainOptInfo insert(GrainOptInfo grainOptInfo);


    int updateByPrimaryKeySelective(GrainOptInfo grainOptInfo);


    List<GrainOptInfo> findGrainOptInfoList(Map<String,Object> params);


    GrainOptInfo selectByPrimaryKey(Long id);

    Integer countGrainOptInfoList(Map<String,Object> params);

    Integer countReceiveCreditValue(Map<String,Object> params);
}
