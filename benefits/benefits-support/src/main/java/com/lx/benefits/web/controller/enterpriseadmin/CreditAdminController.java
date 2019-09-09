package com.lx.benefits.web.controller.enterpriseadmin;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.FLPageResDto;
import com.lx.benefits.bean.base.dto.OptLogQueryReqDto;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfoExample;
import com.lx.benefits.bean.enums.AuditStatusEnum;
import com.lx.benefits.bean.enums.CreditRecoveryRangeEnum;
import com.lx.benefits.bean.enums.CreditTypeEnum;
import com.lx.benefits.bean.enums.OptTypeEnum;
import com.lx.benefits.bean.dto.admin.finance.CreditExchangeReqDto;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.*;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionEnterpriseInfo;
import com.lx.benefits.bean.vo.enterpr.EmployeeCreditDistributionBean;
import com.lx.benefits.bean.vo.enterpr.EmployeeCreditDistributionBeanExcelModel;
import com.lx.benefits.service.creditoperateinfo.CreditOperateInfoService;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.enterprcredit.EnterprCreditInfoService;
import com.lx.benefits.web.util.Query;
import io.swagger.annotations.*;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;

/**
 * @author unknow on 2018-12-05 17:51.
 */
@Api(tags = "企业后台-积分管理模块")
@RestController
@RequestMapping("/enterpriseadmin/credit")
public class CreditAdminController extends BaseEnterpriseAdminController {

    /**
     * 企业积分service
     */
    @Autowired
    private EnterprCreditInfoService enterprCreditInfoService;

    /**
     * 积分操作记录service
     */
    @Autowired
    private CreditOperateInfoService creditOperateInfoService;

    /**
     * 员工积分service
     */
    @Autowired
    private EmployeeCreditInfoService employeeCreditInfoService;


    private final static Logger logger = LoggerFactory.getLogger(CreditAdminController.class);

    @ApiOperation(value = "企业积分信息汇总", response = CreditInfoResDto.class)
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public JSONObject info() {

        SessionEnterpriseInfo sessionEnterpriseInfo = SessionContextHolder.getSessionEnterpriseInfo();

        //查询企业积分列表
        List<EnterprCreditInfoDto> creditInfoDtoList = enterprCreditInfoService.findByEnterprId(sessionEnterpriseInfo.getEnterprId());
        // 普通积分
        CreditInfoResDto common = new CreditInfoResDto();
        common.setCreditType(CreditTypeEnum.PUTONG.name());
        common.setInvalidCredit(BigDecimal.ZERO);
        common.setLockCredit(BigDecimal.ZERO);
        common.setValidCredit(BigDecimal.ZERO);
        common.setDistCredit(BigDecimal.ZERO);
        // 节日积分
        CreditInfoResDto fest = new CreditInfoResDto();
        fest.setCreditType(CreditTypeEnum.JIERILIJIN.name());
        fest.setInvalidCredit(BigDecimal.ZERO);
        fest.setLockCredit(BigDecimal.ZERO);
        fest.setValidCredit(BigDecimal.ZERO);
        fest.setDistCredit(BigDecimal.ZERO);
        // 认可激励积分
        CreditInfoResDto recognition = new CreditInfoResDto();
        recognition.setCreditType(CreditTypeEnum.RENKEJILI.name());
        recognition.setInvalidCredit(BigDecimal.ZERO);
        recognition.setLockCredit(BigDecimal.ZERO);
        recognition.setValidCredit(BigDecimal.ZERO);
        recognition.setDistCredit(BigDecimal.ZERO);

        Optional.ofNullable(creditInfoDtoList).ifPresent(u -> u.forEach(e -> {
            CreditInfoResDto temp = null;
            CreditOperateInfo creditOperateInfo = new CreditOperateInfo();
            if (e.getCreditType() == 1) {
                // 普通积分
                temp = common;
            } else if (e.getCreditType() == 2) {
                // 节日积分
                temp = fest;
            } else {
                // 认可激励积分
                temp = recognition;
            }
            creditOperateInfo.setOwnerUserId(sessionEnterpriseInfo.getEnterprId());
            creditOperateInfo.setCreditType(e.getCreditType().intValue());
            Double distCredit = creditOperateInfoService.selectTotalCredit(creditOperateInfo);
            temp.setLockCredit(temp.getLockCredit().add(e.getLockCredit()));
            temp.setInvalidCredit(temp.getInvalidCredit().add(e.getInvalidCredit()));
            temp.setValidCredit(temp.getValidCredit().add(e.getValidCredit()));
            temp.setDistCredit(temp.getDistCredit().add(new BigDecimal(distCredit)));
        }));

        List<CreditInfoResDto> creditInfoResDtoList = new ArrayList<>();
        creditInfoResDtoList.add(common);
        creditInfoResDtoList.add(recognition);
        creditInfoResDtoList.add(fest);
        return Response.succ(creditInfoResDtoList);
    }

