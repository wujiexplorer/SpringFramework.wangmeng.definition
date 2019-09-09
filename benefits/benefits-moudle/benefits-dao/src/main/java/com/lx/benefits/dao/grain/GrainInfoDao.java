package com.lx.benefits.dao.grain;

import com.lx.benefits.bean.dto.grain.GrainInfo;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:11:16
 * Verision:2.x
 * Description:TODO
 **/
public interface GrainInfoDao {

    int delete(Long id);

    GrainInfo insert(GrainInfo grainInfo);


    int updateByPrimaryKeySelective(GrainInfo grainInfo);


    List<GrainInfo> findGrainInfoList(GrainInfo grainInfo);


    GrainInfo selectByPrimaryKey(Long id);


    List<GrainInfo> findGrainInfoListByServiceStatus(Map<String,Object> params);

    List<GrainInfo> findGrainInfoListByStatus(Map<String,Object> params);


    Integer countGrainInfoListByStatus();

    Integer countGrainInfoListByServiceStatus();

    List<GrainInfo> findGrainInfoListByverifyStatus(Map<String,Object> params);

    Integer countGrainInfoListByverifyStatus();

}
