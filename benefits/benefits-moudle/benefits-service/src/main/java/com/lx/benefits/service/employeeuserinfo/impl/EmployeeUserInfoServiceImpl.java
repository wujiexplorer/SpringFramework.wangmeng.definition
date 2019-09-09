package com.lx.benefits.service.employeeuserinfo.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.LoginReqDto;
import com.lx.benefits.bean.dto.admin.account.ModifyPasswordReqDto;
import com.lx.benefits.bean.dto.admin.customized.EnterprNoticeReqDto;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.EmployeeRecoveryReqDto;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeInfoDto;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeQueryBirthday;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeQueryReqDto;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.ImportReqDto;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeBirthdayCredit;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeBirthdayRecordDto;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.employeeimportdetail.EmplyeeImportDetail;
import com.lx.benefits.bean.entity.employeeimportinfo.EmplyeeImportInfo;
import com.lx.benefits.bean.entity.employeeleaveinfo.EmplyeeLeaveInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfoExample;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.membercenterinfo.MemberCenterInfo;
import com.lx.benefits.bean.entity.memberdetail.MemberDetail;
import com.lx.benefits.bean.entity.memberinfo.MemberInfo;
import com.lx.benefits.bean.entity.memunioninfo.MemUnionInfo;
import com.lx.benefits.bean.entity.user.WxUserOpenId;
import com.lx.benefits.bean.enums.CampaignType;
import com.lx.benefits.bean.enums.CreditSetEnum;
import com.lx.benefits.bean.enums.CreditType;
import com.lx.benefits.bean.enums.EmployeeRecoveryEnum;
import com.lx.benefits.bean.enums.SexEnum;
import com.lx.benefits.bean.enums.YNEnum;
import com.lx.benefits.bean.param.employee.EmployeeUserInfoParam;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.EasyExcelUtil;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.JsonUtils;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SendMailUtil;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionEnterpriseInfo;
import com.lx.benefits.bean.util.SessionShopInfo;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.constant.EmployeeUserConvertUtil;
import com.lx.benefits.dao.employeeimportdetail.EmplyeeImportDetailDao;
import com.lx.benefits.dao.employeeimportinfo.EmplyeeImportInfoDao;
import com.lx.benefits.dao.employeeleaveinfo.EmplyeeLeaveInfoDao;
import com.lx.benefits.dao.employeeuserinfo.EmployeeUserInfoDao;
import com.lx.benefits.dao.membercenterinfo.MemberCenterInfoDao;
import com.lx.benefits.dao.memberdetail.MemberDetailDao;
import com.lx.benefits.dao.memberinfo.MemberInfoDao;
import com.lx.benefits.dao.memunioninfo.MemUnionInfoDao;
import com.lx.benefits.mapper.creditoperateinfo.CreditOperateInfoMapper;
import com.lx.benefits.mapper.employeecreditinfo.EmployeeCreditInfoMapper;
import com.lx.benefits.mapper.employeeuserinfo.EmployeeUserInfoMapper;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.service.client.YibaoApiService;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.employeesequence.EmployeeSequenceService;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.enterprise.EnterpriseService;
import com.lx.benefits.service.user.WxUserOpenIdService;
import com.lx.benefits.utils.RedisUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luojie
 */
@Service
@Slf4j
public class EmployeeUserInfoServiceImpl implements EmployeeUserInfoService {

	@Autowired
	private EmployeeUserInfoDao employeeUserInfoDao;

	@Autowired
	private EmployeeSequenceService employeeSequenceService;

	@Autowired
	private EmplyeeImportInfoDao emplyeeImportInfoDao;

	@Autowired
	private EmplyeeImportDetailDao emplyeeImportDetailDao;

	@Autowired
	private EnterprUserInfoMapper enterprUserInfoMapper;

	@Autowired
	private MemberInfoDao memberInfoDao;

	@Autowired
	private MemberCenterInfoDao memberCenterInfoDao;

	@Autowired
	private EmplyeeLeaveInfoDao emplyeeLeaveInfoDao;

	@Autowired
	private MemUnionInfoDao memUnionInfoDao;

	@Autowired
	private MemberDetailDao memberDetailDao;

	@Autowired
	private EmployeeCreditInfoMapper employeeCreditInfoMapper;
	@Autowired
	private YibaoApiService yibaoApiService;

	private final static Logger logger = LoggerFactory.getLogger(EmployeeUserInfoServiceImpl.class);

	@Autowired
	private EmployeeUserInfoMapper employeeUserInfoMapper;
	@Autowired
	private CreditOperateInfoMapper creditOperateInfoMapper;
	@Autowired
	private WxUserOpenIdService wxUserOpenIdService;
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private EmployeeCreditInfoService employeeCreditInfoService;
	@Autowired
	private EnterpriseService enterpriseService;

