package com.lx.benefits.service.employeeimportinfo.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.FLPageDto;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeImportResDto;
import com.lx.benefits.bean.entity.employeeimportinfo.EmplyeeImportInfo;
import com.lx.benefits.bean.entity.employeeimportinfo.EmplyeeImportInfoExample;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfoCondition;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.dao.employeeimportinfo.EmplyeeImportInfoDao;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.service.employeeimportinfo.EmplyeeImportInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author unknow on 2018-12-26 17:08.
 */
@Service
public class EmplyeeImportInfoServiceImpl implements EmplyeeImportInfoService {
    
    @Autowired
    private EmplyeeImportInfoDao emplyeeImportInfoDao;
    
    @Autowired
    private EnterprUserInfoMapper enterprUserInfoMapper;

    @Override
    public Long insertSelective(EmplyeeImportInfo record) {
        return emplyeeImportInfoDao.insertSelective(record);
    }

    @Override
    public JSONObject selectByExample(FLPageDto req) {
        req = null == req ? new FLPageDto() : req;
        JSONObject jsonObject = new JSONObject();
        List<EmployeeImportResDto> employeeImportResDtoList = new ArrayList<>();
        int count = 0;
        EmplyeeImportInfoExample example = new EmplyeeImportInfoExample();
        example.setPage(req.getPage());
        example.setPageSize(req.getPageSize());
        example.createCriteria().andEnterprIdEqualTo(SessionContextHolder.getSessionEnterpriseInfo().getEnterprId());
        List<EmplyeeImportInfo> emplyeeImportInfoList = emplyeeImportInfoDao.selectByExample(example);
        if (null != emplyeeImportInfoList && !emplyeeImportInfoList.isEmpty()) {
            List<Long> optUserIdList = new ArrayList<>();
            for (EmplyeeImportInfo emplyeeImportInfo: emplyeeImportInfoList) {
                Long optUserId = emplyeeImportInfo.getOptUserId();
                if (!optUserIdList.contains(optUserId)) {
                    optUserIdList.add(optUserId);
                }
            }
            EnterprUserInfoCondition enterprUserExample = new EnterprUserInfoCondition();
            enterprUserExample.createCriteria().andEnterprIdIn(optUserIdList);
            enterprUserExample.setOffset(0);
            enterprUserExample.setLimit(100);
            List<EnterprUserInfo> enterprUserInfoList =enterprUserInfoMapper.selectByExample(enterprUserExample);
            Map<Long, EnterprUserInfo> enterprUserInfoMap = new HashMap<>();
            if (null != enterprUserInfoList && !enterprUserInfoList.isEmpty()) {
                for (EnterprUserInfo enterprUserInfo: enterprUserInfoList) {
                    enterprUserInfoMap.put(enterprUserInfo.getEnterprId(), enterprUserInfo);
                }
            }
            
            for (EmplyeeImportInfo emplyeeImportInfo: emplyeeImportInfoList) {
                EmployeeImportResDto employeeImportResDto = new EmployeeImportResDto();
                BeanUtils.copyProperties(emplyeeImportInfo, employeeImportResDto);
                EnterprUserInfo enterprUserInfo = enterprUserInfoMap.get(emplyeeImportInfo.getOptUserId());
                employeeImportResDto.setOptUserName(enterprUserInfo.getLoginName());
                employeeImportResDto.setImportTime(DateUtil.unixTime2Date(emplyeeImportInfo.getCreated()));
                employeeImportResDtoList.add(employeeImportResDto);
            }
            count = emplyeeImportInfoDao.countByExample(example);
        }
        jsonObject.put("list", employeeImportResDtoList);
        jsonObject.put("count", count);
        return Response.succ(jsonObject);
    }
    
}
