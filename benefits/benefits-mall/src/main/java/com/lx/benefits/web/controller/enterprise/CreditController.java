package com.lx.benefits.web.controller.enterprise;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.enums.CreditTypeEnum;
import com.lx.benefits.bean.enums.OptTypeEnum;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.enterprise.EmployeeCreditDto;
import com.lx.benefits.bean.dto.enterprise.EmployeeCreditInfoDto;
import com.lx.benefits.bean.dto.enterprise.EmployeeOptCreditDto;
import com.lx.benefits.bean.entity.card.EmployeeCardCredit;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfoExample;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.Query;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.service.creditoperateinfo.CreditOperateInfoService;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author unknow on 2018-12-27 00:22.
 */
@Api(tags = "企业商城-员工积分信息模块")
@RestController
@RequestMapping("/enterprise")
public class CreditController {
    /**
     * 员工积分service
     */
    @Autowired
    private EmployeeCreditInfoService employeeCreditInfoService;

    @Autowired
    private CreditOperateInfoService creditOperateInfoService;

    @ApiOperation(value = "员工积分信息汇总", response = EmployeeCreditInfoDto.class)
	// PC: /credit/info；小程序: /user/creditInfo
	@GetMapping(value = { "/credit/info", "/user/creditInfo" })
    public Object info() {
        Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        EmployeeCreditInfoDto employeeCreditInfoDto = new EmployeeCreditInfoDto();
        employeeCreditInfoDto.setEmployeeId(employeeId);
        List<EmployeeCreditInfo> employeeCreditInfoList =  employeeCreditInfoService.getCreditInfo(employeeId);
        List<EmployeeCreditDto> employeeCreditDtoList = new ArrayList<>();
        Map<Integer, EmployeeCreditDto> employeeCreditDtoMap = new HashMap<>();
        if (null != employeeCreditInfoList && !employeeCreditInfoList.isEmpty()) {
            for (EmployeeCreditInfo employeeCreditInfo: employeeCreditInfoList) {
                Integer creditType = employeeCreditInfo.getCreditType();
                if (employeeCreditDtoMap.containsKey(creditType)) {
                    BigDecimal credit = employeeCreditDtoMap.get(creditType).getCredit();
                    credit = credit.add(employeeCreditInfo.getCredit());
                    employeeCreditDtoMap.get(creditType).setCredit(credit);
                } else {
                    EmployeeCreditDto employeeCreditDto = new EmployeeCreditDto();
                    employeeCreditDto.setCredit(employeeCreditInfo.getCredit());
                    employeeCreditDto.setCampaignId(0L);
                    employeeCreditDto.setCreditType(CreditTypeEnum.getCreditTypeEnum(creditType).name());
                    employeeCreditDtoMap.put(creditType, employeeCreditDto);
                }
            }
            employeeCreditDtoList.addAll(employeeCreditDtoMap.values());
        }
		EmployeeCardCredit employeeCardCreditInfo = employeeCreditInfoService.getEmployeeCardCreditInfo(employeeId);
		if (employeeCardCreditInfo != null) {
			EmployeeCreditDto putongCredit = employeeCreditDtoMap.get(CreditTypeEnum.PUTONG.getValue());
			if (putongCredit != null) {
				putongCredit.setCredit(putongCredit.getCredit().add(employeeCardCreditInfo.getCardCredit()));
			} else {
				putongCredit = new EmployeeCreditDto();
				putongCredit.setCreditType(CreditTypeEnum.PUTONG.name());
				putongCredit.setCredit(employeeCardCreditInfo.getCardCredit());
				putongCredit.setCampaignId(0L);
				employeeCreditDtoList.add(putongCredit);
			}
		}
		employeeCreditInfoDto.setEmployeeCreditDtoList(employeeCreditDtoList);
		return Response.succ(employeeCreditInfoDto);
	}
    
