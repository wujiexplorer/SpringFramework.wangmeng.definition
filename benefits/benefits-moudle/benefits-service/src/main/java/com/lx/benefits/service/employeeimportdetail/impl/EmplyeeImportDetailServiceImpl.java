package com.lx.benefits.service.employeeimportdetail.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeImportDetailQueryDto;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeImportDetailResDto;
import com.lx.benefits.bean.entity.employeeimportdetail.EmplyeeImportDetail;
import com.lx.benefits.bean.entity.employeeimportdetail.EmplyeeImportDetailExample;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfoExample;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.dao.employeeimportdetail.EmplyeeImportDetailDao;
import com.lx.benefits.dao.employeeuserinfo.EmployeeUserInfoDao;
import com.lx.benefits.service.employeeimportdetail.EmplyeeImportDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author unknow on 2018-12-26 17:19.
 */
@Service
public class EmplyeeImportDetailServiceImpl implements EmplyeeImportDetailService {
    
    @Autowired
    private EmplyeeImportDetailDao emplyeeImportDetailDao;
    
    @Autowired
    private EmployeeUserInfoDao employeeUserInfoDao;
    
    @Override
    public JSONObject selectByExample(EmployeeImportDetailQueryDto req) {
        req = null == req ? new EmployeeImportDetailQueryDto() : req;
        if (null == req.getImportId() || req.getImportId() < 1) {
            return Response.fail("员工导入记录id不能为空");
        }
        JSONObject jsonObject = new JSONObject();

        List<EmployeeImportDetailResDto> employeeImportDetailResDtoList = new ArrayList<>();
        EmplyeeImportDetailExample example = new EmplyeeImportDetailExample();
        int start = (req.getPage() - 1) * req.getPageSize();
        int end = req.getPage() * req.getPageSize();
        example.setPage(start);
        example.setPageSize(end);
        example.createCriteria().andImportIdEqualTo(req.getImportId());
        List<EmplyeeImportDetail> emplyeeImportDetailList = emplyeeImportDetailDao.selectByExample(example);
        Integer count = emplyeeImportDetailDao.countByExample(example);
        if (null != emplyeeImportDetailList && !emplyeeImportDetailList.isEmpty()) {
            List<Long> employeeIdList = new ArrayList<>();
            for (EmplyeeImportDetail emplyeeImportDetail: emplyeeImportDetailList) {
                Long employeeId = emplyeeImportDetail.getEmployeeId();
                if (!employeeIdList.contains(employeeId)) {
                    employeeIdList.add(employeeId);
                }
            }

            Map<Long, EmployeeUserInfo> employeeUserInfoMap = new HashMap<>();
            EmployeeUserInfoExample employeeUserExample = new EmployeeUserInfoExample();
            employeeUserExample.setPage(1);
            employeeUserExample.setPageSize(req.getPageSize());
            employeeUserExample.createCriteria().andEmployeeIdIn(employeeIdList);
            List<EmployeeUserInfo> employeeUserInfoList = employeeUserInfoDao.find(employeeUserExample);
            for (EmployeeUserInfo employeeUserInfo: employeeUserInfoList) {
                employeeUserInfoMap.put(employeeUserInfo.getEmployeeId(), employeeUserInfo);
            }
            
            for (EmplyeeImportDetail emplyeeImportDetail: emplyeeImportDetailList) {
                EmployeeImportDetailResDto employeeImportDetailResDto = new EmployeeImportDetailResDto();
                BeanUtils.copyProperties(emplyeeImportDetail, employeeImportDetailResDto);
                employeeImportDetailResDto.setImportTime(DateUtil.unixTime2Date(emplyeeImportDetail.getCreated()));

                EmployeeUserInfo employeeUserInfo = employeeUserInfoMap.get(emplyeeImportDetail.getEmployeeId());
                if (employeeUserInfo != null) {
                    employeeImportDetailResDto.setEmployeeNo(employeeUserInfo.getEmployeeNo());
                    employeeImportDetailResDto.setEmployeeName(employeeUserInfo.getEmployeeName());
                    employeeImportDetailResDtoList.add(employeeImportDetailResDto);
                }
            }
            jsonObject.put("count", count);
        } else {
            jsonObject.put("count", 0);
        }
        if (employeeImportDetailResDtoList.size() <= 0) {
            jsonObject.put("count", 0);
        }
        jsonObject.put("list", employeeImportDetailResDtoList);
        return Response.succ(jsonObject);
    }
}
