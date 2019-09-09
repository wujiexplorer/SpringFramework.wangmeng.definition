package com.lx.benefits.dao.grain.impl;

import com.lx.benefits.bean.dto.grain.GrainOptInfo;
import com.lx.benefits.dao.grain.GrainOptInfoDao;
import com.lx.benefits.mapper.grain.GrainOptInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:11:20
 * Verision:2.x
 * Description:TODO
 **/
@Repository
public class GrainOptInfoDaoImpl implements GrainOptInfoDao {

    @Autowired
    private GrainOptInfoMapper grainOptInfoMapper;

    @Override
    public int delete(Long id) {
        return grainOptInfoMapper.delete(id);
    }

    @Override
    public GrainOptInfo insert(GrainOptInfo grainOptInfo) {
        Integer count = grainOptInfoMapper.insert(grainOptInfo);
        if(count > 0){
            return grainOptInfo;
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(GrainOptInfo grainOptInfo) {
        return grainOptInfoMapper.updateByPrimaryKeySelective(grainOptInfo);
    }

    @Override
    public List<GrainOptInfo> findGrainOptInfoList(Map<String,Object> params) {
        return grainOptInfoMapper.findGrainOptInfoList(params);
    }

    @Override
    public GrainOptInfo selectByPrimaryKey(Long id) {
        return grainOptInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer countGrainOptInfoList(Map<String,Object> params) {
        return grainOptInfoMapper.countGrainOptInfoList(params);
    }

    @Override
    public Integer countReceiveCreditValue(Map<String,Object> params) {
        return grainOptInfoMapper.countReceiveCreditValue(params);
    }
}
