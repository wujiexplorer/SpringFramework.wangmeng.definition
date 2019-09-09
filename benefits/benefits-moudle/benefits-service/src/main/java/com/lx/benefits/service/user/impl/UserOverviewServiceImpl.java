package com.lx.benefits.service.user.impl;

import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.enterprise.EnterpriseService;
import com.lx.benefits.service.user.UserOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/7/15
 * Time:10:05
 * Version:2.x
 * Description:TODO
 **/
@Service
public class UserOverviewServiceImpl implements UserOverviewService {

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private EmployeeUserInfoService employeeUserInfoService;

    @Override
    public Map<String, Object> getUserStatistic() {
        try{
            Map<String,Object> map = new HashMap<>();
            Long totalEnterprsCount = enterpriseService.countByExample(null);
            Long totalCurrentEnterprsCount = enterpriseService.countCurrentEnterprise();
            Integer totalEmployeesCount = employeeUserInfoService.countTotalEmployeesByExample();
            Integer totalLeaveEmployeesCount = employeeUserInfoService.countLeaveEmployeesByExample();
            map.put("totalEnterprsCount",totalEnterprsCount);
            map.put("totalCurrentEnterprsCount",totalCurrentEnterprsCount);
            map.put("totalEmployeesCount",totalEmployeesCount);
            map.put("totalLeaveEmployeesCount",totalLeaveEmployeesCount);
            return map;
        }catch (Exception e){
            throw new RuntimeException("统计用户概览出错！",e);
        }
    }
}
