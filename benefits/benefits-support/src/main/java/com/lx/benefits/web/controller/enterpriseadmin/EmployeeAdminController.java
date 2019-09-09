package com.lx.benefits.web.controller.enterpriseadmin;

import com.alibaba.fastjson.JSONObject;
import com.apollo.common.utils.bean.BeanUtil;
import com.apollo.common.utils.bean.CollectionExtUtil;
import com.apollo.common.utils.export.StringTemplateExcelUtil;
import com.google.common.collect.Lists;
import com.lx.benefits.bean.base.dto.FLPageDto;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.*;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeBirthdayCredit;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeBirthdayRecordDto;
import com.lx.benefits.bean.enums.CreditTypeEnum;
import com.lx.benefits.bean.enums.OptTypeEnum;
import com.lx.benefits.bean.enums.YNEnum;
import com.lx.benefits.bean.eo.EmployeeInfoEO;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.*;
import com.lx.benefits.bean.util.*;
import com.lx.benefits.service.creditoperateinfo.CreditOperateInfoService;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.employeeimportdetail.EmplyeeImportDetailService;
import com.lx.benefits.service.employeeimportinfo.EmplyeeImportInfoService;
import com.lx.benefits.service.employeeleaveinfo.EmplyeeLeaveInfoService;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.web.util.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletResponse;

/**
 * @author unknow on 2018-12-04 03:26.
 */
@Api(tags = "企业后台-员工管理模块")
@RestController
@RequestMapping("/enterpriseadmin/employee")
public class EmployeeAdminController extends BaseEnterpriseAdminController {

	private final static Logger logger = LoggerFactory.getLogger(EmployeeAdminController.class);

	@Autowired
	private EmplyeeImportInfoService emplyeeImportInfoService;

	@Autowired
	private EmployeeUserInfoService employeeUserInfoService;

	@Autowired
	private EmplyeeImportDetailService emplyeeImportDetailService;

	@Autowired
	private EmplyeeLeaveInfoService emplyeeLeaveInfoService;

	@Autowired
	private EmployeeCreditInfoService employeeCreditInfoService;

	@Autowired
	private CreditOperateInfoService creditOperateInfoService;

	@ApiOperation(value = "添加企业员工信息", response = Long.class)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JSONObject add(@ApiParam(value = "Request", name = "req") @RequestBody EmployeeInfoDto req) {
		return Response.fail("目前只支持批量导入操作");
	}

	@ApiOperation(value = "批量导入企业员工信息", response = Boolean.class)
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public JSONObject batchImport(@ApiParam(value = "Request", name = "req") @RequestBody ImportReqDto req) {
		try {
			return employeeUserInfoService.batchImport(req);
		} catch (Exception e) {
			logger.error("批量导入员工异常{}", e.getMessage());
			return Response.fail("批量导入员工信息出现异常!");
		}
	}

	@ApiOperation(value = "批量导出企业员工信息")
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void export(@ApiParam(value = "Request", name = "req") @RequestBody EmployeeQueryReqDto req, HttpServletResponse response) {
		Function<EmployeeQueryReqDto, List<EmployeeInfoEO>> f = (pageQueryBO) -> {
			List<EmployeeInfoDto> employeeUserInfo = employeeUserInfoService.select(pageQueryBO);
			if (CollectionUtils.isEmpty(employeeUserInfo)) {
				return Lists.newArrayList();
			}
			return CollectionExtUtil.copyListWithCheck(employeeUserInfo, employeeUserInfoVO -> {
				EmployeeInfoEO eo = BeanUtil.copySpring(employeeUserInfoVO, EmployeeInfoEO.class);
				return eo;
			});

		};
		StringTemplateExcelUtil.exportCompressDataListBySegment(req, f, EmployeeInfoEO.class, response);
	}

	@ApiOperation(value = "企业员工列表", response = EmployeeInfoDto.class)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSONObject list(@ApiParam(value = "Request", name = "req") @ModelAttribute EmployeeQueryReqDto req) {
		return employeeUserInfoService.find(req);
	}

	@ApiOperation(value = "企业员工详细信息", response = EmployeeInfoDto.class)
	@RequestMapping(value = "/detail/{employeeId}", method = RequestMethod.GET)
	public JSONObject detail(@ApiParam(value = "企业员工标示id", name = "employeeId") @PathVariable Long employeeId) {
		Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
		if (null == employeeId || employeeId < 1) {
			return Response.fail("无效的企业员工id");
		}
		EmployeeInfoDto employeeInfoDto = employeeUserInfoService.findByEmployeeId(employeeId, false);
		if (null == employeeInfoDto) {
			return Response.fail("获取企业员工详细信息失败");
		}
		if (!enterprId.equals(employeeInfoDto.getEnterprId())) {
			return Response.fail("您无权查看非本公司员工信息");
		}
		return Response.succ(employeeInfoDto);
	}

