package com.lx.benefits.service.employeeleaveinfo.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.enums.YNEnum;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeLeaveQueryDto;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeLeaveResDto;
import com.lx.benefits.bean.entity.employeeleaveinfo.EmplyeeLeaveInfo;
import com.lx.benefits.bean.entity.employeeleaveinfo.EmplyeeLeaveInfoExample;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfoExample;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.dao.employeeleaveinfo.EmplyeeLeaveInfoDao;
import com.lx.benefits.dao.employeeuserinfo.EmployeeUserInfoDao;
import com.lx.benefits.service.employeeleaveinfo.EmplyeeLeaveInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author unknow on 2018-12-28 23:23.
 */
@Service
public class EmplyeeLeaveInfoServiceImpl implements EmplyeeLeaveInfoService {
    
    @Autowired
    private EmplyeeLeaveInfoDao emplyeeLeaveInfoDao;

    @Autowired
    private EmployeeUserInfoDao employeeUserInfoDao;

    @Override
    public Long insertSelective(EmplyeeLeaveInfo record) {
        return emplyeeLeaveInfoDao.insertSelective(record);
    }

    @Override
    public JSONObject selectByExample(EmployeeLeaveQueryDto queryDto) {
        JSONObject jsonObject = new JSONObject();
        List<EmployeeLeaveResDto> employeeLeaveResDtoList = new ArrayList<>();

        queryDto = null == queryDto ? new EmployeeLeaveQueryDto() : queryDto;
        Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
        EmplyeeLeaveInfoExample example = new EmplyeeLeaveInfoExample();
        example.createCriteria().andEnterprIdEqualTo(enterprId);
        if (null != queryDto.getEmployeeId() && queryDto.getEmployeeId() > 0) {
            example.createCriteria().andEmployeeIdEqualTo(queryDto.getEmployeeId());
        }
        example.setPage(queryDto.getPage());
        example.setPageSize(queryDto.getPageSize());

        int count = 0;
        List<EmplyeeLeaveInfo> emplyeeLeaveInfoList = emplyeeLeaveInfoDao.selectByExample(example);
        
        if (null != emplyeeLeaveInfoList && emplyeeLeaveInfoList.size() > 0) {
            List<Long> employeeIdList = new ArrayList<>();
            for (EmplyeeLeaveInfo emplyeeLeaveInfo: emplyeeLeaveInfoList) {
                Long employeeId = emplyeeLeaveInfo.getEmployeeId();
                if (!employeeIdList.contains(employeeId)) {
                    employeeIdList.add(employeeId);    
                }
            }

            Map<Long, EmployeeUserInfo> employeeUserInfoMap = new HashMap<>();
            EmployeeUserInfoExample employeeUserExample = new EmployeeUserInfoExample();
            employeeUserExample.setPage(1);
            employeeUserExample.setPageSize(queryDto.getPageSize());
            employeeUserExample.createCriteria().andEmployeeIdIn(employeeIdList);
            List<EmployeeUserInfo> employeeUserInfoList = employeeUserInfoDao.find(employeeUserExample);
            for (EmployeeUserInfo employeeUserInfo: employeeUserInfoList) {
                employeeUserInfoMap.put(employeeUserInfo.getEmployeeId(), employeeUserInfo);
            }
            
            for (EmplyeeLeaveInfo emplyeeLeaveInfo: emplyeeLeaveInfoList) {
                EmployeeLeaveResDto employeeLeaveResDto = new EmployeeLeaveResDto();
                BeanUtils.copyProperties(emplyeeLeaveInfo, employeeLeaveResDto);
                employeeLeaveResDto.setLeaveTime(DateUtil.unixTime2Date(emplyeeLeaveInfo.getCreated()));
                
                EmployeeUserInfo employeeUserInfo = employeeUserInfoMap.get(emplyeeLeaveInfo.getEmployeeId());
                if (null != employeeUserInfo) {
                    employeeLeaveResDto.setEmployeeNo(employeeUserInfo.getEmployeeNo());
                    employeeLeaveResDto.setEmployeeName(employeeUserInfo.getEmployeeName());
                    employeeLeaveResDto.setLeaveStatus(YNEnum.getName(emplyeeLeaveInfo.getLeaveStatus(), null));
                    employeeLeaveResDtoList.add(employeeLeaveResDto);
                }
            }
            count = emplyeeLeaveInfoDao.countByExample(example);    
        }

        jsonObject.put("list", employeeLeaveResDtoList);
        jsonObject.put("count", count);
        return Response.succ(jsonObject);
    }
}
