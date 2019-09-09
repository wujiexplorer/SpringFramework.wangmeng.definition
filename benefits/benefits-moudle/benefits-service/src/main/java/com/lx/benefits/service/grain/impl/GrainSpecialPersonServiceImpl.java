package com.lx.benefits.service.grain.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.dto.grain.GrainSpecialPerson;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.dao.grain.GrainSpecialPersonDao;
import com.lx.benefits.service.grain.GrainSpecialPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:15:12
 * Verision:2.x
 * Description:TODO
 **/
@Service
public class GrainSpecialPersonServiceImpl implements GrainSpecialPersonService {

    @Autowired
    private GrainSpecialPersonDao grainSpecialPersonDao;

    @Override
    public int delete(Long id) {
        if(null == id){
            throw new BusinessException("id不能为空！");
        }
        try{
            return grainSpecialPersonDao.delete(id);
        }catch(Exception e){
            throw new RuntimeException("有关颗粒奖励特殊员工信息删除出错！",e);
        }
    }

    @Override
    public GrainSpecialPerson insert(GrainSpecialPerson grainSpecialPerson) {
        validate(grainSpecialPerson);
        try{
            return grainSpecialPersonDao.insert(grainSpecialPerson);
        }catch (Exception e){
            throw new RuntimeException("重复添加相同的员工！",e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(GrainSpecialPerson grainSpecialPerson) {
        //validate(grainSpecialPerson);
        try{
            return grainSpecialPersonDao.updateByPrimaryKeySelective(grainSpecialPerson);
        }catch(Exception e) {
            throw new RuntimeException("有关颗粒奖励特殊员工信息修改出错！", e);
        }
    }

    @Override
    public List<GrainSpecialPerson> findGrainSpecialPersonList(Map<String,Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainSpecialPersonDao.findrainSpecialPersonList(params);
        }catch (Exception e){
            throw new RuntimeException("有关颗粒奖励特殊员工信息查询列表出错！");
        }
    }

    @Override
    public GrainSpecialPerson selectByPrimaryKey(Long id) {
        if(null == id){
            throw new BusinessException("id不能为空！");
        }
        try{
            return grainSpecialPersonDao.selectByPrimaryKey(id);
        }catch (Exception e){
            throw new RuntimeException("有关颗粒奖励特殊员工信息根据ID查询出错！",e);
        }
    }

    @Override
    public Integer countGrainSpecialPersonList(Map<String,Object> params) {
        try{
            return grainSpecialPersonDao.countGrainSpecialPersonList(params);
        }catch (Exception e){
            throw new RuntimeException("统计颗粒奖励特殊员工行数出错！",e);
        }
    }

    private void validate(GrainSpecialPerson grainSpecialPerson){
        if(null == grainSpecialPerson){
            throw new BusinessException("颗粒特殊员工信息不能为空！");
        }else if(grainSpecialPerson.getEmployeeId() == null){
            throw new BusinessException("企业员工ID不能为空！");
        }else if(StringUtil.isEmpty(grainSpecialPerson.getEmployeeName())){
            throw new BusinessException("企业员工名称不能为空！");
        }else if(grainSpecialPerson.getEnterprId() == null){
            throw new BusinessException("企业ID不能为空！");
        }else if(grainSpecialPerson.getSingleAwardGrainValue() == null){
            throw new BusinessException("员工单次奖励颗粒值不能为空！");
        }
    }
}