    @ApiOperation(value = "员工积分信息汇总", response = EmployeeCreditInfoDto.class)
    @RequestMapping(value = "/credit/infoPage", method = RequestMethod.GET)
    public Object infoPage(PageBean pageBean) {
        Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        
        List<EmployeeOptCreditDto> employeeOptCreditDtoList = new ArrayList<>();
        CreditOperateInfoExample cExample = new CreditOperateInfoExample();
        Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
        List<Integer> opTypes = new ArrayList<>();
        opTypes.add(11);
        opTypes.add(12);
        cExample.createCriteria().andOwnerUserIdEqualTo(employeeId).andOptTypeNotIn(opTypes).andCreditGreaterThan(BigDecimal.ZERO);
        cExample.setPage((page - 1) * pageSize);
		cExample.setPageSize(pageSize);
        cExample.setOrderByClause(" optId desc");
        Integer count = creditOperateInfoService.count(cExample);
        List<CreditOperateInfo> creditOperateInfoList = creditOperateInfoService.find(cExample);
        if (null != creditOperateInfoList && !creditOperateInfoList.isEmpty()) {
            for (CreditOperateInfo creditOperateInfo: creditOperateInfoList) {
                if (creditOperateInfo.getCredit().doubleValue() > 0 ) {
                    EmployeeOptCreditDto employeeOptCreditDto = new EmployeeOptCreditDto();
                    employeeOptCreditDto.setCredit(creditOperateInfo.getCredit().doubleValue());
                    employeeOptCreditDto.setCampaignId(creditOperateInfo.getCampaignId());
                    employeeOptCreditDto.setOptId(creditOperateInfo.getOptId());
                    employeeOptCreditDto.setOptTime(DateUtil.unixTime2Date(creditOperateInfo.getOptTime()));
                    employeeOptCreditDto.setOptUserId(creditOperateInfo.getOptUserId());
                    if (!creditOperateInfo.getCreditType().equals(0)) {
                        employeeOptCreditDto.setCreditType(CreditTypeEnum.getCreditTypeEnum(creditOperateInfo.getCreditType()).name());
                    }
                    OptTypeEnum optTypeEnum = OptTypeEnum.getOptTypeEnum(creditOperateInfo.getOptType());
                    if(optTypeEnum!=null) {
                    	employeeOptCreditDto.setOptType(optTypeEnum.name());
                    }
                    employeeOptCreditDto.setRemark(creditOperateInfo.getRemark());
                    employeeOptCreditDtoList.add(employeeOptCreditDto);
                }
            }
        }
        return Response.succ(new PageResultBean<>(page, pageSize, count, employeeOptCreditDtoList));
    }

    @RequestMapping(value = "/user/creditOptInfo", method = RequestMethod.GET)
    public JSONObject creditOptInfo(@RequestParam Map<String, Object> params) {
        Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        JSONObject jsonObject = new JSONObject();
        Integer optType = Integer.parseInt(params.get("optType").toString());

        Query query = new Query(params);
        query.put("ownerUserId",employeeId);
        List<Integer> optTypes = new ArrayList<>();
        if (optType.equals(1)) {
            optTypes.addAll(OptTypeEnum.getEmployeeAddOptType());
        } else if (optType.equals(2)) {
            optTypes.addAll(OptTypeEnum.getEmployeeReduceOptType());
        }
        query.put("optTypes",optTypes);

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
                if (StringUtils.isEmpty(remark)) {
                    if (OptTypeEnum.HR_DISTRIBUTION_USER_ADD.getValue().equals(operateType) ||
                    		OptTypeEnum.ADMIN_DISTRIBUTION_USER_ADD.getValue().equals(operateType) ) {
                        creditOperate.setRemark("企业积分充值");
                    }
                }
                creditOperate.setOptTimeFormat(DateUtil.unixTime2Date(creditOperate.getOptTime()));
            }
        }

        jsonObject.put("count",count);
        jsonObject.put("list",list);
        return Response.succ(jsonObject);
    }

}