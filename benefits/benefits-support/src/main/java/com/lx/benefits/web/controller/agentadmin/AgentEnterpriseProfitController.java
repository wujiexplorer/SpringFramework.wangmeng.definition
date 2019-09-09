package com.lx.benefits.web.controller.agentadmin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionAgentInfo;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.vo.agent.AgentEnterpriseProfitBean;
import com.lx.benefits.bean.vo.agent.AgentOrderProfitBean;
import com.lx.benefits.bean.vo.agent.AgentOrderProfitExcelModel;
import com.lx.benefits.service.agent.AgentProfitService;
import com.lx.benefits.service.enterprise.EnterpriseService;

/**
 * 代理商下的企业业绩信息
 * 
 * @author qixian
 *
 */
@RestController
@RequestMapping("/agentadmin/enterprises/profits")
public class AgentEnterpriseProfitController {

	@Autowired
	private AgentProfitService agentProfitService;
	@Autowired
	private EnterpriseService enterpriseService;

	// 获取代理商业绩报表
	@GetMapping("/report")
	public Object getProfitReport() {
		SessionAgentInfo sessionAgentInfo = SessionContextHolder.getSessionAgentInfo();
		return Response.succ(agentProfitService.getProfitReport(sessionAgentInfo.getAgentId()));
	}

	// 获取代理商业绩明细
	@GetMapping("")
	public Object getEnterpriseProfits(@RequestParam(required = false) String enterprName, @RequestParam(required = false) Long createStartTime,
			@RequestParam(required = false) Long createEndTime, PageBean pageBean) {
		SessionAgentInfo sessionAgentInfo = SessionContextHolder.getSessionAgentInfo();
		if (enterprName != null && "".equals(enterprName = enterprName.trim())) {
			enterprName = null;
		}
		PageResultBean<AgentEnterpriseProfitBean> result = agentProfitService.getEnterpriseProfitsView(sessionAgentInfo.getAgentId(), enterprName,
				createStartTime == null ? null : new Date(createStartTime), createEndTime == null ? null : new Date(createEndTime), pageBean);
		return Response.succ(result);
	}

	// 获取代理商企业收益明细
	@GetMapping("/{enterprId}")
	public Object getEnterpriseProfits(@PathVariable Long enterprId, @RequestParam(required = false) String orderNumber,
			@RequestParam(required = false) String employeeName, @RequestParam(required = false) Long orderStartTime,
			@RequestParam(required = false) Long orderEndTime, PageBean pageBean) {
		SessionAgentInfo sessionAgentInfo = SessionContextHolder.getSessionAgentInfo();
		PageResultBean<?> result = agentProfitService.getEnterpriseProfits(sessionAgentInfo.getAgentId(), enterprId, orderNumber, employeeName,
				orderStartTime == null ? null : new Date(orderStartTime), orderEndTime == null ? null : new Date(orderEndTime), pageBean);
		return Response.succ(result);
	}

	// 代理商企业收益明细订单导出
	@GetMapping("/{enterprId}/orderexport")
	public void profitsOrderExport(@PathVariable Long enterprId, @RequestParam(required = false) String orderNumber,
			@RequestParam(required = false) String employeeName, @RequestParam(required = false) Long orderStartTime,
			@RequestParam(required = false) Long orderEndTime, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer agentId = SessionContextHolder.getSessionAgentInfo().getAgentId();
		final int pageSize = 200;
		PageBean pageBean = new PageBean() {
			public Integer getPageSize() {
				return pageSize;
			}
		};// 默认第一页码
		pageBean.setPageSize(pageSize);
		PageResultBean<?> enterpriseProfits = agentProfitService.getEnterpriseProfits(agentId, enterprId, orderNumber, employeeName,
				orderStartTime == null ? null : new Date(orderStartTime), orderEndTime == null ? null : new Date(orderEndTime), pageBean);
		List<?> list = enterpriseProfits.getList();
		if (CollectionUtils.isEmpty(list)) {
			JSONObject fail = Response.fail("数据为空!");
			response.getWriter().write(fail.toJSONString());
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.setStatus(HttpStatus.SC_OK);
			return;
		}
		Object data0 = list.get(0);
		if (!(data0 instanceof AgentOrderProfitBean)) {
			JSONObject fail = Response.fail("只能导出订单收益信息!");
			response.getWriter().write(fail.toJSONString());
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.setStatus(HttpStatus.SC_OK);
			return;
		}

		String filename = "订单收益信息.xlsx";
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE);
		response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO-8859-1"));
		OutputStream outputStream;
		String header = request.getHeader("Accept-Encoding");
		if (header != null && header.contains("gzip")) {// 支持gzip压缩
			response.setHeader("content-encoding", "gzip");
			outputStream = new GZIPOutputStream(response.getOutputStream());
		} else {
			outputStream = response.getOutputStream();
		}
		ExcelWriter writer = EasyExcelFactory.getWriter(outputStream, ExcelTypeEnum.XLSX, true);
		Sheet sheet = new Sheet(1, 1, AgentOrderProfitExcelModel.class);
		sheet.setSheetName("订单信息");
		Integer count = enterpriseProfits.getCount();
		int page = (count - 1) / pageBean.getPageSize() + 1;
		if (page > 100) {// 至多导出20000条
			page = 100;
		}
		String enterprName;
		EnterprUserInfo enterprise = enterpriseService.getEnterprise(enterprId);
		if (enterprise != null) {
			enterprName = enterprise.getEnterprName();
		} else {
			enterprName = "";
		}
		for (int i = 1; i <= page; i++) {
			if (i > 1) {
				pageBean.setPage(i);
				enterpriseProfits = agentProfitService.getEnterpriseProfits(agentId, enterprId, orderNumber, employeeName,
						orderStartTime == null ? null : new Date(orderStartTime), orderEndTime == null ? null : new Date(orderEndTime), pageBean);
			}
			list = enterpriseProfits.getList();
			writer.write(list.stream().map(item -> {
				AgentOrderProfitExcelModel model = new AgentOrderProfitExcelModel();
				BeanUtils.copyProperties(item, model);
				model.setEnterprName(enterprName);
				return model;
			}).collect(Collectors.toList()), sheet);
		}
		writer.finish();
		outputStream.flush();
	}

}
