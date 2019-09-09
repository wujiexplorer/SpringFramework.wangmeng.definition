package com.lx.benefits.service.grain.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.dto.grain.GrainArticleAward;
import com.lx.benefits.dao.grain.GrainArticleAwardDao;
import com.lx.benefits.service.grain.GrainArticleAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:13:11
 * Verision:2.x
 * Description:TODO
 **/
@Service
public class GrainArticleAwardServiceImpl implements GrainArticleAwardService {

    @Autowired
    private GrainArticleAwardDao grainArticleAwardDao;

    @Override
    public int delete(Long id) {
        if(id == null){
            throw new BusinessException("id不能为空!");
        }
        try{
            Integer count = grainArticleAwardDao.delete(id);
            return count;
        }catch (Exception e){
            throw new RuntimeException("有关颗粒文章奖励删除出错！",e);
        }
    }

    @Override
    public GrainArticleAward insert(GrainArticleAward grainArticleAward) {
        validate(grainArticleAward);
        try{
            GrainArticleAward grainArticleAwardTemp = grainArticleAwardDao.insert(grainArticleAward);
            return grainArticleAwardTemp;
        }catch(Exception e){
            throw new RuntimeException("有关颗粒文章奖励新增出错！",e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(GrainArticleAward grainArticleAward) {
        validate(grainArticleAward);
        try{
            Integer count = grainArticleAwardDao.updateByPrimaryKeySelective(grainArticleAward);
            return count;
        }catch(Exception e){
            throw new RuntimeException("有关颗粒文章奖励修改出错!");
        }
    }

    @Override
    public List<GrainArticleAward> findGrainArticleAwardList(Map<String,Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            List<GrainArticleAward> list = grainArticleAwardDao.findGrainArticleAwardList(params);
            return list;
        }catch (Exception e){
            throw new RuntimeException("有关颗粒文章奖励查询列表出错！");
        }
    }

    @Override
    public Integer countGrainArticleAwardList(Map<String, Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainArticleAwardDao.countGrainArticleAwardList(params);
        }catch (Exception e){
            throw new RuntimeException("统计颗粒文章奖励信息列表行数出错！",e);
        }
    }

    @Override
    public GrainArticleAward selectByPrimaryKey(Long id) {
        if(id == null){
            throw new BusinessException("id不能为空！");
        }
        try{
            return grainArticleAwardDao.selectByPrimaryKey(id);
        }catch (Exception e){
            throw new RuntimeException("有关颗粒文章奖励根据ID查询出错！");
        }
    }

    @Override
    public Double calculateGrainArticleCumulateionAward(Map<String, Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainArticleAwardDao.calculateGrainArticleCumulateionAward(params);
        }catch (Exception e){
            throw new RuntimeException("计算颗粒文章累计奖励总值出错！",e);
        }
    }

    private void validate(GrainArticleAward grainArticleAward){
        if(grainArticleAward == null){
            throw new BusinessException("颗粒文章奖励信息不能为空！");
        }else if(grainArticleAward.getAwardGrainTime() == null){
            throw new BusinessException("颗粒奖励获取时间不能为空！");
        }else if(grainArticleAward.getAwardGrainValue() == null){
            throw new BusinessException("员工奖励颗粒值不能为空！");
        }else if(grainArticleAward.getArticleId() == null){
            throw new BusinessException("文章ID不能为空！");
        }else if(grainArticleAward.getEmployeeId() == null){
            throw new BusinessException("员工ID不能为空！");
        }else if(grainArticleAward.getEmployeeName() == null){
            throw new BusinessException("员工名称不能为空！");
        }
    }
}