    @ApiOperation(value = "企业积分分配AND回收变更列表", response = CreditExchangeResDto.class)
    @RequestMapping(value = "/cheangelist", method = RequestMethod.GET)
    public JSONObject cheangeList(@ApiParam(value = "Request", name = "req") @ModelAttribute CreditExchangeReqDto req) {

        SessionEnterpriseInfo sessionEnterpriseInfo = SessionContextHolder.getSessionEnterpriseInfo();

        //参数验证
        if (!OptTypeEnum.isVaildCode(req.getOptType())) {
            return Response.fail("操作类型错误");
        }

        List<CreditExchangeResDto> creditExchangeResDtoList = new ArrayList<>();
        FLPageResDto<CreditOperateInfoDto> pageResDto = creditOperateInfoService.pageByEnterpriseId(sessionEnterpriseInfo.getEnterprId(),
                req.getPage(), req.getPageSize(), OptTypeEnum.getOptTypeEnum(req.getOptType()).getValue());

        FLPageResDto<CreditExchangeResDto> resultPage = new FLPageResDto<>();
        Optional.ofNullable(pageResDto.getResult()).ifPresent(u -> u.forEach(e -> {
            CreditExchangeResDto resDto = new CreditExchangeResDto();
            resDto.setExchangeId(e.getOptId());
            CreditTypeEnum creditTypeEnum = CreditTypeEnum.getCreditTypeEnum(e.getCreditType());
            if(null != creditTypeEnum) {
                resDto.setCreditType(creditTypeEnum.name());
                resDto.setCreditTypeDesc(creditTypeEnum.getDesc());
            }
            OptTypeEnum optTypeEnum = OptTypeEnum.getOptTypeEnum(e.getOptType());
            resDto.setOptType(optTypeEnum.name());
            resDto.setOptTypeDesc(optTypeEnum.getDesc());
            resDto.setExchangeAmount(e.getCredit().toString());
            resDto.setExchangeStatus(AuditStatusEnum.getAuditStatusEnum(e.getAuditStatus()).name());
            resDto.setCreated(DateUtil.unixTime2Date(e.getCreated()));
            // 积分操作涉及员工人数
            CreditOperateInfoExample example = new CreditOperateInfoExample();
            example.createCriteria().andParentOptIdEqualTo(e.getOptId()).andIsDeletedEqualTo(0);
            Integer employeeNum = creditOperateInfoService.count(example);
            resDto.setEmployeeNum(employeeNum);
            creditExchangeResDtoList.add(resDto);
        }));
        resultPage.setCount(pageResDto.getCount());
        resultPage.setPage(pageResDto.getPage());
        resultPage.setPageSize(pageResDto.getPageSize());
        resultPage.setResult(creditExchangeResDtoList);

        return Response.succ(resultPage);
    }

