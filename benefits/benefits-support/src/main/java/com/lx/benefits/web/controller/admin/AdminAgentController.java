package com.lx.benefits.web.controller.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lx.benefits.bean.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.lx.benefits.bean.constants.AgentRebateType;
import com.lx.benefits.bean.constants.RegisterType;
import com.lx.benefits.bean.entity.agent.AgentAccountInfo;
import com.lx.benefits.bean.entity.agent.AgentIncomeRecorder;
import com.lx.benefits.bean.entity.agent.AgentLevel;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionFuliInfo;
import com.lx.benefits.bean.vo.agent.AgentAccountInfoBean;
import com.lx.benefits.bean.vo.agent.AgentBindInfoBean;
import com.lx.benefits.bean.vo.agent.AgentBlackGoodsBean;
import com.lx.benefits.bean.vo.agent.AgentCheckingBean;
import com.lx.benefits.bean.vo.agent.AgentIncomeRecorderExcelModel;
import com.lx.benefits.bean.vo.agent.AgentProfitReport;
import com.lx.benefits.service.agent.AgentBlackGoodService;
import com.lx.benefits.service.agent.AgentCashService;
import com.lx.benefits.service.agent.AgentProfitService;
import com.lx.benefits.service.agent.AgentService;
import com.lx.benefits.service.agent.AgentUserService;
import com.lx.benefits.service.enterprise.EnterpriseService;

@RestController("adminAgentController")
@RequestMapping("/admin/agents")
@Slf4j
public class AdminAgentController {
	@Autowired
	private AgentService agentService;
	@Autowired
	private AgentUserService agentUserService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private AgentProfitService agentProfitService;
	@Autowired
	private AgentCashService agentCashService;
	@Autowired
	private AgentBlackGoodService agentBlackGoodService;

	// 获取代理商列表
	@GetMapping("")
	public Object getAgents(@RequestParam(required = false) String agentName, @RequestParam(required = false) String contactUser,
			@RequestParam(required = false) String contactPhone, PageBean pageBean) {
		return Response.succ(agentService.getAgents(agentName, contactUser, contactPhone, pageBean));
	}

	// 代理商业绩报表
	@RequestMapping("/{agentId}/profits/report")
	public Object getAgentProfitsReport(@PathVariable Integer agentId) {
		AgentProfitReport AgentProfitReport = agentProfitService.getProfitReportWithRebate(agentId);
		return Response.succ(AgentProfitReport);
	}

	// 代理商收支明细
	@RequestMapping("/{agentId}/profits/recorders")
	public Object getAgentProfitsRecorders(@PathVariable Integer agentId, @RequestParam(required = false) Integer type,
			@RequestParam(required = false) Long createStartTime, @RequestParam(required = false) Long createEndTime, PageBean pageBean) {
		PageResultBean<AgentIncomeRecorder> incomerecorders = agentCashService.getIncomerecorders(agentId, type,
				createStartTime == null ? null : new Date(createStartTime), createEndTime == null ? null : new Date(createEndTime), pageBean);
		return Response.succ(incomerecorders);
	}

	// 查看代理商下的企业
	@GetMapping("/{agentId}/enterprises")
	public Object getEnterprises(@PathVariable Integer agentId, @RequestParam(required = false) String enterprName,
			@RequestParam(required = false) Long bindStartTime, @RequestParam(required = false) Long bindEndTime, PageBean pageBean) {
		PageResultBean<EnterprUserInfo> enterprUserInfos = enterpriseService.getEnterprises(agentId, enterprName,
				bindStartTime == null ? null : new Date(bindStartTime), bindEndTime == null ? null : new Date(bindEndTime), pageBean);
		return Response.succ(enterprUserInfos);
	}

	// 查看代理商下的企业历史绑定信息
	@GetMapping("/{agentId}/enterprbindhistory")
	public Object getEnterprisesBindhistory(@PathVariable Integer agentId, PageBean pageBean) {
		return Response.succ(enterpriseService.getAgentEnterprisesBindhistory(agentId, pageBean));
	}

