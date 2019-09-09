package com.lx.benefits.dao.grain.impl;

import com.lx.benefits.bean.dto.grain.GrainArticleInfo;
import com.lx.benefits.dao.grain.GrainArticleInfoDao;
import com.lx.benefits.mapper.grain.GrainArticleInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:11:10
 * Verision:2.x
 * Description:TODO
 **/
@Repository
public class GrainArticleInfoDaoImpl implements GrainArticleInfoDao {

    @Autowired
    private GrainArticleInfoMapper grainArticleInfoMapper;

    @Override
    public int delete(Long id) {
        return grainArticleInfoMapper.delete(id);
    }

    @Override
    public GrainArticleInfo insert(GrainArticleInfo grainArticleInfo) {
        Integer count = grainArticleInfoMapper.insert(grainArticleInfo);
        if(count > 0){
            return grainArticleInfo;
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(GrainArticleInfo grainArticleInfo) {
        return grainArticleInfoMapper.updateByPrimaryKeySelective(grainArticleInfo);
    }

    @Override
    public List<GrainArticleInfo> findGrainArticleInfoList(Map<String,Object> params) {
        return grainArticleInfoMapper.findGrainArticleInfoList(params);
    }

    @Override
    public GrainArticleInfo selectByPrimaryKey(Long id) {
        return grainArticleInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer countGrainArticleInfoList(Map<String,Object> map) {
        return grainArticleInfoMapper.countGrainArticleInfoList(map);
    }

    @Override
    public List<GrainArticleInfo> findGrainArticleInfoListByStatus(Map<String, Object> params) {
        return grainArticleInfoMapper.findGrainArticleInfoListByStatus(params);
    }

    @Override
    public Integer countGrainArticleInfoListByStatus(Map<String, Object> params) {
        return grainArticleInfoMapper.countGrainArticleInfoListByStatus(params);
    }

    @Override
    public List<GrainArticleInfo> findGrainArticleInfoListByVerifyStatus(Map<String, Object> params) {
        return grainArticleInfoMapper.findGrainArticleInfoListByVerifyStatus(params);
    }

    @Override
    public Integer countGrainArticleInfoListByVerifyStatus(Map<String, Object> params) {
        return grainArticleInfoMapper.countGrainArticleInfoListByVerifyStatus(params);
    }

    @Override
    public List<GrainArticleInfo> findGrainArticleInfoListByOpenAwardStatus(Map<String, Object> params) {
        return grainArticleInfoMapper.findGrainArticleInfoListByOpenAwardStatus(params);
    }

    @Override
    public List<GrainArticleInfo> findGrainArticleInfoListBySuspendOpenAwardStatus(Map<String, Object> params) {
        return grainArticleInfoMapper.findGrainArticleInfoListBySuspendOpenAwardStatus(params);
    }

    @Override
    public Integer countGrainArticleInfoListByOpenAwardStatus(Map<String,Object> params) {
        return grainArticleInfoMapper.countGrainArticleInfoListByOpenAwardStatus(params);
    }

    @Override
    public Integer countGrainArticleInfoListBySuspendOpenAwardStatus(Map<String,Object> params) {
        return grainArticleInfoMapper.countGrainArticleInfoListBySuspendOpenAwardStatus(params);
    }

    @Override
    public Integer calculateGrainArticleInfoListByOpenAwardStatus(Map<String,Object> params) {
        return grainArticleInfoMapper.calculateGrainArticleInfoListByOpenAwardStatus(params);
    }
}