	@ApiOperation(value = "编辑企业员工信息", response = EmployeeInfoDto.class)
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public JSONObject modify(@ApiParam(value = "Request", name = "req") @RequestBody EmployeeInfoDto req) {
		req.setEnterprId(SessionContextHolder.getSessionEnterpriseInfo().getEnterprId());
		return employeeUserInfoService.updateByEmployeeId(req);
	}

	@ApiOperation(value = "企业员工离职状态操作", response = Boolean.class)
	@RequestMapping(value = "/leave", method = RequestMethod.POST)
	public JSONObject leave(@ApiParam(value = "Request", name = "req") @RequestBody EmployeeLeaveOptReqDto req) {
		String leaveStatus = req.getLeaveStatus();
		Long employeeId = req.getEmployeeId();
		if (null == YNEnum.getValue(leaveStatus, null)) {
			return Response.fail("无效的离职状态");
		}
		Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
		EmployeeInfoDto employeeInfoDto = new EmployeeInfoDto();
		employeeInfoDto.setEmployeeId(employeeId);
		employeeInfoDto.setLeaveStatus(leaveStatus);
		employeeInfoDto.setEnterprId(enterprId);
		try {
			// 更新员工离职状态
			JSONObject jsonObject = employeeUserInfoService.updateByEmployeeId(employeeInfoDto);

			if (Response.isSuccess(jsonObject)) {
				return Response.succ(true);
			}
			return Response.fail("员工离职状态操作失败!");
		} catch (Exception e) {
			logger.error("员工离职异常", e.getMessage());
			return Response.fail("员工离职状态操作出现异常!");
		}
	}

	@ApiOperation(value = "企业员工离职记录", response = EmployeeLeaveResDto.class)
	@RequestMapping(value = "/leavelist", method = RequestMethod.GET)
	public JSONObject leaveList(@ApiParam(value = "Request", name = "req") @ModelAttribute EmployeeLeaveQueryDto req) {
		return emplyeeLeaveInfoService.selectByExample(req);
	}

	@ApiOperation(value = "企业员工导入记录", response = EmployeeImportResDto.class)
	@RequestMapping(value = "/importlist", method = RequestMethod.GET)
	public JSONObject importList(@ApiParam(value = "Request", name = "req") @ModelAttribute FLPageDto req) {
		return emplyeeImportInfoService.selectByExample(req);
	}

	@ApiOperation(value = "企业员工导入明细记录", response = EmployeeImportDetailResDto.class)
	@RequestMapping(value = "/importdetail", method = RequestMethod.GET)
	public JSONObject importDetail(
			@ApiParam(value = "Request", name = "req") @ModelAttribute EmployeeImportDetailQueryDto req) {
		return emplyeeImportDetailService.selectByExample(req);
	}

	@ApiOperation(value = "企业员工节日积分明细", response = EmployeeCreditResDto.class)
	@RequestMapping(value = "/festivalInfo", method = RequestMethod.GET)
	public JSONObject festivalInfo(@ApiParam(value = "Request", name = "req") @ModelAttribute EmployeeInfoDto req) {
		JSONObject jsonObject = new JSONObject();
		List<EmployeeCreditResDto> list = employeeCreditInfoService.festivalInfo(req.getEmployeeId());
		for (EmployeeCreditResDto employeeCreditResDto : list) {
			if (null == employeeCreditResDto.getCredit()) {
				employeeCreditResDto.setCredit(new BigDecimal(0));
			}
			Integer creditType = employeeCreditResDto.getCreditType();
			if (creditType.equals(CreditTypeEnum.PUTONG.getValue())) {
				employeeCreditResDto.setCreditTypeDesc("普通积分");
			} else if (creditType.equals(CreditTypeEnum.JIERILIJIN.getValue())) {
				employeeCreditResDto.setCreditTypeDesc("节日积分");
			} else if (creditType.equals(CreditTypeEnum.RENKEJILI.getValue())) {
				employeeCreditResDto.setCreditTypeDesc("认可激励");
			}
		}
		jsonObject.put("list", list);
		return Response.succ(jsonObject);
	}

