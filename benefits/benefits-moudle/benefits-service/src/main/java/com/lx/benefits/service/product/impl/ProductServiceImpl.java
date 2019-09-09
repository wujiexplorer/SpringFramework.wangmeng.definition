package com.lx.benefits.service.product.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.seckill.Seckill;
import com.lx.benefits.bean.entity.voucher.Voucher;
import com.lx.benefits.bean.util.*;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.seckill.SeckillService;
import com.lx.benefits.service.voucher.VoucherService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.bo.product.EsProductBean;
import com.lx.benefits.bean.dto.admin.category.ThreaLevelCategory;
import com.lx.benefits.bean.dto.admin.customized.GoodsModuleInfoDto;
import com.lx.benefits.bean.dto.admin.product.ProductInfoDto;
import com.lx.benefits.bean.dto.admin.product.ProductQueryParam;
import com.lx.benefits.bean.dto.base.ImportReqDto;
import com.lx.benefits.bean.dto.product.ProductSearchBean;
import com.lx.benefits.bean.dto.spec.ProductBean;
import com.lx.benefits.bean.dto.spec.ProductRequestBean;
import com.lx.benefits.bean.dto.spec.ProductRequestBean.SpecBean;
import com.lx.benefits.bean.dto.spec.ProductSpecValueBean;
import com.lx.benefits.bean.dto.spec.SkuBean;
import com.lx.benefits.bean.dto.spec.SkuSpectValueBean;
import com.lx.benefits.bean.dto.spec.SpectValueBean;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfo;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacket;
import com.lx.benefits.bean.entity.product.BrandEntity;
import com.lx.benefits.bean.entity.product.CategoryEntity;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.ProductSpec;
import com.lx.benefits.bean.entity.product.ProductSpecCondition;
import com.lx.benefits.bean.entity.product.ProductSpecInfo;
import com.lx.benefits.bean.entity.product.ProductSpecInfoCondition;
import com.lx.benefits.bean.entity.product.ProductTopicCondition;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.entity.product.SkuSpecValue;
import com.lx.benefits.bean.entity.product.SkuSpecValueCondition;
import com.lx.benefits.bean.entity.supplierInfo.SupplierInfo;
import com.lx.benefits.bean.enums.GoodsStateEnum;
import com.lx.benefits.bean.enums.SexEnum;
import com.lx.benefits.config.properties.ThirdPlaformSeller;
import com.lx.benefits.config.properties.YibaoProperties;
import com.lx.benefits.mapper.basedistrictinfo.DistrictInfoMapper;
import com.lx.benefits.mapper.enterprfestivalpacket.EnterprFestivalPacketMapper;
import com.lx.benefits.mapper.product.BrandMapper;
import com.lx.benefits.mapper.product.CategoryMapper;
import com.lx.benefits.mapper.product.ProductMapper;
import com.lx.benefits.mapper.product.ProductSpecInfoMapper;
import com.lx.benefits.mapper.product.ProductSpecMapper;
import com.lx.benefits.mapper.product.ProductTopicMapper;
import com.lx.benefits.mapper.product.SkuMapper;
import com.lx.benefits.mapper.product.SkuSpecValueMapper;
import com.lx.benefits.mapper.supplierInfo.SupplierInfoMapper;
import com.lx.benefits.service.creditoperateinfo.CreditOperateInfoService;
import com.lx.benefits.service.enterprcustomgoods.EnterprCustomGoodsService;
import com.lx.benefits.service.enterprfestival.EnterprFestivalService;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.ProductSettingService;
import com.lx.benefits.service.supplierInfo.SupplierInfoService;

/**
 * 商品基本 【spu】 服务层实现
 *
 * @author ruoyi
 * @date 2019-02-12
 */
@Service
public class ProductServiceImpl implements ProductService {

	private final static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Resource
	private ProductMapper productMapper;

	@Autowired
	private ProductService productService;

	@Resource
	private SkuMapper skuMapper;

	@Resource
	private ProductTopicMapper productTopicMapper;

	@Resource
	private EnterprFestivalPacketMapper enterprFestivalPacketMapper;

	@Resource
	private CategoryMapper categoryMapper;

	@Resource
	private BrandMapper brandMapper;

	@Resource
	private SupplierInfoMapper supplierInfoMapper;

	@Autowired
	private DistrictInfoMapper districtInfoMapper;

	@Autowired
	private SupplierInfoService supplierInfoService;
	@Autowired
	private ProductSpecMapper productSpecMapper;
	@Autowired
	private SkuSpecValueMapper skuSpecValueMapper;
	@Autowired
	private ProductSpecInfoMapper productSpecInfoMapper;
	@Autowired
	private ProductSettingService productSettingService;
	@Autowired
	private EnterprCustomGoodsService enterprCustomGoodsService;
	@Autowired
	private ThirdPlaformSeller thirdPlaformSeller;
	@Autowired
	private EnterprFestivalService enterprFestivalService;
	@Autowired
	private CreditOperateInfoService creditOperateInfoService;
	@Autowired
	private YibaoProperties yibaoProperties;
	@Autowired
	private VoucherService voucherService;
	@Autowired
	private SeckillService seckillService;
	@Autowired
	private EmployeeCreditInfoService employeeCreditInfoService;

	public ProductServiceImpl() {
		super();
	}

	@Transactional
	@Override
	public void addProduct(ProductRequestBean productRequestBean, Integer supplierId) {
		if (supplierId == null) {
			supplierId = -1;
		}
		if (productRequestBean.getSupplierId().equals(thirdPlaformSeller.getJd().getId())
				|| productRequestBean.getSupplierId().equals(thirdPlaformSeller.getYx().getId())) {
			throw new BusinessException("无法手动添加京东/严选商品");
		}
		Integer categoryId3 = productRequestBean.getCategoryId3();// 根据三类ID获取三级分类
		ThreaLevelCategory threaLevelCategory = categoryMapper.getThreaLevelCategory(categoryId3);
		if (threaLevelCategory == null) {
			throw new BusinessException("商品分类错误!");
		}
		BrandEntity brandEntity = brandMapper.selectBrandById(productRequestBean.getBrandId());
		if (brandEntity == null) {
			throw new BusinessException("品牌不存在!");
		}
		SupplierInfo supplierInfo = supplierInfoService.getSupplierById(productRequestBean.getSupplierId());
		if (supplierInfo == null) {
			throw new BusinessException("供应商不存在!");
		}
		List<SpecBean> specList = productRequestBean.getSpecList();
		int specSize = 1;
		for (SpecBean item : specList) {
			specSize *= item.getSpecValues().size();
		}
		List<SkuEntity> skuList = productRequestBean.getSkuList();
		if (CollectionUtils.isEmpty(specList) || specSize != skuList.size() || specList.size() > 2) {// 数据格式错误
			throw new BusinessException("商品规格信息错误");
		}
		BigDecimal goodsSaleRate = this.getGoodsSaleRate();
		Date createTime = new Date();
		ProductEntity productRecord = new ProductEntity();
		productRecord.setSupplierId(supplierInfo.getId());
		productRecord.setSupplierName(supplierInfo.getName());
		productRecord.setCategoryId(threaLevelCategory.getCategoryId().longValue());
		productRecord.setCategoryName(threaLevelCategory.getCategoryName());
		productRecord.setCategoryId2(threaLevelCategory.getCategoryId2().longValue());
		productRecord.setCategoryName2(threaLevelCategory.getCategoryName2());
		productRecord.setCategoryId3(categoryId3.longValue());
		productRecord.setCategoryName3(threaLevelCategory.getCategoryName3());
		productRecord.setGoodsType(productRequestBean.getGoodsType());
		productRecord.setIsCross(productRequestBean.getIsCross());
		productRecord.setGoodsName(productRequestBean.getGoodsName());
		productRecord.setGoodsNameEn(productRequestBean.getGoodsNameEn());
		productRecord.setBrandId(brandEntity.getId());
		productRecord.setBrandName(brandEntity.getName());
		productRecord.setGoodsImages(JSONArray.toJSONString(productRequestBean.getGoodsImageList()));
		productRecord.setGoodsImage(productRequestBean.getGoodsImageList().get(0));
		productRecord.setPlaceOrigin(productRequestBean.getPlaceOrigin());
		productRecord.setPlaceOriginId(productRequestBean.getPlaceOriginId());
		productRecord.setSeason(productRequestBean.getSeason());
		productRecord.setSex(productRequestBean.getSex());
		productRecord.setCountrySize(productRequestBean.getCountrySize());
		productRecord.setIntroduction(productRequestBean.getIntroduction());
		productRecord.setIntroductionEn(productRequestBean.getIntroductionEn());
		productRecord.setGoodsFreight(productRequestBean.getGoodsFreight());
		productRecord.setCreatedUser(SessionContextHolder.getCurrentLoginName());
		productRecord.setCreatedTime(createTime);
		if (supplierId != -1) {
			productRecord.setGoodsState(GoodsStateEnum.INREVIEW.getValue());
		} else {
			productRecord.setGoodsState(GoodsStateEnum.SOLDOUT.getValue());
		}
		productMapper.insert(productRecord);
		for (SpecBean specBean : specList) {
			ProductSpecCondition productSpecCondition = new ProductSpecCondition();
			productSpecCondition.createCriteria().andSpecNameEqualTo(specBean.getSpecName())
					.andSupplierIdIn(Arrays.asList(0, supplierId));
			List<ProductSpec> productSpecs = productSpecMapper.selectByExample(productSpecCondition);
			ProductSpec productSpecRecord = null;
			if (CollectionUtils.isEmpty(productSpecs)) {
				productSpecRecord = new ProductSpec();
				productSpecRecord.setSpecName(specBean.getSpecName());
				productSpecRecord.setSupplierId(supplierId);
				productSpecMapper.insertSelective(productSpecRecord);
			} else {
				productSpecRecord = productSpecs.get(0);
			}
			specBean.setSpecId(productSpecRecord.getSpecId());
			ProductSpecInfo productSpecInfo = new ProductSpecInfo();
			productSpecInfo.setSpecName(productSpecRecord.getSpecName());
			productSpecInfo.setSpecId(productSpecRecord.getSpecId());
			productSpecInfo.setSpuCode(productRecord.getSpuCode());
			productSpecInfoMapper.insertSelective(productSpecInfo);
		}
		int skuIndex = 0;
		SpecBean specBean = specList.get(0);
		List<SkuSpecValue> skuSpecValues = specBean.getSpecValues();
		for (SkuSpecValue skuSpecValue1 : skuSpecValues) {
			if (specList.size() == 1) {// 仅仅1个规格
				SkuEntity sku = skuList.get(skuIndex++);
				if (sku == null) {
					continue;
				}
				SkuSpecValue skuSpecValueRecorder = new SkuSpecValue();
				skuSpecValueRecorder.setSpecId(specBean.getSpecId());
				skuSpecValueRecorder.setSpecImage(skuSpecValue1.getSpecImage());
				skuSpecValueRecorder.setSpecValue(skuSpecValue1.getSpecValue());
				String skuImage = productRecord.getGoodsImage();
				if (!StringUtils.isEmpty(skuSpecValue1.getSpecImage())) {
					skuImage = skuSpecValue1.getSpecImage();
				}
				sku.setGoodsImage(skuImage);
				this.addSku(sku, productRecord.getSpuCode(), goodsSaleRate, Arrays.asList(skuSpecValueRecorder));
			} else {// 2个规格
				Assert.isTrue(specList.size() == 2, "最多支持添加两个规格类型!");
				SpecBean specBean2 = specList.get(1);
				List<SkuSpecValue> skuSpecValues2 = specBean2.getSpecValues();
				for (SkuSpecValue skuSpecValue2 : skuSpecValues2) {
					SkuEntity sku = skuList.get(skuIndex++);
					if (sku == null) {
						continue;
					}
					// 规格1
					SkuSpecValue skuSpecValueRecorder1 = new SkuSpecValue();
					skuSpecValueRecorder1.setSpecId(specBean.getSpecId());
					skuSpecValueRecorder1.setSpecImage(skuSpecValue1.getSpecImage());
					skuSpecValueRecorder1.setSpecValue(skuSpecValue1.getSpecValue());
					// 规格2
					SkuSpecValue skuSpecValueRecorder2 = new SkuSpecValue();
					skuSpecValueRecorder2.setSpecId(specBean2.getSpecId());
					skuSpecValueRecorder2.setSpecImage(skuSpecValue2.getSpecImage());
					skuSpecValueRecorder2.setSpecValue(skuSpecValue2.getSpecValue());
					String skuImage = productRecord.getGoodsImage();
					if (!StringUtils.isEmpty(skuSpecValue1.getSpecImage())) {
						skuImage = skuSpecValue1.getSpecImage();
					}
					sku.setGoodsImage(skuImage);
					this.addSku(sku, productRecord.getSpuCode(), goodsSaleRate,
							Arrays.asList(skuSpecValueRecorder1, skuSpecValueRecorder2));
				}
			}
		}
	}

