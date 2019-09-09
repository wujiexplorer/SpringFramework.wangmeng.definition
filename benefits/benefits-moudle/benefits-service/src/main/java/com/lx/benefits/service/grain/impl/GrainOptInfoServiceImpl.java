package com.lx.benefits.service.grain.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.dto.grain.GrainOptInfo;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.dao.grain.GrainOptInfoDao;
import com.lx.benefits.service.grain.GrainOptInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:14:46
 * Verision:2.x
 * Description:TODO
 **/
@Service
public class GrainOptInfoServiceImpl implements GrainOptInfoService {

    @Autowired
    private GrainOptInfoDao grainOptInfoDao;

    @Override
    public int delete(Long id) {
        if(null == id){
            throw new BusinessException("id不能为空！");
        }
        try{
            return grainOptInfoDao.delete(id);
        }catch (Exception e){
            throw new RuntimeException("有关颗粒号操作信息删除出错！",e);
        }
    }

    @Override
    public GrainOptInfo insert(GrainOptInfo grainOptInfo) {
        validate(grainOptInfo);
        try{
            return grainOptInfoDao.insert(grainOptInfo);
        }catch(Exception e){
            throw new RuntimeException("有关颗粒号操作信息新增出错！",e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(GrainOptInfo grainOptInfo) {
        validate(grainOptInfo);
        try {
            return grainOptInfoDao.updateByPrimaryKeySelective(grainOptInfo);
        }catch(Exception e){
            throw new RuntimeException("有关颗粒号操作信息修改出错！",e);
        }
    }

    @Override
    public List<GrainOptInfo> findGrainOptInfoList(Map<String,Object> params) {
        try{
            return grainOptInfoDao.findGrainOptInfoList(params);
        }catch(Exception e){
            throw new RuntimeException("有关颗粒号操作信息查询列表出错！",e);
        }
    }

    @Override
    public GrainOptInfo selectByPrimaryKey(Long id) {
        if(null == id){
            throw new BusinessException("id不能为空！");
        }
        try{
            return grainOptInfoDao.selectByPrimaryKey(id);
        }catch (Exception e){
            throw new RuntimeException("有关颗粒号操作信息根据ID查询出错！",e);
        }
    }

    @Override
    public Integer countGrainOptInfoList(Map<String,Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainOptInfoDao.countGrainOptInfoList(params);
        }catch (Exception e){
            throw new RuntimeException("统计颗粒号操作信息列表行数出错！",e);
        }
    }

    @Override
    public Integer countReceiveCreditValue(Map<String,Object> params) {
        try{
            return grainOptInfoDao.countReceiveCreditValue(params);
        }catch (Exception e){
            throw new RuntimeException("统计转入积分总值出错！",e);
        }
    }

    private void validate(GrainOptInfo grainOptInfo){
        if(null == grainOptInfo){
            throw new BusinessException("颗粒号操作信息不能为空！");
        }else if(grainOptInfo.getEnterprId() == null){
            throw new BusinessException("企业ID不能为空！");
        }else if(StringUtil.isEmpty(grainOptInfo.getEnterprName())){
            throw new BusinessException("企业名称不能为空！");
        }else if(grainOptInfo.getParentOptId() == null){
            throw new BusinessException("父颗粒号操作ID不能为空！");
        }else if(grainOptInfo.getReceiveCreditValue() == null){
            throw new BusinessException("转入积分值不能为空！");
        }else if(grainOptInfo.getReceiveTime() == null){
            throw new BusinessException("转入时间值不能为空！");
        }else if(grainOptInfo.getGrainId() == null){
            throw new BusinessException("颗粒号不能为空！");
        }
    }
}
