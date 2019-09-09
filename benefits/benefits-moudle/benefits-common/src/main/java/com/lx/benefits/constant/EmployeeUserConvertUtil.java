package com.lx.benefits.constant;

import com.lx.benefits.bean.enums.SexEnum;
import com.lx.benefits.bean.enums.YNEnum;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeInfoDto;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.util.DateUtil;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author unknow on 2018-12-21 23:21.
 */
public class EmployeeUserConvertUtil {
    
    public static EmployeeUserInfo convertEmployeeInfoDto(EmployeeInfoDto employeeInfoDto) {
        EmployeeUserInfo employeeUserInfo = new EmployeeUserInfo();
        BeanUtils.copyProperties(employeeInfoDto, employeeUserInfo);
        employeeUserInfo.setBirthday(employeeInfoDto.getBirthday());
        String created = employeeInfoDto.getCreated();
        String updated = employeeInfoDto.getUpdated();
        employeeUserInfo.setCreated(DateUtil.date2IntegerUnixTime(created));
        employeeUserInfo.setUpdated(DateUtil.date2IntegerUnixTime(updated));
        employeeUserInfo.setInductionTime(DateUtil.date2IntegerUnixTime(employeeInfoDto.getInductionTime()));
        employeeUserInfo.setSex(SexEnum.getSexEnum(employeeInfoDto.getSex(), SexEnum.S).getValue());
        employeeUserInfo.setLeaveStatus(YNEnum.getValue(employeeInfoDto.getLeaveStatus(), YNEnum.N));
        employeeUserInfo.setIsDeleted(YNEnum.getValue(employeeInfoDto.getIsDeleted(), YNEnum.N));
        return employeeUserInfo;
    }

    public static List<EmployeeUserInfo> convertEmployeeInfoDtoList(List<EmployeeInfoDto> employeeInfoDtoList) {
        if (null == employeeInfoDtoList) {
            return null;
        }
        List<EmployeeUserInfo> employeeUserInfoList = new ArrayList<>();
        for (EmployeeInfoDto employeeInfoDto: employeeInfoDtoList) {
            EmployeeUserInfo employeeUserInfo = new EmployeeUserInfo();
            BeanUtils.copyProperties(employeeInfoDto, employeeUserInfo);
            employeeUserInfo.setBirthday(employeeInfoDto.getBirthday());
            String created = employeeInfoDto.getCreated();
            String updated = employeeInfoDto.getUpdated();
            employeeUserInfo.setCreated(DateUtil.date2IntegerUnixTime(created));
            employeeUserInfo.setUpdated(DateUtil.date2IntegerUnixTime(updated));
            employeeUserInfo.setInductionTime(DateUtil.date2IntegerUnixTime(employeeInfoDto.getInductionTime()));
            employeeUserInfo.setSex(SexEnum.getSexEnum(employeeInfoDto.getSex(), SexEnum.S).getValue());
            employeeUserInfo.setLeaveStatus(YNEnum.getValue(employeeInfoDto.getLeaveStatus(), YNEnum.N));
            employeeUserInfo.setIsDeleted(YNEnum.getValue(employeeInfoDto.getIsDeleted(), YNEnum.N));
            employeeUserInfoList.add(employeeUserInfo);
        }
        return employeeUserInfoList;
    }

    public static EmployeeInfoDto convertEmployeeUserInfo(EmployeeUserInfo employeeUserInfo) {
        EmployeeInfoDto employeeInfoDto = new EmployeeInfoDto();
        BeanUtils.copyProperties(employeeUserInfo, employeeInfoDto);
        employeeInfoDto.setIsDeleted(YNEnum.getName(employeeUserInfo.getIsDeleted(), YNEnum.N));
        employeeInfoDto.setSex(SexEnum.getSexEnum(employeeUserInfo.getSex(), SexEnum.S).name());
        employeeInfoDto.setLeaveStatus(YNEnum.getName(employeeUserInfo.getLeaveStatus(), YNEnum.N));
        employeeInfoDto.setBirthday(employeeUserInfo.getBirthday());
        employeeInfoDto.setVouchersNum(employeeUserInfo.getVouchersNum());
        employeeInfoDto.setCreated(DateUtil.unixTime2Date(employeeUserInfo.getCreated()));
        employeeInfoDto.setUpdated(DateUtil.unixTime2Date(employeeUserInfo.getUpdated()));
        employeeInfoDto.setInductionTime(DateUtil.unixTime2Date(employeeUserInfo.getInductionTime()));
        return employeeInfoDto;
    }

    public static List<EmployeeInfoDto> convertEmployeeUserInfoList(List<EmployeeUserInfo> employeeUserInfoList) {
        if (null == employeeUserInfoList) {
            return null;
        }
        List<EmployeeInfoDto> employeeInfoDtoList = new ArrayList<>();
        for (EmployeeUserInfo employeeUserInfo: employeeUserInfoList) {
            EmployeeInfoDto employeeInfoDto = new EmployeeInfoDto();
            BeanUtils.copyProperties(employeeUserInfo, employeeInfoDto);
            employeeInfoDto.setIsDeleted(YNEnum.getName(employeeUserInfo.getIsDeleted(), YNEnum.N));
            employeeInfoDto.setSex(SexEnum.getSexEnum(employeeUserInfo.getSex(), SexEnum.S).name());
            employeeInfoDto.setLeaveStatus(YNEnum.getName(employeeUserInfo.getLeaveStatus(), YNEnum.N));
            employeeInfoDto.setBirthday(employeeUserInfo.getBirthday());
            employeeInfoDto.setCreated(DateUtil.unixTime2Date(employeeUserInfo.getCreated()));
            employeeInfoDto.setUpdated(DateUtil.unixTime2Date(employeeUserInfo.getUpdated()));
            employeeInfoDto.setInductionTime(DateUtil.unixTime2Date(employeeUserInfo.getInductionTime()));
            employeeInfoDtoList.add(employeeInfoDto);
        }
        return employeeInfoDtoList;
    }
    
}