	@ApiOperation(value = "企业员工积分明细,充值记录、消费记录", response = EmployeeCreditResDto.class)
	@RequestMapping(value = "/creditOptInfo", method = RequestMethod.GET)
	public JSONObject creditOptInfo(@RequestParam Map<String, Object> params) {
		JSONObject jsonObject = new JSONObject();
		Integer optType = Integer.parseInt(params.get("optType").toString());

		Query query = new Query(params);
		query.put("ownerUserId", params.get("employeeId"));
		List<Integer> optTypes = new ArrayList<>();
		if (optType.equals(1)) {
			// 5,10
			optTypes.add(OptTypeEnum.HR_DISTRIBUTION_USER_ADD.getValue());
			optTypes.add(OptTypeEnum.ADMIN_DISTRIBUTION_USER_ADD.getValue());
			optTypes.add(OptTypeEnum.USER_REFUND_ADD.getValue());
		} else if (optType.equals(2)) {
			// 6,7,8
			optTypes.add(OptTypeEnum.HR_RECOVERY_USER_REDUCE.getValue());
			optTypes.add(OptTypeEnum.USER_ORDER_REDUCE.getValue());
		}
		query.put("optTypes", optTypes);

		Integer count = creditOperateInfoService.creditOptInfocount(query);
		List<CreditOperateInfo> list = new ArrayList<>();
		if (count > 0) {
			list = creditOperateInfoService.creditOptInfo(query);
			for (CreditOperateInfo creditOperate : list) {
				Integer creditType = creditOperate.getCreditType();
				if (creditType.equals(1)) {
					creditOperate.setCreditTypeDesc("普通积分");
				} else if (creditType.equals(3)) {
					creditOperate.setCreditTypeDesc("认可激励");
				} else if (creditType.equals(2)) {
					creditOperate.setCreditTypeDesc("节日积分");
				}
				if (null == creditOperate.getCredit()) {
					creditOperate.setCredit(new BigDecimal(0));
				}
				Integer operateType = creditOperate.getOptType();
				String remark = creditOperate.getRemark();
				if (remark == null || StringUtil.isEmpty(remark)) {
					if (operateType.equals(5) || operateType.equals(10)) {
						creditOperate.setRemark("企业积分充值");
					} else if (operateType.equals(7) || operateType.equals(6)) {
						creditOperate.setRemark(remark);
					}
				}
				creditOperate.setOptTimeFormat(DateUtil.unixTime2Date(creditOperate.getOptTime()));
			}
		}

		jsonObject.put("count", count);
		jsonObject.put("list", list);
		return Response.succ(jsonObject);
	}

	@ApiOperation(value = "企业员工积分回收操作", response = Boolean.class)
	@RequestMapping(value = "/recovery", method = RequestMethod.POST)
	public JSONObject recovery(
			@ApiParam(value = "企业员工积分回收操作", name = "reqDto") @RequestBody EmployeeRecoveryReqDto reqDto) {
		if (null == reqDto) {
			return Response.fail("请求参数不能为空");
		}
		try {
			Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
			if (null == enterprId || enterprId < 1) {
				return Response.fail("登录失效");
			}
			reqDto.setEnterprId(enterprId);
			reqDto.setRemark("[hr平台]员工积分回收");
			employeeCreditInfoService.recoveryEmployee(reqDto);
			return Response.succ("回收成功");
		} catch (Exception e) {
			logger.error("企业员工积分回收异常");
			return Response.fail("回收失败");
		}
	}
	
	@ApiOperation(value = "企业员工生日列表", response = EmployeeInfoDto.class)
	@RequestMapping(value = "/birthdaylist", method = RequestMethod.POST)
	public JSONObject birthdayList(@ApiParam(value = "Request", name = "req") @RequestBody EmployeeQueryBirthday req) {
		return Response.succ(employeeUserInfoService.birthdayQuery(req));
	}
	
	@ApiOperation(value = "员工生日设置", response = Boolean.class)
	@RequestMapping(value = "/birthdayset", method = RequestMethod.POST)
	public JSONObject birthdaySet(@ApiParam(value = "Request", name = "req") @RequestBody EmployeeBirthdayCredit req) {
		return employeeUserInfoService.birthdayCreditSet(req);
	}
	
	@ApiOperation(value = "员工生日批量设置", response = Boolean.class)
	@PostMapping(value = "/birthdayBatchSet")
	public JSONObject birthdayBacthSet(@RequestBody List<EmployeeBirthdayCredit> EmployeeBirthdayList) {
		if(CollectionUtils.isEmpty(EmployeeBirthdayList)) {
			return Response.fail("设置生日积分的员工为空");
		}
		int result = employeeUserInfoService.birthdayCreditBacthSet(EmployeeBirthdayList);
		if(result > 0) {
			return Response.succ("批量设置生日积分自动发放成功");
		}else {
			return Response.fail("批量设置生日积分自动发放失败");
		}
	}
	
	@ApiOperation(value = "员工生日发放记录", response = EmployeeBirthdayRecordDto.class)
	@RequestMapping(value = "/birthdayDistRecord", method = RequestMethod.GET)
	public JSONObject birthdayDistRecord(@RequestParam Map<String, Object> params) {
		return employeeUserInfoService.employeeBirthdayDistRecord(params);
	}
}