	// 获取代理商的下级列表
	@GetMapping("/{agentId}/subagents")
	public Object getSubagents(@PathVariable Integer agentId, PageBean pageBean) {
		PageResultBean<AgentAccountInfoBean> pageResult = agentService.getSubagents(agentId, null, null, null, null, pageBean, false);
		return Response.succ(pageResult);
	}

	// 修改代理商密码
	@PostMapping("/{agentId}/password")
	public Object resetAgentPassword(@PathVariable Integer agentId, @RequestBody AgentAccountInfo request) {
		agentUserService.resetAgentPassword(agentId, request.getPassword());
		return Response.succ();
	}

	// 冻结/解冻代理商
	@PostMapping("/{agentId}/accountStatus")
	public Object updateAgentAccountStatus(@PathVariable Integer agentId, @RequestBody AgentAccountInfo request) {
		agentService.updateAgentAccountStatus(agentId, request.getAccountStatus());
		return Response.succ();
	}

	// 提交代理商信息
	@PostMapping("/add")
	public Object addAgent(@RequestBody AgentAccountInfoBean request) {
		SessionFuliInfo sessionInfo = SessionContextHolder.getSessionFuliInfo();
		agentService.addAgent(request, RegisterType.INTERNAL_OPENING, sessionInfo.getLoginName(), null);
		return Response.succ();
	}

	// 更新代理商联系人信息
	@PostMapping("/{agentId}/contactinfo/update")
	public Object updateAgentContactinfo(@PathVariable Integer agentId, @RequestBody AgentAccountInfoBean request) {
		agentService.updateAgentContactinfo(agentId, request);
		return Response.succ();
	}

	// 修改代理商上级绑定关系
	@PostMapping("/{agentId}/bindinfo/update")
	public Object updateAgentBindInfo(@PathVariable Integer agentId, @Validated @RequestBody AgentBindInfoBean request) {
		agentService.updateAgentBindInfo(agentId, request);
		return Response.succ();
	}

	// 获取代理商审批列表
	@GetMapping("/checking/list")
	public Object getCheckingList(@RequestParam(defaultValue = "0") Integer checked, @RequestParam(required = false) String agentName,
			@RequestParam(required = false) Integer checkStatus, PageBean pageBean) {
		return Response.succ(agentService.getCheckingList(checked, agentName, checkStatus, pageBean));
	}

	// 对代理商进行审批
	@PostMapping("/checking/{agentId}")
	public Object checkingAgent(@PathVariable Integer agentId, @RequestBody AgentCheckingBean agentCheckingBean) {
		SessionFuliInfo sessionFuliInfo = SessionContextHolder.getSessionFuliInfo();
		agentService.checkingAgent(agentId, agentCheckingBean, sessionFuliInfo.getLoginName());
		return Response.succ();
	}

	// 获取代理商审批记录
	@GetMapping("/checking/{agentId}")
	public Object getCheckingAgent(@PathVariable Integer agentId) {
		return Response.succ(agentService.getAgentCheckingInfo(agentId));
	}

	// 获取代理商等级列表
	@GetMapping("/agentlevels")
	public Object getAgentlevels() {
		List<AgentLevel> agentlevels = agentService.getAgentlevels();
		return Response.succ(agentlevels);
	}

	// 添加代理商等级
	@PostMapping("/agentlevels/add")
	public Object addAgentlevel(@Validated @RequestBody AgentLevel agentLevel) {
		agentService.addAgentLevel(agentLevel);
		return Response.succ();
	}

	// 获取代理商信息
	@GetMapping("/{agentId}")
	public Object getAgentInfo(@PathVariable Integer agentId) {
		AgentAccountInfoBean agentInfoBean = agentService.getAgentInfoBean(agentId);
		if (agentInfoBean == null) {
			return Response.fail("该代理商不存在!");
		}
		return Response.succ(agentInfoBean);
	}

