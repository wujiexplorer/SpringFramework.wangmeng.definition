package com.lx.benefits.service.enterprcredit.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.EnterprCreditInfoDto;
import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfo;
import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfoExample;
import com.lx.benefits.dao.entercreditinfo.EnterprCreditInfoDao;
import com.lx.benefits.service.enterprcredit.EnterprCreditInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.prefs.BackingStoreException;

/**
 * @author by yingcai on 2018/12/13.
 */
@Service
public class EnterprCreditInfoServiceImpl implements EnterprCreditInfoService {

    @Autowired
    private EnterprCreditInfoDao enterprCreditInfoDao;

    @Override
    public Long insert(EnterprCreditInfo enterprCreditInfo) {
        return enterprCreditInfoDao.insert(enterprCreditInfo);
    }

    @Override
    public Integer update(EnterprCreditInfo enterprCreditInfo, EnterprCreditInfoExample example) {
        return enterprCreditInfoDao.update(enterprCreditInfo, example);
    }

    @Override
    public List<EnterprCreditInfo> find(EnterprCreditInfoExample example) {
        return enterprCreditInfoDao.find(example);
    }

    @Override
    public Integer count(EnterprCreditInfoExample example) {
        return enterprCreditInfoDao.count(example);
    }

    @Override
    public List<EnterprCreditInfoDto> findByEnterprId(Long enterprId) {
        EnterprCreditInfoExample example = new EnterprCreditInfoExample();
        example.createCriteria().andEnterprIdEqualTo(enterprId);
        List<EnterprCreditInfo> creditInfoList = enterprCreditInfoDao.find(example);
        List<EnterprCreditInfoDto> dtoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(creditInfoList)) {
            creditInfoList.forEach(e -> {
                EnterprCreditInfoDto dto = new EnterprCreditInfoDto();
                BeanUtils.copyProperties(e, dto);
                dtoList.add(dto);
            });
        }
        return dtoList;
    }

    @Override
    public EnterprCreditInfoDto findByEnterprIdOne(Long enterprId) {
        EnterprCreditInfoExample example = new EnterprCreditInfoExample();
        example.createCriteria().andEnterprIdEqualTo(enterprId);
        EnterprCreditInfo creditInfo = enterprCreditInfoDao.findByEnterprIdOne(example);
        if(null != creditInfo) {
            EnterprCreditInfoDto creditInfoDto = new EnterprCreditInfoDto();
            BeanUtils.copyProperties(creditInfo, creditInfoDto);
            return creditInfoDto;
        }
        return null;
    }

    @Override
    public int updateValidCreditByEnterprId(Map<String, Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return enterprCreditInfoDao.updateValidCreditByEnterprId(params);
        }catch (Exception e){
            throw new RuntimeException("根据企业ID更新积分值出错！",e);
        }
    }

}