    @ApiOperation(value = "企业积分分配操作", response = Boolean.class)
    @RequestMapping(value = "/distribution", method = RequestMethod.POST)
    public JSONObject distribution(@ApiParam(value = "企业积分分配信息", name = "reqDto") @RequestBody CreditDistributionReqDto reqDto) {

        SessionEnterpriseInfo sessionEnterpriseInfo = SessionContextHolder.getSessionEnterpriseInfo();

        if (null == reqDto) {
            return Response.fail("请求参数不能为空");
        }


        if (CreditRecoveryRangeEnum.PART.name().equals(reqDto.getUrl()) && StringUtils.isEmpty(reqDto.getUrl())) {
            return Response.fail("文件地址为空");
        }

        if (!CreditTypeEnum.isVaildCode(reqDto.getCreditType())) {
            return Response.fail("回收类型错误");
        }

        int distribution = 0;
        List<Object> data = null;
        try {
            UrlResource resource = new UrlResource(reqDto.getUrl());
            data = EasyExcelFactory.read(resource.getInputStream(), new Sheet(1, 1, CreditDistributionExcelDto.class));
        } catch (Exception e) {
            logger.error("解析积分分配文件异常：{}", e);
            return Response.fail("积分分配出错");
        }

        if (CollectionUtils.isEmpty(data)) {
            return Response.fail("excel表格数据为空");
        }

        //数据封装
        CreditDistributionDto distributionDto = new CreditDistributionDto();
        distributionDto.setRemark(reqDto.getRemarks());
        distributionDto.setEnterprId(sessionEnterpriseInfo.getEnterprId());
        distributionDto.setOptUserId(sessionEnterpriseInfo.getEnterprId());
        distributionDto.setDetailDtoList(new ArrayList<>());
        distributionDto.setCreditType(CreditTypeEnum.getCreditTypeEnum(reqDto.getCreditType()).getValue());

        BigDecimal totalCredit = new BigDecimal(0);
        //excel表格信息组装
        for (Object datum : data) {
            CreditDistributionExcelDto excelDto = (CreditDistributionExcelDto) datum;
            CreditDistributionDetailDto dto = new CreditDistributionDetailDto();
            dto.setCredit(new BigDecimal(excelDto.getCredit()));
            totalCredit = totalCredit.add(new BigDecimal(excelDto.getCredit()));
            dto.setEmployeeNo(excelDto.getEmployeeNo());
            dto.setCreditType(CreditTypeEnum.getCreditTypeEnum(reqDto.getCreditType()).getValue());
            dto.setRemark(reqDto.getRemarks());

            distributionDto.getDetailDtoList().add(dto);
        }

        try {
            List<EnterprCreditInfoDto> enterprCreditInfoDtoList = enterprCreditInfoService.findByEnterprId(sessionEnterpriseInfo.getEnterprId());
            if (null != enterprCreditInfoDtoList && enterprCreditInfoDtoList.size() == 1) {
                BigDecimal validCredit = enterprCreditInfoDtoList.get(0).getValidCredit();
                if ( validCredit.compareTo(totalCredit) < 0) {
                    return Response.fail("企业积分不够,分配失败");
                } else {
                    distribution = employeeCreditInfoService.distributionList(distributionDto)-1;
                    return Response.succ(distribution + "条数据积分分配成功");
                }
            }
        } catch (Exception e) {
            logger.error("积分分配异常 {}",e.getMessage());
            return Response.fail("积分分配异常");
        }
        return Response.fail(distribution + "条数据积分分配失败");
    }

    @ApiOperation(value = "企业积分回收操作", response = Boolean.class)
    @RequestMapping(value = "/recovery", method = RequestMethod.POST)
    public JSONObject recovery(@ApiParam(value = "企业积分回收信息", name = "reqDto") @RequestBody CreditRecoveryReqDto reqDto) {

        SessionEnterpriseInfo sessionEnterpriseInfo = SessionContextHolder.getSessionEnterpriseInfo();
        if (null == reqDto) {
            return Response.fail("请求参数不能为空");
        }

        if (CreditRecoveryRangeEnum.PART.name().equals(reqDto.getRecoveryRange()) && StringUtils.isEmpty(reqDto.getFilePath())) {
            return Response.fail("文件地址为空");
        }

        if (!CreditTypeEnum.isVaildCode(reqDto.getCreditType())) {
            return Response.fail("回收类型错误");
        }

        List<Object> data = null;
        List<CreditRecoveryDetailDto> detailDtoList = new ArrayList<>(32);
        int recoveryResult = 0;
        if (reqDto.getRecoveryRange().equals(CreditRecoveryRangeEnum.PART.name())) {
            try {
                UrlResource resource = new UrlResource(reqDto.getFilePath());
                data = EasyExcelFactory.read(resource.getInputStream(), new Sheet(1, 1, CreditRecoveryExcelDto.class));
            } catch (Exception e) {
                logger.error("积分回收文件解析出错：", e);
                return Response.fail("文件解析出错!");
            }

            if (CollectionUtils.isEmpty(data)) {
                return Response.fail("excel表格数据为空");
            }

            //excel表格信息组装
            for (Object datum : data) {
                CreditRecoveryExcelDto excelDto = (CreditRecoveryExcelDto) datum;
                CreditRecoveryDetailDto dto = new CreditRecoveryDetailDto();
                dto.setCredit(BigDecimal.ZERO);
                dto.setEmployeeNo(excelDto.getEmployeeNo());
                dto.setCreditType(dto.getCreditType());
                dto.setRemark(dto.getRemark());
                dto.setEnterprId(sessionEnterpriseInfo.getEnterprId());
                dto.setOptUserId(sessionEnterpriseInfo.getEnterprId());

                detailDtoList.add(dto);
            }
        }

        //数据封装
        CreditRecoveryDto dto = new CreditRecoveryDto();
        dto.setEnterprId(sessionEnterpriseInfo.getEnterprId());
        dto.setOptUserId(sessionEnterpriseInfo.getEnterprId());
        dto.setCreditType(CreditTypeEnum.getCreditTypeEnum(reqDto.getCreditType()).getValue());
        dto.setDetailDtoList(detailDtoList);
        dto.setRecoveryRange(reqDto.getRecoveryRange());
        dto.setRemark(reqDto.getRemarks());

        recoveryResult = employeeCreditInfoService.recoveryList(dto);


        return Response.succ("成功回收" + recoveryResult + "个人员积分");
    }

