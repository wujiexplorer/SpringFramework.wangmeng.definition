package com.lx.benefits.web.controller.admin;

import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.OptLogQueryReqDto;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.CreditInfoResDto;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.CreditOperateInfoDto;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.CreditOptResDto;
import com.lx.benefits.bean.enums.AuditStatusEnum;
import com.lx.benefits.bean.enums.CreditTypeEnum;
import com.lx.benefits.bean.enums.OptTypeEnum;
import com.lx.benefits.bean.dto.admin.enterprise.EnterpriseCreditOptReqDto;
import com.lx.benefits.bean.dto.admin.finance.*;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.EnterprCreditInfoDto;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfoExample;
import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfo;
import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfoExample;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfoCondition;
import com.lx.benefits.bean.entity.userinfo.UserInfo;
import com.lx.benefits.bean.entity.userinfo.UserInfoExample;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.service.creditoperateinfo.CreditOperateInfoService;
import com.lx.benefits.service.enterprcredit.EnterprCreditInfoService;
import com.lx.benefits.service.userinfo.UserInfoService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author unknow on 2018-12-06 00:10.
 */
@Api(tags = "运营后台-财务管理模块")
@RestController
@RequestMapping("/admin/finance")
public class FinanceController extends BaseAdminController {

    @Autowired
    private EnterprCreditInfoService enterprCreditInfoService;

    @Autowired
    private CreditOperateInfoService creditOperateInfoService;

