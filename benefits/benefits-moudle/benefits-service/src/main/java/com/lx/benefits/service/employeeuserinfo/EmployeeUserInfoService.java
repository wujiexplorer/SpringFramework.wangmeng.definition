package com.lx.benefits.service.employeeuserinfo;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.LoginReqDto;
import com.lx.benefits.bean.dto.admin.account.ModifyPasswordReqDto;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeInfoDto;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeQueryBirthday;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeQueryReqDto;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.ImportReqDto;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeBirthdayCredit;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfoExample;
import com.lx.benefits.bean.util.SessionShopInfo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author luojie
 */
public interface EmployeeUserInfoService {

	JSONObject insert(EmployeeInfoDto employeeInfoDto);

	JSONObject batchImport(ImportReqDto reqDto);

	EmployeeInfoDto findByEmployeeId(Long employeeId, boolean needPassword);

    JSONObject find(EmployeeQueryReqDto reqDto);
    
    List<EmployeeInfoDto> select(EmployeeQueryReqDto reqDto);

	/**
	 * 用户登录
	 * 
	 * @param loginName
	 *            账号
	 * @param password
	 *            密码
	 * @param code
	 *            小程序openId
	 * @return
	 */
	SessionShopInfo login(String loginName, String password, String wxOpenId);

	int updateByPrimaryKeySelective(EmployeeUserInfo record);

	JSONObject updateByEmployeeId(EmployeeInfoDto employeeInfoDto);

	EmployeeUserInfo findByNoAndEnterprId(String employeeNo, Long enterprId);

	EmployeeUserInfo findByLoginName(String loginName);

	Integer queryByParamCount(Map<String, Object> params);

	List<EmployeeUserInfo> selectListByEnterprId(Long enterprId);

	List<EmployeeUserInfo> selectList(EmployeeUserInfoExample example);

	JSONObject bindMobile(LoginReqDto req);

	JSONObject findPasword(LoginReqDto req);
	
    JSONObject birthdayQuery(EmployeeQueryBirthday reqDto);
    
    JSONObject birthdayCreditSet(EmployeeBirthdayCredit req);
    
    int birthdayCreditBacthSet(List<EmployeeBirthdayCredit> EmployeeBirthdayList);

	int modifyPassword(ModifyPasswordReqDto req);

	List<EmployeeUserInfo> findEmployeeUserInfosByEnterprId(Long enterprId);

	List<EmployeeUserInfo> findEmployeeUserInfosByName(Map<String, Object> params);

	Boolean updateCredit(List<BigInteger> employeeIdList, List<BigInteger> enterprIdList, List<BigDecimal> birthdayCreditList, List<String> email,
			boolean flag);

	JSONObject employeeBirthdayDistRecord(Map<String, Object> params);

	SessionShopInfo wxLogin(String openId);

	/**
	 * 注册企业员工
	 * 
	 * @param loginName
	 *            账号
	 * @param employeeNo
	 *            员工号
	 * @param password
	 *            密码
	 * @param enterprId
	 *            企业ID
	 * @return
	 */
	EmployeeUserInfo registerEmployee(String loginName, String employeeNo, String password, Long enterprId);

	/**
	 * 第三方用户登录
	 * 
	 * @param token
	 * @param orgCode
	 * @return
	 */
	SessionShopInfo clientUserlogin(String token, String orgCode);

	public Integer countTotalEmployeesByExample();

	public Integer countLeaveEmployeesByExample();
	
	SessionShopInfo login(Long employeeId,String wxOpenId);

}
