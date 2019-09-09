package com.lx.benefits.service.grain;

import com.lx.benefits.bean.dto.grain.GrainArticleInfo;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:11:56
 * Verision:2.x
 * Description:TODO
 **/
public interface GrainArticleInfoService {

    int delete(Long id);

    GrainArticleInfo insert(GrainArticleInfo grainArticleInfo);


    int updateByPrimaryKeySelective(GrainArticleInfo grainArticleInfo);


    List<GrainArticleInfo> findGrainArticleInfoList(Map<String,Object> params);


    GrainArticleInfo selectByPrimaryKey(Long id);

    Integer countGrainArticleInfoList(Map<String,Object> map);

    List<GrainArticleInfo> findGrainArticleInfoListByStatus(Map<String,Object> params);

    Integer countGrainArticleInfoListByStatus(Map<String,Object> params);

    List<GrainArticleInfo> findGrainArticleInfoListByVerifyStatus(Map<String,Object> params);

    Integer countGrainArticleInfoListByVerifyStatus(Map<String,Object> params);

    List<GrainArticleInfo> findGrainArticleInfoListByOpenAwardStatus(Map<String,Object> params);

    List<GrainArticleInfo> findGrainArticleInfoListBySuspendOpenAwardStatus(Map<String,Object> params);

    Integer countGrainArticleInfoListByOpenAwardStatus(Map<String,Object> params);

    Integer countGrainArticleInfoListBySuspendOpenAwardStatus(Map<String,Object> params);

    Integer calculateGrainArticleInfoListByOpenAwardStatus(Map<String,Object> params);
}
