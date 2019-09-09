package com.lx.benefits.dao.grain.impl;

import com.lx.benefits.bean.dto.grain.GrainInfo;
import com.lx.benefits.dao.grain.GrainInfoDao;
import com.lx.benefits.mapper.grain.GrainInfoMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:11:16
 * Verision:2.x
 * Description:TODO
 **/
@Repository
public class GrainInfoDaoImpl implements GrainInfoDao {

    @Autowired
    private GrainInfoMapper grainInfoMapper;


    @Override
    public int delete(Long id) {
        return grainInfoMapper.delete(id);
    }

    @Override
    public GrainInfo insert(GrainInfo grainInfo) {
        Integer count = grainInfoMapper.insert(grainInfo);
        if(count > 0){
            return grainInfo;
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(GrainInfo grainInfo) {
        return grainInfoMapper.updateByPrimaryKeySelective(grainInfo);
    }

    @Override
    public List<GrainInfo> findGrainInfoList(GrainInfo grainInfo) {
        return grainInfoMapper.findGrainInfoList(grainInfo);
    }

    @Override
    public GrainInfo selectByPrimaryKey(Long id) {
        return grainInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GrainInfo> findGrainInfoListByServiceStatus(Map<String, Object> params) {
        return grainInfoMapper.findGrainInfoListByServiceStatus(params);
    }

    @Override
    public List<GrainInfo> findGrainInfoListByStatus(Map<String, Object> params) {
        return grainInfoMapper.findGrainInfoListByStatus(params);
    }

    @Override
    public Integer countGrainInfoListByStatus() {
        return grainInfoMapper.countGrainInfoListByStatus();
    }

    @Override
    public Integer countGrainInfoListByServiceStatus() {
        return grainInfoMapper.countGrainInfoListByServiceStatus();
    }

    @Override
    public List<GrainInfo> findGrainInfoListByverifyStatus(Map<String, Object> params) {
        return grainInfoMapper.findGrainInfoListByverifyStatus(params);
    }

    @Override
    public Integer countGrainInfoListByverifyStatus() {
        return grainInfoMapper.countGrainInfoListByverifyStatus();
    }
}
