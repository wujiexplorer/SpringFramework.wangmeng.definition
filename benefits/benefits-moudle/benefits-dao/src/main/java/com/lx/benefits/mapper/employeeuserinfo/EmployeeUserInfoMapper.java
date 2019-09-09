package com.lx.benefits.mapper.employeeuserinfo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.dto.admin.customized.EnterprNoticeReqDto;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeQueryBirthday;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeBirthdayCredit;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeBirthdayRecordDto;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfoExample;
import com.lx.benefits.bean.param.employee.EmployeeUserInfoParam;

public interface EmployeeUserInfoMapper {
    int countByExample(EmployeeUserInfoExample example);

    int deleteByExample(EmployeeUserInfoExample example);

    int deleteByPrimaryKey(Long employeeId);

    int insert(EmployeeUserInfo record);
    
    int batchInsert(@Param("employeeUserInfoList") List<EmployeeUserInfo> employeeUserInfoList);

    int insertSelective(EmployeeUserInfo record);

    List<EmployeeUserInfo> selectByExample(EmployeeUserInfoExample example);

    EmployeeUserInfo selectByPrimaryKey(Long employeeId);

    EmployeeUserInfo fetchOneByExample(EmployeeUserInfoExample example);

    int updateByExampleSelective(@Param("record") EmployeeUserInfo record, @Param("example") EmployeeUserInfoExample example);

    int updateByExample(@Param("record") EmployeeUserInfo record, @Param("example") EmployeeUserInfoExample example);

    int updateByPrimaryKeySelective(EmployeeUserInfo record);

    int updateByPrimaryKey(EmployeeUserInfo record);

    List<EmployeeUserInfo> selectByExampleList(EmployeeUserInfoExample example);

    List<EmployeeUserInfo> selectByParams(Map<String,Object> map);

    Integer  queryByParamCount(Map<String,Object> params);
    
    List<Long> selectEmployeeId(EmployeeQueryBirthday reqDto);
    
    List<Long> selectEmployeeIds(EmployeeQueryBirthday reqDto);
    
    List<EmployeeUserInfo> selectBirthdayList(EmployeeQueryBirthday reqDto);
    
    int birthdayListCount(EmployeeQueryBirthday reqDto);
    
    Integer employeeStatusSet(EmployeeBirthdayCredit req);
    
    int employeeBirthdayRemindStatusBatchSet(@Param(value = "employeeBirthdayList") List<EmployeeBirthdayCredit> employeeBirthdayList);
    
    Integer employeeBirthdayCreditSet(EmployeeBirthdayCredit req);
    
    int employeeBirthdayCreditBatchSet(@Param(value = "employeeBirthdayList") List<EmployeeBirthdayCredit> employeeBirthdayList);
    
    Integer insertEmployeeCreditInfo(EmployeeCreditInfo info);

    List<EmployeeUserInfo> findEmployeeUserInfosByEnterprId(Long enterprId);

    List<EmployeeUserInfo> findEmployeeUserInfosByName(Map<String,Object> params);
    
    List<EmployeeUserInfoParam> selectCurrentTimeBirthdayList(EmployeeQueryBirthday reqDto);
    
    Integer updateEmployeeCredit(Map<String,Object> map);
    
    Integer updateEnterprCredit(Map<String,Object> map);
    
    Integer updateEmployeeInfo(@Param("employeeIdList") List<BigInteger> employeeIdList);
    
    BigDecimal selectValidCredit(Map<String,Object> map);
    
    Integer insertBirthdayCreditRecord(Map<String,Object> map);
    
    Integer updateBirthdayCreditRecord(Map<String,Object> map);
    
    List<EmployeeBirthdayRecordDto> employeeBirthdayDistRecord(Map<String, Object> params);
    
    Integer birthdayDistRecordCount(Map<String, Object> params);
    
    EnterprNoticeReqDto selectEmailContent(BigInteger enterprId);
    
    List<EmployeeUserInfoParam> selectEmployeeBirthdayHangUp(BigInteger enterprId);
    
    List<EmployeeBirthdayRecordDto> selectEmployeeBirthdayRecord(BigInteger employeeId);
    
    List<EmployeeUserInfoParam> queryEnterprInfo(@Param("enterprId") Long enterprId,@Param("pageBean") PageBean pageBean);
    
    int countEmployeeInfo(@Param("enterprId") Long enterprId);
}
