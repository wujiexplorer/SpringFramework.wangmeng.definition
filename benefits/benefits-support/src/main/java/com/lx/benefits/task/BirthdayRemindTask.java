package com.lx.benefits.task;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.apollo.common.enums.base.DatePatternEnum;
import com.apollo.common.utils.base.DateUtil;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeQueryBirthday;
import com.lx.benefits.bean.enums.CreditSetEnum;
import com.lx.benefits.bean.param.employee.EmployeeUserInfoParam;
import com.lx.benefits.mapper.employeeuserinfo.EmployeeUserInfoMapper;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BirthdayRemindTask {
	@Autowired
    private EmployeeUserInfoMapper employeeUserInfoMapper;
	
	@Autowired
	private EmployeeUserInfoService employeeUserInfoService;
	@Scheduled(cron = "0 16 8 * * ?")
	public void checkBirthdayStatus() {
		log.info("生日积分自动发放，任务开始时间：" + DateUtil.getDate(new Date(), DatePatternEnum.Y_M_D_SPACE_H_M_S.getPattern()));
		EmployeeQueryBirthday employeeQueryBirthday = new EmployeeQueryBirthday();
		employeeQueryBirthday.setBirthdayRemind(CreditSetEnum.OPEN.getValue());
		String currentTime = DateUtil.getDate(new Date(), DatePatternEnum.Y_M_D.getPattern());
		employeeQueryBirthday.setCurrentTime(currentTime.substring(5));
		List<EmployeeUserInfoParam> employeeUserInfo = employeeUserInfoMapper.selectCurrentTimeBirthdayList(employeeQueryBirthday);
		if(CollectionUtils.isEmpty(employeeUserInfo)) {
			return;
		}
		List<String> emailList = new ArrayList<>();
		List<BigInteger> employeeIdList = new ArrayList<>();
		List<BigInteger> enterprIdList = new ArrayList<>();
		List<BigDecimal> birthdayCreditList = new ArrayList<>();
		for (EmployeeUserInfoParam employeeUser : employeeUserInfo) {
			employeeIdList.add(new BigInteger(employeeUser.getEmployeeId().toString()));
			enterprIdList.add(new BigInteger(employeeUser.getEnterprId().toString()));
			birthdayCreditList.add(employeeUser.getCredit());
			emailList.add(employeeUser.getEmail());
		}
		employeeUserInfoService.updateCredit(employeeIdList,enterprIdList,birthdayCreditList,emailList,true);
		log.info("生日积分自动发放，任务结束时间：" + DateUtil.getDate(new Date(), DatePatternEnum.Y_M_D_SPACE_H_M_S.getPattern()));
	}
}
