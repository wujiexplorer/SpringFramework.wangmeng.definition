package com.lx.benefits.service.grain;

import com.lx.benefits.bean.dto.grain.GrainArticleAward;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:11:54
 * Verision:2.x
 * Description:TODO
 **/
public interface GrainArticleAwardService {

    int delete(Long id);

    GrainArticleAward insert(GrainArticleAward grainArticleAward);


    int updateByPrimaryKeySelective(GrainArticleAward grainArticleAward);


    List<GrainArticleAward> findGrainArticleAwardList(Map<String,Object> params);

    Integer countGrainArticleAwardList(Map<String,Object> params);


    GrainArticleAward selectByPrimaryKey(Long id);

    Double calculateGrainArticleCumulateionAward(Map<String,Object> params);
}