    @Autowired
    private EnterprUserInfoMapper enterprUserInfoMapper;

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "企业积分信息汇总", response = CreditInfoResDto.class)
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public JSONObject info( @ModelAttribute CreditQueryReqDto req) {

        Long enterpriseId = req.getEnterpriseId();
        //查询企业积分列表
        List<EnterprCreditInfoDto> creditInfoDtoList = enterprCreditInfoService.findByEnterprId(enterpriseId);
        // 普通积分
        CreditInfoResDto common = new CreditInfoResDto();
        common.setCreditType(CreditTypeEnum.PUTONG.name());
        common.setLockCredit(BigDecimal.ZERO);
        common.setInvalidCredit(BigDecimal.ZERO);
        common.setValidCredit(BigDecimal.ZERO);
        // 节日积分
        CreditInfoResDto fest = new CreditInfoResDto();
        fest.setCreditType(CreditTypeEnum.JIERILIJIN.name());
        fest.setInvalidCredit(BigDecimal.ZERO);
        fest.setLockCredit(BigDecimal.ZERO);
        fest.setValidCredit(BigDecimal.ZERO);
        // 认可激励积分
        CreditInfoResDto recognition = new CreditInfoResDto();
        recognition.setCreditType(CreditTypeEnum.RENKEJILI.name());
        recognition.setInvalidCredit(BigDecimal.ZERO);
        recognition.setLockCredit(BigDecimal.ZERO);
        recognition.setValidCredit(BigDecimal.ZERO);

        Optional.ofNullable(creditInfoDtoList).ifPresent(u -> u.forEach(e -> {
            CreditInfoResDto temp = null;
            //1  普通积分 2 节日积分  3 认可激励
            if (e.getCreditType() == 1) {
                temp = common;
            } else if (e.getCreditType() == 2) {
                temp = fest;
            } else {
                temp = recognition;
            }
            temp.setInvalidCredit(temp.getInvalidCredit().add(e.getInvalidCredit()));
            temp.setLockCredit(temp.getLockCredit().add(e.getLockCredit()));
            temp.setValidCredit(temp.getValidCredit().add(e.getValidCredit()));
        }));

        List<CreditInfoResDto> creditInfoResDtoList = new ArrayList<>();
        creditInfoResDtoList.add(common);
        creditInfoResDtoList.add(recognition);
        creditInfoResDtoList.add(fest);
        return Response.succ(creditInfoResDtoList);
    }

    @ApiOperation(value = "企业客户积分充值OR退款操作", response = Boolean.class)
    @RequestMapping(value = "/creditrecharge", method = RequestMethod.POST)
    public JSONObject creditRecharge(@ApiParam(value = "ReqFinanceController.javauest", name = "req") @RequestBody EnterpriseCreditOptReqDto req) {
        Long enterpriseId = req.getEnterpriseId();
        if (null == enterpriseId || enterpriseId <= 0) {
            return Response.fail("enterpriseId is null");
        }
        Double creditAmount = req.getCreditAmount();
        if (null == creditAmount || creditAmount <= 0) {
            return Response.fail("creditAmount is null");
        }
        String financeNo = req.getFinanceNo();
        String creditType = req.getCreditType();
        CreditTypeEnum creditTypeEnum = CreditTypeEnum.getCreditTypeEnum(creditType);
        if (null == creditTypeEnum || creditTypeEnum.equals(CreditTypeEnum.JIERILIJIN)) {
            return Response.fail("creditType is null");
        }
        String optType = req.getOptType();
        if (!OptTypeEnum.DISTRIBUTION.name().equals(optType) && !OptTypeEnum.RECOVERY.name().equals(optType)) {
            return Response.fail("optType is error");
        }
        OptTypeEnum optTypeEnum = OptTypeEnum.getOptTypeEnum(optType);
        Long optUserId = SessionContextHolder.getSessionFuliInfo().getAdminId();
        if (null == optUserId || optUserId <= 0) {
            return Response.fail("optUserId is null");
        }
        String remarks = req.getRemarks();

        EnterprCreditInfo enterprCreditInfo = new EnterprCreditInfo();
        EnterprCreditInfoExample enterprCreditInfoExample = new EnterprCreditInfoExample();
        enterprCreditInfoExample.createCriteria().andEnterprIdEqualTo(enterpriseId)
                .andCreditTypeEqualTo(creditTypeEnum.getValue().byteValue());


        List<EnterprCreditInfo> enterprCreditInfoList = enterprCreditInfoService.find(enterprCreditInfoExample);

        if (null == enterprCreditInfoList || enterprCreditInfoList.size() == 0) {

            EnterprCreditInfo infoDto = new EnterprCreditInfo();
            infoDto.setEnterprId(enterpriseId);
            infoDto.setCreditType(creditTypeEnum.getValue().byteValue());
            Long enterId = enterprCreditInfoService.insert(infoDto);
            if (null == enterId || enterId == 0) {
                return Response.fail("创建企业失败");
            }
        }
        Integer updateCount = 0;
        //充值
        if (optTypeEnum.equals(OptTypeEnum.DISTRIBUTION)) {
            BigDecimal validCredit = null;
            if (null != enterprCreditInfoList && enterprCreditInfoList.size() > 0) {
                validCredit = enterprCreditInfoList.get(0).getInvalidCredit();
            } else {
                validCredit = new BigDecimal(0);
            }
            enterprCreditInfo.setInvalidCredit(validCredit.add(new BigDecimal(creditAmount)));
            updateCount = enterprCreditInfoService.update(enterprCreditInfo, enterprCreditInfoExample);
        } else if (optTypeEnum.equals(OptTypeEnum.RECOVERY)) {
            //退款
            BigDecimal validCredit = enterprCreditInfoList.get(0).getValidCredit();
            if (validCredit.compareTo(new BigDecimal(creditAmount)) >= 0) {
                BigDecimal lockCredit = enterprCreditInfoList.get(0).getLockCredit();
                enterprCreditInfo.setValidCredit(validCredit.subtract(new BigDecimal(creditAmount)));
                enterprCreditInfo.setLockCredit(lockCredit.add(new BigDecimal(creditAmount)));
                updateCount = enterprCreditInfoService.update(enterprCreditInfo, enterprCreditInfoExample);
            } else {
                return Response.fail("积分不够，退款失败");
            }
        }

        CreditOperateInfo creditOperateInfo = new CreditOperateInfo();
        creditOperateInfo.setOwnerUserId(enterpriseId);
        creditOperateInfo.setOptUserId(optUserId);
        creditOperateInfo.setCreditType(creditTypeEnum.getValue());
        creditOperateInfo.setFinanceNo(financeNo);
        creditOperateInfo.setOptType(optTypeEnum.getValue());
        creditOperateInfo.setRemark(remarks);
        creditOperateInfo.setOptType(optTypeEnum.getValue());
        creditOperateInfo.setCredit(new BigDecimal(creditAmount));
        Long optId = creditOperateInfoService.insert(creditOperateInfo);

        if (optId > 0 && updateCount > 0) {
            return Response.succ("操作成功");
        } else {
            return Response.fail("操作失败");
        }
    }

    @ApiOperation(value = "企业客户积分充值AND退款AND审核列表", response = CreditExchangeResDto.class)
    @RequestMapping(value = "/creditlist", method = RequestMethod.GET)
    public JSONObject creditList(@ApiParam(value = "Request", name = "req") @ModelAttribute CreditQueryReqDto req) {
        CreditExchangeListResDto res = new CreditExchangeListResDto();
        List<CreditExchangeResDto> creditExchangeResDtoList = new ArrayList<>();
        Long enterpriseId = req.getEnterpriseId();
        String creditStatus = req.getCreditStatus();
        AuditStatusEnum auditStatusEnum = AuditStatusEnum.getAuditStatusEnum(creditStatus);

        String optType = req.getOptType();
        String createdStart = req.getCreatedStart();
        String createdEnd = req.getCreatedEnd();
        Integer end = DateUtil.date2IntegerUnixTime(createdEnd);

        CreditOperateInfoExample creditOperateInfoExample = new CreditOperateInfoExample();
        CreditOperateInfoExample.Criteria criteria = creditOperateInfoExample.createCriteria();

        if (null != enterpriseId && enterpriseId > 0) {
            criteria.andOwnerUserIdEqualTo(enterpriseId);
        }
        if (null != auditStatusEnum) {
            criteria.andAuditStatusEqualTo(auditStatusEnum.getValue());
        }
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(createdStart)) {
            Integer start = DateUtil.date2IntegerUnixTime(createdStart);
            if (start > 0) {
                criteria.andCreatedGreaterThanOrEqualTo(start);
            }
        }
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(createdEnd)) {
            if (end > 0) {
                criteria.andCreatedLessThanOrEqualTo(end);
            }
        }
        List<Integer> optTypeList = new ArrayList<>();
        if (OptTypeEnum.DISTRIBUTION.name().equals(optType) || OptTypeEnum.RECOVERY.name().equals(optType)) {
            optTypeList.add(OptTypeEnum.getOptTypeEnum(optType).getValue());

        } else {
            optTypeList.add(OptTypeEnum.DISTRIBUTION.getValue());
            optTypeList.add(OptTypeEnum.RECOVERY.getValue());
        }

        criteria.andOptTypeIn(optTypeList);

        Integer page = req.getPage();
        Integer pageSize = req.getPageSize();
        creditOperateInfoExample.setPage((page - 1) * pageSize);
        creditOperateInfoExample.setPageSize(pageSize);

        creditOperateInfoExample.setOrderByClause(" created desc ");

        List<CreditOperateInfo> creditOperateInfoList = creditOperateInfoService.find(creditOperateInfoExample);
        Integer count = creditOperateInfoService.count(creditOperateInfoExample);
        for (CreditOperateInfo creditOperateInfo : creditOperateInfoList) {
            CreditExchangeResDto dto = new CreditExchangeResDto();
            dto.setOptId(creditOperateInfo.getOptId());
            dto.setEnterpriseId(creditOperateInfo.getOwnerUserId());

            EnterprUserInfoCondition userInfoExample = new EnterprUserInfoCondition();
            userInfoExample.createCriteria().andEnterprIdEqualTo(creditOperateInfo.getOwnerUserId());
            EnterprUserInfo enterprUserInfo= enterprUserInfoMapper.selectByPrimaryKey(creditOperateInfo.getOwnerUserId());
            if (null != enterprUserInfo) {
                dto.setEnterpriseName(enterprUserInfo.getEnterprName());
            }

            dto.setCreated(DateUtil.unixTime2Date(creditOperateInfo.getCreated()));
            dto.setUpdated(DateUtil.unixTime2Date(creditOperateInfo.getUpdated()));
            if (null != CreditTypeEnum.getCreditTypeEnum(creditOperateInfo.getCreditType())) {
                dto.setCreditType(CreditTypeEnum.getCreditTypeEnum(creditOperateInfo.getCreditType()).getDesc());
            }
            if (null != OptTypeEnum.getOptTypeEnum(creditOperateInfo.getOptType())) {
                dto.setOptType(OptTypeEnum.getOptTypeEnum(creditOperateInfo.getOptType()).getDesc());
            }
            dto.setExchangeAmount(creditOperateInfo.getCredit().toString());
            if (null != AuditStatusEnum.getAuditStatusEnum(creditOperateInfo.getAuditStatus().intValue())) {
                dto.setExchangeStatus(AuditStatusEnum.getAuditStatusEnum(creditOperateInfo.getAuditStatus().intValue()).getDesc());
            }
            dto.setFinanceNo(creditOperateInfo.getFinanceNo());
            dto.setLogSummary(creditOperateInfo.getRemark());

            if (null != creditOperateInfo.getOptUserId()) {
                dto.setOptUserId(creditOperateInfo.getOptUserId().intValue());
                UserInfoExample example = new UserInfoExample();
                example.createCriteria().andIdEqualTo(creditOperateInfo.getOptUserId());
                List<UserInfo> userInfoList = userInfoService.find(example);
                if (null != userInfoList && userInfoList.size() > 0) {
                    dto.setOptUserName(userInfoList.get(0).getUser_name());
                }
            }
            if (null != creditOperateInfo.getAuditUserId()) {
                dto.setAuditUserId(creditOperateInfo.getAuditUserId().intValue());
                UserInfoExample example = new UserInfoExample();
                example.createCriteria().andIdEqualTo(creditOperateInfo.getOptUserId());
                List<UserInfo> userInfoList = userInfoService.find(example);
                if (null != userInfoList && userInfoList.size() > 0) {
                    dto.setAuditUserName(userInfoList.get(0).getUser_name());
                }
            }
            if (null != creditOperateInfo.getOptTime()) {
                dto.setOptTime(DateUtil.unixTime2Date(creditOperateInfo.getOptTime()));
            }
            if (null != creditOperateInfo.getAuditTime()) {
                dto.setAuditTime(DateUtil.unixTime2Date(creditOperateInfo.getAuditTime()));
            }

            creditExchangeResDtoList.add(dto);
        }

        res.setCount(count);
        res.setCreditExchangeResDtoList(creditExchangeResDtoList);
        return Response.succ(res);
    }

    @ApiOperation(value = "积分审核操作", response = Boolean.class)
    @RequestMapping(value = "/creditaudit", method = RequestMethod.POST)
    public JSONObject creditAudit(@ApiParam(value = "Request", name = "req") @RequestBody CreditAuditReqDto req) {

        Long exchangeId = req.getOptId();
        String auditAction = req.getAuditAction();
        AuditStatusEnum auditStatusEnum = AuditStatusEnum.getAuditStatusEnum(auditAction);
        if (null == auditStatusEnum) {
            return Response.fail("auditAction 错误");
        }

        CreditOperateInfo creditOperateInfo = creditOperateInfoService.findByOptId(exchangeId);
        if (null == creditOperateInfo) {
            return Response.fail("optId 错误");
        }
        //企业ID
        Long enterprId = creditOperateInfo.getOwnerUserId();
        EnterprCreditInfoDto enterprCreditInfoDto = enterprCreditInfoService.findByEnterprIdOne(enterprId);
        if (null == enterprCreditInfoDto) {
            return Response.fail("查询不到企业");
        }

        //审核通过
        Integer result = creditOperateInfoService.actionCredit(exchangeId, auditAction);
        if (result > 0) {
            Integer optType = creditOperateInfo.getOptType();
            BigDecimal credit = creditOperateInfo.getCredit();

            EnterprCreditInfo enterprCreditInfo = new EnterprCreditInfo();
            EnterprCreditInfoExample enterprCreditInfoExample = new EnterprCreditInfoExample();
            enterprCreditInfoExample.createCriteria().andEnterprIdEqualTo(enterprId);

            Integer updateCount = 0;
            //充值
            if (optType.equals(OptTypeEnum.DISTRIBUTION.getValue())) {
                //审核通过
                if (auditStatusEnum.equals(AuditStatusEnum.COMPLETE)) {
                    BigDecimal validCredit = enterprCreditInfoDto.getValidCredit();
                    enterprCreditInfo.setValidCredit(validCredit.add(credit));
                    BigDecimal invalidCredit = enterprCreditInfoDto.getInvalidCredit();
                    enterprCreditInfo.setInvalidCredit(invalidCredit.subtract(credit));
                    updateCount = enterprCreditInfoService.update(enterprCreditInfo, enterprCreditInfoExample);
                } else if (auditStatusEnum.equals(AuditStatusEnum.UNPAID)) {
                    //审核拒绝
                    BigDecimal invalidCredit = enterprCreditInfoDto.getInvalidCredit();
                    enterprCreditInfo.setInvalidCredit(invalidCredit.subtract(credit));
                    updateCount = enterprCreditInfoService.update(enterprCreditInfo, enterprCreditInfoExample);
                }
            } else if (optType.equals(OptTypeEnum.RECOVERY.getValue())) {
                //退款
                //审核通过
                if (auditStatusEnum.equals(AuditStatusEnum.COMPLETE)) {
                    BigDecimal lockCredit = enterprCreditInfoDto.getLockCredit();
                    enterprCreditInfo.setLockCredit(lockCredit.subtract(credit));
                    updateCount = enterprCreditInfoService.update(enterprCreditInfo, enterprCreditInfoExample);
                } else if (auditStatusEnum.equals(AuditStatusEnum.UNPAID)) {
                    //审核拒绝
                    BigDecimal lockCredit = enterprCreditInfoDto.getLockCredit();
                    enterprCreditInfo.setLockCredit(lockCredit.subtract(credit));
                    BigDecimal validCredit = enterprCreditInfoDto.getValidCredit();
                    enterprCreditInfo.setValidCredit(validCredit.add(credit));
                    updateCount = enterprCreditInfoService.update(enterprCreditInfo, enterprCreditInfoExample);
                }
            }
            if (updateCount == 0) {
                return Response.fail("退回EnterprCreditInfo积分失败");
            }
        }
        return Response.succ(true);
    }

