package com.lx.benefits.service.grain.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.dto.grain.GrainInfo;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.dao.grain.GrainInfoDao;
import com.lx.benefits.service.grain.GrainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:14:16
 * Verision:2.x
 * Description:TODO
 **/
@Service
public class GrainInfoServiceImpl implements GrainInfoService {

    @Autowired
    private GrainInfoDao grainInfoDao;

    @Override
    public int delete(Long id) {
        if(null == id){
            throw new BusinessException("id不能为空！");
        }
        try{
            return grainInfoDao.delete(id);
        }catch (Exception e){
            throw new RuntimeException("有关颗粒信息删除出错！");
        }
    }

    @Override
    public GrainInfo insert(GrainInfo grainInfo) {
        validate(grainInfo);
        try{
            return grainInfoDao.insert(grainInfo);
        }catch(Exception e){
            throw new RuntimeException("有关颗粒号信息新增出错！",e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(GrainInfo grainInfo) {
        //validate(grainInfo);
        try{
            return grainInfoDao.updateByPrimaryKeySelective(grainInfo);
        }catch (Exception e){
            throw new RuntimeException("有关颗粒号信息修改出错！",e);
        }
    }

    @Override
    public List<GrainInfo> findGrainInfoList(GrainInfo grainInfo) {
        if(null == grainInfo){
            throw new BusinessException("颗粒号信息不能为空！");
        }
        try{
            return grainInfoDao.findGrainInfoList(grainInfo);
        }catch (Exception e){
            throw new RuntimeException("有关颗粒号信息查询列表出错！",e);
        }
    }

    @Override
    public GrainInfo selectByPrimaryKey(Long id) {
        if(null == id){
            throw new RuntimeException("id不能为空！");
        }
        try{
            return grainInfoDao.selectByPrimaryKey(id);
        }catch (Exception e){
            throw new RuntimeException("有关颗粒号信息根据ID查询出错！");
        }
    }

    @Override
    public List<GrainInfo> findGrainInfoListByServiceStatus(Map<String, Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainInfoDao.findGrainInfoListByServiceStatus(params);
        }catch (Exception e){
            throw new RuntimeException("根据服务状态查询颗粒号信息列表出错！",e);
        }
    }

    @Override
    public List<GrainInfo> findGrainInfoListByStatus(Map<String, Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainInfoDao.findGrainInfoListByStatus(params);
        }catch (Exception e){
            throw new RuntimeException("根据状态查询颗粒号信息列表出错！",e);
        }
    }

    @Override
    public Integer countGrainInfoListByStatus() {
        try{
            return grainInfoDao.countGrainInfoListByStatus();
        }catch (Exception e){
            throw new RuntimeException("根据状态获取颗粒号信息列表总数",e);
        }
    }

    @Override
    public Integer countGrainInfoListByServiceStatus() {
        try{
            return grainInfoDao.countGrainInfoListByServiceStatus();
        }catch (Exception e){
            throw new RuntimeException("根据服务状态获取颗粒号信息列表总数",e);
        }
    }

    @Override
    public List<GrainInfo> findGrainInfoListByverifyStatus(Map<String, Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainInfoDao.findGrainInfoListByverifyStatus(params);
        }catch (Exception e){
            throw new RuntimeException("查询审核状态的颗粒信息列表出错！",e);
        }
    }

    @Override
    public Integer countGrainInfoListByverifyStatus() {
        try{
            return grainInfoDao.countGrainInfoListByverifyStatus();
        }catch (Exception e){
            throw new BusinessException("统计审核状态的颗粒信息列表行数出错！",e);
        }

    }

    private void validate(GrainInfo grainInfo){
        if(null == grainInfo){
            throw new BusinessException("颗粒号信息不能为空！");
        }else if(grainInfo.getApplyTime() == null){
            throw new BusinessException("颗粒号申请时间不能为空！");
        }else if(grainInfo.getCumulationReadQuantity() == null){
            throw new BusinessException("颗粒号累计阅读量不能为空！");
        }else if(grainInfo.getEnterprId() == null){
            throw new BusinessException("企业ID不能为空！");
        }else if(StringUtil.isEmpty(grainInfo.getEnterprName())){
            throw new BusinessException("企业名称不能为空！");
        }else if(grainInfo.getEstablishedTime() == null){
            throw new BusinessException("颗粒号开通时间不能为空！");
        }else if(StringUtil.isEmpty(grainInfo.getTypeList())){
            throw new BusinessException("权限设置返回信息不能为空！");
        }else if(grainInfo.getLeftGrainValue() == null){
            throw new BusinessException("剩余颗粒值不能为空！");
        }else if(grainInfo.getReceiveValue() == null){
            throw new BusinessException("转入积分值不能为空！");
        }else if(grainInfo.getStatus() == null){
            throw new BusinessException("颗粒号状态不能为空！");
        }else if(grainInfo.getServiceStatus() == null){
            throw new BusinessException("颗粒号服务状态不能为空！");
        }
    }
}
