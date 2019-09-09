package com.lx.benefits.service.enterprfestival.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.admin.campaign.FestivalPacketInfoBean;
import com.lx.benefits.bean.dto.admin.campaign.FestivalPacketInfoDto;
import com.lx.benefits.bean.dto.admin.campaign.FestivalPacketQueryReqDto;
import com.lx.benefits.bean.dto.admin.campaign.ImportCampainReqDto;
import com.lx.benefits.bean.dto.admin.campaign.PointsAllocateExcelDto;
import com.lx.benefits.bean.dto.admin.campaign.TopicInfoPageBean;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.EnterprCreditInfoDto;
import com.lx.benefits.bean.dto.product.ProductSearchBean;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfoExample;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfo;
import com.lx.benefits.bean.entity.entercreditinfo.EnterprCreditInfoExample;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacket;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacketExample;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacketExample.Criteria;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.operation.TopicEntity;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.ProductTopicCondition;
import com.lx.benefits.bean.enums.CampaignStatusEnum;
import com.lx.benefits.bean.enums.CreditTypeEnum;
import com.lx.benefits.bean.enums.OptTypeEnum;
import com.lx.benefits.bean.enums.YNEnum;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.EasyExcelUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionShopInfo;
import com.lx.benefits.dao.enterprfestivalpacket.EnterprFestivalDao;
import com.lx.benefits.mapper.creditoperateinfo.CreditOperateInfoMapper;
import com.lx.benefits.mapper.employeecreditinfo.EmployeeCreditInfoMapper;
import com.lx.benefits.mapper.enterprfestivalpacket.EnterprFestivalPacketMapper;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.mapper.product.ProductTopicMapper;
import com.lx.benefits.service.creditoperateinfo.CreditOperateInfoService;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.enterprcredit.EnterprCreditInfoService;
import com.lx.benefits.service.enterprfestival.EnterprFestivalService;
import com.lx.benefits.service.operation.TopicService;
import com.lx.benefits.service.product.ProductService;

/**
 * @author by yingcai on 2018/12/26.
 */
@Service
public class EnterprFestivalServiceImpl implements EnterprFestivalService {

	private final static Logger logger = LoggerFactory.getLogger(EnterprFestivalServiceImpl.class);

	@Autowired
	private EnterprFestivalDao enterprFestivalDao;

	/** 操作信息 */
	@Autowired
	private CreditOperateInfoService creditOperateInfoService;

	/** 企业信息 */
	@Autowired
	private EnterprCreditInfoService enterprCreditInfoService;

	@Autowired
	private EmployeeUserInfoService employeeUserInfoService;

	@Autowired
	private EnterprUserInfoMapper enterprUserInfoMapper;

	/** 企业员工信息 */
	@Autowired
	private EmployeeCreditInfoService employeeCreditInfoService;

	@Autowired
	private EnterprFestivalPacketMapper enterprFestivalPacketMapper;

	@Autowired
	private EmployeeCreditInfoMapper employeeCreditInfoMapper;

	@Autowired
	private CreditOperateInfoMapper creditOperateInfoMapper;
	@Autowired
	private TopicService topicService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductTopicMapper productTopicMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject insert(FestivalPacketInfoDto req) {
		EnterprFestivalPacket enterprFestivalPacket = new EnterprFestivalPacket();
		enterprFestivalPacket.setIsWhitelist(req.getIsWhitelist());
		Long enterpriseId = req.getEnterpriseId();
		if (null == enterpriseId || enterpriseId < 1) {
			return Response.fail("企业ID不能为空");
		}
		enterprFestivalPacket.setEnterprId(enterpriseId);
		String campaignName = req.getCampaignName();
		if (null == campaignName || campaignName.isEmpty()) {
			return Response.fail("活动名称不能为空");
		}

		String campaignNameEn = req.getCampaignNameEn();
		if (null == campaignNameEn || campaignNameEn.isEmpty()) {
			return Response.fail("活动英文名称不能为空");
		}

		EnterprFestivalPacketExample example = new EnterprFestivalPacketExample();
		example.createCriteria().andCampaignNameEqualTo(campaignName);
		List<EnterprFestivalPacket> list = enterprFestivalDao.find(example);
		if (null != list && list.size() > 0) {
			return Response.fail("活动名称不能重复");
		}

		EnterprFestivalPacketExample example2 = new EnterprFestivalPacketExample();
		example2.createCriteria().andCampaignNameEqualTo(campaignNameEn);
		List<EnterprFestivalPacket> list2 = enterprFestivalDao.find(example2);
		if (null != list2 && list2.size() > 0) {
			return Response.fail("活动英文名称不能重复");
		}

		enterprFestivalPacket.setCampaignNameEn(campaignNameEn);
		enterprFestivalPacket.setCampaignName(campaignName);
		String creditType = req.getCreditType();
		CreditTypeEnum creditTypeEnum = CreditTypeEnum.getCreditTypeEnum(creditType);
		if (null == creditTypeEnum) {
			return Response.fail("无效的活动积分类型");
		}
		Integer creditTypeVal = creditTypeEnum.getValue();
		enterprFestivalPacket.setCreditType(creditTypeVal);

		Long campaignThemeId = req.getCampaignThemeId();
		// if (null == campaignThemeId || campaignThemeId < 1) {
		if (null == campaignThemeId) {
			return Response.fail("无效的活动主题");
		}
		enterprFestivalPacket.setCampaignThemeId(campaignThemeId.intValue());

		String campaignStatus = req.getCampaignStatus();
		CampaignStatusEnum campaignStatusEnum = CampaignStatusEnum.getCampaignStatusEnum(campaignStatus);
		if (null != campaignStatusEnum) {
			enterprFestivalPacket.setCampaignStatus(campaignStatusEnum.getValue());
		}

		String startTime = req.getStartTime();
		enterprFestivalPacket.setStartTime(DateUtil.date2IntegerUnixTime(startTime));

		String endTime = req.getEndTime();
		enterprFestivalPacket.setEndTime(DateUtil.date2IntegerUnixTime(endTime));

		String creditListFile = req.getCreditListFile();
		if (StringUtils.isNotEmpty(creditListFile)) {
			enterprFestivalPacket.setCreditListFile(creditListFile);
		}
		List<Long> campaignThemeIdList = req.getCampaignThemeIdList();
		if (null != campaignThemeIdList && campaignThemeIdList.size() > 0) {
			StringBuilder goodsIds = new StringBuilder();
			for (int i = 0; i < campaignThemeIdList.size(); i++) {
				if (i < campaignThemeIdList.size() - 1)
					goodsIds.append(campaignThemeIdList.get(i) + ",");
				else
					goodsIds.append(String.valueOf(campaignThemeIdList.get(i)));
			}
			enterprFestivalPacket.setCampaignThemeIdList(goodsIds.toString());
		}

		String bannerPath = req.getBannerPath();
		if (StringUtils.isNotEmpty(bannerPath)) {
			enterprFestivalPacket.setBannerPath(bannerPath);
		}
		Long defaultGoodsId = req.getDefaultGoodsId();
		if (null != defaultGoodsId && defaultGoodsId > 0) {
			enterprFestivalPacket.setDefaultGoodsId(defaultGoodsId);
		}
		String remark = req.getRemark();
		if (StringUtils.isNotEmpty(remark)) {
			enterprFestivalPacket.setRemark(remark);
		}
		enterprFestivalPacket.setType(req.getType());

		if (null != req.getIsEmail()) {
			enterprFestivalPacket.setIsEmail(req.getIsEmail());
		}
		String remindEmail = req.getRemindEmail();
		if (StringUtils.isNotEmpty(remindEmail)) {
			enterprFestivalPacket.setRemindEmail(remindEmail);
		}
		if (null != req.getIsSms()) {
			enterprFestivalPacket.setIsSms(req.getIsSms());
		}
		String remindSms = req.getRemindSms();
		if (StringUtils.isNotEmpty(remindSms)) {
			enterprFestivalPacket.setRemindSms(remindSms);
		}
		if (null != req.getThirdPay()) {
			enterprFestivalPacket.setThirdPay(req.getThirdPay());
		}
		// 生成活动编号
		enterprFestivalPacket.setCampaignCode(DateUtil.dateToString(new Date(), "yyMMddhhmmss"));
		/** 新增礼金活动 */
		Long festivalId = enterprFestivalDao.insert(enterprFestivalPacket);
		if (festivalId < 1) {
			return Response.fail("添加失败");
		}
		return Response.succ(true);
	}