	// 修改代理商等级
		@PostMapping("agentlevels/{id}/update")
	public Object updateAgentlevel(@PathVariable Integer id, @RequestBody AgentLevel agentLevel) {
		agentService.updateAgentlevelById(id, agentLevel);
		return Response.succ();
	}

	// 删除代理商等级
	@GetMapping("/agentlevels/{id}/delete")
	public Object deleteAgentlevel(@PathVariable Integer id) {
		agentService.deleteAgentlevelById(id);
		return Response.succ();
	}

	// 获取商品黑名单配置
	@GetMapping("/customized/blackgoods")
	public Object getBlackGoods() {
		AgentBlackGoodsBean agentBlackGoods = agentBlackGoodService.getBlackGoodsWithName(AgentRebateType.SALES.getType());
		return Response.succ(agentBlackGoods);
	}

	// 商品黑名单配置
	@PostMapping("/customized/blackgoods")
	public Object updateBlackGoods(@RequestBody AgentBlackGoodsBean agentBlackGoodsBean) {
		agentBlackGoodService.updateBlackGoods(AgentRebateType.SALES.getType(), agentBlackGoodsBean);
		return Response.succ();
	}

	// 代理商收支明细导出
	@RequestMapping("/{agentId}/profits/recorders/export")
	public void profitsOrderExport(@PathVariable Integer agentId, @RequestParam(required = false) Integer type,
			@RequestParam(required = false) Long createStartTime, @RequestParam(required = false) Long createEndTime, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		final int pageSize = 200;
		PageBean pageBean = new PageBean() {
			public Integer getPageSize() {
				return pageSize;
			}
		};// 默认第一页码
		pageBean.setPageSize(pageSize);
		PageResultBean<AgentIncomeRecorder> incomerecorders = agentCashService.getIncomerecorders(agentId, type,
				createStartTime == null ? null : new Date(createStartTime), createEndTime == null ? null : new Date(createEndTime), pageBean);
		List<AgentIncomeRecorder> list = incomerecorders.getList();
		if (CollectionUtils.isEmpty(list)) {
			JSONObject fail = Response.fail("数据为空!");
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.getWriter().write(fail.toJSONString());
			response.setStatus(HttpStatus.SC_OK);
			return;
		}
		String filename = "代理商收支明细.xlsx";
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
		Sheet sheet = new Sheet(1, 1, AgentIncomeRecorderExcelModel.class);
		sheet.setSheetName("收支明细");
		Integer count = incomerecorders.getCount();
		int page = (count - 1) / pageBean.getPageSize() + 1;
		if (page > 100) {// 至多导出20000条
			page = 100;
		}
		for (int i = 1; i <= page; i++) {
			if (i > 1) {
				pageBean.setPage(i);
				incomerecorders = agentCashService.getIncomerecorders(agentId, type, createStartTime == null ? null : new Date(createStartTime),
						createEndTime == null ? null : new Date(createEndTime), pageBean);
			}
			list = incomerecorders.getList();
			writer.write(list.stream().map(item -> {
				AgentIncomeRecorderExcelModel model = new AgentIncomeRecorderExcelModel();
				model.setCreateTime(item.getCreateTime());
				model.setType(item.getType());
				model.setVoucherNumber(item.getVoucherNumber());
				model.setCashAmount(item.getCashAmount());
				return model;
			}).collect(Collectors.toList()), sheet);
		}
		writer.finish();
		outputStream.flush();
	}


	@PostMapping("/updateAccountInfoAgentLevel")
	public JSONObject updateAccountInfoAgentLevel(Integer agentId,Integer agentLevelId){
		try{
			Integer count  = agentService.updateAccountInfoAgentLevel(agentId,agentLevelId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("count",count);
			log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
			return Response.succ(jsonObject);
		} catch (Exception e) {
			log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
			log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
			return Response.fail(e.getMessage());
		}
	}
}