    @ApiOperation(value = "企业积分分配AND回收操作记录", response = CreditOptResDto.class)
    @RequestMapping(value = "/optlist", method = RequestMethod.GET)
    public JSONObject optList(@ApiParam(value = "企业操作积分操作记录信息", name = "reqDto") @ModelAttribute OptLogQueryReqDto reqDto) {

        List<CreditOperateInfoDto> dtos = creditOperateInfoService.listByParentOptId(reqDto.getBizId());

        List<CreditOptResDto> creditOptResDtoList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(dtos)) {
            dtos.forEach(e -> {
                CreditOptResDto resDto = new CreditOptResDto();
                resDto.setOptId(e.getOptId());
                resDto.setOptType(OptTypeEnum.getOptTypeEnum(e.getOptType()).name());
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

    @ApiOperation(value = "下载excel模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "1表示下载积分分配模板 2表示回收积分模板", required = true)
    })
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public JSONObject downloadExcel(HttpServletResponse response, String type) {

        ServletOutputStream out = null;
        List<CreditDistributionExcelDto> dtos = new ArrayList<>();

        String fileName = "" + "111111" + ".xlsx";

        try {
            out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            Sheet sheet1 = null;
            if ("1".equals(type)) {
                sheet1 = new Sheet(1, 0, CreditDistributionExcelDto.class);
            } else {
                sheet1 = new Sheet(1, 0, CreditRecoveryExcelDto.class);
            }
            writer.write(dtos, sheet1);
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.reset();

            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            writer.finish();
            out.flush();
        } catch (IOException e) {
            logger.info("积分分配模板导出出错：{}", e);
        }
        return null;
    }

    /**
     * 积分分配列表
     */
    @RequestMapping(value = "/uselist", method = RequestMethod.GET)
    public JSONObject uselist(@ApiParam(value = "Request", name = "req") @ModelAttribute CreditExchangeReqDto req) {
        try {
            Map<String, Object> map = JSON.parseObject(JSON.toJSONString(req));
            Query query =  new Query(map);
            return creditOperateInfoService.useList(query);
        } catch (Exception e) {
            logger.info("积分查询列表异常：{}", e);
            return Response.fail("系统异常");
        }
    }
    
    //积分分配记录导出
  	@RequestMapping("/credit/distribution/export")
  	public void creditDistributionExport(HttpServletRequest request, HttpServletResponse response) throws IOException{
  		final int pageSize = 200;
  		PageBean pageBean = new PageBean() {
  			public Integer getPageSize() {
  				return pageSize;
  			}
  		};// 默认第一页码
  		pageBean.setPageSize(pageSize);
  		PageResultBean<EmployeeCreditDistributionBean> employeeCreditDistribution = creditOperateInfoService.employeeCreditDistribution(pageBean);
  		List<EmployeeCreditDistributionBean> list = employeeCreditDistribution.getList();
		if (CollectionUtils.isEmpty(list)) {
			JSONObject fail = Response.fail("数据为空!");
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.getWriter().write(fail.toJSONString());
			response.setStatus(HttpStatus.SC_OK);
			return;
		}
		String filename;
		Class<? extends BaseRowModel> modelclass;
		filename = "积分分配记录.xlsx";
		modelclass = EmployeeCreditDistributionBeanExcelModel.class;
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE);
		response.addHeader("Content-Disposition",
				"attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO-8859-1"));
		OutputStream outputStream;
		String header = request.getHeader("Accept-Encoding");
		if (header != null && header.contains("gzip")) {// 支持gzip压缩
			response.setHeader("content-encoding", "gzip");
			outputStream = new GZIPOutputStream(response.getOutputStream());
		} else {
			outputStream = response.getOutputStream();
		}
		ExcelWriter writer = EasyExcelFactory.getWriter(outputStream, ExcelTypeEnum.XLSX, true);
		Sheet sheet = new Sheet(1, 1, modelclass);
		sheet.setSheetName(filename);
		Integer count = employeeCreditDistribution.getCount();
		int page = (count - 1) / pageBean.getPageSize() + 1;
		if (page > 100) {// 至多导出20000条
			page = 100;
		}
		for (int i = 1; i <= page; i++) {
			if (i > 1) {
				pageBean.setPage(i);
				employeeCreditDistribution = creditOperateInfoService.employeeCreditDistribution(pageBean);
			}
			list = employeeCreditDistribution.getList();
			writer.write(list.stream().map(item -> {
				BaseRowModel model;
				try {
					model = modelclass.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
				BeanUtils.copyProperties(item, model);
				return model;
			}).collect(Collectors.toList()), sheet);
		}
		writer.finish();
		outputStream.flush();
  	}
}