	private void addSku(SkuEntity sku, Long spuCode, BigDecimal goodsSaleRate, List<SkuSpecValue> skuSpecValues) {
		if (sku == null) {
			return;
		}
		SkuEntity skuRecorder = new SkuEntity();
		skuRecorder.setSpuCode(spuCode);
		skuRecorder.setGoodsPrice(sku.getGoodsPrice());
		skuRecorder.setGoodsMarkprice(sku.getGoodsMarkprice());
		skuRecorder.setGoodsCostprice(sku.getGoodsCostprice());
		skuRecorder.setGoodsDiscount(sku.getGoodsPrice().divide(sku.getGoodsMarkprice(), 2, RoundingMode.HALF_UP));
		skuRecorder.setGoodsRate(sku.getGoodsPrice().subtract(sku.getGoodsCostprice()).divide(sku.getGoodsPrice(), 4,
				RoundingMode.HALF_UP));
		Integer goodsStorge = sku.getGoodsStorge();
		if (goodsStorge == null || goodsStorge.compareTo(Integer.valueOf(0)) < 0) {
			goodsStorge = 0;
		}
		skuRecorder.setGoodsStorge(goodsStorge);
		skuRecorder.setItemNumber(sku.getItemNumber());
		skuRecorder.setGoodsSpec(sku.getGoodsSpec());
		skuRecorder.setGoodsImage(sku.getGoodsImage());
		skuRecorder.setCreatedUser(SessionContextHolder.getCurrentLoginName());
		skuRecorder.setCreatedTime(new Date());
		if (goodsSaleRate.compareTo(skuRecorder.getGoodsRate()) > 0) {
			skuRecorder.setStatus(0);
		} else {
			skuRecorder.setStatus(1);
		}
		skuMapper.insert(skuRecorder);
		// 处理SKU规格信息
		if (CollectionUtils.isEmpty(skuSpecValues)) {
			return;
		}
		for (SkuSpecValue item : skuSpecValues) {
			item.setSkuId(skuRecorder.getId());
			skuSpecValueMapper.insertSelective(item);
		}
	}

	/**
	 * 查询商品基本 【spu】信息
	 *
	 * @param id 商品基本 【spu】ID
	 * @return 商品基本 【spu】信息
	 */
	@Override
	public ProductEntity selectById(Long id) {
		return productMapper.selectById(id);
	}

	@Override
	public ProductEntity getProductBySkuCode(String skuCode, Integer supplierId) {
		return productMapper.getProductBySkuCode(skuCode, supplierId);
	}

