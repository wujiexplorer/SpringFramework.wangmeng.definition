package com.lx.benefits.service.enterprcustomprice.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.admin.customized.PriceDetailResDto;
import com.lx.benefits.bean.dto.admin.customized.PriceInfoDto;
import com.lx.benefits.bean.dto.admin.customized.PriceModuleInfoDto;
import com.lx.benefits.bean.dto.admin.customized.PriceQueryReqDto;
import com.lx.benefits.bean.entity.enterprcustomprice.EnterprCustomPrice;
import com.lx.benefits.bean.entity.enterprcustomprice.EnterprCustomPriceExample;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfoCondition;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.EasyExcelUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.dao.enterprcustomprice.EnterprCustomPriceDao;
import com.lx.benefits.mapper.enterprcustomprice.EnterprCustomPriceMapper;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.mapper.product.SkuMapper;
import com.lx.benefits.service.enterprcustomprice.EnterprCustomPriceService;

/**
 * @author unknow on 2018-12-25 23:38.
 */
@Service
public class EnterprCustomPriceServiceImpl implements EnterprCustomPriceService {

	private final static Logger logger = LoggerFactory.getLogger(EnterprCustomPriceServiceImpl.class);

	@Autowired
	private EnterprCustomPriceDao enterprCustomPriceDao;
	@Autowired
	private EnterprUserInfoMapper enterprUserInfoMapper;
	@Autowired
	private SkuMapper skuMapper;
	@Autowired
	private EnterprCustomPriceMapper enterprCustomPriceMapper;

	@Override
	public Long insertSelective(PriceModuleInfoDto priceModuleInfoDto) {
		EnterprCustomPrice enterprCustomPrice = new EnterprCustomPrice();
		BeanUtils.copyProperties(priceModuleInfoDto, enterprCustomPrice);
		if (null != priceModuleInfoDto.getGoodsPrice()) {
			enterprCustomPrice.setGoodsPrice(new BigDecimal(priceModuleInfoDto.getGoodsPrice()));
		}
		if (null == enterprCustomPrice.getCustomId() || enterprCustomPrice.getCustomId() < 1) {
			Long rowCount = enterprCustomPriceDao.insertSelective(enterprCustomPrice);
			if (null != rowCount && rowCount > 0) {
				return enterprCustomPrice.getCustomId();
			}
		}
		Integer rowCount = enterprCustomPriceDao.updateByPrimaryKeySelective(enterprCustomPrice);
		return Long.valueOf(rowCount);
	}