	public void redistribution(Long enterpriseId, FestivalPacketInfoDto req) {
		String creditListFile = req.getCreditListFile();
		Long campaignId = req.getCampaignId();
		CreditTypeEnum creditTypeEnum = CreditTypeEnum.getCreditTypeEnum(req.getCreditType());
		Integer creditTypeVal = creditTypeEnum.getValue();
		EnterprCreditInfoDto enterprCreditInfoDto = enterprCreditInfoService.findByEnterprIdOne(enterpriseId);
		/** 计算企业积分够不够，并减去 */
		BigDecimal totalCredit = new BigDecimal(0.0);
		try {
			UrlResource resource = new UrlResource(creditListFile);
			List<Object> list = EasyExcelUtil.readExcelWithModel(resource.getInputStream(), PointsAllocateExcelDto.class, ExcelTypeEnum.XLSX);
			for (Object obj : list) {
				PointsAllocateExcelDto pointsAllocateExcelDto = (PointsAllocateExcelDto) obj;
				String points = pointsAllocateExcelDto.getPoints();
				totalCredit = totalCredit.add(new BigDecimal(points));
			}

			// 扣除企业
			Long creditId = 0L;
			boolean flag = false;
			if (enterprCreditInfoDto.getValidCredit().subtract(totalCredit).doubleValue() >= 0.0) {
				EnterprCreditInfo uenterprinfo = new EnterprCreditInfo();
				uenterprinfo.setValidCredit(enterprCreditInfoDto.getValidCredit().subtract(totalCredit));
				EnterprCreditInfoExample enterprCreditInfoExample = new EnterprCreditInfoExample();
				enterprCreditInfoExample.createCriteria().andEnterprIdEqualTo(enterpriseId);
				// 扣除企业有效积分
				Integer enterPrresult = enterprCreditInfoService.update(uenterprinfo, enterprCreditInfoExample);
				if (enterPrresult > 0) {
					CreditOperateInfo creditOperateInfo = new CreditOperateInfo();
					creditOperateInfo.setCampaignId(campaignId);
					creditOperateInfo.setCreditType(creditTypeVal);
					creditOperateInfo.setCredit(totalCredit);
					creditOperateInfo.setOptType(OptTypeEnum.DISTRIBUTION_TO_EMPLOYEE.getValue());
					creditOperateInfo.setOptUserId(SessionContextHolder.getSessionFuliInfo().getAdminId());
					creditOperateInfo.setOwnerUserId(enterpriseId);
					creditId = creditOperateInfoService.insert(creditOperateInfo);
				}
				flag = true;
			} else {
				throw new RuntimeException("企业积分不够");
			}

			// 企业扣除成功
			if (flag && creditId > 0) {
				for (Object obj : list) {
					String no = ((PointsAllocateExcelDto) obj).getEmployeeNo();
					String points = ((PointsAllocateExcelDto) obj).getPoints();
					EmployeeUserInfo employeeUserInfo = employeeUserInfoService.findByNoAndEnterprId(no, enterpriseId);
					if (null != employeeUserInfo) {
						Long employeeId = employeeUserInfo.getEmployeeId();
						// 给员工加上积分
						employeeCreditInfoService.alloCredit(employeeId, campaignId, creditTypeVal, new BigDecimal(points));
						// 添加员工操作记录
						CreditOperateInfo creditOperateInfo = new CreditOperateInfo();

						creditOperateInfo.setCampaignId(campaignId);
						creditOperateInfo.setCreditType(creditTypeVal);
						creditOperateInfo.setCredit(new BigDecimal(points));
						creditOperateInfo.setOptType(OptTypeEnum.ADMIN_DISTRIBUTION_USER_ADD.getValue());
						creditOperateInfo.setParentOptId(creditId);
						creditOperateInfo.setOptUserId(SessionContextHolder.getSessionFuliInfo().getAdminId());
						creditOperateInfo.setOwnerUserId(employeeId);
						creditOperateInfoService.insert(creditOperateInfo);
					} else {
						throw new RuntimeException("查不到员工，员工no：" + no);
					}
				}
			}

		} catch (MalformedURLException e) {
			throw new RuntimeException("分配积分失败");
		} catch (IOException e) {
			throw new RuntimeException("分配积分失败");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long update(FestivalPacketInfoDto req) {
		EnterprFestivalPacket enterprFestivalPacket = new EnterprFestivalPacket();
		enterprFestivalPacket.setIsWhitelist(req.getIsWhitelist());
		Long enterpriseId = req.getEnterpriseId();
		if (enterpriseId > 0) {
			enterprFestivalPacket.setEnterprId(enterpriseId);
		}
		String campaignName = req.getCampaignName();
		if (StringUtils.isNotEmpty(campaignName)) {
			enterprFestivalPacket.setCampaignName(campaignName);

		}
		String campaignNameEn = req.getCampaignNameEn();
		if (StringUtils.isNotEmpty(campaignNameEn)) {
			enterprFestivalPacket.setCampaignNameEn(campaignNameEn);

		}
		String creditType = req.getCreditType();
		CreditTypeEnum creditTypeEnum = CreditTypeEnum.getCreditTypeEnum(creditType);
		if (null != creditTypeEnum) {
			enterprFestivalPacket.setCreditType(creditTypeEnum.getValue());
		}

		Long campaignThemeId = req.getCampaignThemeId();
		// if (campaignThemeId > 0) {
		if (campaignThemeId >= 0) {
			enterprFestivalPacket.setCampaignThemeId(campaignThemeId.intValue());
		}

		String campaignStatus = req.getCampaignStatus();
		CampaignStatusEnum campaignStatusEnum = CampaignStatusEnum.getCampaignStatusEnum(campaignStatus);
		if (null != campaignStatusEnum) {
			enterprFestivalPacket.setCampaignStatus(campaignStatusEnum.getValue());
		}

		String startTime = req.getStartTime();
		Integer start = DateUtil.date2IntegerUnixTime(startTime);
		if (start > 0) {
			enterprFestivalPacket.setStartTime(start);
		}

		String endTime = req.getEndTime();
		Integer end = DateUtil.date2IntegerUnixTime(endTime);
		if (end > 0) {
			enterprFestivalPacket.setEndTime(end);
		}

		String creditListFile = req.getCreditListFile();
		if (StringUtils.isNotEmpty(creditListFile)) {
			enterprFestivalPacket.setCreditListFile(creditListFile);
		}
		List<Long> campaignThemeIdList = req.getCampaignThemeIdList();
		if (null != campaignThemeIdList && campaignThemeIdList.size() > 0) {
			StringBuilder goodsIds = new StringBuilder();
			for (int i = 0; i < campaignThemeIdList.size(); i++) {
				if (i < campaignThemeIdList.size() - 1)
					goodsIds.append(campaignThemeIdList.get(i) + ",");
				else
					goodsIds.append(String.valueOf(campaignThemeIdList.get(i)));
			}
			enterprFestivalPacket.setCampaignThemeIdList(goodsIds.toString());
		}

		String bannerPath = req.getBannerPath();
		if (StringUtils.isNotEmpty(bannerPath)) {
			enterprFestivalPacket.setBannerPath(bannerPath);
		}
		Long defaultGoodsId = req.getDefaultGoodsId();
		if (null != defaultGoodsId && defaultGoodsId > 0) {
			enterprFestivalPacket.setDefaultGoodsId(defaultGoodsId);
		}
		String remark = req.getRemark();
		if (StringUtils.isNotEmpty(remark)) {
			enterprFestivalPacket.setRemark(remark);
		}

		if (null != req.getIsEmail()) {
			enterprFestivalPacket.setIsEmail(req.getIsEmail());
		}
		String remindEmail = req.getRemindEmail();
		if (StringUtils.isNotEmpty(remindEmail)) {
			enterprFestivalPacket.setRemindEmail(remindEmail);
		}
		if (null != req.getIsSms()) {
			enterprFestivalPacket.setIsSms(req.getIsSms());
		}
		String remindSms = req.getRemindSms();
		if (StringUtils.isNotEmpty(remindSms)) {
			enterprFestivalPacket.setRemindSms(remindSms);
		}

		enterprFestivalPacket.setType(req.getType());
		enterprFestivalPacket.setThirdPay(req.getThirdPay());

		EnterprFestivalPacketExample example = new EnterprFestivalPacketExample();
		example.createCriteria().andCampaignIdEqualTo(req.getCampaignId());

		// 积分分配
		EnterprFestivalPacket festivalPacket = enterprFestivalDao.selectByPrimaryKey(req.getCampaignId());
		if (!StringUtils.isEmpty(enterprFestivalPacket.getCreditListFile())) {
			if (!festivalPacket.getCreditListFile().equals(enterprFestivalPacket.getCreditListFile())) {
				try {
					redistribution(enterpriseId, req);
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage());
				}
			}
		}
		Integer count = enterprFestivalDao.update(enterprFestivalPacket, example);
		return Long.valueOf(count);
	}

	@Override
	public Long insert(EnterprFestivalPacket enterprFestival) {
		return enterprFestivalDao.insert(enterprFestival);
	}

	@Override
	public JSONObject findById(Long campaignId, boolean isFront) {
		EnterprFestivalPacket enterprFestivalPacket = enterprFestivalDao.selectByPrimaryKey(campaignId);
		if (null == enterprFestivalPacket) {
			return Response.fail("活动信息获取失败");
		}
		if (isFront) {
			Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
			// 判断时间范围
			Long currentTime = DateUtil.getCurrentUnixTime();
			Integer startTime = enterprFestivalPacket.getStartTime();
			startTime = null == startTime || startTime < 1 ? null : startTime;
			Integer endTime = enterprFestivalPacket.getEndTime();
			endTime = null == endTime || endTime < 1 ? null : endTime;
			boolean isInRange = true;
			if (null != startTime && currentTime < startTime) {
				isInRange = false;
			}
			if (null != endTime && currentTime > endTime) {
				isInRange = false;
			}
			if (!isInRange) {
				return Response.fail("活动不在有效期内");
			}
			Long enterprId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
			if (!enterprId.equals(enterprFestivalPacket.getEnterprId())) {
				return Response.notAuth();
			}
			if (!YNEnum.N.val().equals(enterprFestivalPacket.getCampaignStatus())) {
				return Response.fail("活动已失效");
			}
			CreditOperateInfoExample cExample = new CreditOperateInfoExample();
			cExample.setPage(1);
			cExample.setPageSize(1000);
			cExample.createCriteria().andOptTypeEqualTo(OptTypeEnum.ADMIN_DISTRIBUTION_USER_ADD.getValue())
					.andCampaignIdEqualTo(enterprFestivalPacket.getCampaignId());
			List<CreditOperateInfo> creditOperateInfoList = creditOperateInfoService.find(cExample);
			if (null == creditOperateInfoList || creditOperateInfoList.isEmpty()) {
				return Response.fail("无权限查看当前活动");
			}
			List<Long> ownerUserIdList = new ArrayList<>();
			for (CreditOperateInfo creditOperateInfo : creditOperateInfoList) {
				ownerUserIdList.add(creditOperateInfo.getOwnerUserId());
			}
			if (!ownerUserIdList.contains(employeeId)) {
				return Response.fail("无权限查看当前活动");
			}
		}
		FestivalPacketInfoDto dto = new FestivalPacketInfoDto();
		dto.setBannerPath(enterprFestivalPacket.getBannerPath());
		dto.setCampaignId(enterprFestivalPacket.getCampaignId());
		dto.setCampaignName(enterprFestivalPacket.getCampaignName());
		dto.setCampaignCode(enterprFestivalPacket.getCampaignCode());
		dto.setIsEmail(enterprFestivalPacket.getIsEmail());
		dto.setRemindEmail(enterprFestivalPacket.getRemindEmail());
		dto.setIsSms(enterprFestivalPacket.getIsSms());
		dto.setRemindSms(enterprFestivalPacket.getRemindSms());
		dto.setThirdPay(enterprFestivalPacket.getThirdPay());
		dto.setIsWhitelist(enterprFestivalPacket.getIsWhitelist());
		dto.setType(enterprFestivalPacket.getType());
		dto.setCampaignNameEn(enterprFestivalPacket.getCampaignNameEn());

		Integer creditType = enterprFestivalPacket.getCreditType();
		CreditTypeEnum creditTypeEnum = CreditTypeEnum.getCreditTypeEnum(creditType);
		dto.setCreditType(creditTypeEnum.name());

		Integer campaignStatus = enterprFestivalPacket.getCampaignStatus();
		CampaignStatusEnum campaignStatusEnum = CampaignStatusEnum.getCampaignStatusEnum(campaignStatus);
		dto.setCampaignStatus(campaignStatusEnum.name());

		dto.setCreditListFile(enterprFestivalPacket.getCreditListFile());
		dto.setDefaultGoodsId(enterprFestivalPacket.getDefaultGoodsId());
		dto.setEnterpriseId(enterprFestivalPacket.getEnterprId());
		String campaignThemeIds = enterprFestivalPacket.getCampaignThemeIdList();
		List<Long> CampaignThemeIdList = Arrays.asList(campaignThemeIds.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
		dto.setCampaignThemeIdList(CampaignThemeIdList);
		dto.setRemark(enterprFestivalPacket.getRemark());
		dto.setStartTime(DateUtil.unixTime2Date(enterprFestivalPacket.getStartTime()));
		dto.setEndTime(DateUtil.unixTime2Date(enterprFestivalPacket.getEndTime()));
		dto.setCreated(DateUtil.unixTime2Date(enterprFestivalPacket.getCreated()));
		dto.setUpdated(DateUtil.unixTime2Date(enterprFestivalPacket.getUpdated()));
		dto.setCampaignThemeId(Long.valueOf(enterprFestivalPacket.getCampaignThemeId()));
		return Response.succ(dto);
	}

	@Override
	public JSONObject findByName(FestivalPacketQueryReqDto reqDto, boolean isFront) {
		List<FestivalPacketInfoDto> list = new ArrayList<>();
		int count = 0;
		EnterprFestivalPacketExample example = new EnterprFestivalPacketExample();
		BeanUtils.copyProperties(reqDto, example);
		if (null != reqDto.getEnterprId() && reqDto.getEnterprId() > 0) {
			example.createCriteria().andEnterprIdEqualTo(reqDto.getEnterprId());
		}
		if (null != reqDto.getCampaignId() && reqDto.getCampaignId() > 0) {
			example.createCriteria().andCampaignIdEqualTo(reqDto.getCampaignId());
		}
		if (null != reqDto.getCampaignName() && !reqDto.getCampaignName().isEmpty()) {
			example.createCriteria().andCampaignNameEqualTo(reqDto.getCampaignName());
		}
		if (!isFront) {
			if (null != reqDto.getType() && reqDto.getType() > 0) {
				example.createCriteria().andTypeEqualTo(reqDto.getType());
			} else {
				List<Integer> types = new ArrayList<>();
				types.add(CreditTypeEnum.ADVANCESALE.getValue());
				example.createCriteria().andTypeNotIn(types);
			}
		}
		Long employeeId = null;
		if (isFront) {
			employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
			example.createCriteria().andCampaignStatusEqualTo(0);
		}
		Integer page = (reqDto.getPage() - 1) * reqDto.getPageSize();
		example.setPage(page);
		example.setPageSize(reqDto.getPageSize());
		example.setOrderByClause(" created desc ");
		List<EnterprFestivalPacket> enterprFestivalPacketList = enterprFestivalDao.find(example);
		if (null != enterprFestivalPacketList && !enterprFestivalPacketList.isEmpty()) {
			Map<Long, List<Long>> campaignIdMap = new HashMap<>();
			List<EnterprFestivalPacket> festivalPacketList = new ArrayList<>();
			if (isFront) {
				List<Long> campaignIdList = new ArrayList<>();
				for (EnterprFestivalPacket enterprFestivalPacket : enterprFestivalPacketList) {
					// 判断时间范围
					Long currentTime = DateUtil.getCurrentUnixTime();
					Integer startTime = enterprFestivalPacket.getStartTime();
					startTime = null == startTime || startTime < 1 ? null : startTime;
					Integer endTime = enterprFestivalPacket.getEndTime();
					endTime = null == endTime || endTime < 1 ? null : endTime;
					boolean isInRange = true;
					if (null != startTime && currentTime < startTime) {
						isInRange = false;
					}
					if (null != endTime && currentTime > endTime) {
						isInRange = false;
					}
					if (isInRange) {
						festivalPacketList.add(enterprFestivalPacket);
						Long campaignId = enterprFestivalPacket.getCampaignId();
						campaignIdList.add(campaignId);
					}
				}

				CreditOperateInfoExample cExample = new CreditOperateInfoExample();
				cExample.setPage(0);
				cExample.setPageSize(10000);
				List<Integer> ops = new ArrayList<>();
				ops.add(OptTypeEnum.ADMIN_DISTRIBUTION_USER_ADD.getValue());
				ops.add(OptTypeEnum.SALE_DISTRIBUTION_USER_ADD.getValue());
				cExample.createCriteria().andOptTypeIn(ops);
				if (campaignIdList.size() > 0) {
					cExample.createCriteria().andCampaignIdIn(campaignIdList);
				}
				List<CreditOperateInfo> creditOperateInfoList = creditOperateInfoService.find(cExample);
				if (null != creditOperateInfoList && !creditOperateInfoList.isEmpty()) {
					for (CreditOperateInfo creditOperateInfo : creditOperateInfoList) {
						Long campaignId = creditOperateInfo.getCampaignId();
						if (!campaignIdMap.containsKey(campaignId)) {
							campaignIdMap.put(campaignId, new ArrayList<>());
						}
						campaignIdMap.get(campaignId).add(creditOperateInfo.getOwnerUserId());
					}
				}
			} else {
				festivalPacketList.addAll(enterprFestivalPacketList);
			}

			for (EnterprFestivalPacket enterprFestivalPacket : festivalPacketList) {
				Long campaignId = enterprFestivalPacket.getCampaignId();
				if (isFront) {
					List<Long> ownerUserIdList = campaignIdMap.get(campaignId);
					if (null == ownerUserIdList || !ownerUserIdList.contains(employeeId)) {
						continue;
					}
				}
				FestivalPacketInfoDto dto = new FestivalPacketInfoDto();
				dto.setBannerPath(enterprFestivalPacket.getBannerPath());
				dto.setCampaignId(enterprFestivalPacket.getCampaignId());
				dto.setCampaignName(enterprFestivalPacket.getCampaignName());

				Integer creditType = enterprFestivalPacket.getCreditType();
				CreditTypeEnum creditTypeEnum = CreditTypeEnum.getCreditTypeEnum(creditType);
				dto.setCreditType(creditTypeEnum.name());

				Integer campaignStatus = enterprFestivalPacket.getCampaignStatus();
				CampaignStatusEnum campaignStatusEnum = CampaignStatusEnum.getCampaignStatusEnum(campaignStatus);
				dto.setCampaignStatus(campaignStatusEnum.name());

				dto.setCreditListFile(enterprFestivalPacket.getCreditListFile());
				dto.setDefaultGoodsId(enterprFestivalPacket.getDefaultGoodsId());
				dto.setEnterpriseId(enterprFestivalPacket.getEnterprId());

				EnterprUserInfo enterprUserInfo = enterprUserInfoMapper.selectByPrimaryKey(enterprFestivalPacket.getEnterprId());
				if (null != enterprUserInfo) {
					dto.setEnterpriseName(enterprUserInfo.getEnterprName());
				}

				// dto.setGoodsIdList(JsonUtils.getList(enterprFestivalPacket.getGoodsIdList(),
				// new TypeReference<List<Long>>(){}));
				dto.setCampaignThemeIdList(Arrays.asList((Long[]) ConvertUtils.convert(enterprFestivalPacket.getCampaignThemeIdList().split(","), Long.class)));
				dto.setRemark(enterprFestivalPacket.getRemark());
				dto.setStartTime(DateUtil.unixTime2Date(enterprFestivalPacket.getStartTime()));
				dto.setEndTime(DateUtil.unixTime2Date(enterprFestivalPacket.getEndTime()));
				dto.setCreated(DateUtil.unixTime2Date(enterprFestivalPacket.getCreated()));
				dto.setUpdated(DateUtil.unixTime2Date(enterprFestivalPacket.getUpdated()));
				dto.setCampaignThemeId(Long.valueOf(enterprFestivalPacket.getCampaignThemeId()));

				dto.setType(enterprFestivalPacket.getType());
				dto.setCampaignCode(enterprFestivalPacket.getCampaignCode());
				dto.setIsEmail(enterprFestivalPacket.getIsEmail());
				dto.setRemindEmail(enterprFestivalPacket.getRemindEmail());
				dto.setIsSms(enterprFestivalPacket.getIsSms());
				dto.setRemindSms(enterprFestivalPacket.getRemindSms());
				dto.setThirdPay(enterprFestivalPacket.getThirdPay());
				// 查询总积分
				Double creditTotal = creditOperateInfoMapper.selectTotalByCampaignId(enterprFestivalPacket.getCampaignId());
				dto.setCreditTotal(creditTotal);
				// 查询剩余积分
				Double creditSurplus = employeeCreditInfoMapper.selectSumByCampaignId(enterprFestivalPacket.getCampaignId());
				// 剩余积分
				dto.setSurplus(creditSurplus);
				list.add(dto);
			}
			count = isFront ? list.size() : enterprFestivalDao.count(example);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", list);
		jsonObject.put("count", count);
		return Response.succ(jsonObject);
	}

	@Override
	public Integer update(EnterprFestivalPacket enterprFestival, EnterprFestivalPacketExample example) {
		return enterprFestivalDao.update(enterprFestival, example);
	}

	@Override
	public List<EnterprFestivalPacket> find(EnterprFestivalPacketExample example) {
		return enterprFestivalDao.find(example);
	}

	@Override
	public Integer count(EnterprFestivalPacketExample example) {
		return enterprFestivalDao.count(example);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject batchImport(ImportCampainReqDto req) {
		Long campaignId = req.getCampaignId();
		if (null == campaignId || campaignId < 1) {
			return Response.fail("节日礼金id不能为空");
		}

		EnterprFestivalPacket enterprFestivalPacket = enterprFestivalDao.selectByPrimaryKey(campaignId);
		if (null == enterprFestivalPacket) {
			return Response.fail("节日礼金不存在");
		}
		Integer creditTypeVal = enterprFestivalPacket.getCreditType();

		Long enterpriseId = enterprFestivalPacket.getEnterprId();
		String creditListFile = req.getCreditListFile();
		EnterprCreditInfoDto enterprCreditInfoDto = enterprCreditInfoService.findByEnterprIdOne(enterpriseId);
		/** 计算企业积分够不够，并减去 */
		BigDecimal totalCredit = new BigDecimal(0.0);
		try {
			UrlResource resource = new UrlResource(creditListFile);
			List<Object> list = EasyExcelUtil.readExcelWithModel(resource.getInputStream(), PointsAllocateExcelDto.class, ExcelTypeEnum.XLSX);
			if (!CreditTypeEnum.PUTONG.getValue().equals(creditTypeVal)) {
				for (Object obj : list) {
					PointsAllocateExcelDto pointsAllocateExcelDto = (PointsAllocateExcelDto) obj;
					String points = pointsAllocateExcelDto.getPoints();
					totalCredit = totalCredit.add(new BigDecimal(points));
				}
			}

			// 扣除企业
			Long creditId = 0L;
			boolean flag = false;
			if (enterprCreditInfoDto.getValidCredit().subtract(totalCredit).doubleValue() >= 0.0) {
				EnterprCreditInfo uenterprinfo = new EnterprCreditInfo();
				uenterprinfo.setValidCredit(enterprCreditInfoDto.getValidCredit().subtract(totalCredit));
				EnterprCreditInfoExample enterprCreditInfoExample = new EnterprCreditInfoExample();
				enterprCreditInfoExample.createCriteria().andEnterprIdEqualTo(enterpriseId);
				// 扣除企业有效积分
				Integer enterPrresult = enterprCreditInfoService.update(uenterprinfo, enterprCreditInfoExample);
				if (enterPrresult > 0) {
					CreditOperateInfo creditOperateInfo = new CreditOperateInfo();
					creditOperateInfo.setCreditType(creditTypeVal);
					creditOperateInfo.setCampaignId(campaignId);
					creditOperateInfo.setCredit(totalCredit);
					creditOperateInfo.setOptType(OptTypeEnum.DISTRIBUTION_TO_EMPLOYEE.getValue());
					creditOperateInfo.setOptUserId(enterprFestivalPacket.getEnterprId());
					creditOperateInfo.setFilePath(creditListFile);
					creditOperateInfo.setOwnerUserId(enterpriseId);
					creditOperateInfo.setRemark("分配节日礼金、企业积分扣减");
					creditId = creditOperateInfoService.insert(creditOperateInfo);
				}
				flag = true;
			} else {
				throw new RuntimeException("企业积分不够");
			}

			// 企业扣除成功
			if (flag && creditId > 0) {
				// 节日礼金活动修改总积分
				enterprFestivalPacket.setCredit(totalCredit);
				EnterprFestivalPacketExample example = new EnterprFestivalPacketExample();
				example.createCriteria().andCampaignIdEqualTo(enterprFestivalPacket.getCampaignId());
				Integer update = enterprFestivalDao.update(enterprFestivalPacket, example);
				if (update < 1) {
					throw new RuntimeException("导入失败");
				}
				for (Object obj : list) {
					String no = ((PointsAllocateExcelDto) obj).getEmployeeNo();
					String points = "0";
					if (!CreditTypeEnum.PUTONG.getValue().equals(creditTypeVal)) {
						points = ((PointsAllocateExcelDto) obj).getPoints();
					}
					EmployeeUserInfo employeeUserInfo = employeeUserInfoService.findByNoAndEnterprId(no, enterpriseId);
					if (null != employeeUserInfo) {
						Long employeeId = employeeUserInfo.getEmployeeId();
						// 给员工加上积分
						employeeCreditInfoService.alloCredit(employeeId, campaignId, creditTypeVal, new BigDecimal(points));
						// 写入员工操作记录
						CreditOperateInfo creditOperateInfo = new CreditOperateInfo();

						creditOperateInfo.setCampaignId(campaignId);
						creditOperateInfo.setCredit(new BigDecimal(points));
						creditOperateInfo.setCreditType(creditTypeVal);
						creditOperateInfo.setOptType(OptTypeEnum.ADMIN_DISTRIBUTION_USER_ADD.getValue());
						creditOperateInfo.setParentOptId(creditId);
						creditOperateInfo.setOptUserId(enterprFestivalPacket.getEnterprId());
						creditOperateInfo.setOwnerUserId(employeeId);
						creditOperateInfo.setRemark("分配节日礼金，员工积分增加");
						creditOperateInfoService.insert(creditOperateInfo);
					} else {
						throw new RuntimeException("查不到员工，员工no：" + no);
					}
				}
			}

		} catch (MalformedURLException e) {
			throw new RuntimeException("积分分配失败");
		} catch (IOException e) {
			throw new RuntimeException("积分分配失败");
		}
		return Response.succ(true);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject saleImport(ImportCampainReqDto req) {
		Long campaignId = req.getCampaignId();
		if (null == campaignId || campaignId < 1) {
			return Response.fail("活动id不能为空");
		}

		EnterprFestivalPacket enterprFestivalPacket = enterprFestivalDao.selectByPrimaryKey(campaignId);
		Integer creditTypeVal = enterprFestivalPacket.getCreditType();
		if (!creditTypeVal.equals(CreditTypeEnum.ADVANCESALE.getValue())) {
			return Response.fail("预售活动类型不匹配");
		}

		Long enterpriseId = enterprFestivalPacket.getEnterprId();
		String creditListFile = req.getCreditListFile();
		BigDecimal totalCredit = new BigDecimal(0.0);

		try {
			UrlResource resource = new UrlResource(creditListFile);
			List<Object> list = EasyExcelUtil.readExcelWithModel(resource.getInputStream(), PointsAllocateExcelDto.class, ExcelTypeEnum.XLSX);
			for (Object obj : list) {
				PointsAllocateExcelDto pointsAllocateExcelDto = (PointsAllocateExcelDto) obj;
				String points = pointsAllocateExcelDto.getPoints();
				totalCredit = totalCredit.add(new BigDecimal(points));
			}

			CreditOperateInfo creditOperateInfo = new CreditOperateInfo();
			creditOperateInfo.setCreditType(creditTypeVal);
			creditOperateInfo.setCampaignId(campaignId);
			creditOperateInfo.setCredit(totalCredit);
			creditOperateInfo.setOptType(OptTypeEnum.SALE_DISTRIBUTION_USER_ADD.getValue());
			creditOperateInfo.setOptUserId(SessionContextHolder.getSessionFuliInfo().getAdminId());
			creditOperateInfo.setOptUserName(SessionContextHolder.getSessionFuliInfo().getLoginName());
			creditOperateInfo.setFilePath(creditListFile);
			creditOperateInfo.setOwnerUserId(enterpriseId);
			creditOperateInfo.setRemark("分配预售活动额度");
			Long creditId = creditOperateInfoService.insert(creditOperateInfo);

			if (creditId > 0) {
				EnterprFestivalPacketExample example = new EnterprFestivalPacketExample();
				example.createCriteria().andCampaignIdEqualTo(enterprFestivalPacket.getCampaignId());
				Integer update = enterprFestivalDao.update(enterprFestivalPacket, example);
				if (update < 1) {
					throw new RuntimeException("导入失败");
				}

				for (Object obj : list) {
					String no = ((PointsAllocateExcelDto) obj).getEmployeeNo();
					String points = ((PointsAllocateExcelDto) obj).getPoints();
					EmployeeUserInfo employeeUserInfo = employeeUserInfoService.findByNoAndEnterprId(no, enterpriseId);
					if (null != employeeUserInfo) {
						Long employeeId = employeeUserInfo.getEmployeeId();
						// 给员工加上额度
						employeeCreditInfoService.alloCredit(employeeId, campaignId, creditTypeVal, new BigDecimal(points));
						// 写入员工操作记录
						CreditOperateInfo creditOperate = new CreditOperateInfo();
						creditOperate.setCampaignId(campaignId);
						creditOperate.setCreditType(creditTypeVal);
						creditOperate.setCredit(new BigDecimal(points));
						creditOperate.setOptType(OptTypeEnum.SALE_DISTRIBUTION_USER_ADD.getValue());
						creditOperate.setParentOptId(creditId);
						creditOperate.setOptUserId(SessionContextHolder.getSessionFuliInfo().getAdminId());
						creditOperate.setOwnerUserId(employeeId);
						creditOperate.setRemark("预售活动分配额度");
						creditOperateInfoService.insert(creditOperate);
					} else {
						throw new RuntimeException("查不到员工，员工no：" + no);
					}
				}
			}

		} catch (MalformedURLException e) {
			logger.error("预售活动分配异常{}", e.getMessage());
			throw new RuntimeException("预售额度分配失败");
		} catch (IOException e) {
			logger.error("预售活动分配异常{}", e.getMessage());
			throw new RuntimeException("预售额度分配失败");
		}
		return Response.succ(true);
	}

	@Override
	public List<EnterprFestivalPacket> queryByParam(EnterprFestivalPacketExample example) {
		return enterprFestivalPacketMapper.selectByExample(example);
	}

	@Override
	public EnterprFestivalPacket getById(Long campaignId) {
		EnterprFestivalPacket enterprFestivalPacket = enterprFestivalDao.selectByPrimaryKey(campaignId);
		if (campaignId == 0L) {
			return EnterprFestivalPacket.ZERO_ENTERPR_FESTIVAL_PACKET;
		}
		if (null == enterprFestivalPacket) {
			throw new BusinessException("节日礼金活动信息获取失败");
		}
		// 判断时间范围
		Long currentTime = DateUtil.getCurrentUnixTime();
		Integer startTime = enterprFestivalPacket.getStartTime();
		startTime = null == startTime || startTime < 1 ? null : startTime;
		Integer endTime = enterprFestivalPacket.getEndTime();
		endTime = null == endTime || endTime < 1 ? null : endTime;

		if (enterprFestivalPacket.getCreditType().equals(CreditTypeEnum.ADVANCESALE.getValue())) {
			if (null == endTime || currentTime < endTime) {
				throw new BusinessException("预售活动未结束");
			}
		} else {
			boolean isInRange = true;
			if (null != startTime && currentTime < startTime) {
				isInRange = false;
			}
			if (null != endTime && currentTime > endTime) {
				isInRange = false;
			}
			if (!isInRange) {
				throw new BusinessException("节日礼金活动不在有效期内");
			}
		}
		// Long enterprId =
		// SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
		// if (!enterprId.equals(enterprFestivalPacket.getEnterprId())) {
		// throw new BusinessException("非法请求");
		// }
		if (!YNEnum.N.val().equals(enterprFestivalPacket.getCampaignStatus())) {
			throw new BusinessException("节日礼金活动已失效");
		}
		return enterprFestivalPacket;
	}

	@Override
	public List<FestivalPacketInfoBean> getPCFestivalProducts(Long campaignId, PageBean pageBean) {
		SessionShopInfo employeeInfo = SessionContextHolder.getSessionEmployeeInfo();
		Long enterprId = employeeInfo.getEnterprId();
		int currentTimeSeconds = (int) (System.currentTimeMillis() / 1000);
		EnterprFestivalPacketExample example = new EnterprFestivalPacketExample();
		Criteria criteria = example.createCriteria().andEnterprIdEqualTo(enterprId).andStartTimeLessThanOrEqualTo(currentTimeSeconds)
				.andEndTimeGreaterThanOrEqualTo(currentTimeSeconds).andCampaignStatusEqualTo(YNEnum.N.val());
		if (campaignId != null) {
			criteria.andCampaignIdEqualTo(campaignId);
		}
		List<EnterprFestivalPacket> enterprFestivalPackets = enterprFestivalPacketMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(enterprFestivalPackets)) {
			return Collections.emptyList();
		}
		if (enterprFestivalPackets.size() == 1) {
			FestivalPacketInfoBean festivalPacketInfoBean = this.getFestivalPacketInfoBean(enterprId, employeeInfo.getEmployeeId(),
					enterprFestivalPackets.get(0), null, pageBean);
			return festivalPacketInfoBean == null ? Collections.emptyList() : Arrays.asList(festivalPacketInfoBean);
		} else {
			return enterprFestivalPackets.parallelStream()
					.map(enterprFestivalPacket -> this.getFestivalPacketInfoBean(enterprId, employeeInfo.getEmployeeId(), enterprFestivalPacket, null, pageBean))
					.filter(Objects::nonNull).sorted(Comparator.comparing(FestivalPacketInfoBean::getCampaignId).reversed()).collect(Collectors.toList());
		}
	}

	
	@Override
	public PageResultBean<ProductEntity> getPCFestivalTopicProducts(Long campaignId, Integer topicId, PageBean pageBean) {
		SessionShopInfo employeeInfo = SessionContextHolder.getSessionEmployeeInfo();
		Long enterprId = employeeInfo.getEnterprId();
		int currentTimeSeconds = (int) (System.currentTimeMillis() / 1000);
		EnterprFestivalPacketExample example = new EnterprFestivalPacketExample();
		example.createCriteria().andEnterprIdEqualTo(enterprId).andStartTimeLessThanOrEqualTo(currentTimeSeconds)
				.andEndTimeGreaterThanOrEqualTo(currentTimeSeconds).andCampaignStatusEqualTo(YNEnum.N.val()).andCampaignIdEqualTo(campaignId);
		List<EnterprFestivalPacket> enterprFestivalPackets = enterprFestivalPacketMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(enterprFestivalPackets)) {
			return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), 0, Collections.emptyList());
		}
		FestivalPacketInfoBean festivalPacketInfoBean = this.getFestivalPacketInfoBean(enterprId, employeeInfo.getEmployeeId(),
				enterprFestivalPackets.get(0), Arrays.asList(topicId), pageBean);
		if(festivalPacketInfoBean == null||CollectionUtils.isEmpty(festivalPacketInfoBean.getTopicList())) {
			return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), 0, Collections.emptyList());
		}
		return festivalPacketInfoBean.getTopicList().get(0);
	}
	
	private FestivalPacketInfoBean getFestivalPacketInfoBean(Long enterprId, Long employeeId, EnterprFestivalPacket enterprFestivalPacket, List<Integer> topicIds, PageBean pageBean) {
		Boolean isWhitelist = enterprFestivalPacket.getIsWhitelist();
		if (isWhitelist == null) {
			isWhitelist = false;
		}
		CreditOperateInfoExample condition = new CreditOperateInfoExample();
		condition.createCriteria().andOwnerUserIdEqualTo(employeeId).andCampaignIdEqualTo(enterprFestivalPacket.getCampaignId());
		condition.setPage(0);
		condition.setPageSize(1);
		List<CreditOperateInfo> creditOperateInfos = creditOperateInfoService.find(condition);
		if (CollectionUtils.isEmpty(creditOperateInfos)) {
			return null;
		}
		String campaignThemeIdList = enterprFestivalPacket.getCampaignThemeIdList();
		if (StringUtils.isEmpty(campaignThemeIdList)) {
			return null;
		}
		List<Integer> topicIdList = new ArrayList<>();
		for (String item : campaignThemeIdList.split(",")) {
			topicIdList.add(Integer.valueOf(item.trim()));
		}
		if (topicIds != null) {
			topicIdList.retainAll(topicIds);
		}
		List<TopicEntity> topics = topicService.getTopics(topicIdList);
		if (CollectionUtils.isEmpty(topics)) {
			return null;
		}
		List<TopicInfoPageBean> topicList = new ArrayList<>();
		for (TopicEntity topicEntity : topics) {
			ProductSearchBean productSearchBean = new ProductSearchBean();
			productSearchBean.setTopicId(topicEntity.getId().intValue());
			PageResultBean<ProductEntity> productResultBean = productService.getProductsByPage(enterprId, productSearchBean, isWhitelist, pageBean);
			if (CollectionUtils.isEmpty(productResultBean.getList())) {
				continue;
			}
			TopicInfoPageBean topicInfoPageBean = new TopicInfoPageBean();
			topicInfoPageBean.setTopicId(topicEntity.getId().intValue());
			topicInfoPageBean.setTopicName(topicEntity.getName());
			topicInfoPageBean.setPage(productResultBean.getPage());
			topicInfoPageBean.setPageSize(productResultBean.getPageSize());
			topicInfoPageBean.setList(productResultBean.getList());
			topicInfoPageBean.setCount(productResultBean.getCount());
			topicList.add(topicInfoPageBean);
		}
		FestivalPacketInfoBean festivalPacketInfoBean = new FestivalPacketInfoBean();
		BeanUtils.copyProperties(enterprFestivalPacket, festivalPacketInfoBean);
		if (topicList.size() > 0) {
			festivalPacketInfoBean.setTopicList(topicList);
			return festivalPacketInfoBean;
		} else {
			festivalPacketInfoBean.setTopicList(Collections.emptyList());
			return festivalPacketInfoBean;
		}
	}

	@Override
	public boolean isExist(Long campaignId, Long spuCode) {
		EnterprFestivalPacket enterprFestivalPacket = enterprFestivalDao.selectByPrimaryKey(campaignId);
		String campaignThemeIdList;
		if (enterprFestivalPacket == null || StringUtils.isEmpty(campaignThemeIdList = enterprFestivalPacket.getCampaignThemeIdList())) {
			return false;
		}
		String[] themeIds = campaignThemeIdList.split(",");
		List<Integer> topicIds = Stream.of(themeIds).filter(item -> !"".equals(item)).map(Integer::valueOf).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(topicIds)) {
			return false;
		}
		ProductTopicCondition example = new ProductTopicCondition();
		example.createCriteria().andTopicIdIn(topicIds).andSpuCodeEqualTo(spuCode);
		return productTopicMapper.countByExample(example) > 0;
	}

}