	@Override
	public JSONObject insert(EmployeeInfoDto employeeInfoDto) {
		if (null == employeeInfoDto) {
			return Response.fail("员工信息不能为空!");
		}
		if (null == employeeInfoDto.getEmployeeNo() || employeeInfoDto.getEmployeeNo().trim().isEmpty()) {
			return Response.fail("员工工号不能为空!");
		}
		employeeInfoDto.setEmployeeNo(employeeInfoDto.getEmployeeNo().trim());

		if (null == employeeInfoDto.getLoginName() || employeeInfoDto.getLoginName().trim().isEmpty()) {
			return Response.fail("员工登录用户名不能为空!");
		}
		employeeInfoDto.setLoginName(employeeInfoDto.getLoginName().trim());

		if (null == employeeInfoDto.getPassword() || employeeInfoDto.getPassword().trim().isEmpty()) {
			return Response.fail("员工登录密码不能为空!");
		}
		employeeInfoDto.setPassword(employeeInfoDto.getPassword().trim());

		if (null == employeeInfoDto.getEmployeeName() || employeeInfoDto.getEmployeeName().trim().isEmpty()) {
			return Response.fail("员工姓名不能为空!");
		}
		if (null == employeeInfoDto.getEnterprId() || employeeInfoDto.getEnterprId() < 1) {
			return Response.fail("员工所属企业ID不能为空!");
		}
		if (null == employeeInfoDto.getBirthday() || employeeInfoDto.getBirthday().isEmpty()) {
			return Response.fail("员工生日不能为空!");
		}
		if (SexEnum.isVaildSexEnum(employeeInfoDto.getSex())) {
			return Response.fail("无效的员工性别!");
		}
		if (null == employeeInfoDto.getInductionTime() || employeeInfoDto.getInductionTime().isEmpty()) {
			return Response.fail("入职日期不能为空!");
		}
		if (null == employeeInfoDto.getPosition() || employeeInfoDto.getPosition().isEmpty()) {
			return Response.fail("员工职级不能为空!");
		}
		if (null == employeeInfoDto.getEmail() || employeeInfoDto.getEmail().isEmpty()) {
			return Response.fail("员工E-Mail不能为空!");
		}
		if (null == employeeInfoDto.getMobile() || employeeInfoDto.getMobile().isEmpty()) {
			return Response.fail("员工电话不能为空!");
		}

		EmployeeUserInfo employeeUserInfo = EmployeeUserConvertUtil.convertEmployeeInfoDto(employeeInfoDto);
		// 生成密码
		String secret = EncryptUtil.generateSecret();
		employeeUserInfo.setSecret(secret);
		employeeUserInfo.setPassword(EncryptUtil.encodePassword(employeeInfoDto.getPassword().trim(), secret));
		// 获取employeeId
		Long employeeId = employeeSequenceService.getEmployeeId();
		if (null == employeeId || employeeId < 1) {
			return Response.fail("获取员工ID信息失败!");
		}
		// set
		employeeUserInfo.setEmployeeId(employeeId);
		Long lastInsertId = employeeUserInfoDao.insert(employeeUserInfo);
		if (employeeId.equals(lastInsertId)) {
			return Response.succ(employeeId);
		}
		return Response.fail("员工信息添加失败!");
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public JSONObject batchImport(ImportReqDto reqDto) {
		if (null == reqDto || null == reqDto.getFilePath() || reqDto.getFilePath().isEmpty()) {
			return Response.fail("员工Excel表格链接不能为空");
		}
		String filePath = reqDto.getFilePath();
		SessionEnterpriseInfo sessionEnterpriseInfo = SessionContextHolder.getSessionEnterpriseInfo();
		Long enterprId = sessionEnterpriseInfo.getEnterprId();
		String loginName = sessionEnterpriseInfo.getLoginName();

		// 1. 解析文件中的员工信息
		List<Object> employeeList;
		try {
			UrlResource resource = new UrlResource(filePath);
			String suffix = filePath.substring(filePath.lastIndexOf(".") + 1);
			ExcelTypeEnum excelTypeEnum = "xlsx".equalsIgnoreCase(suffix) ? ExcelTypeEnum.XLSX : ExcelTypeEnum.XLS;
			employeeList = EasyExcelUtil.readExcelWithModel(resource.getInputStream(), EmployeeInfoDto.class, excelTypeEnum);
		} catch (Exception e) {
			logger.error("企业员工文件解析出错：", e);
			return Response.fail("企业员工文件解析出错!");
		}
		if (CollectionUtils.isEmpty(employeeList)) {
			return Response.fail("excel表格数据为空");
		}

		// 2. excel表格信息组装&数据验证
		List<EmployeeUserInfo> employeeUserInfoList = new ArrayList<>();
		int line = 2;
		int validEmployeeNum = 0;
		for (Object employee : employeeList) {
			EmployeeInfoDto employeeInfoDto = (EmployeeInfoDto) employee;
			if (null == employeeInfoDto) {
				return Response.fail(String.format("第 %s 行员工信息不能为空!", line));
			}
			if (null == employeeInfoDto.getEmployeeName() || employeeInfoDto.getEmployeeName().trim().isEmpty()) {
				return Response.fail(String.format("第 %s 行员工姓名不能为空!", line));
			}
			if (null == employeeInfoDto.getEmployeeNo() || employeeInfoDto.getEmployeeNo().trim().isEmpty()) {
				return Response.fail(String.format("第 %s 行员工工号不能为空!", line));
			}
			if (null == employeeInfoDto.getLoginName() || employeeInfoDto.getLoginName().trim().isEmpty()) {
				return Response.fail(String.format("第 %s 行员工登录用户名不能为空!", line));
			}

			if (null == employeeInfoDto.getPassword() || employeeInfoDto.getPassword().trim().isEmpty()) {
				return Response.fail(String.format("第 %s 行员工登录密码不能为空!", line));
			}
			if (null == employeeInfoDto.getMobile() || employeeInfoDto.getMobile().trim().isEmpty()) {
				/*
				 * return Response.fail(String.format("第 %s 行员工电话不能为空不能为空!",
				 * line));
				 */
				employeeInfoDto.setMobile("");
			}
			if (null == employeeInfoDto.getEmail() || employeeInfoDto.getEmail().trim().isEmpty()) {
				/*
				 * return Response.fail(String.format("第 %s 行员工E-Mail不能为空!",
				 * line));
				 */
				employeeInfoDto.setEmail("");
			}
			if (null == employeeInfoDto.getPosition() || employeeInfoDto.getPosition().trim().isEmpty()) {
				/*
				 * return Response.fail(String.format("第 %s 行员工职级不能为空!", line));
				 */
				employeeInfoDto.setPosition("");
			}
			if (null == employeeInfoDto.getInductionTime() || employeeInfoDto.getInductionTime().trim().isEmpty()) {
				/*
				 * return Response.fail(String.format("第 %s 行入职日期不能为空!", line));
				 */
				employeeInfoDto.setInductionTime("0");
			}
			if (null == employeeInfoDto.getBirthday() || employeeInfoDto.getBirthday().trim().isEmpty()) {
				/*
				 * return Response.fail(String.format("第 %s 行员工生日不能为空!", line));
				 */
				employeeInfoDto.setBirthday("");
			}
			if (!SexEnum.isVaildSexEnum(employeeInfoDto.getSex())) {
				// return Response.fail(String.format("第 %s 行员工性别无效!", line));
			}
			EmployeeUserInfo userInfoNo = employeeUserInfoDao.findOne(employeeInfoDto.getEmployeeNo(), enterprId);
			if (userInfoNo != null) {
				return Response.fail(String.format("第 %s 行员工工号(%s)重复!", line, employeeInfoDto.getEmployeeNo()));
			}

			EmployeeUserInfoExample employeeUserInfoExample = new EmployeeUserInfoExample();
			employeeUserInfoExample.createCriteria().andLoginNameEqualTo(employeeInfoDto.getLoginName());
			EmployeeUserInfo userInfoLoginName = employeeUserInfoDao.fetchOne(employeeUserInfoExample);
			if (userInfoLoginName != null) {
				return Response.fail(String.format("第 %s 行员工登录用户名(%s)重复，请修改!", line, employeeInfoDto.getLoginName()));
			}

			Integer leaveStatus = YNEnum.getValue(employeeInfoDto.getLeaveStatus(), null);
			if (null == leaveStatus) {
				/** 无离职状态，默认在职 */
				leaveStatus = YNEnum.N.val();
				employeeInfoDto.setLeaveStatus(YNEnum.getName(leaveStatus, null));
			}
			// 只有当是在职状态才能加企业员工数
			if (YNEnum.N.val().equals(leaveStatus)) {
				validEmployeeNum++;
			}
			line++;
			// 设置企业id
			employeeInfoDto.setEnterprId(enterprId);
			EmployeeUserInfo employeeUserInfo = EmployeeUserConvertUtil.convertEmployeeInfoDto(employeeInfoDto);
			// 生成密码
			String secret = EncryptUtil.generateSecret();
			employeeUserInfo.setSecret(secret);
			employeeUserInfo.setPassword(EncryptUtil.encodePassword(employeeUserInfo.getPassword().trim(), secret));
			employeeUserInfoList.add(employeeUserInfo);
		}

		int employeeNum = employeeUserInfoList.size();
		if (employeeNum < 1) {
			return Response.fail("至少需要导入一条员工数据!");
		}
		if (employeeNum > 5000) {
			return Response.fail("每次最多只能导入5000条员工数据!");
		}

		// 3. 获取全局员工id
		List<Long> employeeIdList = employeeSequenceService.batchGetEmployeeIdList(employeeUserInfoList.size());
		if (null == employeeIdList || employeeIdList.isEmpty() || employeeIdList.size() != employeeUserInfoList.size()) {
			return Response.fail("批量获取员工ID信息失败!");
		}
		List<MemberInfo> memberInfoList = new ArrayList<>();
		List<MemberCenterInfo> memberCenterInfoList = new ArrayList<>();
		List<MemUnionInfo> memUnionInfoList = new ArrayList<>();
		List<MemberDetail> memberDetailList = new ArrayList<>();
		int index = 0;
		for (EmployeeUserInfo employeeUserInfo : employeeUserInfoList) {
			Long employeeId = employeeIdList.get(index);
			employeeUserInfo.setEmployeeId(employeeId);

			// 组装同步到mem_member_info表需要的数据 id, nick_name, mobile, email,
			// password, salt, user_name, term_of_validity
			MemberInfo memberInfo = new MemberInfo();
			memberInfo.setId(employeeId);
			memberInfo.setNick_name(employeeUserInfo.getEmployeeName());
			memberInfo.setMobile(employeeUserInfo.getMobile());
			memberInfo.setEmail(employeeUserInfo.getEmail());
			String salt = EncryptUtil.getYianSalt();
			// yian密码
			String password = EncryptUtil.encodeYianPassword(employeeUserInfo.getPassword(), salt);
			memberInfo.setPassword(password);
			memberInfo.setSalt(salt);
			memberInfo.setUser_name(employeeUserInfo.getLoginName());
			Long currentTime = DateUtil.getCurrentUnixTime();
			// 86400 * 365 * 10
			memberInfo.setTerm_of_validity(DateUtil.unixTime2Timestamp(currentTime + 315360000L));
			memberInfoList.add(memberInfo);

			// 组装同步到mem_member_center_info表的数据 id, mobile, password, salt,
			// create_user_id, modify_user_id
			MemberCenterInfo memberCenterInfo = new MemberCenterInfo();
			memberCenterInfo.setId(employeeId);
			memberCenterInfo.setCreate_user_id(enterprId);
			memberCenterInfo.setModify_user_id(enterprId);
			memberCenterInfo.setMobile(employeeUserInfo.getMobile());
			memberCenterInfo.setSalt(salt);
			memberCenterInfo.setPassword(password);
			memberCenterInfoList.add(memberCenterInfo);

			// 组装同步到mem_union_info表的数据mem_id, union_val, org_code, ee_no,
			// union_data
			MemUnionInfo memUnionInfo = new MemUnionInfo();
			String employeeNo = employeeUserInfo.getEmployeeNo();
			String unionVal = String.format("%s-%s", loginName, employeeNo);
			memUnionInfo.setMem_id(employeeId);
			memUnionInfo.setEe_no(employeeNo);
			memUnionInfo.setOrg_code(loginName);
			memUnionInfo.setUnion_val(unionVal);
			Map<String, String> unionDataMap = new HashMap<>();
			unionDataMap.put("eeNo", employeeNo);
			unionDataMap.put("orgCode", loginName);
			unionDataMap.put("ee_no", employeeNo);
			unionDataMap.put("name", employeeUserInfo.getEmployeeName());
			unionDataMap.put("org_code", loginName);
			memUnionInfo.setUnion_data(JsonUtils.getJsonString(unionDataMap));
			memUnionInfoList.add(memUnionInfo);

			// 组装同步到mem_member_detail表的数据uid, birthday, true_name
			MemberDetail memberDetail = new MemberDetail();
			memberDetail.setUid(employeeId);
			memberDetail.setBirthday(DateUtil.stringToDate(employeeUserInfo.getBirthday(), "yyyy-mm-dd"));
			memberDetail.setTrue_name(employeeUserInfo.getEmployeeName());
			memberDetailList.add(memberDetail);

			index++;
		}

		// 4. 批量新增
		try {
			int rowCount = employeeUserInfoDao.batchInsert(employeeUserInfoList);
			if (rowCount < 1) {
				return Response.fail("批量添加员工信息失败!");
			}

			// 5. 批量新增mem_member_info
			memberInfoDao.batchInsert(memberInfoList);

			// 6. 批量新增mem_member_center_info
			memberCenterInfoDao.batchInsert(memberCenterInfoList);

			// 7. 批量新增mem_union_info信息
			memUnionInfoDao.batchInsert(memUnionInfoList);

			// 8. 批量新增mem_member_detail信息
			memberDetailDao.batchInsert(memberDetailList);

			// 9. 记录导入记录
			EmplyeeImportInfo emplyeeImportInfo = new EmplyeeImportInfo();
			emplyeeImportInfo.setEnterprId(enterprId);
			emplyeeImportInfo.setOptUserId(enterprId);
			emplyeeImportInfo.setImportCount((long) employeeNum);
			emplyeeImportInfo.setFileUrl(filePath);
			// 10. 记录导入记录
			Long importId = emplyeeImportInfoDao.insertSelective(emplyeeImportInfo);
			// 11. 记录导入记录明细
			List<EmplyeeImportDetail> emplyeeImportDetailList = new ArrayList<>();
			for (EmployeeUserInfo employeeUserInfo : employeeUserInfoList) {
				EmplyeeImportDetail emplyeeImportDetail = new EmplyeeImportDetail();
				emplyeeImportDetail.setEmployeeId(employeeUserInfo.getEmployeeId());
				emplyeeImportDetail.setImportId(importId);
				emplyeeImportDetailList.add(emplyeeImportDetail);
			}
			emplyeeImportDetailDao.batchInsert(emplyeeImportDetailList);
			// 12. 更新企业员工总数
			if (validEmployeeNum > 0) {
				enterprUserInfoMapper.incrEmployeeNum(enterprId, validEmployeeNum);
			}
			return Response.succ(true);
		} catch (Exception e) {
			logger.error("batchInsert exception", e);
			throw new RuntimeException("批量导入员工信息出现异常!");
		}
	}

	@Override
	public EmployeeInfoDto findByEmployeeId(Long employeeId, boolean needPassword) {
		if (null == employeeId || employeeId < 1) {
			return null;
		}
		EmployeeUserInfo employeeUserInfo = employeeUserInfoDao.selectByPrimaryKey(employeeId);
		if (null != employeeUserInfo && employeeId.equals(employeeUserInfo.getEmployeeId())) {
			EmployeeInfoDto employeeInfoDto = EmployeeUserConvertUtil.convertEmployeeUserInfo(employeeUserInfo);
			if (!needPassword) {
				employeeInfoDto.setPassword(null);
				employeeInfoDto.setSecret(null);
			}
			return employeeInfoDto;
		}
		return null;
	}

	@Override
	public JSONObject find(EmployeeQueryReqDto reqDto) {
		Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
		EmployeeUserInfoExample example = new EmployeeUserInfoExample();
		reqDto = null == reqDto ? new EmployeeQueryReqDto() : reqDto;
		example.createCriteria().andEnterprIdEqualTo(enterprId);
		if (null != reqDto.getEmployeeNo() && !reqDto.getEmployeeNo().trim().isEmpty()) {
			example.createCriteria().andEmployeeNoLike("%" + reqDto.getEmployeeNo().trim() + "%");
		}
		if (null != reqDto.getEmployeeEmail() && !reqDto.getEmployeeEmail().trim().isEmpty()) {
			example.createCriteria().andEmailLike("%" + reqDto.getEmployeeEmail().trim() + "%");
		}
		if (null != reqDto.getEmployeeMobile() && !reqDto.getEmployeeMobile().trim().isEmpty()) {
			example.createCriteria().andMobileLike("%" + reqDto.getEmployeeMobile().trim() + "%");
		}
		if (null != reqDto.getEmployeeName() && !reqDto.getEmployeeName().trim().isEmpty()) {
			example.createCriteria().andEmployeeNameLike("%" + reqDto.getEmployeeName().trim() + "%");
		}

		example.setPage(reqDto.getPage());
		example.setPageSize(reqDto.getPageSize());
		example.setOrderByClause("employeeId DESC");

		List<EmployeeUserInfo> employeeUserInfoList = employeeUserInfoDao.find(example);
		Integer rowCount = employeeUserInfoDao.count(example);
		List<Long> employeeIdList = new ArrayList<>();

		for (EmployeeUserInfo employeeUserInfo : employeeUserInfoList) {
			employeeIdList.add(employeeUserInfo.getEmployeeId());
		}
		Map<Long, EmployeeCreditInfo> ordMap = new HashMap<>();
		Map<Long, EmployeeCreditInfo> festivalMap = new HashMap<>();
		if (null != employeeIdList && employeeIdList.size() > 0) {
			// 普通积分
			List<EmployeeCreditInfo> ordinaryList = employeeCreditInfoMapper.totalCreaditByParam(employeeIdList, 1);
			ordMap = ordinaryList.stream().collect(Collectors.toMap(EmployeeCreditInfo::getEmployeeId, creditInfo -> creditInfo));
			// 节日积分
			List<EmployeeCreditInfo> festivalList = employeeCreditInfoMapper.totalCreaditByParam(employeeIdList, 2);
			festivalMap = festivalList.stream().collect(Collectors.toMap(EmployeeCreditInfo::getEmployeeId, creditInfo -> creditInfo));
		}

		List<EmployeeInfoDto> employeeInfoDtoList = EmployeeUserConvertUtil.convertEmployeeUserInfoList(employeeUserInfoList);
		for (EmployeeInfoDto employeeInfoDto : employeeInfoDtoList) {
			// 普通积分
			EmployeeCreditInfo ordCredit = ordMap.get(employeeInfoDto.getEmployeeId());
			if (null != ordCredit) {
				employeeInfoDto.setOrdCredit(ordCredit.getCredit());
			}
			// 节日积分
			EmployeeCreditInfo festCredit = festivalMap.get(employeeInfoDto.getEmployeeId());
			if (null != festCredit) {
				employeeInfoDto.setFestCredit(festCredit.getCredit());
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", employeeInfoDtoList);
		jsonObject.put("count", rowCount);
		return Response.succ(jsonObject);
	}

	@Override
	public SessionShopInfo login(String loginName, String password, String wxOpenId) {
		EmployeeUserInfoExample example;
		if(loginName != null && !loginName.trim().isEmpty()) {
			example = new EmployeeUserInfoExample();
			example.createCriteria().andLoginNameEqualTo(loginName);
		}else if(!StringUtils.isEmpty(wxOpenId)) {
			WxUserOpenId wxUserOpenId = wxUserOpenIdService.getByOpenId(wxOpenId);
			if(wxUserOpenId==null) {
				throw new BusinessException("登录的员工信息不存在!");
			}
			example = new EmployeeUserInfoExample();
			example.createCriteria().andEmployeeIdEqualTo(wxUserOpenId.getUserId());
		}else {
			return null;
		}
		EmployeeUserInfo employeeUserInfo = employeeUserInfoDao.fetchOne(example);
		if (employeeUserInfo == null) {
			throw new BusinessException("登录的员工信息不存在!");
		}
		Integer isDeleted = employeeUserInfo.getIsDeleted();
		if (Integer.valueOf(1).equals(isDeleted)) {
			throw new BusinessException("登录员工已被禁用,请联系网站管理员!");
		}
		if(!StringUtils.isEmpty(loginName)) {
			String encodePassword = EncryptUtil.encodePassword(password, employeeUserInfo.getSecret());
			if (!encodePassword.equals(employeeUserInfo.getPassword())) {
				throw new BusinessException("用户名或者密码错误,请重新登录!");
			}
		}
		SessionShopInfo sessionShopInfo = new SessionShopInfo();
		BeanUtils.copyProperties(employeeUserInfo, sessionShopInfo);
		if (StringUtils.isEmpty(wxOpenId)) {
			sessionShopInfo.setFromWeb(true);
		} else {// 小程序绑定
			sessionShopInfo.setFromWeb(false);
			sessionShopInfo.setWxOpenId(wxOpenId);
			if(!StringUtils.isEmpty(loginName)) {
				wxUserOpenIdService.bindUser(employeeUserInfo.getEmployeeId(), wxOpenId);
			}
		}
		return sessionShopInfo;
	}

	@Override
	public int updateByPrimaryKeySelective(EmployeeUserInfo record) {
		return employeeUserInfoDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public JSONObject updateByEmployeeId(EmployeeInfoDto employeeInfoDto) {
		if (null == employeeInfoDto) {
			return Response.fail("员工信息不能为空");
		}
		Long employeeId = employeeInfoDto.getEmployeeId();
		if (null == employeeId || employeeId < 1) {
			return Response.fail("无效的员工标示ID");
		}
		Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
		// 不支持修改的数据全部设置为null
		employeeInfoDto.setEnterprId(null);
		employeeInfoDto.setSecret(null);
		if (null != employeeInfoDto.getPassword() && !employeeInfoDto.getPassword().isEmpty()) {
			// 需要重新set值的字段
			String secret = EncryptUtil.generateSecret();
			employeeInfoDto.setPassword(EncryptUtil.encodePassword(employeeInfoDto.getPassword(), secret));
			employeeInfoDto.setSecret(secret);
		} else {
			employeeInfoDto.setPassword(null);
		}

		if (null != employeeInfoDto.getEmployeeNo()) {
			if (employeeInfoDto.getEmployeeNo().trim().isEmpty()) {
				return Response.fail("员工工号不能为空!");
			}
			employeeInfoDto.setEmployeeNo(employeeInfoDto.getEmployeeNo().trim());
		}

		if (null != employeeInfoDto.getLoginName()) {
			if (employeeInfoDto.getLoginName().trim().isEmpty()) {
				return Response.fail("员工登录用户名不能为空!");
			}
			employeeInfoDto.setLoginName(employeeInfoDto.getLoginName().trim());
		}

		if (null != employeeInfoDto.getEmployeeName()) {
			if (employeeInfoDto.getEmployeeName().trim().isEmpty()) {
				return Response.fail("员工姓名不能为空!");
			}
		}
		if (null == enterprId || enterprId < 1) {
			return Response.fail("员工所属企业ID不能为空!");
		}
		if (null != employeeInfoDto.getBirthday()) {
			if (employeeInfoDto.getBirthday().isEmpty()) {
				return Response.fail("员工生日不能为空!");
			}
		}
		if (null != employeeInfoDto.getSex()) {
			if (!SexEnum.isVaildSexEnum(employeeInfoDto.getSex())) {
				return Response.fail("无效的员工性别!");
			}
		}

		if (null != employeeInfoDto.getInductionTime()) {
			if (employeeInfoDto.getInductionTime().isEmpty()) {
				return Response.fail("入职日期不能为空!");
			}
		}
		if (null != employeeInfoDto.getPosition()) {
			if (employeeInfoDto.getPosition().isEmpty()) {
				return Response.fail("员工职级不能为空!");
			}
		}
		if (null != employeeInfoDto.getEmail()) {
			if (employeeInfoDto.getEmail().isEmpty()) {
				return Response.fail("员工E-Mail不能为空!");
			}
		}
		if (null != employeeInfoDto.getMobile()) {
			if (employeeInfoDto.getMobile().isEmpty()) {
				return Response.fail("员工电话不能为空!");
			}
		}
		String leaveStatus = employeeInfoDto.getLeaveStatus();
		if (null != leaveStatus) {
			if (null == YNEnum.getValue(leaveStatus, null)) {
				return Response.fail("员工职场状态无效!");
			}
		}
		EmployeeUserInfo oriEmployeeUserInfo = employeeUserInfoDao.selectByPrimaryKey(employeeId);

		if (null == oriEmployeeUserInfo) {
			return Response.fail("被编辑的员工不存在!");
		}
		if (!enterprId.equals(oriEmployeeUserInfo.getEnterprId())) {
			return Response.fail("您无权操作非本公司员工信息!");
		}

		EmployeeUserInfo employeeUserInfo = EmployeeUserConvertUtil.convertEmployeeInfoDto(employeeInfoDto);
		Integer rowCount = employeeUserInfoDao.updateByPrimaryKey(employeeUserInfo);
		if (null != rowCount && rowCount > 0) {
			if (null != leaveStatus) {
				Integer oriLeaveStatus = oriEmployeeUserInfo.getLeaveStatus();
				Integer nowLeaveStatus = YNEnum.getValue(leaveStatus, null);
				if (!nowLeaveStatus.equals(oriLeaveStatus)) {
					// 加/减员工数量
					if (YNEnum.Y.val().equals(nowLeaveStatus)) {// 离职
						enterprUserInfoMapper.reduceEmployeeNum(enterprId, 1);
						// 回收积分
						EnterprUserInfo enterprUserInfo = enterprUserInfoMapper.selectByPrimaryKey(enterprId);
						if (enterprUserInfo.getLeaveCredit() == 1) {
							log.info("员工离职，回收员工积分, enterprId={}, employeeId={}", enterprId, employeeUserInfo.getEmployeeId());
							EmployeeRecoveryReqDto reqDto = new EmployeeRecoveryReqDto();
							reqDto.setEnterprId(enterprId);
							reqDto.setEmployeeId(employeeUserInfo.getEmployeeId());
							reqDto.setCreditStatus(EmployeeRecoveryEnum.All.getValue());
							reqDto.setRemark("离职员工到期，回收积分");
							employeeCreditInfoService.recoveryEmployee(reqDto);
						}
					} else {
						enterprUserInfoMapper.incrEmployeeNum(enterprId, 1);
					}
					// 记录员工离职记录
					EmplyeeLeaveInfo emplyeeLeaveInfo = new EmplyeeLeaveInfo();
					emplyeeLeaveInfo.setEmployeeId(employeeId);
					emplyeeLeaveInfo.setEnterprId(enterprId);
					emplyeeLeaveInfo.setOptUserId(enterprId);
					emplyeeLeaveInfo.setLeaveStatus(YNEnum.getValue(leaveStatus, null));
					emplyeeLeaveInfoDao.insertSelective(emplyeeLeaveInfo);
				}
			}
			// 修改mem_member_info
			// 组装同步到mem_member_info表需要的数据 id, nick_name, mobile, email,
			// password, salt, user_name, term_of_validity
			MemberInfo memberInfo = new MemberInfo();
			memberInfo.setId(employeeId);
			memberInfo.setNick_name(employeeUserInfo.getEmployeeName());
			memberInfo.setMobile(employeeUserInfo.getMobile());
			memberInfo.setEmail(employeeUserInfo.getEmail());
			String salt = EncryptUtil.getYianSalt();
			// yian密码
			String password = EncryptUtil.encodeYianPassword(employeeUserInfo.getPassword(), salt);
			memberInfo.setPassword(password);
			memberInfo.setSalt(salt);
			memberInfo.setUser_name(employeeUserInfo.getLoginName());
			Long currentTime = DateUtil.getCurrentUnixTime();
			// 86400 * 365 * 10
			memberInfo.setTerm_of_validity(DateUtil.unixTime2Timestamp(currentTime + 315360000L));
			memberInfoDao.updateByPrimaryKeySelective(memberInfo);

			String orgCode = SessionContextHolder.getSessionEnterpriseInfo().getLoginName();
			// 修改mem_union_info信息
			// 组装同步到mem_union_info表的数据mem_id, union_val, org_code, ee_no,
			// union_data
			MemUnionInfo memUnionInfo = new MemUnionInfo();
			String employeeNo = employeeUserInfo.getEmployeeNo();
			if (StringUtils.isEmpty(employeeNo)) {
				employeeNo = oriEmployeeUserInfo.getEmployeeNo();
			}
			String unionVal = String.format("%s-%s", orgCode, employeeNo);
			memUnionInfo.setMem_id(employeeId);
			memUnionInfo.setEe_no(employeeNo);
			memUnionInfo.setOrg_code(orgCode);
			memUnionInfo.setUnion_val(unionVal);
			Map<String, String> unionDataMap = new HashMap<>();
			unionDataMap.put("eeNo", employeeNo);
			unionDataMap.put("orgCode", orgCode);
			unionDataMap.put("ee_no", employeeNo);
			unionDataMap.put("name", employeeUserInfo.getEmployeeName());
			unionDataMap.put("org_code", orgCode);
			memUnionInfo.setUnion_data(JsonUtils.getJsonString(unionDataMap));
			memUnionInfoDao.updateByMemId(memUnionInfo);

			return Response.succ(employeeUserInfo.getEmployeeId());
		}
		return Response.fail("员工信息修改失败!");
	}

	@Override
	public EmployeeUserInfo findByNoAndEnterprId(String employeeNo, Long enterprId) {
		EmployeeUserInfoExample employeeUserInfoExample = new EmployeeUserInfoExample();
		employeeUserInfoExample.createCriteria().andEmployeeNoEqualTo(employeeNo).andEnterprIdEqualTo(enterprId);
		List<EmployeeUserInfo> employeeUserInfoList = employeeUserInfoDao.find(employeeUserInfoExample);
		if (employeeUserInfoList.size() == 1) {
			return employeeUserInfoList.get(0);
		}
		return null;
	}

	@Override
	public EmployeeUserInfo findByLoginName(String loginName) {
		EmployeeUserInfoExample example = new EmployeeUserInfoExample();
		example.createCriteria().andLoginNameEqualTo(loginName);
		List<EmployeeUserInfo> employeeUserInfoList = employeeUserInfoDao.find(example);
		if (employeeUserInfoList.size() == 1) {
			return employeeUserInfoList.get(0);
		}
		return null;
	}

	@Override
	public Integer queryByParamCount(Map<String, Object> params) {
		return employeeUserInfoMapper.queryByParamCount(params);
	}

	@Override
	public List<EmployeeUserInfo> selectListByEnterprId(Long enterprId) {
		EmployeeUserInfoExample example = new EmployeeUserInfoExample();
		example.createCriteria().andEnterprIdEqualTo(enterprId);
		return employeeUserInfoMapper.selectByExampleList(example);
	}

	@Override
	public List<EmployeeUserInfo> selectList(EmployeeUserInfoExample example) {
		return employeeUserInfoMapper.selectByExampleList(example);
	}

	@Override
	public JSONObject bindMobile(LoginReqDto req) {
		String mobile = req.getMobile();
		if (null == mobile || StringUtil.isEmpty(mobile)) {
			return Response.fail("手机号不能为空");
		}
		String smsCode = req.getSmsCode();
		if (null == smsCode || StringUtil.isEmpty(smsCode)) {
			return Response.fail("短信验证码不能为空");
		}
		Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
		if (null == employeeId || employeeId < 1) {
			return Response.fail("登录失效");
		}
		EmployeeUserInfo employeeUserInfo = employeeUserInfoMapper.selectByPrimaryKey(employeeId);
		if (null == employeeUserInfo) {
			return Response.fail("用户不存在");
		}
		// if (!StringUtil.isEmpty(employeeUserInfo.getMobile())) {
		// return Response.fail("不能重复绑定手机号");
		// }
		String redisSmsCode = redisUtils.get(mobile);
		if (!smsCode.equals(redisSmsCode)) {
			return Response.fail("验证码错误");
		}
		redisUtils.delete(employeeUserInfo.getMobile());
		EmployeeUserInfo updatedEmployeeUserInfo = new EmployeeUserInfo();
		updatedEmployeeUserInfo.setEmployeeId(employeeId);
		updatedEmployeeUserInfo.setMobile(mobile);
		int update = employeeUserInfoMapper.updateByPrimaryKeySelective(updatedEmployeeUserInfo);
		if (update > 0) {
			return Response.succ("绑定手机成功");
		}
		return Response.fail("绑定手机失败");
	}

	@Override
	public JSONObject findPasword(LoginReqDto req) {
		String mobile = req.getMobile();
		if (null == mobile || StringUtil.isEmpty(mobile)) {
			return Response.fail("手机号不能为空");
		}
		String password = req.getPassword();
		if (null == password || StringUtil.isEmpty(password)) {
			return Response.fail("密码不能为空");
		}
		String confimPwd = req.getConfirmPwd();
		if (null == confimPwd || StringUtil.isEmpty(confimPwd)) {
			return Response.fail("确认密码不能为空");
		}
		if (!password.equals(confimPwd)) {
			return Response.fail("确认密码不一致");
		}
		String smsCode = req.getSmsCode();
		if (null == smsCode || StringUtil.isEmpty(smsCode)) {
			return Response.fail("短信验证码不能为空");
		}
		Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
		if (null == employeeId || employeeId < 1) {
			return Response.fail("登录失效");
		}
		EmployeeUserInfo employeeUserInfo = employeeUserInfoMapper.selectByPrimaryKey(employeeId);
		if (null == employeeUserInfo) {
			return Response.fail("用户不存在");
		}
		if (!employeeUserInfo.getMobile().equals(mobile)) {
			return Response.fail("手机号不是绑定手机");
		}
		String redisSmsCode = redisUtils.get(mobile);
		if (!smsCode.equals(redisSmsCode)) {
			return Response.fail("验证码错误");
		}
		redisUtils.delete(employeeUserInfo.getMobile());
		EmployeeUserInfoExample example = new EmployeeUserInfoExample();
		example.createCriteria().andEmployeeIdEqualTo(employeeId);
		String encodePassword = EncryptUtil.encodePassword(password, employeeUserInfo.getSecret());
		employeeUserInfo.setPassword(encodePassword);
		int update = employeeUserInfoMapper.updateByExample(employeeUserInfo, example);
		if (update > 0) {
			return Response.succ("找回密码成功");
		}
		return Response.fail("找回密码失败");
	}

	@Override
	public int modifyPassword(ModifyPasswordReqDto req) {
		SessionShopInfo sessionEmployeeInfo = SessionContextHolder.getSessionEmployeeInfo();
		EmployeeUserInfo employeeUserInfo = employeeUserInfoMapper.selectByPrimaryKey(sessionEmployeeInfo.getEmployeeId());
		if (null == employeeUserInfo) {
			throw new BusinessException("用户不存在，请重新登录");
		}
		String oldPassword = req.getOldPassword();
		if (oldPassword == null || StringUtils.isEmpty(oldPassword = oldPassword.trim())) {
			throw new BusinessException("请输入正确的登录密码");
		}
		String newPassword = req.getPassword();
		if (StringUtil.isEmpty(newPassword) || (newPassword = newPassword.trim()).length() < 6) {
			throw new BusinessException("请输入六位以上的新密码");
		}
		if (oldPassword.equals(newPassword)) {// 新旧密码一致
			throw new BusinessException("新密码和旧密码不能相同");
		}
		String loginName = sessionEmployeeInfo.getLoginName();
		String secret = employeeUserInfo.getSecret();
		String oldEncodePassword = EncryptUtil.encodePassword(oldPassword, secret);
		if (loginName.equals(employeeUserInfo.getLoginName()) && oldEncodePassword.equals(employeeUserInfo.getPassword())) {
			EmployeeUserInfo updatedRecord = new EmployeeUserInfo();
			updatedRecord.setEmployeeId(employeeUserInfo.getEmployeeId());
			updatedRecord.setPassword(EncryptUtil.encodePassword(newPassword, secret));
			return employeeUserInfoMapper.updateByPrimaryKeySelective(updatedRecord);
		} else {
			throw new BusinessException("旧密码错误");
		}
	}

	@Override
	public List<EmployeeInfoDto> select(EmployeeQueryReqDto reqDto) {
		Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
		EmployeeUserInfoExample example = new EmployeeUserInfoExample();
		reqDto = null == reqDto ? new EmployeeQueryReqDto() : reqDto;
		example.createCriteria().andEnterprIdEqualTo(enterprId);
		if (null != reqDto.getEmployeeNo() && !reqDto.getEmployeeNo().trim().isEmpty()) {
			example.createCriteria().andEmployeeNoLike("%" + reqDto.getEmployeeNo().trim() + "%");
		}
		if (null != reqDto.getEmployeeEmail() && !reqDto.getEmployeeEmail().trim().isEmpty()) {
			example.createCriteria().andEmailLike("%" + reqDto.getEmployeeEmail().trim() + "%");
		}
		if (null != reqDto.getEmployeeMobile() && !reqDto.getEmployeeMobile().trim().isEmpty()) {
			example.createCriteria().andMobileLike("%" + reqDto.getEmployeeMobile().trim() + "%");
		}
		if (null != reqDto.getEmployeeName() && !reqDto.getEmployeeName().trim().isEmpty()) {
			example.createCriteria().andEmployeeNameLike("%" + reqDto.getEmployeeName().trim() + "%");
		}

		example.setPage(reqDto.getPage());
		example.setPageSize(reqDto.getPageSize());
		example.setOrderByClause("employeeId DESC");

		List<EmployeeUserInfo> employeeUserInfoList = employeeUserInfoDao.find(example);
		List<Long> employeeIdList = new ArrayList<>();

		for (EmployeeUserInfo employeeUserInfo : employeeUserInfoList) {
			employeeIdList.add(employeeUserInfo.getEmployeeId());
		}
		Map<Long, EmployeeCreditInfo> ordMap = new HashMap<>();
		Map<Long, EmployeeCreditInfo> festivalMap = new HashMap<>();
		if (null != employeeIdList && employeeIdList.size() > 0) {
			// 普通积分
			List<EmployeeCreditInfo> ordinaryList = employeeCreditInfoMapper.totalCreaditByParam(employeeIdList, 1);
			ordMap = ordinaryList.stream().collect(Collectors.toMap(EmployeeCreditInfo::getEmployeeId, creditInfo -> creditInfo));
			// 节日积分
			List<EmployeeCreditInfo> festivalList = employeeCreditInfoMapper.totalCreaditByParam(employeeIdList, 2);
			festivalMap = festivalList.stream().collect(Collectors.toMap(EmployeeCreditInfo::getEmployeeId, creditInfo -> creditInfo));
		}

		List<EmployeeInfoDto> employeeInfoDtoList = EmployeeUserConvertUtil.convertEmployeeUserInfoList(employeeUserInfoList);
		for (EmployeeInfoDto employeeInfoDto : employeeInfoDtoList) {
			// 普通积分
			EmployeeCreditInfo ordCredit = ordMap.get(employeeInfoDto.getEmployeeId());
			if (null != ordCredit) {
				employeeInfoDto.setOrdCredit(ordCredit.getCredit());
			}
			// 节日积分
			EmployeeCreditInfo festCredit = festivalMap.get(employeeInfoDto.getEmployeeId());
			if (null != festCredit) {
				employeeInfoDto.setFestCredit(festCredit.getCredit());
			}
		}
		return employeeInfoDtoList;
	}

	@Override
	public JSONObject birthdayQuery(EmployeeQueryBirthday reqDto) {
		Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
		if (null == enterprId || enterprId <= 0) {
			return Response.fail("企业未登陆,请重新登陆！");
		}
		reqDto.setEnterprId(enterprId);
		reqDto.setPage((reqDto.getPage() - 1) * reqDto.getPageSize());
		List<Long> employeeIdList = employeeUserInfoMapper.selectEmployeeId(reqDto);
		List<Long> employeeIds = employeeUserInfoMapper.selectEmployeeIds(reqDto);
		employeeIdList.removeAll(employeeIds);
		if (!CollectionUtils.isEmpty(employeeIdList)) {
			for (int i = 0; i < employeeIdList.size(); i++) {
				EmployeeCreditInfo info = new EmployeeCreditInfo();
				info.setEmployeeId(employeeIdList.get(i));
				info.setCreditType(CreditType.normal.getCode());
				info.setCampaignId(0L);
				info.setCredit(new BigDecimal(0.00));
				info.setBirthdayCredit(new BigDecimal(0.00));
				info.setStatus(1);
				info.setIsDeleted(0);
				info.setCreated((int) DateUtil.getNowTimestamp10());
				info.setUpdated((int) DateUtil.getNowTimestamp10());
				employeeUserInfoMapper.insertEmployeeCreditInfo(info);
			}
		}
		List<EmployeeUserInfo> employeeUserInfo = employeeUserInfoMapper.selectBirthdayList(reqDto);
		Integer count = employeeUserInfoMapper.birthdayListCount(reqDto);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("count", count);
		jsonObject.put("employeelist", employeeUserInfo);
		return jsonObject;
	}

	@Override
	@Transactional
	public JSONObject birthdayCreditSet(EmployeeBirthdayCredit req) {
		if (req.getCredit().compareTo(BigDecimal.ZERO) <= 0 || req.getCredit() == null) {
			return Response.fail("积分分配数量至少大于零!");
		}
		if (!(req.getStatus().equals(CreditSetEnum.CLOSE.getValue()) || req.getStatus().equals(CreditSetEnum.OPEN.getValue()))) {
			return Response.fail("自动发放积分状态错误!");
		}
		if (req.getEmployeeId() == null) {
			return Response.fail("未选择员工!");
		}
		int rowStatus = employeeUserInfoMapper.employeeStatusSet(req);
		int rowCredit = employeeUserInfoMapper.employeeBirthdayCreditSet(req);
		int rowBirthdayCredit = 0;
		if (rowCredit == 0) { // 不能更新 插入新数据
			EmployeeCreditInfo info = new EmployeeCreditInfo();
			info.setEmployeeId(req.getEmployeeId().longValue());
			info.setCreditType(CreditType.normal.getCode());
			info.setCampaignId(0L);
			info.setCredit(new BigDecimal(0.00));
			info.setBirthdayCredit(req.getCredit());
			info.setStatus(1);
			info.setIsDeleted(0);
			info.setCreated((int) DateUtil.getNowTimestamp10());
			info.setUpdated((int) DateUtil.getNowTimestamp10());
			rowBirthdayCredit = employeeUserInfoMapper.insertEmployeeCreditInfo(info);
		}
		if (rowStatus + (rowCredit == 0 ? rowBirthdayCredit : rowCredit) == 2) {
			return Response.succ("积分自动发放设置成功！");
		} else {
			throw new BusinessException("积分自动发放设置失败!");
		}
	}

	@Override
	@Transactional
	public int birthdayCreditBacthSet(List<EmployeeBirthdayCredit> EmployeeBirthdayList) {

		for (EmployeeBirthdayCredit req : EmployeeBirthdayList) {
			if (req.getCredit() == null || req.getCredit().compareTo(BigDecimal.ZERO) <= 0) {
				throw new BusinessException("积分分配数量至少大于零!");
			}
			if (!(CreditSetEnum.CLOSE.getValue().equals(req.getStatus()) || CreditSetEnum.OPEN.getValue().equals(req.getStatus()))) {
				throw new BusinessException("自动发放积分状态错误!");
			}
		}
		int rowStatus = 0;
		int rowCredits = 0;
		for (int i = 0; i < EmployeeBirthdayList.size(); i += 100) {
			List<EmployeeBirthdayCredit> BirthdayLists = EmployeeBirthdayList.stream().skip(i).limit(100).collect(Collectors.toList());
			rowStatus += employeeUserInfoMapper.employeeBirthdayRemindStatusBatchSet(BirthdayLists);
			rowCredits += employeeUserInfoMapper.employeeBirthdayCreditBatchSet(BirthdayLists);
		}
		if (rowStatus == rowCredits && rowStatus > 0) {
			return rowStatus;
		}else {
			throw new BusinessException("批量设置生日积分自动发放失败!");
		}
	}

	@Override
	public List<EmployeeUserInfo> findEmployeeUserInfosByEnterprId(Long enterprId) {
		if (null == enterprId) {
			throw new BusinessException("企业ID不能为空！");
		}
		try {
			return employeeUserInfoDao.findEmployeeUserInfosByEnterprId(enterprId);
		} catch (Exception e) {
			throw new RuntimeException("根据企业ID查询员工信息出错！", e);
		}
	}

	@Override
	public List<EmployeeUserInfo> findEmployeeUserInfosByName(Map<String, Object> params) {
		if (params.isEmpty()) {
			throw new BusinessException("params参数不能为空！");
		}
		try {
			return employeeUserInfoDao.findEmployeeUserInfosByName(params);
		} catch (Exception e) {
			throw new RuntimeException("根据员工名称查询员工信息出错！", e);
		}
	}

	@Override
	@Transactional
	public Boolean updateCredit(List<BigInteger> employeeIdList, List<BigInteger> enterprIdList, List<BigDecimal> birthdayCreditList, List<String> email,
			boolean flag) {
		for (int i = 0; i < employeeIdList.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("birthdayCredit", birthdayCreditList.get(i));
			map.put("employeeId", employeeIdList.get(i));
			map.put("enterprId", enterprIdList.get(i));
			map.put("createTime", LocalDate.now());
			map.put("updateTime", LocalDate.now());
			map.put("creditType", CreditType.normal.getCode());
			List<EmployeeBirthdayRecordDto> employeeBirthdayRecordList = employeeUserInfoMapper.selectEmployeeBirthdayRecord(employeeIdList.get(i));
			if (!CollectionUtils.isEmpty(employeeBirthdayRecordList) && flag == true) {
				continue;
			}
			BigDecimal credit = employeeUserInfoMapper.selectValidCredit(map);
			if (credit != null && credit.compareTo(birthdayCreditList.get(i)) >= 0) {// 企业积分足够分配
				map.put("status", 1);// 积分已发放状态
				Integer rowEnterprCredit = employeeUserInfoMapper.updateEnterprCredit(map);
				Integer rowEmployeeCredit = employeeUserInfoMapper.updateEmployeeCredit(map);
				Integer rowBirthdayCreditRecord = null;
				if (flag)
					rowBirthdayCreditRecord = employeeUserInfoMapper.insertBirthdayCreditRecord(map);
				if (!flag)
					rowBirthdayCreditRecord = employeeUserInfoMapper.updateBirthdayCreditRecord(map);
				if ((rowEnterprCredit + rowEmployeeCredit + rowBirthdayCreditRecord) != 3) {
					throw new BusinessException("生日积分自动发放失败!");
				}
				CreditOperateInfo operateInfo = new CreditOperateInfo();
				Long cur = (System.currentTimeMillis() / 1000);
				operateInfo.setAuditStatus(2);
				operateInfo.setAuditTime(cur);
				operateInfo.setAuditUserId(enterprIdList.get(i).longValue());
				operateInfo.setOptTime(cur);
				// 操作类型{1: 运营积分充值; 2: 运营积分退款; 3: HR积分分配扣减企业积分; 4: HR积分回收增加企业积分;
				// 5: HR积分分配员工增加积分; 6: HR积分回收员工扣减积分; 7: 员工下单扣积分; 8: 员工退货退款时退积分;
				// 9: 运营从企业可用积分账户分配积分给员工 - 企业扣减积分; 10: 运营从企业可用积分账户分配积分给员工 -
				// 员工增加积分;14生日自动分配积分 }
				operateInfo.setOptType(14);
				operateInfo.setOptUserId(enterprIdList.get(i).longValue());
				operateInfo.setParentOptId(0L);
				operateInfo.setCampaignId((long) CampaignType.all.ordinal());
				operateInfo.setCreated(cur);
				operateInfo.setUpdated(cur);
				operateInfo.setCredit(birthdayCreditList.get(i));
				operateInfo.setCreditType(CreditType.normal.getCode());
				operateInfo.setIsDeleted(0);
				operateInfo.setOwnerUserId(enterprIdList.get(i).longValue());
				operateInfo.setRemark("生日自动分配积分");
				int rowInsertEnterpr = creditOperateInfoMapper.insertSelective(operateInfo);

				operateInfo.setOptType(5);
				operateInfo.setParentOptId(operateInfo.getOptId());
				operateInfo.setOwnerUserId(employeeIdList.get(i).longValue());
				// operateInfo.setRemark("");
				int rowInsertEmployee = creditOperateInfoMapper.insertSelective(operateInfo);
				if ((rowInsertEnterpr + rowInsertEmployee) != 2) {
					throw new BusinessException("生日积分自动发放失败!");
				}
				// 发送提醒邮件
				EnterprNoticeReqDto emailMsg = employeeUserInfoMapper.selectEmailContent(enterprIdList.get(i));
				// String emailContent =
				// employeeUserInfoMapper.selectEmailContent(enterprIdList.get(i));
				if (null != emailMsg) {
					SendMailUtil.singleSendMail(email.get(i), emailMsg.getEmailTitle(), emailMsg.getEmailContent());
				}
			} else {// 企业积分不足分配
				if (flag) {
					map.put("status", 0);// 积分挂起状态
					Integer rowBirthdayCreditRecord = employeeUserInfoMapper.insertBirthdayCreditRecord(map);
					if (rowBirthdayCreditRecord != 1) {
						throw new BusinessException("生日积分自动发放失败!");
					}
				}
			}

		}
		return true;
	}

	@Override
	public JSONObject employeeBirthdayDistRecord(Map<String, Object> params) {
		Integer page = Integer.parseInt(params.get("page").toString()) - 1;
		page = page * (Integer.parseInt(params.get("pageSize").toString()));
		params.put("page", page);
		params.put("pageSize", Integer.parseInt(params.get("pageSize").toString()));
		List<EmployeeBirthdayRecordDto> employeeBirthdayRecord = employeeUserInfoMapper.employeeBirthdayDistRecord(params);
		Integer count = employeeUserInfoMapper.birthdayDistRecordCount(params);
		for (EmployeeBirthdayRecordDto employeeBirthdayRecordDto : employeeBirthdayRecord) {
			if (employeeBirthdayRecordDto.getStatus() == 1)
				employeeBirthdayRecordDto.setStatusDesc("已发放");
			else
				employeeBirthdayRecordDto.setStatusDesc("挂起");
			if (employeeBirthdayRecordDto.getCreditType() == 1)
				employeeBirthdayRecordDto.setCreditTypeDesc("普通积分");

		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", employeeBirthdayRecord);
		jsonObject.put("count", count);
		return Response.succ(jsonObject);
	}

	// 处理因企业积分不够，充值后重新分配
	@Async
	public void handlerEmployeeBirthdayHangUp(Long enterprId) {
		try {
			if (enterprId == null) {
				Response.fail("企业未登陆,请重新登陆！");
			}
			Thread.sleep(1000);
			List<EmployeeUserInfoParam> employeeUserInfo = employeeUserInfoMapper.selectEmployeeBirthdayHangUp(new BigInteger(enterprId.toString()));
			if (CollectionUtils.isEmpty(employeeUserInfo)) {
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
			updateCredit(employeeIdList, enterprIdList, birthdayCreditList, emailList, false);
		} catch (InterruptedException e) {

		}

	}

	@Override
	public SessionShopInfo wxLogin(String openId) {
		WxUserOpenId wxUser = wxUserOpenIdService.getByOpenId(openId);
		if (wxUser == null) {
			throw new BusinessException("该用户未注册");
		}
		Long userId = wxUser.getUserId();
		EmployeeUserInfo employeeUserInfo = employeeUserInfoMapper.selectByPrimaryKey(userId);
		if (employeeUserInfo == null) {
			throw new BusinessException("登录的员工信息不存在!");
		}
		Integer isDeleted = employeeUserInfo.getIsDeleted();
		if (Integer.valueOf(1).equals(isDeleted)) {
			throw new BusinessException("登录员工已被禁用,请联系网站管理员!");
		}
		SessionShopInfo sessionShopInfo = new SessionShopInfo();
		BeanUtils.copyProperties(employeeUserInfo, sessionShopInfo);
		sessionShopInfo.setFromWeb(false);
		sessionShopInfo.setWxOpenId(openId);
		return sessionShopInfo;
	}

	@Transactional
	@Override
	public EmployeeUserInfo registerEmployee(String loginName, String employeeNo, String password, Long enterprId) {
		Assert.hasText(loginName, "账号不能为空!");
		Assert.notNull(employeeNo, "员工号不能为空!");
		Assert.hasText(password, "密码不能为空!");
		Assert.notNull(enterprId, "企业ID不能为空!");
		EnterprUserInfo enterprise = enterpriseService.getEnterprise(enterprId);
		if (enterprise == null) {
			throw new BusinessException("企业信息不存在!");
		}
		EmployeeUserInfo employeeUserInfo = new EmployeeUserInfo();
		employeeUserInfo.setLoginName(loginName);
		employeeUserInfo.setEnterprId(enterprId);
		employeeUserInfo.setEmployeeNo(loginName);
		employeeUserInfo.setEmployeeName(loginName);
		employeeUserInfo.setSex(0);
		// 生成密码
		String secret = EncryptUtil.generateSecret();
		employeeUserInfo.setSecret(secret);
		employeeUserInfo.setPassword(EncryptUtil.encodePassword(password, secret));
		this.registerEmployee(employeeUserInfo);
		enterprUserInfoMapper.incrEmployeeNum(enterprId, 1);// 添加数量
		return employeeUserInfo;
	}

	@Override
	public Integer countTotalEmployeesByExample() {
		try{
			Integer count = employeeUserInfoMapper.countByExample(null);
			return count;
		}catch (Exception e){
			throw new RuntimeException("统计员工总数出错！",e);
		}
	}

	@Override
	public Integer countLeaveEmployeesByExample() {
		try{
			EmployeeUserInfoExample employeeUserInfoExample = new EmployeeUserInfoExample();
			employeeUserInfoExample.createCriteria().andLeaveStatusEqualTo(true);
			Integer count = employeeUserInfoMapper.countByExample(employeeUserInfoExample);
			return count;
		}catch (Exception e){
			throw new RuntimeException("统计离职员工数据出错！",e);
		}
	}

	private void registerEmployee(EmployeeUserInfo employeeUserInfo) {
		Long employeeId = employeeSequenceService.getEmployeeId();
		if (null == employeeId || employeeId < 1) {
			throw new BusinessException("获取员工ID信息失败!");
		}
		employeeUserInfo.setEmployeeId(employeeId);
		if (employeeUserInfo.getSex() == null) {
			employeeUserInfo.setSex(0);
		}
		employeeUserInfoMapper.insertSelective(employeeUserInfo);
	}

	@Override
	public SessionShopInfo clientUserlogin(String token, String orgCode) {
		Assert.hasText(token, "token不能为空!");
		Assert.hasText(orgCode, "orgCode不能为空!");
		if (yibaoApiService.getOrgCode().equals(orgCode)) {// 暂时通过这种方式处理，后期有多种第三方用户再处理
			EmployeeUserInfo userInfo = yibaoApiService.getUserInfo(token);
			if (userInfo == null) {
				throw new BusinessException("用户获取失败!");
			}
			String employeeName = userInfo.getEmployeeName();
			EmployeeUserInfo employeeUserInfo = this.findByNoAndEnterprId(userInfo.getEmployeeNo(), userInfo.getEnterprId());
			if (employeeUserInfo == null) {
				if (StringUtils.isEmpty(employeeName)) {
					userInfo.setEmployeeName(userInfo.getEmployeeNo());
				}
				userInfo.setLoginName(UUID.randomUUID().toString().replace("-", ""));
				userInfo.setPassword(UUID.randomUUID().toString().replace("-", "").substring(0, 6));
				// 生成密码
				String secret = EncryptUtil.generateSecret();
				userInfo.setSecret(secret);
				String password = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
				userInfo.setPassword(EncryptUtil.encodePassword(password, secret));
				this.registerEmployee(userInfo);
				log.info("registerEmployee, employeeName={}, employeeNo={}, loginName={}, password={}", employeeName, userInfo.getEmployeeNo(),
						userInfo.getLoginName(), password);
				employeeUserInfo = this.findByNoAndEnterprId(userInfo.getEmployeeNo(), userInfo.getEnterprId());
			} else {
				if (!StringUtils.isEmpty(employeeName) && !employeeName.equals(employeeUserInfo.getEmployeeName())) {
					EmployeeUserInfo record = new EmployeeUserInfo();
					record.setEmployeeId(employeeUserInfo.getEmployeeId());
					record.setEmployeeName(employeeName);
					this.updateByPrimaryKeySelective(record);// 同步员工姓名
					employeeUserInfo.setEmployeeName(employeeName);
				}
			}
			if (employeeUserInfo != null) {
				SessionShopInfo sessionShopInfo = new SessionShopInfo();
				BeanUtils.copyProperties(employeeUserInfo, sessionShopInfo);
				sessionShopInfo.setFromWeb(true);
				return sessionShopInfo;
			}
		}
		return null;
	}

	@Override
	public SessionShopInfo login(Long employeeId,String wxOpenId) {
		EmployeeUserInfo employeeUserInfo = employeeUserInfoMapper.selectByPrimaryKey(employeeId);
		if (employeeUserInfo == null) {
			throw new BusinessException("登录的员工信息不存在!");
		}
		Integer isDeleted = employeeUserInfo.getIsDeleted();
		if (Integer.valueOf(1).equals(isDeleted)) {
			throw new BusinessException("登录员工已被禁用,请联系网站管理员!");
		}
		SessionShopInfo sessionShopInfo = new SessionShopInfo();
		BeanUtils.copyProperties(employeeUserInfo, sessionShopInfo);
		sessionShopInfo.setFromWeb(false);
		sessionShopInfo.setWxOpenId(wxOpenId);
		wxUserOpenIdService.bindUser(employeeId, wxOpenId);
		return sessionShopInfo;
	}
	
	
}