//    @ApiOperation(value = "企业客户积分充值AND退款AND通过AND拒绝操作记录", response = CreditOptLogResDto.class)
//    @RequestMapping(value = "/optlist", method = RequestMethod.GET)
//    public JSONObject optList(@RequestBody OptLogQueryReqDto reqDto) {
//        String optType = reqDto.getOptType();
//        optType = "RECHARGE".equals(optType) || "REFUND".equals(optType) || "PASS".equals(optType) || "REFUSE".equals(optType) ? optType : "RECHARGE";
//        List<CreditOptLogResDto> creditOptLogResDtoList = new ArrayList<>();
//        return Response.succ(creditOptLogResDtoList);
//    }

    @ApiOperation(value = "企业积分分配AND回收操作记录", response = CreditOptResDto.class)
    @RequestMapping(value = "/optlist", method = RequestMethod.GET)
    public JSONObject optList(@ApiParam(value = "企业操作积分操作记录信息", name = "reqDto") @ModelAttribute OptLogQueryReqDto reqDto) {

        List<CreditOperateInfoDto> dtos = creditOperateInfoService.listByOptId(reqDto.getBizId());

        List<CreditOptResDto> creditOptResDtoList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(dtos)) {
            dtos.forEach(e -> {
                CreditOptResDto resDto = new CreditOptResDto();
                resDto.setOptType(OptTypeEnum.getOptTypeEnum(e.getOptType()).name());
                resDto.setOptId(e.getOptId());
                resDto.setOptUserId(e.getOptUserId());
                resDto.setOptUserName(e.getOptUserName());
                resDto.setOptRemarks(e.getRemark());
                if (null == e.getOptTime() || e.getOptTime() == 0) {
                    resDto.setOptTime(DateUtil.unixTime2Date(e.getCreated()));
                } else {
                    resDto.setOptTime(DateUtil.unixTime2Date(e.getOptTime()));
                }
                Optional.ofNullable(e.getOptTime()).ifPresent(u ->
                        resDto.setOptTime(DateUtil.unixTime2Date(u)));
                creditOptResDtoList.add(resDto);
            });
        }
        return Response.succ(creditOptResDtoList);
    }

    @ApiOperation(value = "积分详情", response = CreditExchangeResDto.class)
    @RequestMapping(value = "/creditdetail/{optId}", method = RequestMethod.GET)
    public JSONObject creditDetail(@ApiParam(value = "积分变更id", name = "optId") @PathVariable Long optId) {
        CreditExchangeResDto creditExchangeResDto = new CreditExchangeResDto();
        if (null == optId || optId <= 0) {
            return Response.fail("optId is error");
        }
        CreditOperateInfoExample creditOperateInfoExample = new CreditOperateInfoExample();
        CreditOperateInfoExample.Criteria criteria = creditOperateInfoExample.createCriteria();
        criteria.andOptIdEqualTo(optId);

        List<CreditOperateInfo> creditOperateInfoList = creditOperateInfoService.find(creditOperateInfoExample);

        if (null == creditOperateInfoList || creditOperateInfoList.size() == 0) {
            return Response.fail("optId get Info is error");
        }

        for (CreditOperateInfo creditOperateInfo : creditOperateInfoList) {
            creditExchangeResDto.setOptId(creditOperateInfo.getOptId());
            creditExchangeResDto.setEnterpriseId(creditOperateInfo.getOwnerUserId());
            creditExchangeResDto.setCreated(DateUtil.unixTime2Date(creditOperateInfo.getCreated()));
            creditExchangeResDto.setUpdated(DateUtil.unixTime2Date(creditOperateInfo.getUpdated()));
            if (null != CreditTypeEnum.getCreditTypeEnum(creditOperateInfo.getCreditType())) {
                creditExchangeResDto.setCreditType(CreditTypeEnum.getCreditTypeEnum(creditOperateInfo.getCreditType()).getDesc());
            }
            if (null != OptTypeEnum.getOptTypeEnum(creditOperateInfo.getOptType())) {
                creditExchangeResDto.setOptType(OptTypeEnum.getOptTypeEnum(creditOperateInfo.getOptType()).getDesc());
            }
            creditExchangeResDto.setExchangeAmount(creditOperateInfo.getCredit().toString());
            if (null != AuditStatusEnum.getAuditStatusEnum(creditOperateInfo.getAuditStatus().intValue())) {
                creditExchangeResDto.setExchangeStatus(AuditStatusEnum.getAuditStatusEnum(creditOperateInfo.getAuditStatus().intValue()).getDesc());
            }
            creditExchangeResDto.setFinanceNo(creditOperateInfo.getFinanceNo());
            creditExchangeResDto.setLogSummary(creditOperateInfo.getRemark());


            if (null != creditOperateInfo.getOptUserId()) {
                creditExchangeResDto.setOptUserId(creditOperateInfo.getOptUserId().intValue());
                UserInfoExample example = new UserInfoExample();
                example.createCriteria().andIdEqualTo(creditOperateInfo.getOptUserId());
                List<UserInfo> userInfoList = userInfoService.find(example);
                if (null != userInfoList && userInfoList.size() > 0) {
                    creditExchangeResDto.setOptUserName(userInfoList.get(0).getUser_name());
                }
            }
            if (null != creditOperateInfo.getAuditUserId()) {
                creditExchangeResDto.setAuditUserId(creditOperateInfo.getAuditUserId().intValue());
                UserInfoExample example = new UserInfoExample();
                example.createCriteria().andIdEqualTo(creditOperateInfo.getOptUserId());
                List<UserInfo> userInfoList = userInfoService.find(example);
                if (null != userInfoList && userInfoList.size() > 0) {
                    creditExchangeResDto.setAuditUserName(userInfoList.get(0).getUser_name());
                }
            }
            if (null != creditOperateInfo.getOptTime()) {
                creditExchangeResDto.setOptTime(DateUtil.unixTime2Date((Long.valueOf(creditOperateInfo.getOptTime()))));
            }
            if (null != creditOperateInfo.getAuditTime() && creditOperateInfo.getAuditTime() > 0) {
                creditExchangeResDto.setAuditTime(DateUtil.unixTime2Date(Long.valueOf(creditOperateInfo.getAuditTime())));
            }
            creditExchangeResDto.setFinanceNo(creditOperateInfo.getFinanceNo());


        }
        return Response.succ(creditExchangeResDto);
    }

    @ApiOperation(value = "积分结算报表列表", response = CreditReportResDto.class)
    @RequestMapping(value = "/creditreportlist", method = RequestMethod.POST)
    public JSONObject creditReportList(@ApiParam(value = "Request", name = "req") @ModelAttribute CreditReportQueryReqDto req) {
        List<CreditReportResDto> creditReportResDtoList = new ArrayList<>();
        return Response.succ(creditReportResDtoList);
    }

    @ApiOperation(value = "积分结算报表详情", response = CreditReportResDto.class)
    @RequestMapping(value = "/creditreportdetail/{reportId}", method = RequestMethod.GET)
    public JSONObject creditReportDetail(@ApiParam(value = "积分结算报表id", name = "reportId") @PathVariable Long reportId) {
        List<CreditReportResDto> creditReportResDtoList = new ArrayList<>();
        return Response.succ(creditReportResDtoList);
    }
}