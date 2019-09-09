package com.lx.benefits.web.controller.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.enterprise.EnterprCreditDetailDto;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeCreditDetailDto;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.entercreditinfo.CreditChekingRecorder;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionFuliInfo;
import com.lx.benefits.bean.vo.entercreditinfo.CreditChekingRecorderBean;
import com.lx.benefits.bean.vo.entercreditinfo.CreditRechargeRecorder;
import com.lx.benefits.bean.vo.entercreditinfo.CreditRechargeRecorderExcelModel;
import com.lx.benefits.bean.vo.entercreditinfo.CreditRechargerReport;
import com.lx.benefits.bean.vo.entercreditinfo.CreditRefundRecorderExcelModel;
import com.lx.benefits.service.enterprise.EnterpriseCreditService;

@RequestMapping("/admin/creditinfo")
@RestController
public class EnterpriseCreditController {

	@Autowired
	private EnterpriseCreditService enterpriseCreditService;

	// 积分充值报表
	@RequestMapping("/recharge/report")
	public Object getCreditRechargeReport() {
		CreditRechargerReport creditRechargerReport = enterpriseCreditService.getCreditRechargeReport();
		return Response.succ(creditRechargerReport);
	}

	// 积分充值记录
	@RequestMapping("/recharge/recorders")
	public Object getCreditRechargeRecorders(@RequestParam(defaultValue = "1") Integer type,
			@RequestParam(required = false) String enterprName, @RequestParam(required = false) String agentName,
			@RequestParam(required = false) Long rechargeStartTime,
			@RequestParam(required = false) Long rechargeEndTime, PageBean pageBean,
			@RequestParam(required = false) Long enterprId) {
		PageResultBean<CreditRechargeRecorder> result = enterpriseCreditService.getCreditRechargeRecorders(type,
				enterprName, agentName, rechargeStartTime == null ? null : new Date(rechargeStartTime),
				rechargeEndTime == null ? null : new Date(rechargeEndTime), pageBean, enterprId);
		return Response.succ(result);
	}

	// 获取企业积分信息
	@RequestMapping("/{enterprId}")
	public Object getCreditinfo(@PathVariable Long enterprId) {
		return Response.succ(enterpriseCreditService.getCreditinfo(enterprId));
	}

	// 企业充值/退款明细
	@RequestMapping("/{enterprId}/creditlist")
	public Object getEnterpriseCreditList(@PathVariable Long enterprId,
			@RequestParam(required = false) Integer applyType, PageBean pageBean) {
		PageResultBean<CreditChekingRecorder> result = enterpriseCreditService.getEnterpriseCreditList(enterprId,
				applyType, pageBean);
		return Response.succ(result);
	}

	// 企业充值/退款申请
	@PostMapping("/{enterprId}/apply")
	public Object creditApply(@PathVariable Long enterprId, @RequestBody CreditChekingRecorder creditChekingRecorder) {
		SessionFuliInfo sessionFuliInfo = SessionContextHolder.getSessionFuliInfo();
		enterpriseCreditService.creditApply(enterprId, creditChekingRecorder, sessionFuliInfo.getLoginName());
		return Response.succ();
	}

	// 获取待审批列表
	@GetMapping("/unchecking")
	public Object getApplyInfos(@RequestParam(required = false) Integer applyType,
			@RequestParam(required = false) String enterprName, @RequestParam(required = false) Long applyStartTime,
			@RequestParam(required = false) Long applyEndTime, PageBean pageBean) {
		PageResultBean<CreditChekingRecorderBean> result = enterpriseCreditService.getUncheckedApplyInfos(applyType,
				enterprName, applyStartTime == null ? null : new Date(applyStartTime),
				applyEndTime == null ? null : new Date(applyEndTime), pageBean);
		return Response.succ(result);
	}

	// 审批企业充值/退款申请
	@PostMapping("/checking/{cashNumber}")
	public Object checkingCreditApply(@PathVariable String cashNumber,
			@RequestBody CreditChekingRecorder creditChekingRecorder) {
		SessionFuliInfo sessionFuliInfo = SessionContextHolder.getSessionFuliInfo();
		enterpriseCreditService.checkingCreditApply(cashNumber, creditChekingRecorder, sessionFuliInfo.getLoginName());
		return Response.succ();
	}