	@Override
	public JSONObject selectByExample(PriceQueryReqDto priceQueryReqDto) {

		EnterprCustomPriceExample example = new EnterprCustomPriceExample();
		BeanUtils.copyProperties(priceQueryReqDto, example);
		if (null != priceQueryReqDto.getEnterpriseId() && priceQueryReqDto.getEnterpriseId() > 0) {
			example.createCriteria().andEnterprIdEqualTo(priceQueryReqDto.getEnterpriseId());
		}
		if (null != priceQueryReqDto.getGoodsId() && priceQueryReqDto.getGoodsId() > 0) {
			example.createCriteria().andGoodsIdEqualTo(priceQueryReqDto.getGoodsId());
		}
		example.setOrderByClause("enterprId DESC");
		int count = enterprCustomPriceDao.countByExample(example);
		List<PriceDetailResDto> priceDetailResDtoList = new ArrayList<>();
		if (count > 0) {
			List<EnterprCustomPrice> enterprCustomPriceList = enterprCustomPriceMapper.selectByExample(example);
			if (null != enterprCustomPriceList && !enterprCustomPriceList.isEmpty()) {

				List<Long> enterprIdList = new ArrayList<>();
				for (EnterprCustomPrice enterprCustomPrice : enterprCustomPriceList) {
					Long enterprId = enterprCustomPrice.getEnterprId();
					if (!enterprIdList.contains(enterprId)) {
						enterprIdList.add(enterprId);
					}
				}
				EnterprUserInfoCondition euExample = new EnterprUserInfoCondition();
				euExample.createCriteria().andEnterprIdIn(enterprIdList);
				Integer page = priceQueryReqDto.getPage();
				Integer pageSize = priceQueryReqDto.getPageSize();
				euExample.setOffset((page - 1) * pageSize);
				euExample.setLimit(pageSize);
				List<EnterprUserInfo> enterprUserInfoList = enterprUserInfoMapper.selectByExample(euExample);
				Map<Long, EnterprUserInfo> enterprUserInfoMap = new HashMap<>();
				if (null != enterprUserInfoList && !enterprUserInfoList.isEmpty()) {
					for (EnterprUserInfo enterprUserInfo : enterprUserInfoList) {
						enterprUserInfoMap.put(enterprUserInfo.getEnterprId(), enterprUserInfo);
					}
				}

				for (EnterprCustomPrice enterprCustomPrice : enterprCustomPriceList) {
					Long enterprId = enterprCustomPrice.getEnterprId();
					PriceDetailResDto priceDetailResDto = new PriceDetailResDto();
					BeanUtils.copyProperties(enterprCustomPrice, priceDetailResDto);
					priceDetailResDto.setGoodsPrice(enterprCustomPrice.getGoodsPrice().doubleValue());
					priceDetailResDto.setPrice(enterprCustomPrice.getPrice().doubleValue());
					priceDetailResDto.setSupplierId(enterprCustomPrice.getSupplierId());
					priceDetailResDto.setCreatedUser(enterprCustomPrice.getCreatedUser());
					priceDetailResDto.setCreated(DateUtil.unixTime2Date(enterprCustomPrice.getCreated()));
					priceDetailResDto.setUpdated(DateUtil.unixTime2Date(enterprCustomPrice.getUpdated()));
					if (null != enterprUserInfoMap.get(enterprId)) {
						priceDetailResDto.setEnterprName(enterprUserInfoMap.get(enterprId).getEnterprName());
					}
					priceDetailResDto.setGoodsId(enterprCustomPrice.getGoodsId());
					priceDetailResDto.setGoodsNo(enterprCustomPrice.getSpuId().toString());
					priceDetailResDto.setGoodsTitle(enterprCustomPrice.getGoodsName());
					priceDetailResDto.setSupplierName(enterprCustomPrice.getSupplierName());
					priceDetailResDtoList.add(priceDetailResDto);
				}
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", priceDetailResDtoList);
		jsonObject.put("count", count);
		return Response.succ(jsonObject);
	}

	@Override
	public PriceDetailResDto selectByPrimaryKey(Long customId) {
		EnterprCustomPrice enterprCustomPrice = enterprCustomPriceDao.selectByPrimaryKey(customId);
		if (null != enterprCustomPrice) {
			PriceDetailResDto priceDetailResDto = new PriceDetailResDto();
			BeanUtils.copyProperties(enterprCustomPrice, priceDetailResDto);
			priceDetailResDto.setGoodsPrice(enterprCustomPrice.getGoodsPrice().doubleValue());
			priceDetailResDto.setCreated(DateUtil.unixTime2Date(enterprCustomPrice.getCreated()));
			priceDetailResDto.setUpdated(DateUtil.unixTime2Date(enterprCustomPrice.getUpdated()));
			EnterprUserInfo enterprUserInfo = enterprUserInfoMapper.selectByPrimaryKey(enterprCustomPrice.getEnterprId());
			if (null != enterprUserInfo) {
				priceDetailResDto.setEnterprName(enterprUserInfo.getEnterprName());
			}
			/*
			 * ItemInfo itemInfo =
			 * itemInfoDao.selectByPrimaryKey(enterprCustomPrice.getGoodsId());
			 * if (null != itemInfo) {
			 * priceDetailResDto.setGoodsTitle(itemInfo.getMain_title());
			 * priceDetailResDto.setGoodsNo(itemInfo.getSpu()); }
			 */
			return priceDetailResDto;
		}
		return null;
	}

	@Override
	public PriceDetailResDto selectByEnterprId(Long enterprId) {
		List<EnterprCustomPrice> list = enterprCustomPriceDao.selectByEnterprId(enterprId, null);
		if (!CollectionUtils.isEmpty(list)) {
			EnterprCustomPrice enterprCustomPrice = list.get(0);
			PriceDetailResDto priceDetailResDto = new PriceDetailResDto();
			BeanUtils.copyProperties(enterprCustomPrice, priceDetailResDto);
			priceDetailResDto.setGoodsPrice(enterprCustomPrice.getGoodsPrice().doubleValue());
			priceDetailResDto.setCreated(DateUtil.unixTime2Date(enterprCustomPrice.getCreated()));
			priceDetailResDto.setUpdated(DateUtil.unixTime2Date(enterprCustomPrice.getUpdated()));
			EnterprUserInfo enterprUserInfo = enterprUserInfoMapper.selectByPrimaryKey(enterprCustomPrice.getEnterprId());
			if (null != enterprUserInfo) {
				priceDetailResDto.setEnterprName(enterprUserInfo.getEnterprName());
			}
			return priceDetailResDto;
		}
		return null;
	}

	@Override
	public Boolean delEnterprCustomPrice(Long customId) {
		Integer rowCount = enterprCustomPriceDao.deleteByPrimaryKey(customId);
		// 标记一下
		return (null != rowCount && rowCount > 0);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject batchImport(PriceModuleInfoDto reqDto) {

		String filePath = reqDto.getFilePath();
		// 1. 解析企业定制商品信息
		List<Object> productList;
		try {
			UrlResource resource = new UrlResource(filePath);
			String suffix = filePath.substring(filePath.lastIndexOf(".") + 1);
			ExcelTypeEnum excelTypeEnum = "xlsx".equalsIgnoreCase(suffix) ? ExcelTypeEnum.XLSX : ExcelTypeEnum.XLS;
			productList = EasyExcelUtil.readExcelWithModel(resource.getInputStream(), PriceInfoDto.class, excelTypeEnum);
		} catch (Exception e) {
			logger.error("企业定制商品价格文件解析出错：", e);
			return Response.fail("企业定制商品价格文件解析出错!");
		}
		if (CollectionUtils.isEmpty(productList)) {
			return Response.fail("excel表格数据为空");
		}

		Long enterId = reqDto.getEnterprId();

		String loginName = SessionContextHolder.getSessionFuliInfo().getLoginName();
		// 2. excel表格信息组装&数据验证
		List<EnterprCustomPrice> list = new ArrayList<>();
		List<EnterprCustomPrice> updateList = new ArrayList<>();
		int line = 2;
		try {
			for (Object customPrice : productList) {
				EnterprCustomPrice enterprCustomPrice = new EnterprCustomPrice();
				PriceInfoDto priceInfoDto = (PriceInfoDto) customPrice;
				if (null == priceInfoDto) {
					return Response.fail(String.format("第 %s 行定制信息不能为空!", line));
				}
				Long skuId = priceInfoDto.getGoodsId();
				if (null == skuId || skuId < 1) {
					return Response.fail(String.format("第 %s 行skuId不能为空!", line));
				} else {
					enterprCustomPrice.setGoodsId(skuId);
				}
				if (null == priceInfoDto.getGoodsPrice()) {
					return Response.fail(String.format("第 %s 行定制价格不能为空!", line));
				} else {
					enterprCustomPrice.setGoodsPrice(priceInfoDto.getGoodsPrice());
				}
				SkuEntity skuEntity = skuMapper.selectById(skuId);
				if (null == skuEntity) {
					return Response.fail(String.format("第 %s 行skuId不存在!", line));
				}
				enterprCustomPrice.setSpuId(skuEntity.getSpuCode());
				enterprCustomPrice.setCreatedUser(loginName);
				enterprCustomPrice.setEnterprId(enterId);
				enterprCustomPrice.setIsDeleted(0);
				EnterprCustomPriceExample example = new EnterprCustomPriceExample();
				example.createCriteria().andGoodsIdEqualTo(skuId).andEnterprIdEqualTo(enterId);
				EnterprCustomPrice customPrice1 = enterprCustomPriceDao.selectByParam(example);
				if (null == customPrice1) {
					list.add(enterprCustomPrice);
				} else {
					enterprCustomPrice.setUpdatedUser(loginName);
					enterprCustomPrice.setCustomId(customPrice1.getCustomId());
					updateList.add(enterprCustomPrice);
				}
			}
			boolean flag = true;
			if (null != list && list.size() > 0) {
				int insert = enterprCustomPriceDao.insertBatch(list);
				if (insert < 1) {
					flag = false;
				}
			}
			if (null != updateList && updateList.size() > 0) {
				int update = enterprCustomPriceDao.updateBatch(updateList);
				if (update < 1) {
					flag = false;
				}
			}
			if (flag) {
				return Response.succ("成功！");
			} else {
				throw new RuntimeException("导入失败");
			}
		} catch (Exception e) {
			logger.error("batchImport exception", e);
			throw new RuntimeException("导入企业定制商品价格异常!");
		}
	}

	@Override
	public PageResultBean<SkuEntity> getPCFeatureListByPage(Long enterpriseId, PageBean pageBean) {
		PageResultBean<SkuEntity> pageResultBean = new PageResultBean<>();
		int count = enterprCustomPriceMapper.countPCFeatureList(enterpriseId);
		pageResultBean.setCount(count);
		pageResultBean.setPage(pageBean.getPage());
		pageResultBean.setPageSize(pageBean.getPageSize());
		if (count == 0 || pageBean.getOffset() >= count) {
			pageResultBean.setList(Collections.emptyList());
		} else {
			pageResultBean.setList(enterprCustomPriceMapper.getPCFeatureListByPage(enterpriseId, pageBean));
		}
		return pageResultBean;
	}
}