	/**
	 * 新增商品基本 【spu】
	 *
	 * @param product 商品基本 【spu】信息
	 * @return 结果
	 */
	@Override
	public int insert(ProductEntity product) {
		product.setCreatedTime(new Date());
		product.setUpdatedTime(new Date());
		return productMapper.insert(product);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertBatch(ProductEntity product, List<SkuEntity> list) {
		product.setCreatedTime(new Date());
		product.setUpdatedTime(new Date());
		int productStatus = productMapper.insert(product);
		if (productStatus > 0) {
			int skuStatus = 0;
			for (SkuEntity skuEntity : list) {
				if (StringUtils.isBlank(skuEntity.getSkuCode())) {
					skuEntity.setSkuCode(product.getSpuCode() + "-" + getUUID());
				}
				skuEntity.setCreatedTime(new Date());
				skuEntity.setUpdatedTime(new Date());
				skuEntity.setSpuCode(product.getSpuCode());
				if (StringUtils.isBlank(skuEntity.getGoodsImage())) {
					skuEntity.setGoodsImage(product.getGoodsImage());
				}
				int row = skuMapper.insert(skuEntity);
				skuStatus += row;
			}
			if (skuStatus > 0) {
				return skuStatus;
			} else {
				throw new RuntimeException("sku商品添加失败");
			}
		}
		return productStatus;
	}

	private String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 修改商品基本 【spu】
	 *
	 * @param product 商品基本 【spu】信息
	 * @return 结果
	 */
	@Override
	public int update(ProductEntity product) {
		product.setUpdatedTime(new Date());
		return productMapper.update(product);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateBatch(ProductEntity product, List<SkuEntity> list) {
		product.setUpdatedTime(new Date());
		int spuCode = productMapper.update(product);
		if (spuCode > 0) {
			if (null != list && list.size() > 0) {
				int skuStatus = 0;
				for (SkuEntity skuEntity : list) {
					int row;
					skuEntity.setUpdatedTime(new Date());
					skuEntity.setSpuCode(product.getSpuCode());
					if (skuEntity.getId() == null || skuEntity.getId() == 0) {
						skuEntity.setSkuCode(product.getSpuCode() + "-" + getUUID());
						row = skuMapper.insert(skuEntity);
					} else {
						row = skuMapper.update(skuEntity);
					}
					skuStatus += row;
				}
				if (skuStatus > 0) {
					return skuStatus;
				} else {
					throw new RuntimeException("sku商品编辑失败");
				}
			}
		}
		return spuCode;
	}

	/**
	 * 删除商品基本 【spu】对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteByIds(String ids) {
		int productDel = productMapper.deleteByIds(ids.split(","));
		if (productDel > 0) {
			int skuDel = skuMapper.deleteBySpuCodes(ids.split(","));
			if (skuDel > 0) {
				return skuDel;
			} else {
				throw new RuntimeException("商品删除失败");
			}
		}
		return productDel;
	}

	@Override
	public int updateProduct(ProductEntity product) {
		return productMapper.updateProduct(product);
	}

	@Override
	public int updateBatch(Map<String, Object> params) {
		params.put("statedTime", new Date());
		return productMapper.updateBatch(params);
	}

	/**
	 * 条件查询
	 *
	 * @param params
	 * @return
	 */
	@Override
	public List<ProductEntity> queryByParam(Map<String, Object> params) {
		return productMapper.queryByParam(params);
	}

	@Override
	public List<ProductEntity> selectFilterCategory() {
		return productMapper.selectFilterCategory();
	}

	@Override
	public List<ProductEntity> selectFilterYxCategory() {
		return productMapper.selectFilterYxCategory();
	}

	/**
	 * @param campaignId  节日礼金id
	 * @param spuCodeList
	 * @return
	 */
	@Override
	public Boolean checkProductMix(Long campaignId, Long topicId, List<Long> spuCodeList) {
		List<Integer> topicIdsList = null;
		EnterprFestivalPacket enterprFestivalPacket = enterprFestivalPacketMapper.selectByPrimaryKey(campaignId);
		if (null != enterprFestivalPacket) {
			String topicIdStr = enterprFestivalPacket.getCampaignThemeIdList().toString();
			if (!StringUtils.isEmpty(topicIdStr)) {
				topicIdsList = new ArrayList<>();
				topicIdsList
						.addAll(Stream.of(topicIdStr.split(",")).map(String::trim).filter(item -> item.matches("\\d+"))
								.map(item -> Integer.valueOf(item.trim())).distinct().collect(Collectors.toList()));
			}
		}
		if (topicId != null) {
			if (topicIdsList == null) {
				topicIdsList = new ArrayList<>();
			}
			topicIdsList.add(topicId.intValue());
		}
		if (CollectionUtils.isEmpty(topicIdsList)) {
			return false;
		}
		for (Long spu : spuCodeList) {
			ProductTopicCondition productTopicCondition = new ProductTopicCondition();
			productTopicCondition.createCriteria().andSpuCodeEqualTo(spu).andTopicIdIn(topicIdsList);
			long count = productTopicMapper.countByExample(productTopicCondition);
			if (count < 1) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<ProductEntity> queryProductsByTopicId(Map<String, Object> params) {
		return productMapper.queryProductsByTopicId(params);
	}

	@Override
	public List<ProductEntity> querySkuByTopicId(Map<String, Object> params) {
		return productMapper.querySkuByTopicId(params);
	}

	@Override
	public Integer querySkuByTopicIdCount(Map<String, Object> params) {
		return productMapper.querySkuByTopicIdCount(params);
	}

	@Override
	public List<ProductEntity> queryFeatureList(Map<String, Object> params) {
		return productMapper.queryFeatureList(params);
	}

	@Override
	public Integer queryProductsByTopicIdCount(Map<String, Object> params) {
		return productMapper.queryProductsByTopicIdCount(params);
	}

	@Override
	public List<ProductEntity> queryProductsByCampaignId(Map<String, Object> params) {
		String campaignId = params.get("campaignId").toString();
		EnterprFestivalPacket enterprFestivalPacket = enterprFestivalPacketMapper
				.selectByPrimaryKey(Long.parseLong(campaignId));
		if (null == enterprFestivalPacket) {
			return null;
		}
		params.remove("campaignId");
		params.put("topicId", campaignId);
		return productMapper.queryProductsByTopicId(params);
	}

	/**
	 * 自营商品导入
	 *
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject batchImport(ImportReqDto reqDto) {
		return dataImport(reqDto, null);
	}

	/**
	 * w供应商商品导入
	 *
	 * @return
	 */
	@Override
	public JSONObject batchImport(ImportReqDto reqDto, Long supplierId) {
		return dataImport(reqDto, supplierId);
	}

	public JSONObject dataImport(ImportReqDto reqDto, Long supplierId) {
		if (null == reqDto || null == reqDto.getFilePath() || reqDto.getFilePath().isEmpty()) {
			return Response.fail("自营商品导入文件不能为空");
		}
		String filePath = reqDto.getFilePath();
		// 1. 解析自营商品信息
		List<Object> productList;
		try {
			UrlResource resource = new UrlResource(filePath);
			String suffix = filePath.substring(filePath.lastIndexOf(".") + 1);
			ExcelTypeEnum excelTypeEnum = "xlsx".equalsIgnoreCase(suffix) ? ExcelTypeEnum.XLSX : ExcelTypeEnum.XLS;
			productList = EasyExcelUtil.readExcelWithModel(resource.getInputStream(), ProductInfoDto.class,
					excelTypeEnum);
		} catch (Exception e) {
			logger.error("自营商品文件解析出错：", e);
			return Response.fail("自营商品文件解析出错!");
		}
		if (CollectionUtils.isEmpty(productList)) {
			return Response.fail("excel表格数据为空");
		}
		List<ProductInfoDto> list = new ArrayList<>();
		int line = 2;
		try {
			for (Object product : productList) {
				ProductInfoDto productDto = (ProductInfoDto) product;
				if (null == productDto) {
					return Response.fail(String.format("商品信息不能为空!", line));
				}
				// 跳过空行
				if (null == productDto.getBrandName() && null == productDto.getGoodsName()
						&& null == productDto.getSupplierName()) {
					break;
				}
				// 品牌
				if (null == productDto.getBrandName() || productDto.getBrandName().trim().isEmpty()) {
					return Response.fail(String.format("第 %s 行一级品牌名称不能为空!", line));
				}
				BrandEntity brandEntity = brandMapper.selectByName(productDto.getBrandName());
				if (null == brandEntity) {
					return Response.fail(String.format("第 %s 行品牌错误不能为空!", line));
				} else {
					productDto.setBrandId(brandEntity.getId());
				}
				// 一级类目
				if (null == productDto.getCategoryName() || productDto.getCategoryName().trim().isEmpty()) {
					return Response.fail(String.format("第 %s 行一级类目不能为空!", line));
				}
				CategoryEntity categoryEntity = categoryMapper.queryByNameAndPid(productDto.getCategoryName(), 0L);
				if (null == categoryEntity) {
					return Response.fail(String.format("第 %s 行一级类目错误!", line));
				} else {
					productDto.setCategoryId(categoryEntity.getId());
				}
				// 二级类目
				if (null == productDto.getCategoryName2() || productDto.getCategoryName2().trim().isEmpty()) {
					return Response.fail(String.format("第 %s 行二级类目不能为空!", line));
				}
				CategoryEntity categoryEntity2 = categoryMapper.queryByNameAndPid(productDto.getCategoryName2(),
						categoryEntity.getId());
				if (null == categoryEntity2) {
					return Response.fail(String.format("第 %s 行二级类目错误!", line));
				} else {
					productDto.setCategoryId2(categoryEntity2.getId());
				}
				// 三级类目
				if (null == productDto.getCategoryName3() || productDto.getCategoryName3().trim().isEmpty()) {
					return Response.fail(String.format("第 %s 行三级类目不能为空!", line));
				}
				CategoryEntity categoryEntity3 = categoryMapper.queryByNameAndPid(productDto.getCategoryName3(),
						categoryEntity2.getId());
				if (null == categoryEntity3) {
					return Response.fail(String.format("第 %s 行三级类目错误!", line));
				} else {
					productDto.setCategoryId3(categoryEntity3.getId());
				}
				// 供应商
				if (null == productDto.getSupplierName() || productDto.getSupplierName().trim().isEmpty()) {
					return Response.fail(String.format("第 %s 行供应商不能为空!", line));
				}
				SupplierInfo supplierInfo = supplierInfoMapper.getSupplierInfoByName(productDto.getSupplierName());
				if (null == supplierInfo) {
					return Response.fail(String.format("第 %s 行供应商错误!", line));
				} else if (supplierId != null && !supplierInfo.getId().equals(supplierId)) {
					return Response.fail(String.format("第 %s 行禁止导入其他供应商商品!", line));
				} else {
					productDto.setSupplierId(supplierInfo.getId());
				}
				// 产地
				if (null == productDto.getPlaceOrigin() || productDto.getPlaceOrigin().trim().isEmpty()) {
					return Response.fail(String.format("第 %s 行产地不能为空!", line));
				}
				DistrictInfo districtInfo = districtInfoMapper.getInfoByName(productDto.getPlaceOrigin());
				if (null == districtInfo) {
					return Response.fail(String.format("第 %s 行产地错误!", line));
				} else {
					productDto.setPlaceOriginId(districtInfo.getId());
				}

				if (null == productDto.getIsCross()) {
					return Response.fail(String.format("第 %s 行是否跨境不能为空!", line));
				}
				if (null == productDto.getGoodsType()) {
					return Response.fail(String.format("第 %s 行商品类型不能为空!", line));
				}
				if (null == productDto.getGoodsName() || productDto.getGoodsName().trim().isEmpty()) {
					return Response.fail(String.format("第 %s 行商品名称不能为空!", line));
				}
				if (null == productDto.getGoodsMarkprice()) {
					return Response.fail(String.format("第 %s 行市场价不能为空!", line));
				}
				if (null == productDto.getGoodsCostprice()) {
					return Response.fail(String.format("第 %s 行成本价不能为空!", line));
				}
				if (null == productDto.getGoodsPrice()) {
					return Response.fail(String.format("第 %s 行销售价不能为空!", line));
				}
				if (null == productDto.getGoodsImage() || productDto.getGoodsImage().trim().isEmpty()) {
					return Response.fail(String.format("第 %s 行主图片不能为空!", line));
				}
				if (null == productDto.getItemNumber() || productDto.getItemNumber().trim().isEmpty()) {
					return Response.fail(String.format("第 %s 行原厂货号不能为空!", line));
				}
				if (null == productDto.getGoodsFreight()) {
					return Response.fail(String.format("第 %s 行运费不能为空!", line));
				}
				if (null == productDto.getGoodsDiscount()) {
					return Response.fail(String.format("第 %s 行折扣不能为空!", line));
				}
				if (null == productDto.getGoodsStorge()) {
					return Response.fail(String.format("第 %s 行商品库存不能为空!", line));
				}
				if (null != productDto.getGoodsImages() && !productDto.getGoodsImages().trim().isEmpty()) {
					productDto.setGoodsImages(JSON.toJSONString(productDto.getGoodsImages().split(";")));
				}
				list.add(productDto);
				line++;
			}
		} catch (Exception e) {
			logger.error("batchImport exception", e);
			throw new RuntimeException("导入自营商品异常!");
		}
		int productNum = productList.size();
		if (productNum < 1) {
			return Response.fail("至少需要导入一条数据!");
		}
		if (productNum > 2000) {
			return Response.fail("每次最多只能导入2000条数据!");
		}
		try {
			List<ProductEntity> supList = new ArrayList<>();
			List<SkuEntity> skuList = new ArrayList<>();
			ProductEntity product;
			for (int i = 0; i < list.size(); i++) {
				if (i == 0 || !list.get(i).getGoodsName().equals(list.get(i - 1).getGoodsName())) {
					product = BeansUtils.copyProperties(list.get(i), ProductEntity.class);
					skuList = new ArrayList<>();
					product.setSkuList(skuList);
					supList.add(product);
				}
				SkuEntity skuEntity = new SkuEntity();
				skuEntity.setGoodsStorge(list.get(i).getGoodsStorge());
				skuEntity.setGoodsImage(list.get(i).getGoodsImage());
				skuEntity.setCreatedTime(new Date());
				skuEntity.setUpdatedTime(new Date());
				skuEntity.setGoodsPrice(list.get(i).getGoodsPrice());
				skuEntity.setGoodsMarkprice(list.get(i).getGoodsMarkprice());
				skuEntity.setGoodsCostprice(list.get(i).getGoodsCostprice());
				skuEntity.setGoodsRate(
						CalRateUtil.calRate(list.get(i).getGoodsPrice(), null, list.get(i).getGoodsCostprice()));
				// 折扣
				BigDecimal discount = skuEntity.getGoodsPrice().divide(skuEntity.getGoodsMarkprice(), 2,
						RoundingMode.HALF_UP);
				skuEntity.setGoodsDiscount(discount);
				skuEntity.setSaleUnit(list.get(i).getSaleUnit());
				skuList.add(skuEntity);
			}

			for (ProductEntity entity : supList) {
				productService.insertBatch(entity, entity.getSkuList());
			}
		} catch (Exception e) {
			logger.error("batchImport exception", e);
			throw new RuntimeException("导入自营商品异常!");
		}
		return Response.succ(true);
	}

	@Override
	public List<ProductEntity> listBySpuCodeList(List<Long> spuCodeList) {
		if (CollectionUtils.isEmpty(spuCodeList)) {
			return Collections.emptyList();
		}
		return productMapper.selectBySpuCodeList(spuCodeList);
	}

	@Override
	public List<ProductEntity> selectSkuBySpuCode(Map<String, Object> params) {
		return productMapper.selectSkuBySpuCode(params);
	}

	@Override
	public ProductBean getPCProductSpecBean(Long spuCode, Long campaignId) {
		ProductEntity product = this.selectById(spuCode);
		if (product == null) {
			return null;
		}
		SessionShopInfo sessionEmployeeInfo = SessionContextHolder.getSessionEmployeeInfo();
		if (campaignId != null && campaignId > 0) {
			if (!enterprFestivalService.isExist(campaignId, spuCode)) {
				throw new BusinessException("抱歉,该商品已不在该活动中!");
			} else {
				if (!creditOperateInfoService.isAuthority(campaignId, sessionEmployeeInfo.getEmployeeId())) {
					throw new BusinessException("抱歉,您没有权限参加该活动!");
				}
				if (sessionEmployeeInfo != null && yibaoProperties.getEnterprId().equals(sessionEmployeeInfo.getEnterprId())) {
					if (creditOperateInfoService.isParticipate(campaignId, sessionEmployeeInfo.getEmployeeId())) {
						throw new BusinessException("抱歉,您已经参加过该活动!");
					}
				}
			}
		}
		ProductBean productBean = new ProductBean();
		productBean.setGoodsName(product.getGoodsName());
		productBean.setGoodsNameEn(product.getGoodsNameEn());
		productBean.setGoodsImage(product.getGoodsImage());
		String defaultGoodsImages = product.getGoodsImages();
		if (!StringUtils.isEmpty(defaultGoodsImages)) {
			try {
				productBean.setImgUrlList(JSONArray.parseArray(defaultGoodsImages, String.class));
			} catch (Exception e) {
			}
		}
		productBean.setSpuCode(product.getSpuCode());
		ProductEntity productEntity = productService.selectById(product.getSpuCode());
		List<Voucher> vouchers = new ArrayList<>();
		if(StringUtil.isNotEmpty(productEntity.getVoucherIds())){
			List<Long> voucherIds = JsonUtil.parseObject(productEntity.getVoucherIds(),new TypeReference<List<Long>>(){});
			EmployeeCreditInfo employeeCreditInfo =
					employeeCreditInfoService.getEmployeeCreditInfo(SessionContextHolder.getSessionEmployeeInfo().getEmployeeId(), 0L);
			String receivedVouchers = employeeCreditInfo.getReceivedVouchers();
			for(int i=0,len=voucherIds.size();i<len;i++){
				Voucher voucher = voucherService.getVoucherByVoucherId(voucherIds.get(i));
				Integer flag = 1;
				if(StringUtil.isNotEmpty(receivedVouchers)){
					String[] receivedVouchersArray = receivedVouchers.split(",");
					for(int z=0,len1 = receivedVouchersArray.length;z<len1;z++){
						Long voucherId = Long.parseLong(receivedVouchersArray[z].split(":")[0]);
						Integer num = Integer.parseInt(receivedVouchersArray[z].split(":")[1]);
						if(voucherId.equals(voucherIds.get(i))&& num <= 0){
							flag = 2;
							break;
						}else if(voucherId.equals(voucherIds.get(i))&& num > 0){
							flag = 3;
							break;
						}
						if(z == len1-1){
							if(flag == 1){
								flag = 2;
							}
						}
					}
					if(flag == 2){
						continue;
					}
				}else {
					if (voucher.getVoucherStatus() == 2) {
						continue;
					}
				}
				if(voucher != null && System.currentTimeMillis() >= voucher.getValidateStartTime().getTime() && System.currentTimeMillis()<voucher.getValidateEndTime().getTime()){
					vouchers.add(voucher);
				}
			}
		}
		List<Long> voucherIdsTemp = voucherService.fingFullRangeVoucherIds();
		if(!voucherIdsTemp.isEmpty()){
			EmployeeCreditInfo employeeCreditInfo =
					employeeCreditInfoService.getEmployeeCreditInfo(SessionContextHolder.getSessionEmployeeInfo().getEmployeeId(), 0L);
			String receivedVouchers = employeeCreditInfo.getReceivedVouchers();
			for(int i=0,len=voucherIdsTemp.size();i<len;i++){
				Voucher voucher = voucherService.getVoucherByVoucherId(voucherIdsTemp.get(i));
				Integer flag = 1;
				if(StringUtil.isNotEmpty(receivedVouchers)){
					String[] receivedVouchersArray = receivedVouchers.split(",");
					for(int z=0,len1 = receivedVouchersArray.length;z<len1;z++){
						Long voucherId = Long.parseLong(receivedVouchersArray[z].split(":")[0]);
						Integer num = Integer.parseInt(receivedVouchersArray[z].split(":")[1]);
						if(voucherId.equals(voucherIdsTemp.get(i))&& num <= 0){
							flag = 2;
							break;
						}else if(voucherId.equals(voucherIdsTemp.get(i))&& num > 0){
							flag = 3;
							break;
						}
						if(z == len1-1){
							if(flag == 1){
								flag = 2;
							}
						}
					}
					if(flag == 2){
						continue;
					}
				}else {
					if (voucher.getVoucherStatus() == 2) {
						continue;
					}
				}
				if(voucher != null && System.currentTimeMillis() >= voucher.getValidateStartTime().getTime() && System.currentTimeMillis()<voucher.getValidateEndTime().getTime()){
					vouchers.add(voucher);
				}
			}
		}
		productBean.setVouchers(vouchers);
		productBean.setBrandName(product.getBrandName());
		productBean.setSupplierId(product.getSupplierId());
		productBean.setPlaceOrigin(product.getPlaceOrigin());
		productBean.setCountrySize(product.getCountrySize());
		productBean.setGoodsFreight(product.getGoodsFreight());
		productBean.setIntroduction(product.getIntroduction());
		productBean.setIntroductionEn(product.getIntroductionEn());
		productBean.setSeason(product.getSeason());
		productBean.setSexName(SexEnum.getSexEnum(product.getSex(), SexEnum.L).getDesc());
		productBean.setGoodsState(product.getGoodsState());
		productBean.setGoodsType(product.getGoodsType());
		HashMap<Long, SkuEntity> skuBeanMap = new HashMap<>();
		List<SkuEntity> skus = skuMapper.getSkuBySpuCode(spuCode, null);
		if (!CollectionUtils.isEmpty(skus)) {
			skus = skus.stream().filter(item -> item.getGoodsStorge() >= 0).map(item -> {
				skuBeanMap.put(item.getId(), item);
				return item;
			}).collect(Collectors.toList());
		}
		if ((campaignId == null||campaignId==0) && sessionEmployeeInfo != null) {
			GoodsModuleInfoDto goodsModuleInfoDto = enterprCustomGoodsService.findByIdWithAgent(sessionEmployeeInfo.getEnterprId());
			BigDecimal lowestPrice = goodsModuleInfoDto.getLowestPrice();
			if (lowestPrice != null && BigDecimal.ZERO.compareTo(lowestPrice) < 0) {
				for (Iterator<SkuEntity> iterator = skus.iterator(); iterator.hasNext();) {
					SkuEntity sku = iterator.next();
					if (sku.getGoodsPrice().compareTo(lowestPrice) < 0) {
						iterator.remove();
					}
				}
			}
		}
		if (CollectionUtils.isEmpty(skus)) {
			productBean.setSkuList(Collections.emptyList());
			productBean.setSkuSpecList(Collections.emptyList());
			return productBean;
		}
		ProductSpecInfoCondition productInfoSpecCondition = new ProductSpecInfoCondition();
		productInfoSpecCondition.createCriteria().andSpuCodeEqualTo(spuCode);
		productInfoSpecCondition.setOrderByClause("id ASC");
		List<ProductSpecInfo> productSpecInfos = productSpecInfoMapper.selectByExample(productInfoSpecCondition);
		if (CollectionUtils.isEmpty(productSpecInfos)) {// 没有规格
			// 制定一个默认规格（京东，严选，规格名：规格）
			if (skus.size() == 1) {// 1个产品
				SkuBean skuBean = new SkuBean();
				SkuEntity sku = skus.get(0);
				skuBean.setFlag(true);
				if (sku.getStatus() == 0) {
					skuBean.setFlag(false);
				}
				skuBean.setSkuId(sku.getId());
				skuBean.setSkuCode(sku.getSkuCode());
				//设置秒杀商品详情
				if(StringUtil.isNotEmpty(sku.getSeckillIds())){
					List<Long> seckillIds= JsonUtil.parseObject(sku.getSeckillIds(),new TypeReference<List<Long>>(){});
					for(int i=0,len = seckillIds.size();i<len;i++){
						Seckill seckill = seckillService.getSeckill(seckillIds.get(i));
						if(seckillService.validateSeckillEffective(seckill,false)){
							skuBean.setSeckill(seckill);
						}
					}

				}
				skuBean.setGoodsStorge(sku.getGoodsStorge());
				skuBean.setGoodsName(sku.getGoodsName());
				skuBean.setGoodsPrice(sku.getGoodsPrice());
				skuBean.setGoodsImage(sku.getGoodsImage());
				String goodsImages = sku.getGoodsImages();
				if (!StringUtils.isEmpty(goodsImages)) {
					try {
						skuBean.setImgUrlList(JSONArray.parseArray(goodsImages, String.class));
					} catch (Exception e) {
					}
				}
				productBean.setSkuList(Arrays.asList(skuBean));
				String goodsSpec = sku.getGoodsSpec();
				if (!StringUtils.isEmpty(goodsSpec)) {
					ProductSpecValueBean productSpecValueBean = new ProductSpecValueBean();
					productSpecValueBean.setSpecName("规格");
					productBean.setSkuSpecList(Arrays.asList(productSpecValueBean));// 规格表
					SpectValueBean spectValueBean = new SpectValueBean(0, goodsSpec.trim());
					productSpecValueBean.setSpecValues(Arrays.asList(spectValueBean));
					skuBean.setSpecLinkeId("0");
				}
			} else {
				List<SkuBean> skuList = new ArrayList<>(skus.size());// sku列表
				productBean.setSkuList(skuList);
				ProductSpecValueBean productSpecValueBean = new ProductSpecValueBean();
				productSpecValueBean.setSpecName("规格");
				productBean.setSkuSpecList(Arrays.asList(productSpecValueBean));// 规格表
				List<SpectValueBean> specValueList = new ArrayList<>();
				productSpecValueBean.setSpecValues(specValueList);
				int index = 0;
				for (SkuEntity sku : skus) {
					SkuBean skuBean = new SkuBean();
					skuBean.setFlag(true);
					if (sku.getStatus() == 0) {
						skuBean.setFlag(false);
					}
					skuBean.setSkuId(sku.getId());
					skuBean.setSkuCode(sku.getSkuCode());
					//设置秒杀商品详情
					if(StringUtil.isNotEmpty(sku.getSeckillIds())){
						List<Long> seckillIds= JsonUtil.parseObject(sku.getSeckillIds(),new TypeReference<List<Long>>(){});
						for(int i=0,len = seckillIds.size();i<len;i++){
							Seckill seckill = seckillService.getSeckill(seckillIds.get(i));
							if(seckillService.validateSeckillEffective(seckill,false)){
								skuBean.setSeckill(seckill);
							}
						}

					}
					skuBean.setGoodsStorge(sku.getGoodsStorge());
					skuBean.setGoodsName(sku.getGoodsName());
					skuBean.setGoodsPrice(sku.getGoodsPrice());
					skuBean.setGoodsImage(sku.getGoodsImage());
					String goodsImages = sku.getGoodsImages();
					if (!StringUtils.isEmpty(goodsImages)) {// 商品轮播图
						try {
							skuBean.setImgUrlList(JSONArray.parseArray(goodsImages, String.class));
						} catch (Exception e) {
						}
					}
					skuBean.setSpecLinkeId(String.valueOf(index));
					skuList.add(skuBean);
					String goodsSpec = sku.getGoodsSpec();
					specValueList.add(new SpectValueBean(index,
							StringUtils.isEmpty(goodsSpec) ? "规格" + index : goodsSpec.trim()));
					index++;
				}
			}
		} else if (productSpecInfos.size() == 1) {
			ProductSpecInfo productSpecInfo = productSpecInfos.get(0);
			List<SkuSpectValueBean> skuSpectBeans = skuSpecValueMapper
					.seleSkuOneSpecValue(new ArrayList<>(skuBeanMap.keySet()), productSpecInfo.getSpecId());
			if (CollectionUtils.isEmpty(skuSpectBeans)) {
				return productBean;// throw new BusinessException("抱歉,该产品已下架!");
			}
			List<SkuBean> skuList = new ArrayList<>(skuSpectBeans.size());// sku列表
			List<ProductSpecValueBean> skuSpecList = new ArrayList<>(1);// 规格表
			productBean.setSkuList(skuList);
			productBean.setSkuSpecList(skuSpecList);
			ProductSpecValueBean productSpecValueBean = new ProductSpecValueBean();
			productSpecValueBean.setSpecName(productSpecInfo.getSpecName());
			skuSpecList.add(productSpecValueBean);
			List<SpectValueBean> specValueList = new ArrayList<>();
			productSpecValueBean.setSpecValues(specValueList);
			for (int index = 0; index < skuSpectBeans.size(); index++) {
				SkuSpectValueBean skuSpectValueBean = skuSpectBeans.get(index);
				SkuBean skuBean = new SkuBean();
				SkuEntity sku = skuBeanMap.get(skuSpectValueBean.getSkuId());
				skuBean.setFlag(true);
				if (sku.getStatus() == 0) {
					skuBean.setFlag(false);
				}
				skuBean.setSkuId(sku.getId());
				skuBean.setSkuCode(sku.getSkuCode());
				skuBean.setGoodsStorge(sku.getGoodsStorge());
				skuBean.setGoodsName(sku.getGoodsName());
				//设置秒杀商品详情
				if(StringUtil.isNotEmpty(sku.getSeckillIds())){
					List<Long> seckillIds= JsonUtil.parseObject(sku.getSeckillIds(),new TypeReference<List<Long>>(){});
					for(int i=0,len = seckillIds.size();i<len;i++){
						Seckill seckill = seckillService.getSeckill(seckillIds.get(i));
						if(seckillService.validateSeckillEffective(seckill,false)){
							skuBean.setSeckill(seckill);
						}
					}

				}
				skuBean.setGoodsImage(sku.getGoodsImage());
				String goodsImages = sku.getGoodsImages();
				if (!StringUtils.isEmpty(goodsImages)) {// 商品轮播图
					try {
						skuBean.setImgUrlList(JSONArray.parseArray(goodsImages, String.class));
					} catch (Exception e) {
					}
				}
				skuBean.setGoodsPrice(sku.getGoodsPrice());
				skuBean.setSpecLinkeId(String.valueOf(index));
				skuList.add(skuBean);
				specValueList.add(new SpectValueBean(index, skuSpectValueBean.getSpecValue1(),
						skuSpectValueBean.getSpecImage1()));
			}
		} else {// 规格大于等于2
			Integer firstSpecId = productSpecInfos.get(0).getSpecId();
			Integer secondSpecId = productSpecInfos.get(1).getSpecId();
			List<SkuSpectValueBean> skuSpectBeans = skuSpecValueMapper
					.selectSkuTwoSpecValue(new ArrayList<>(skuBeanMap.keySet()), firstSpecId, secondSpecId);
			if (CollectionUtils.isEmpty(skuSpectBeans)) {
				return productBean;// throw new BusinessException("抱歉,该产品已下架!");
			}

			List<SkuBean> skuList = new ArrayList<>(skuSpectBeans.size());// sku列表
			List<ProductSpecValueBean> skuSpecList = new ArrayList<>(2);// 规格表
			productBean.setSkuList(skuList);
			productBean.setSkuSpecList(skuSpecList);
			List<SpectValueBean> specValueList1 = new ArrayList<>();
			List<SpectValueBean> specValueList2 = new ArrayList<>();
			ProductSpecValueBean productSpecValueBean1 = new ProductSpecValueBean();
			productSpecValueBean1.setSpecName(productSpecInfos.get(0).getSpecName());
			productSpecValueBean1.setSpecValues(specValueList1);
			ProductSpecValueBean productSpecValueBean2 = new ProductSpecValueBean();
			productSpecValueBean2.setSpecName(productSpecInfos.get(1).getSpecName());
			productSpecValueBean2.setSpecValues(specValueList2);
			productBean.setSkuSpecList(Arrays.asList(productSpecValueBean1, productSpecValueBean2));
			Map<String, Integer> hashMap = new HashMap<>();
			for (int index = 0; index < skuSpectBeans.size(); index++) {
				SkuSpectValueBean skuSpectValueBean = skuSpectBeans.get(index);
				SkuBean skuBean = new SkuBean();
				SkuEntity sku = skuBeanMap.get(skuSpectValueBean.getSkuId());
				skuBean.setFlag(true);
				if (sku.getStatus() == 0) {
					skuBean.setFlag(false);
				}
				skuBean.setSkuId(sku.getId());
				skuBean.setSkuCode(sku.getSkuCode());
				skuBean.setGoodsStorge(sku.getGoodsStorge());
				skuBean.setGoodsName(sku.getGoodsName());
				//设置秒杀商品详情
				if(StringUtil.isNotEmpty(sku.getSeckillIds())){
					List<Long> seckillIds= JsonUtil.parseObject(sku.getSeckillIds(),new TypeReference<List<Long>>(){});
					for(int i=0,len = seckillIds.size();i<len;i++){
						Seckill seckill = seckillService.getSeckill(seckillIds.get(i));
						if(seckillService.validateSeckillEffective(seckill,false)){
							skuBean.setSeckill(seckill);
						}
					}
				}
				skuBean.setGoodsPrice(sku.getGoodsPrice());
				skuBean.setGoodsImage(sku.getGoodsImage());
				String goodsImages = sku.getGoodsImages();
				if (!StringUtils.isEmpty(goodsImages)) {
					try {
						skuBean.setImgUrlList(JSONArray.parseArray(goodsImages, String.class));
					} catch (Exception e) {
					}
				}
				String specValue1 = skuSpectValueBean.getSpecValue1();
				String specValue2 = skuSpectValueBean.getSpecValue2();
				Integer number1 = hashMap.computeIfAbsent("1_" + specValue1, item -> {
					int number = specValueList1.size();
					specValueList1.add(new SpectValueBean(number, specValue1, skuSpectValueBean.getSpecImage1()));
					return number;
				});
				Integer number2 = hashMap.computeIfAbsent("2_" + specValue2, item -> {
					int number = specValueList2.size();
					specValueList2.add(new SpectValueBean(number, specValue2, skuSpectValueBean.getSpecImage2()));
					return number;
				});
				skuBean.setSpecLinkeId(number1 + "_" + number2);
				skuList.add(skuBean);
			}
		}
		return productBean;
	}

	@Override
	public List<ProductEntity> selectSpusByVoucherId(Map<String, Object> params) {
		if(params.isEmpty()){
			throw new BusinessException("params参数不能为空！");
		}
		return productMapper.selectSpusByVoucherId(params);
	}

	@Override
	public Integer countSpusByVoucherId(Map<String, Object> params) {
		if(params.isEmpty()){
			throw new BusinessException("params参数不能为空！");
		}
		return productMapper.countSpusByVoucherId(params);
	}

	@Override
	public ProductBean getPCProductSpecBean(Long spuCode) {
		return this.getPCProductSpecBean(spuCode, null);
	}

	@Override
	public int updateProductState(Long spuCode, Integer goodsState) {
		if (spuCode == null) {
			throw new BusinessException("spu编码不能为空!");
		}
		ProductEntity basicInfo = this.getBasicInfo(spuCode);
		if (basicInfo == null) {
			throw new BusinessException("该商品不存在!");
		}
		if (basicInfo.getGoodsState().equals(goodsState)) {// 状态未发生变化
			return 0;
		}
		ProductEntity product = new ProductEntity();
		product.setSpuCode(spuCode);
		Date nowTime = new Date();
		if (Integer.valueOf(1).equals(goodsState)) {// 上架
			if (basicInfo.getGoodsState().equals(2)) {// 2审核中 不能上架
				throw new BusinessException("商品正在审核，请勿提交上架!");
			}
			product.setGoodsState(goodsState);
			product.setStatedTime(nowTime);
		} else if (Integer.valueOf(0).equals(goodsState)) {// 下架
			if (basicInfo.getGoodsState().equals(2)) {// 2审核中 不能下架
				throw new BusinessException("商品正在审核，不能下架操作!");
			}
			product.setGoodsState(goodsState);
		} else if (Integer.valueOf(3).equals(goodsState)) {// 审核通过
			product.setGoodsState(goodsState);
		} else {
			throw new BusinessException("上下架状态不正确!");
		}
		product.setUpdatedTime(nowTime);
		product.setUpdatedUser(SessionContextHolder.getCurrentLoginName());
		return productMapper.update(product);
	}

	@Override
	public int updateProductState(List<Long> spuCodeList, Integer type) {
		if (CollectionUtils.isEmpty(spuCodeList)) {
			return 0;
		}
		ProductEntity product = new ProductEntity();
		Date nowTime = new Date();
		if (Integer.valueOf(1).equals(type)) {// 上架
			for (Long spuCode : spuCodeList) {
				ProductEntity basicInfo = this.getBasicInfo(spuCode);
				if (basicInfo == null) {
					throw new BusinessException("该商品不存在!");
				}
				if (basicInfo.getGoodsState().equals(2)) {// 2审核中
					throw new BusinessException("有商品正在审核，请勿提交上架!");
				}
			}
			product.setStatedTime(new Date());
		} else if (Integer.valueOf(0).equals(type)) {// 下架
			for (Long spuCode : spuCodeList) {
				ProductEntity basicInfo = this.getBasicInfo(spuCode);
				if (basicInfo == null) {
					throw new BusinessException("该商品不存在!");
				}
				if (basicInfo.getGoodsState().equals(2)) {// 2审核中
					throw new BusinessException("有商品正在审核，不能下架操作!");
				}
			}
		} else {
			throw new BusinessException("上下架状态不正确!");
		}
		product.setGoodsState(type);
		product.setUpdatedTime(nowTime);
		product.setUpdatedUser(SessionContextHolder.getCurrentLoginName());
		return productMapper.batchUpdateProductState(spuCodeList, product);
	}

	@Override
	public PageResultBean<ProductEntity> selectPage(ProductQueryParam queryParam, PageBean pageBean) {
		PageResultBean<ProductEntity> pageResultBean = new PageResultBean<>();
		int count = productMapper.selectCount(queryParam);
		pageResultBean.setCount(count);
		pageResultBean.setPage(pageBean.getPage());
		pageResultBean.setPageSize(pageBean.getPageSize());
		if (count == 0 || pageBean.getOffset() >= count) {
			pageResultBean.setList(Collections.emptyList());
		} else {
			pageResultBean.setList(productMapper.selectPage(queryParam, pageBean));
		}
		return pageResultBean;
	}

	@Override
	public ProductEntity getBasicInfo(Long spuCode) {
		return productMapper.getBasicInfo(spuCode);
	}

	@Override
	public String getProductIntruduction(Long spuCode) {
		return productMapper.getProductIntruduction(spuCode);
	}

	@Override
	public int setGoodsSaleRate(BigDecimal goodsRate) {
		if (goodsRate == null || goodsRate.compareTo(BigDecimal.valueOf(0)) < 0
				|| goodsRate.compareTo(BigDecimal.valueOf(1)) >= 0) {
			throw new BusinessException("毛利率需在0%-100%之间!");
		}
		return productSettingService.updateSettingValue(ProductSettingService.PRODUCT_GOODS_SALERATE_SETTING,
				goodsRate.toString());
	}

	@Override
	public BigDecimal getGoodsSaleRate() {
		String settingVallue = productSettingService
				.getSettingVallue(ProductSettingService.PRODUCT_GOODS_SALERATE_SETTING);
		return new BigDecimal(settingVallue);
	}

	@Override
	public PageResultBean<ProductEntity> getProductsByPage(Long enterprId, ProductSearchBean productSearchBean, boolean isWhitelist, PageBean pageBean) {
		if (productSearchBean == null) {
			productSearchBean = new ProductSearchBean();
		}
		productSearchBean.setEnterprId(enterprId);
		Integer categoryId = productSearchBean.getCategoryId();
		if (categoryId != null) {
			if (categoryId >= 30000) {
				productSearchBean.setCategoryId(null);
				productSearchBean.setCategoryId3(categoryId);
			} else if (categoryId >= 20000) {
				productSearchBean.setCategoryId(null);
				productSearchBean.setCategoryId2(categoryId);
			}
		}
		if (!isWhitelist) {
			GoodsModuleInfoDto goodsModuleInfoDto = enterprCustomGoodsService.findByIdWithAgent(enterprId);
			if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getSupplierIdsList())) {// 排除供应商
				productSearchBean.setExcludeSupplierIds(goodsModuleInfoDto.getSupplierIdsList());
			}
			if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getCategoryIdsList())) {// 排除某些分类
				Map<Integer, List<Long>> categoryIdsMap = new HashMap<>(3);
				for (Long item : goodsModuleInfoDto.getCategoryIdsList()) {
					Integer type;
					if (item < 20000) {
						type = 1;
					} else if (item < 30000) {
						type = 2;
					} else {
						type = 3;
					}
					categoryIdsMap.compute(type, (key, oldValue) -> {
						if (oldValue == null) {
							oldValue = new ArrayList<>();
						} else {
						}
						oldValue.add(item);
						return oldValue;
					});
				}
				productSearchBean.setExcludeCategoryIds(categoryIdsMap.get(1));
				productSearchBean.setExcludeCategoryId2s(categoryIdsMap.get(2));
				productSearchBean.setExcludeCategoryId3s(categoryIdsMap.get(3));
			}
			if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getBrandIdsList())) {// 排除某些品牌
				productSearchBean.setExcludeBrandIds(goodsModuleInfoDto.getBrandIdsList());
			}
			if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getTopicIdsList())) {// 排除某些主题商品
				List<Long> excludeTopicIds = goodsModuleInfoDto.getTopicIdsList();
				productSearchBean.setExcludeTopicIds(excludeTopicIds);
				if (productSearchBean.getTopicId() != null
						&& excludeTopicIds.contains(productSearchBean.getTopicId().longValue())) {
					return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), 0, Collections.emptyList());
				}
			}
			// 商品最低价过滤
			if (goodsModuleInfoDto.getLowestPrice() != null && goodsModuleInfoDto.getLowestPrice().compareTo(BigDecimal.ZERO) > 0) {
				if (productSearchBean.getStartPrice() == null || productSearchBean.getStartPrice().compareTo(goodsModuleInfoDto.getLowestPrice()) < 0) {
					productSearchBean.setStartPrice(goodsModuleInfoDto.getLowestPrice());
				}
				if (productSearchBean.getEndPrice() != null && productSearchBean.getEndPrice().compareTo(productSearchBean.getStartPrice()) < 0) {
					return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), 0, Collections.emptyList());
				}
			}
			if (productSearchBean.isHot()) {// 热门商品
				List<Long> hotGoodsIdList = goodsModuleInfoDto.getHotGoodsIdList();
				if (!CollectionUtils.isEmpty(hotGoodsIdList)) {
					List<Long> excludeTopicIds = productSearchBean.getExcludeTopicIds();
					if (!CollectionUtils.isEmpty(excludeTopicIds)) {
						hotGoodsIdList.removeAll(excludeTopicIds);
					}
				}
				if (CollectionUtils.isEmpty(hotGoodsIdList)) {
					return new PageResultBean<>(pageBean.getPage(), pageBean.getPageSize(), 0, Collections.emptyList());
				}
				productSearchBean.setHotGoodsIdList(hotGoodsIdList);
			}
		}
		PageResultBean<ProductEntity> pageResultBean = new PageResultBean<>();
		// 总条数统计去掉，节省时间
		/**
		 * int count = productMapper.countProducts(productSearchBean);
		 * pageResultBean.setCount(count); if (count == 0 || pageBean.getOffset() >=
		 * count) { pageResultBean.setList(Collections.emptyList()); } else {
		 * pageResultBean.setList(productMapper.getProductsByPage(productSearchBean,
		 * pageBean)); }
		 **/
		pageResultBean.setPage(pageBean.getPage());
		pageResultBean.setPageSize(pageBean.getPageSize());
		pageResultBean.setList(productMapper.getProductsByPage(productSearchBean, pageBean));
		return pageResultBean;
	
	}

	@Override
	public List<ProductEntity> getBlackProductsByPage() {
		ProductSearchBean productSearchBean = new ProductSearchBean();
		GoodsModuleInfoDto goodsModuleInfoDto = enterprCustomGoodsService.findByIdWithAgent(SessionContextHolder.getSessionEmployeeInfo().getEnterprId());
		if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getSupplierIdsList())) {// 排除供应商
			productSearchBean.setExcludeSupplierIds(goodsModuleInfoDto.getSupplierIdsList());
		}
		if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getCategoryIdsList())) {// 排除某些分类
			Map<Integer, List<Long>> categoryIdsMap = new HashMap<>(3);
			for (Long item : goodsModuleInfoDto.getCategoryIdsList()) {
				Integer type;
				if (item < 20000) {
					type = 1;
				} else if (item < 30000) {
					type = 2;
				} else {
					type = 3;
				}
				categoryIdsMap.compute(type, (key, oldValue) -> {
					if (oldValue == null) {
						oldValue = new ArrayList<>();
					} else {
					}
					oldValue.add(item);
					return oldValue;
				});
			}
			productSearchBean.setExcludeCategoryIds(categoryIdsMap.get(1));
			productSearchBean.setExcludeCategoryId2s(categoryIdsMap.get(2));
			productSearchBean.setExcludeCategoryId3s(categoryIdsMap.get(3));
		}
		if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getBrandIdsList())) {// 排除某些品牌
			productSearchBean.setExcludeBrandIds(goodsModuleInfoDto.getBrandIdsList());
		}
		if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getTopicIdsList())) {// 排除某些主题商品
			List<Long> excludeTopicIds = goodsModuleInfoDto.getTopicIdsList();
			productSearchBean.setExcludeTopicIds(excludeTopicIds);
		}
		return productMapper.getBlackProducts(productSearchBean);
	}

	@Override
	public PageResultBean<ProductEntity> getProductsByPage(Long enterprId, ProductSearchBean productSearchBean, PageBean pageBean) {
		return this.getProductsByPage(enterprId, productSearchBean, false, pageBean);
	}

	@Override
	public Map<Long, String> getSkuSpecValue(List<Long> skuIds) {
		if (CollectionUtils.isEmpty(skuIds)) {
			return Collections.emptyMap();
		}
		List<SkuSpecValue> skuSpecValues = skuSpecValueMapper.getSkuSpecValue(skuIds);
		if (CollectionUtils.isEmpty(skuSpecValues)) {
			return Collections.emptyMap();
		}
		Map<Long, String> result = new HashMap<>(skuSpecValues.size());
		for (SkuSpecValue skuSpecValue : skuSpecValues) {
			result.put(skuSpecValue.getSkuId(), skuSpecValue.getSpecValue());
		}
		return result;
	}

	@Override
	public ProductBean getProductSpecBean(Long spuCode) {
		ProductEntity product = this.selectById(spuCode);
		if (product == null) {
			throw new BusinessException("该商品不存在!");
		}
		if (product.getSupplierId().equals(thirdPlaformSeller.getJd().getId())
				|| product.getSupplierId().equals(thirdPlaformSeller.getYx().getId())) {
			throw new BusinessException("京东/严选商品信息无法修改");
		}
		ProductBean productBean = new ProductBean();
		BeanUtils.copyProperties(product, productBean);
		String defaultGoodsImages = product.getGoodsImages();
		if (!StringUtils.isEmpty(defaultGoodsImages)) {
			try {
				productBean.setImgUrlList(JSONArray.parseArray(defaultGoodsImages, String.class));
			} catch (Exception e) {
			}
		}

		// 获取商品所有的规格列表
		List<SkuEntity> skus = skuMapper.getSkuBySpuCode(spuCode, null);
		if (CollectionUtils.isEmpty(skus)) {
			productBean.setSkuList(Collections.emptyList());
			productBean.setSkuSpecList(Collections.emptyList());
			return productBean;
		}
		Map<Long, SkuEntity> skuBeanMap = new HashMap<>();
		for (SkuEntity item : skus) {
			skuBeanMap.put(item.getId(), item);
		}

		// 获取商品所有的规格分类
		ProductSpecInfoCondition productInfoSpecCondition = new ProductSpecInfoCondition();
		productInfoSpecCondition.createCriteria().andSpuCodeEqualTo(spuCode);
		productInfoSpecCondition.setOrderByClause("id ASC");
		List<ProductSpecInfo> productSpecInfos = productSpecInfoMapper.selectByExample(productInfoSpecCondition);
		if (productSpecInfos.size() > 1) {// 多个规格
			ProductSpecInfo firstSpec = productSpecInfos.get(0);
			ProductSpecInfo secondSpec = productSpecInfos.get(1);
			List<SkuSpectValueBean> skuSpectBeans = skuSpecValueMapper.selectSkuTwoSpecValue(
					new ArrayList<>(skuBeanMap.keySet()), firstSpec.getSpecId(), secondSpec.getSpecId());
			if (CollectionUtils.isEmpty(skuSpectBeans)) {
				productBean.setSkuList(Collections.emptyList());
				productBean.setSkuSpecList(Collections.emptyList());
				return productBean;
			}
			skuSpectBeans.forEach(item -> {
				SkuEntity skuEntity = skuBeanMap.get(item.getSkuId());
				item.setSpecName1(firstSpec.getSpecName());
				item.setSpecName2(secondSpec.getSpecName());
				item.setGoodsCostprice(skuEntity.getGoodsCostprice());
				item.setGoodsMarkprice(skuEntity.getGoodsMarkprice());
				item.setGoodsPrice(skuEntity.getGoodsPrice());
				item.setItemNumber(skuEntity.getItemNumber());
			});
			productBean.setSkuSpectValueList(skuSpectBeans);
		} else if (productSpecInfos.size() == 1) {// 一个规格
			ProductSpecInfo productSpecInfo = productSpecInfos.get(0);
			List<SkuSpectValueBean> skuSpectBeans = skuSpecValueMapper
					.seleSkuOneSpecValue(new ArrayList<>(skuBeanMap.keySet()), productSpecInfo.getSpecId());
			if (CollectionUtils.isEmpty(skuSpectBeans)) {
				productBean.setSkuList(Collections.emptyList());
				productBean.setSkuSpecList(Collections.emptyList());
				return productBean;
			}
			skuSpectBeans.forEach(item -> {
				SkuEntity skuEntity = skuBeanMap.get(item.getSkuId());
				item.setSpecName1(productSpecInfo.getSpecName());
				item.setGoodsCostprice(skuEntity.getGoodsCostprice());
				item.setGoodsMarkprice(skuEntity.getGoodsMarkprice());
				item.setGoodsPrice(skuEntity.getGoodsPrice());
				item.setItemNumber(skuEntity.getItemNumber());
			});
			productBean.setSkuSpectValueList(skuSpectBeans);
		} else {// 无规格（制定一个默认规格（规格名：规格））
			if (skus.size() == 1) {// 1个产品
				SkuEntity sku = skus.get(0);
				SkuSpectValueBean skuSpectValueBean = new SkuSpectValueBean();
				skuSpectValueBean.setSkuId(sku.getId());
				skuSpectValueBean.setGoodsCostprice(sku.getGoodsCostprice());
				skuSpectValueBean.setGoodsPrice(sku.getGoodsPrice());
				skuSpectValueBean.setGoodsMarkprice(sku.getGoodsMarkprice());
				skuSpectValueBean.setSpecId1(-1);
				skuSpectValueBean.setSpecImage1(sku.getGoodsImage());
				skuSpectValueBean.setSpecName1("规格");
				skuSpectValueBean.setSpecValue1(sku.getGoodsSpec());
				productBean.setSkuSpectValueList(Arrays.asList(skuSpectValueBean));
			} else {
				productBean.setSkuSpectValueList(skus.stream().map(sku -> {
					SkuSpectValueBean skuSpectValueBean = new SkuSpectValueBean();
					skuSpectValueBean.setSkuId(sku.getId());
					skuSpectValueBean.setGoodsCostprice(sku.getGoodsCostprice());
					skuSpectValueBean.setGoodsPrice(sku.getGoodsPrice());
					skuSpectValueBean.setGoodsMarkprice(sku.getGoodsMarkprice());
					skuSpectValueBean.setSpecId1(-1);
					skuSpectValueBean.setSpecName1("规格");
					skuSpectValueBean.setSpecImage1(sku.getGoodsImage());
					skuSpectValueBean.setSpecValue1(sku.getGoodsSpec());
					return skuSpectValueBean;
				}).collect(Collectors.toList()));
			}
		}
		return productBean;
	}

	@Override
	public List<SkuEntity> getSkuBySpuCode(Long spuCode, Integer status) {
		return skuMapper.getSkuBySpuCode(spuCode,status);
	}

	@Transactional
	@Override
	public int udpateProductInfo(Long spuCode, ProductBean productBean) {
		ProductEntity product = this.selectById(spuCode);
		if (product == null) {
			throw new BusinessException("该商品不存在!");
		}
		if (product.getSupplierId().equals(thirdPlaformSeller.getJd().getId())
				|| product.getSupplierId().equals(thirdPlaformSeller.getYx().getId())) {
			throw new BusinessException("京东/严选商品信息无法修改");
		}
		SessionInfo sessionInfo = SessionContextHolder.getSessionInfo();
		if (sessionInfo == null) {
			throw new BusinessException("获取当前用户失败!");
		} else if (sessionInfo instanceof SessionSupplierInfo) {// 供应商
			Long supplierId = ((SessionSupplierInfo) sessionInfo).getSupplierId();
			if (!supplierId.equals(product.getSupplierId())) {
				throw new BusinessException("您无权修改此商品!");
			}
			productBean.setSupplierId(null);
		} else if (sessionInfo instanceof SessionFuliInfo) {
			// do nothing
		} else {
			throw new BusinessException("您无权修改此商品!");
		}
		// 检查商品spu信息是否变化
		ProductEntity updatedProductEntity = new ProductEntity();
		boolean isSpuUpdate = false;
		String goodsName = productBean.getGoodsName();
		String goodsNameEn = productBean.getGoodsNameEn();
		if (!StringUtils.isEmpty(goodsName) && !goodsName.equals(product.getGoodsName())) {// 处理商品名称
			updatedProductEntity.setGoodsName(goodsName);
			isSpuUpdate = true;
		}
		if (!StringUtils.isEmpty(goodsNameEn) && !goodsNameEn.equals(product.getGoodsNameEn())) {// 处理商品英文名称
			updatedProductEntity.setGoodsNameEn(goodsNameEn);
			isSpuUpdate = true;
		}
		Long categoryId3 = productBean.getCategoryId3();
		if (categoryId3 != null && !categoryId3.equals(product.getCategoryId3())) {// 处理分类信息
			ThreaLevelCategory threaLevelCategory = categoryMapper.getThreaLevelCategory(categoryId3.intValue());
			if (threaLevelCategory == null) {
				throw new BusinessException("商品分类错误!");
			}
			updatedProductEntity.setCategoryId(threaLevelCategory.getCategoryId().longValue());
			updatedProductEntity.setCategoryName(threaLevelCategory.getCategoryName());
			updatedProductEntity.setCategoryId2(threaLevelCategory.getCategoryId2().longValue());
			updatedProductEntity.setCategoryName2(threaLevelCategory.getCategoryName2());
			updatedProductEntity.setCategoryId3(categoryId3);
			updatedProductEntity.setCategoryName3(threaLevelCategory.getCategoryName3());
			isSpuUpdate = true;
		}
		Long brandId = productBean.getBrandId();
		if (brandId != null && !brandId.equals(product.getBrandId())) {// 处理品牌信息
			BrandEntity brandEntity = brandMapper.selectBrandById(brandId);
			if (brandEntity == null) {
				throw new BusinessException("品牌不存在!");
			}
			updatedProductEntity.setBrandId(brandId);
			updatedProductEntity.setBrandName(brandEntity.getName());
			isSpuUpdate = true;
		}
		Long supplierId = productBean.getSupplierId();
		if (supplierId != null && !supplierId.equals(product.getSupplierId())) {// 处理供应商信息
			SupplierInfo supplierInfo = supplierInfoService.getSupplierById(supplierId);
			if (supplierInfo == null) {
				throw new BusinessException("供应商不存在!");
			}
			updatedProductEntity.setSupplierId(supplierId);
			updatedProductEntity.setSupplierName(supplierInfo.getName());
			isSpuUpdate = true;
		}
		BigDecimal goodsFreight = productBean.getGoodsFreight();
		if (goodsFreight != null && goodsFreight.compareTo(BigDecimal.ZERO) >= 0
				&& !goodsFreight.equals(product.getGoodsFreight())) {
			updatedProductEntity.setGoodsFreight(goodsFreight);
			isSpuUpdate = true;
		}
		String introduction = productBean.getIntroduction();
		if (!StringUtils.isEmpty(introduction) && !introduction.equals(product.getIntroduction())) {
			updatedProductEntity.setIntroduction(introduction);
			isSpuUpdate = true;
		}
		String introductionEn = productBean.getIntroductionEn();
		if (!StringUtils.isEmpty(introductionEn) && !introductionEn.equals(product.getIntroductionEn())) {
			updatedProductEntity.setIntroductionEn(introductionEn);
			isSpuUpdate = true;
		}
		String season = productBean.getSeason();
		if (!StringUtils.isEmpty(season) && !season.equals(product.getSeason())) {
			updatedProductEntity.setSeason(season);
			isSpuUpdate = true;
		}
		Long placeOriginId = productBean.getPlaceOriginId();
		if (placeOriginId != null && !placeOriginId.equals(product.getPlaceOriginId())) {
			updatedProductEntity.setPlaceOriginId(placeOriginId);
			updatedProductEntity.setPlaceOrigin(productBean.getPlaceOrigin());
			isSpuUpdate = true;
		}
		Integer goodsType = productBean.getGoodsType();
		Integer sex = productBean.getSex();
		Integer isCross = productBean.getIsCross();
		if (goodsType != null && !goodsType.equals(product.getGoodsType())) {
			updatedProductEntity.setGoodsType(goodsType);
			isSpuUpdate = true;
		}
		if (sex != null && !sex.equals(product.getSex())) {
			updatedProductEntity.setSex(sex);
			isSpuUpdate = true;
		}
		if (isCross != null && !isCross.equals(product.getIsCross())) {
			updatedProductEntity.setIsCross(isCross);
			isSpuUpdate = true;
		}
		String countrySize = productBean.getCountrySize();
		if (!StringUtils.isEmpty(countrySize) && !countrySize.equals(product.getCountrySize())) {
			updatedProductEntity.setCountrySize(countrySize);
			isSpuUpdate = true;
		}
		List<String> imgUrlList = productBean.getImgUrlList();
		if (!CollectionUtils.isEmpty(imgUrlList)) {// 处理商品轮播图信息
			String goodsImages = JSONArray.toJSONString(imgUrlList);
			if (!goodsImages.equals(product.getGoodsImages())) {
				updatedProductEntity.setGoodsImages(goodsImages);
				updatedProductEntity.setGoodsImage(imgUrlList.get(0));
				isSpuUpdate = true;
			}
		}
		if (isSpuUpdate) {
			updatedProductEntity.setSpuCode(product.getSpuCode());
			updatedProductEntity.setUpdatedUser(SessionContextHolder.getCurrentLoginName());
			if (productBean.getSupplierId() == null) {
				updatedProductEntity.setGoodsState(GoodsStateEnum.INREVIEW.getValue());
			}
			this.update(updatedProductEntity);
		}
		// 检查sku信息是否变化
		List<SkuSpectValueBean> skuSpectValueList = productBean.getSkuSpectValueList();
		if (CollectionUtils.isEmpty(skuSpectValueList)) {
			return 1;
		}
		ProductSpecInfoCondition productInfoSpecCondition = new ProductSpecInfoCondition();
		productInfoSpecCondition.createCriteria().andSpuCodeEqualTo(spuCode);
		productInfoSpecCondition.setOrderByClause("id ASC");
		List<ProductSpecInfo> productSpecInfos = productSpecInfoMapper.selectByExample(productInfoSpecCondition);
		Map<Integer, ProductSpecInfo> specInfoMap = new HashMap<>();
		if (!CollectionUtils.isEmpty(productSpecInfos)) {
			for (ProductSpecInfo productSpecInfo : productSpecInfos) {
				specInfoMap.put(productSpecInfo.getSpecId(), productSpecInfo);
			}
		}
		BigDecimal goodsSaleRate = this.getGoodsSaleRate();
		for (SkuSpectValueBean skuSpectValueBean : skuSpectValueList) {
			BigDecimal goodsCostprice = skuSpectValueBean.getGoodsCostprice();
			if (goodsCostprice == null || goodsCostprice.compareTo(BigDecimal.ZERO) < 0) {
				throw new BusinessException("商品成本价错误!");
			}
			BigDecimal goodsMarkprice = skuSpectValueBean.getGoodsMarkprice();
			if (goodsMarkprice == null || goodsMarkprice.compareTo(BigDecimal.ZERO) < 0) {
				throw new BusinessException("商品市场价错误!");
			}
			BigDecimal goodsPrice = skuSpectValueBean.getGoodsPrice();
			if (goodsPrice == null || goodsPrice.compareTo(BigDecimal.ZERO) < 0) {
				throw new BusinessException("商品销售价错误!");
			}
			Long skuId = skuSpectValueBean.getSkuId();
			if (skuId == null) {// 添加
				SkuEntity skuEntity = new SkuEntity();
				skuEntity.setSpuCode(spuCode);
				String specValue1 = skuSpectValueBean.getSpecValue1();
				if (StringUtils.isEmpty(specValue1)) {
					throw new BusinessException("新增规格的规格值不能为空!");
				}
				skuEntity.setGoodsCostprice(goodsCostprice);
				skuEntity.setGoodsPrice(goodsPrice);
				skuEntity.setGoodsMarkprice(goodsMarkprice);
				skuEntity.setGoodsStorge(skuSpectValueBean.getGoodsStorge());
				skuEntity.setItemNumber(skuSpectValueBean.getItemNumber());
				skuEntity.setGoodsImage(skuSpectValueBean.getSpecImage1());
				List<SkuSpecValue> skuSpecValues;
				if (CollectionUtils.isEmpty(specInfoMap)) {// 对以前的商品做兼容
					skuEntity.setGoodsSpec(specValue1);
					skuSpecValues = Collections.emptyList();
				} else {
					Integer specId1 = skuSpectValueBean.getSpecId1();
					if (specId1 == null || specInfoMap.get(specId1) == null) {
						throw new BusinessException("商品规格参数错误!");
					}
					skuSpecValues = new ArrayList<>();
					SkuSpecValue skuSpecValue = new SkuSpecValue();
					skuSpecValue.setSpecId(specId1);
					skuSpecValue.setSpecImage(skuSpectValueBean.getSpecImage1());
					skuSpecValue.setSpecValue(specValue1);
					skuSpecValues.add(skuSpecValue);
					if (specInfoMap.size() > 1) {// 两个规格
						Integer specId2 = skuSpectValueBean.getSpecId2();
						if (specId2 == null || specInfoMap.get(specId2) == null
								|| StringUtils.isEmpty(skuSpectValueBean.getSpecValue2())) {
							throw new BusinessException("商品规格参数错误!");
						}
						SkuSpecValue skuSpecValue2 = new SkuSpecValue();
						skuSpecValue2.setSpecId(specId2);
						skuSpecValue2.setSpecImage(skuSpectValueBean.getSpecImage2());
						skuSpecValue2.setSpecValue(skuSpectValueBean.getSpecValue2());
						skuSpecValues.add(skuSpecValue2);
					}
				}
				this.addSku(skuEntity, spuCode, goodsSaleRate, skuSpecValues);
			} else {// 修改
				SkuEntity skuEntity = skuMapper.selectBySkuId(skuId);
				if (skuEntity == null) {
					throw new BusinessException("修改失败,对应的规格不存在!");
				}
				// 判断sku的基本信息是否变化
				SkuEntity skuRecorder = null;
				if (goodsCostprice.compareTo(skuEntity.getGoodsCostprice()) != 0
						|| goodsMarkprice.compareTo(skuEntity.getGoodsMarkprice()) != 0
						|| goodsPrice.compareTo(skuEntity.getGoodsPrice()) != 0) {// 判断价格是否改变
					skuRecorder = new SkuEntity();
					skuRecorder.setGoodsCostprice(goodsCostprice);
					skuRecorder.setGoodsPrice(goodsPrice);
					skuRecorder.setGoodsMarkprice(goodsMarkprice);
					skuRecorder.setGoodsDiscount(goodsPrice.divide(goodsMarkprice, 2, RoundingMode.HALF_UP));
					skuRecorder.setGoodsRate(
							goodsPrice.subtract(goodsCostprice).divide(goodsPrice, 4, RoundingMode.HALF_UP));
					if (goodsSaleRate.compareTo(skuRecorder.getGoodsRate()) > 0) {
						skuRecorder.setStatus(0);
					}
				}
				String itemNumber = skuSpectValueBean.getItemNumber();
				if (itemNumber != null) {// 判断原厂货号是否改变
					if (skuRecorder == null) {
						skuRecorder = new SkuEntity();
					}
					skuRecorder.setItemNumber(itemNumber);
				}
				// 判断sku图片是否变化
				String skuImage = skuSpectValueBean.getSpecImage1();
				if (StringUtils.isEmpty(skuImage) && !CollectionUtils.isEmpty(imgUrlList)) {
					skuImage = imgUrlList.get(0);
				}
				if (!StringUtils.isEmpty(skuImage) && !skuImage.equals(skuEntity.getGoodsImage())) {
					if (skuRecorder == null) {
						skuRecorder = new SkuEntity();
					}
					skuRecorder.setGoodsImage(skuImage);
				}
				if (CollectionUtils.isEmpty(specInfoMap)) {// 对以前的商品做兼容
					String specValue1 = skuSpectValueBean.getSpecValue1();
					if (!StringUtils.isEmpty(specValue1)) {
						if (skuRecorder == null) {
							skuRecorder = new SkuEntity();
						}
						skuRecorder.setGoodsSpec(specValue1);
					}
				} else {
					// 判断sku的规格信息是否变化
					SkuSpecValueCondition example = new SkuSpecValueCondition();
					example.createCriteria().andSkuIdEqualTo(skuId);
					List<SkuSpecValue> skuSpecValues = skuSpecValueMapper.selectByExample(example);
					if (!CollectionUtils.isEmpty(skuSpecValues)) {
						Map<Integer, SkuSpecValue> skuSpecMap = new HashMap<>();
						for (SkuSpecValue skuSpecValue : skuSpecValues) {
							skuSpecMap.put(skuSpecValue.getSpecId(), skuSpecValue);
						}
						Integer specId1 = skuSpectValueBean.getSpecId1();
						SkuSpecValue skuSpecValue;
						if (specId1 == null || (skuSpecValue = skuSpecMap.get(specId1)) == null) {
							throw new BusinessException("规格信息错误!");
						} else {
							String specValue1 = skuSpectValueBean.getSpecValue1();
							String specImage1 = skuSpectValueBean.getSpecImage1();
							if (!StringUtils.isEmpty(specValue1) && !specValue1.equals(skuSpecValue.getSpecValue())
									|| !Objects.equals(specImage1, skuSpecValue.getSpecImage())) {
								if (!StringUtils.isEmpty(specValue1)) {
									skuSpecValue.setSpecValue(specValue1);
								}
								skuSpecValue.setSpecImage(specImage1);
								skuSpecValue.setUpdateTime(new Date());
								skuSpecValueMapper.updateByPrimaryKey(skuSpecValue);
							}
							Integer specId2 = skuSpectValueBean.getSpecId2();
							if (specId2 != null) {
								SkuSpecValue skuSpecValue2 = skuSpecMap.get(specId2);
								if (skuSpecValue2 == null) {
									throw new BusinessException("规格信息错误!");
								}
								String specValue2 = skuSpectValueBean.getSpecValue2();
								String specImage2 = skuSpectValueBean.getSpecImage2();
								if (!StringUtils.isEmpty(specValue2) && !specValue2.equals(skuSpecValue2.getSpecValue())
										|| !Objects.equals(specImage2, skuSpecValue2.getSpecImage())) {
									if (!StringUtils.isEmpty(specValue2)) {
										skuSpecValue2.setSpecValue(specValue2);
									}
									skuSpecValue2.setSpecImage(specImage2);
									skuSpecValue2.setUpdateTime(new Date());
									skuSpecValueMapper.updateByPrimaryKey(skuSpecValue2);
								}
							}
						}
					}
				}
				if (skuRecorder != null) {
					skuRecorder.setId(skuId);
					skuRecorder.setUpdatedTime(new Date());
					skuRecorder.setUpdatedUser(SessionContextHolder.getCurrentLoginName());
					skuMapper.update(skuRecorder);
				}
			}
		}
		return 1;
	}

	@Override
	public int updateProductState(ProductQueryParam queryParam, Integer type) {
		Assert.notNull(queryParam, "查询参数不能为空!");
		List<Integer> goodsStatus = productMapper.selectGoodsStatus(queryParam);
		ProductEntity product = new ProductEntity();
		Date nowTime = new Date();
		if (Integer.valueOf(1).equals(type)) {// 上架
			if (goodsStatus.contains(2)) {
				throw new BusinessException("有商品正在审核，请勿提交上架!");
			}
			product.setStatedTime(new Date());
		} else if (Integer.valueOf(0).equals(type)) {// 下架
			if (goodsStatus.contains(2)) {
				throw new BusinessException("有商品正在审核，不能下架操作!");
			}
		} else {
			throw new BusinessException("上下架状态不正确!");
		}
		product.setGoodsState(type);
		product.setUpdatedTime(nowTime);
		product.setUpdatedUser(SessionContextHolder.getCurrentLoginName());
		return productMapper.updateProductStateByQuery(queryParam, product);
	}

	@Override
	public List<EsProductBean> getEsProductBeansByPage(PageBean pageBean) {
		return productMapper.selectEsProductBeansByPage(pageBean);
	}

	@Override
	public List<EsProductBean> getEsProductBeansByIds(List<Long> spuCodes) {
		return productMapper.selectEsProductBeansByIds(spuCodes);
	}

	private List<Voucher> findVouchersBySku(List<Long> voucherIds){
		List<Voucher> list = new ArrayList<>();
		for(int i=0,len=voucherIds.size();i<len;i++){
			Voucher voucher = voucherService.getVoucherByVoucherId(voucherIds.get(i));
			list.add(voucher);
		}
		return list;
	}
}