	// 积分充值记录
	@RequestMapping("/recharge/recorders/export")
	public void creditRechargeRecordersExport(@RequestParam(defaultValue = "1") Integer type,
			@RequestParam(required = false) String enterprName, @RequestParam(required = false) String agentName,
			@RequestParam(required = false) Long rechargeStartTime,
			@RequestParam(required = false) Long rechargeEndTime, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		final int pageSize = 200;
		PageBean pageBean = new PageBean() {
			public Integer getPageSize() {
				return pageSize;
			}
		};// 默认第一页码
		pageBean.setPageSize(pageSize);
		PageResultBean<CreditRechargeRecorder> recorders = enterpriseCreditService.getCreditRechargeRecorders(type,
				enterprName, agentName, rechargeStartTime == null ? null : new Date(rechargeStartTime),
				rechargeEndTime == null ? null : new Date(rechargeEndTime), pageBean, null);
		List<CreditRechargeRecorder> list = recorders.getList();
		if (CollectionUtils.isEmpty(list)) {
			JSONObject fail = Response.fail("数据为空!");
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.getWriter().write(fail.toJSONString());
			response.setStatus(HttpStatus.SC_OK);
			return;
		}
		String filename;
		Class<? extends BaseRowModel> modelclass;
		if (type == 1) {
			filename = "充值记录.xlsx";
			modelclass = CreditRechargeRecorderExcelModel.class;
		} else {
			filename = "退款记录.xlsx";
			modelclass = CreditRefundRecorderExcelModel.class;
		}
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
		Integer count = recorders.getCount();
		int page = (count - 1) / pageBean.getPageSize() + 1;
		if (page > 100) {// 至多导出20000条
			page = 100;
		}
		for (int i = 1; i <= page; i++) {
			if (i > 1) {
				pageBean.setPage(i);
				recorders = enterpriseCreditService.getCreditRechargeRecorders(type, enterprName, agentName,
						rechargeStartTime == null ? null : new Date(rechargeStartTime),
						rechargeEndTime == null ? null : new Date(rechargeEndTime), pageBean, null);
			}
			list = recorders.getList();
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

	// 到账金额和积分数据统计
	@RequestMapping("/countMoneyCreditinfo/{enterprId}")
	public Object countMoneyCreditinfo(@PathVariable Long enterprId) {
		return Response.succ(enterpriseCreditService.countMoneyCreditinfo(enterprId));
	}

	// 积分消耗明细
	@RequestMapping("/creditInfoPage")
	public Object getCreditInfoDetail(@RequestParam(required = false) Long enterprId, PageBean pageBean) {
		PageResultBean<EnterprCreditDetailDto> result = enterpriseCreditService.getCreditInfoDetail(enterprId,
				pageBean);
		return Response.succ(result);
	}

	// 员工存量积分明细
	@RequestMapping("/EmployeeStockCredit")
	public Object getEmployeeStockCredit(@RequestParam(required = false) Long enterprId, PageBean pageBean) {
		PageResultBean<EmployeeCreditDetailDto> result = enterpriseCreditService.getEmployeeCredit(enterprId, pageBean);
		return Response.succ(result);
	}
	
	//积分回收明细
	@RequestMapping("/recycleCreditRecord")
	public Object recycleCreditRecord(@RequestParam(required = false) Long enterprId, PageBean pageBean) {
		PageResultBean<CreditOperateInfo> result = enterpriseCreditService.recycleCreditRecord(enterprId, pageBean);
		return Response.succ(result);
	}

	// 银行回执记录添加
	@RequestMapping("/insertReceipt")
	public Object updateReceipt(@RequestBody CreditRechargeRecorder creditRecorder) {
		if (creditRecorder.getPayVoucher() == null || creditRecorder.getPayVoucher().trim().isEmpty()) {
			return Response.fail("收款凭证不能为空！");
		}
		if (creditRecorder.getPayTime() == null) {
			return Response.fail("到账日期不能为空！");
		}
		if (creditRecorder.getPayAmount() == null || creditRecorder.getPayAmount().compareTo(BigDecimal.ZERO) <= 0) {
			return Response.fail("到账金额必须大于0！");
		}
		SessionFuliInfo sessionFuliInfo = SessionContextHolder.getSessionFuliInfo();
		creditRecorder.setPayVoucher(creditRecorder.getPayVoucher().trim());
		creditRecorder.setCheckUser(sessionFuliInfo.getLoginName());
		int row = enterpriseCreditService.insertReceipt(creditRecorder);
		if(row>0) {	
			return Response.succ("回款成功！");
		}else {
			return Response.fail("回款失败！");
		}
	}
	
	//回款详情
	@RequestMapping("/getMoneyDetail")
	public Object getMoneyDetai(@RequestBody CreditRechargeRecorder creditRecorder ) {
		return Response.succ(enterpriseCreditService.getMoneyDetai(creditRecorder));
	}
}
