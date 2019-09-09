package com.lx.benefits.service.product.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.constants.Constant;
import com.lx.benefits.bean.constants.ItemComonConstant;
import com.lx.benefits.bean.dto.admin.product.SkuQueryParam;
import com.lx.benefits.bean.dto.product.InfoDetailDto;
import com.lx.benefits.bean.dto.product.ItemInfo;
import com.lx.benefits.bean.entity.enterprcustomprice.EnterprCustomPrice;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacket;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.entity.product.SkuStorageRecorder;
import com.lx.benefits.bean.entity.product.SkuStorageRecorderCondition;
import com.lx.benefits.bean.exception.ItemServiceException;
import com.lx.benefits.bean.util.ProductUtil;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionSupplierInfo;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.bean.vo.product.SkuPriceBean;
import com.lx.benefits.dao.enterprcustomprice.EnterprCustomPriceDao;
import com.lx.benefits.dao.enterprfestivalpacket.EnterprFestivalDao;
import com.lx.benefits.mapper.product.ProductMapper;
import com.lx.benefits.mapper.product.SkuMapper;
import com.lx.benefits.mapper.product.SkuStorageRecorderMapper;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.SkuService;

/**
 * 商品 【sku】商品 服务层实现
 * 
 * @author ruoyi
 * @date 2019-02-12
 */
@Service
public class SkuServiceImpl implements SkuService {

	private final static Logger logger = LoggerFactory.getLogger(SkuServiceImpl.class);

	@Resource
	private SkuMapper skuMapper;

	@Resource
	private ProductMapper productMapper;

	@Autowired
	private EnterprFestivalDao enterprFestivalDao;
	@Autowired
	private EnterprCustomPriceDao enterprCustomPriceDao;
	@Autowired
	private ProductService productService;

	@Value("${xg.item.detail.replace.images}")
	private String replaceimages;

	@Autowired
	private SkuStorageRecorderMapper skuStorageRecorderMapper;

	/**
	 * 查询商品 【sku】商品信息
	 * 
	 * @param id
	 *            商品 【sku】商品ID
	 * @return 商品 【sku】商品信息
	 */
	@Override
	public SkuEntity selectById(Long id) {
		return skuMapper.selectById(id);
	}

	@Override
	public SkuEntity queryBySkuId(Long id) {
		return skuMapper.queryBySkuId(id);
	}

	@Override
	public SkuEntity selectBySku(String sku, String supplierName) {
		return skuMapper.selectBySku(sku, supplierName);
	}

	@Override
	public String getSpuId(List<String> sku) {
		return skuMapper.getSpuId(sku);
	}

	/**
	 * 查询商品 【sku】商品列表
	 * 
	 * @param sku
	 *            商品 【sku】商品信息
	 * @return 商品 【sku】商品集合
	 */
	@Override
	public List<SkuEntity> selectList(SkuEntity sku) {
		return skuMapper.selectList(sku);
	}

	/**
	 * 新增商品 【sku】商品
	 * 
	 * @param sku
	 *            商品 【sku】商品信息
	 * @return 结果
	 */
	@Override
	public int insert(SkuEntity sku) {
		sku.setCreatedTime(new Date());
		sku.setUpdatedTime(new Date());
		return skuMapper.insert(sku);
	}

	@Override
	public int insertBatch(List<SkuEntity> skuList) {
		return skuMapper.insertBatch(skuList);
	}

	/**
	 * 修改商品 【sku】商品
	 * 
	 * @param sku
	 *            商品 【sku】商品信息
	 * @return 结果
	 */
	@Override
	public int update(SkuEntity sku) {
		sku.setUpdatedTime(new Date());
		return skuMapper.update(sku);
	}

	@Override
	public int updateBatch(List<SkuEntity> skuList) {
		return skuMapper.updateBatch(skuList);
	}

	/**
	 * 条件查询
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public List<SkuEntity> queryByParam(Map<String, Object> params) {
		return skuMapper.queryByParam(params);
	}

	/**
	 * 查询总记录
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public Integer selectCount(Map<String, Object> params) {
		return skuMapper.selectCount(params);
	}

	/**
	 * 分页查询列表
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public List<SkuEntity> selectPageList(Map<String, Object> params) {
		return skuMapper.selectPageList(params);
	}

	@Override
	public int updateSkus(SkuEntity sku) {
		return skuMapper.updateSkus(sku);
	}

	@Override
	public SkuEntity getBySkuCode(String skuCode) {
		return skuMapper.selectBySkuCode(skuCode);
	}

	/**
	 * 下单商品减库存（未支付占用）
	 * 
	 * @param skuId
	 * @param buyCount
	 * @return
	 */
	@Override
	public Integer reduceStock(Long skuId, int buyCount) {
		return skuMapper.reduceStock(skuId, buyCount);
	}

	@Override
	public int reducePayStock(Long skuId, int buyCount) {
		return skuMapper.reducePayStock(skuId, buyCount);
	}

	@Override
	public int unPayReturnStock(Long skuId, int buyCount) {
		return skuMapper.unPayReturnStock(skuId, buyCount);
	}

	@Override
	public int payReturnStock(Long skuId, int buyCount) {
		return skuMapper.payReturnStock(skuId, buyCount);
	}

	@Override
	public List<SkuEntity> listByIdList(List<Integer> idList) {
		return skuMapper.selectByIdList(idList);
	}

	@Override
	public SkuEntity getBySkuId(Long id) {
		return skuMapper.selectBySkuId(id);
	}

	@Override
	public InfoDetailDto queryItemSkuTopicInfoForAPPHaiTao(String tid, String sku) {
		return replaceDesc(getDBItemInfoHaiTao(sku, tid));
	}

	/**
	 * 查询企业定制价格
	 */
	@Override
	public Map<Long, SkuPriceBean> singleCustomPrice(List<Long> skuIds, Long enterprId) {
		if (CollectionUtils.isEmpty(skuIds) || enterprId == null) {
			return Collections.emptyMap();
		}
		Map<Long, SkuPriceBean> skuEntityMap = new HashMap<>();
		List<EnterprCustomPrice> priceList = enterprCustomPriceDao.selectByEnterprId(enterprId, skuIds);
		if (!CollectionUtils.isEmpty(priceList)) {
			for (EnterprCustomPrice enterprCustomPrice : priceList) {
				BigDecimal price = enterprCustomPrice.getGoodsPrice();
				if (price.compareTo(BigDecimal.ZERO) > 0) {
					skuEntityMap.put(enterprCustomPrice.getGoodsId(), new SkuPriceBean(enterprCustomPrice.getGoodsPrice(), true));
				}
			}
		}
		// 查询是否是活动商品改价
		List<SkuEntity> skuEntityList = skuMapper.selectGjPriceList(skuIds);
		if (!CollectionUtils.isEmpty(skuEntityList)) {
			for (SkuEntity skuEntity : skuEntityList) {
				Long skuId = skuEntity.getId();
				if (!skuEntityMap.containsKey(skuId)) {
					Integer isEffect = skuEntity.getIsEffect();
					BigDecimal goodsPrice = ProductUtil.gjGoodsPrice(isEffect, skuEntity.getGjDiscount(), skuEntity.getGjValidStarttime(),
							skuEntity.getGjValidEndtime(), skuEntity.getGoodsPrice());
					skuEntityMap.put(skuId, new SkuPriceBean(goodsPrice, false));
				}
			}
		}
		return skuEntityMap;
	}

	@Override
	public List<SkuEntity> selectSkuBySpuCode(Map<String, Object> params) {
		return skuMapper.selectSkuBySpuCode(params);
	}

	private InfoDetailDto getDBItemInfoHaiTao(String skuCode, String activityId) {
		InfoDetailDto infoDto = new InfoDetailDto();
		/** sku 基本信息包括 skuid sku code sku基本价 供应商等信息 **/
		try {
			infoDto.setSku(skuCode);
			SkuEntity skuEntity = skuMapper.queryBySkuId(Long.parseLong(skuCode));
			ItemInfo setInfo = new ItemInfo();
			if (skuEntity != null) {
				setInfo.setUnitName(skuEntity.getSaleUnit());
				// 折扣
				if (skuEntity.getGoodsDiscount() != null) {
					infoDto.setDiscount(skuEntity.getGoodsDiscount().doubleValue());
				}
				if (skuEntity.getGoodsPrice() != null) {
					infoDto.setXgPrice(skuEntity.getGoodsPrice().doubleValue());
					infoDto.setBasicPrice(skuEntity.getGoodsPrice().doubleValue());
				}
			}
			EnterprFestivalPacket packet = enterprFestivalDao.selectByPrimaryKey(Long.parseLong(activityId));
			if (null != packet) {
				infoDto.setStoretime(Long.parseLong(packet.getStartTime().toString()));
				infoDto.setEndtime(Long.parseLong(packet.getEndTime().toString()));
			}
			ProductEntity productEntity = productMapper.selectById(skuEntity.getSpuCode());
			if (productEntity != null) {
				if (productEntity.getCategoryId() != null) {
					setInfo.setLargeId(productEntity.getCategoryId());
				}
				if (productEntity.getCategoryId2() != null) {
					setInfo.setMediumId(productEntity.getCategoryId2());
				}
				if (productEntity.getCategoryId3() != null) {
					setInfo.setSmallId(productEntity.getCategoryId3());
				}
				setInfo.setItemStyle(productEntity.getGoodsType());
				infoDto.setItemInfo(setInfo);
				// 获取品牌信息
				infoDto.setBrandName(productEntity.getBrandName());
				// 产地
				infoDto.setCountryId(productEntity.getPlaceOriginId().longValue());
				infoDto.setStatus(productEntity.getGoodsState().toString());
				infoDto.setDetailDesc(productEntity.getIntroduction());
				// 商品名
				infoDto.setMainTitle(productEntity.getGoodsName());
				infoDto.setCnName(productEntity.getGoodsName());
				infoDto.setEnName(productEntity.getGoodsNameEn());
				// 供应商
				infoDto.setSpId(productEntity.getSupplierId());
				infoDto.setSpName(productEntity.getSupplierName());
				// 图片
				infoDto.setListPictures(productEntity.getGoodsImages());

				/** 商品为下架商品 **/
				if (ItemComonConstant.ITEM_UNDERCARRIAGE.equals(infoDto.getStatus()) || ItemComonConstant.ITEM_NO_USE.equals(infoDto.getStatus())) {
					return infoDto;
				}
			}
		} catch (ItemServiceException e1) {
			logger.error("获取商品基本信息出错:sku code" + skuCode, e1);
		}
		/** 更具detail 数组获取规格信息 **/
		return infoDto;
	}

	public InfoDetailDto replaceDesc(final InfoDetailDto infoDetailDto) {
		String detailDesc = infoDetailDto.getDetailDesc();
		if (StringUtil.isNotBlank(detailDesc) && ArrayUtils.isNotEmpty(replaceimages.split(Constant.SPLIT_SIGN.COMMA))) {
			for (String restr : replaceimages.split(Constant.SPLIT_SIGN.COMMA)) {
				detailDesc = detailDesc.replaceAll("<img\\s+src=\".+" + restr + ".jpg.+/>", "");
			}
		}
		infoDetailDto.setDetailDesc(detailDesc);
		return infoDetailDto;
	}

	@Override
	public SkuEntity getSkuDetailInfo(Long skuId) {
		if (skuId == null) {
			return null;
		}
		PageBean pageBean = new PageBean();
		pageBean.setPage(1);
		pageBean.setPageSize(1);
		SkuQueryParam skuQueryParam = new SkuQueryParam();
		skuQueryParam.setSkuId(skuId);
		List<SkuEntity> skuEntitys = skuMapper.selectByQueryParam(skuQueryParam, pageBean);
		if (CollectionUtils.isEmpty(skuEntitys)) {
			return null;
		}
		return skuEntitys.get(0);
	}

	@Override
	public List<SkuEntity> selectByVoucherId(Map<String,Object> params) {
		if(params.isEmpty()){
			throw new BusinessException("优惠卷params参数不能为空！");
		}
		return skuMapper.selectByVoucherId(params);
	}

	@Override
	public Integer countByVoucherId(Map<String,Object> params) {
		if(params.isEmpty()){
			throw new BusinessException("优惠卷params参数不能为空！");
		}
		return skuMapper.countByVoucherId(params);
	}

	@Override
	public List<SkuEntity> selectSkuByseckillId(Map<String, Object> params) {
		if(params.isEmpty()){
			throw new BusinessException("优惠卷params参数不能为空！");
		}
		return skuMapper.selectSkuByseckillId(params);
	}

	@Override
	public Integer countSkuByseckillId(Map<String,Object> params) {
		if(params.isEmpty()){
			throw new BusinessException("优惠卷params参数不能为空！");
		}
		return skuMapper.countSkuByseckillId(params);
	}

	@Override
	public PageResultBean<SkuEntity> selectPage(SkuQueryParam skuQueryParam, PageBean pageBean) {
		if (skuQueryParam.getSaleRateStatus() != null) {
			skuQueryParam.setGoodsRate(productService.getGoodsSaleRate());
		}
		PageResultBean<SkuEntity> pageResultBean = new PageResultBean<>();
		int count = skuMapper.countByQueryParam(skuQueryParam);
		pageResultBean.setCount(count);
		pageResultBean.setPage(pageBean.getPage());
		pageResultBean.setPageSize(pageBean.getPageSize());
		if (count == 0 || pageBean.getOffset() >= count) {
			pageResultBean.setList(Collections.emptyList());
		} else {
			pageResultBean.setList(skuMapper.selectByQueryParam(skuQueryParam, pageBean));
		}
		return pageResultBean;
	}

	@Override
	public int updateSkuState(Long skuId, Integer status) {
		if (skuId == null) {
			throw new BusinessException("sku编码不能为空!");
		}
		if (!Integer.valueOf(1).equals(status) && !Integer.valueOf(0).equals(status)) {
			throw new BusinessException("销售状态不正确!");
		}
		SkuEntity skuEntity = skuMapper.selectSkuInfoById(skuId);
		if (skuEntity == null) {
			throw new BusinessException("SKU不存在!");
		}
		if (skuEntity.getStatus().equals(status)) {// 状态未发生变化
			return 0;
		}
		if (Integer.valueOf(1).equals(status) && skuEntity.getGoodsStorge() < 1) {
			throw new BusinessException("开启销售失败，请先添加库存数量!");
		}
		SkuEntity skuRecorder = new SkuEntity();
		skuRecorder.setId(skuId);
		skuRecorder.setStatus(status);
		skuRecorder.setUpdatedTime(new Date());
		skuRecorder.setUpdatedUser(SessionContextHolder.getCurrentLoginName());
		return skuMapper.updateSkuStatusById(skuRecorder);
	}

	@Transactional
	@Override
	public int updateSkuStock(Long skuId, Integer goodsStorge) {
		int updateCount = 0;
		if (goodsStorge == 0) {
			return 0;
		}
		SkuEntity skuEntity = skuMapper.selectSkuInfoById(skuId);
		if (skuEntity == null) {
			throw new BusinessException("该商品不存在!");
		}
		// 如果当前登陆的用户是供应商则做限制
		SessionSupplierInfo sessionSupplierInfo = SessionContextHolder.getSessionSupplierInfo();
		if (sessionSupplierInfo != null) {
			ProductEntity basicInfo = productMapper.getBasicInfo(skuEntity.getSpuCode());
			if (!sessionSupplierInfo.getSupplierId().equals(basicInfo.getSupplierId())) {
				throw new BusinessException("你没有权限操作此商品!");
			}
		}
		if (goodsStorge < 0) {
			if (skuEntity.getGoodsStorge() + goodsStorge < 0) {
				throw new BusinessException("库存减少量不能大于当前库存!");
			}
		}
		SkuEntity updateUserInfo = new SkuEntity();
		updateUserInfo.setUpdatedTime(new Date());
		updateUserInfo.setUpdatedUser(SessionContextHolder.getCurrentLoginName());
		updateCount = skuMapper.updateSkuStock(skuId, goodsStorge, updateUserInfo);
		if (updateCount > 0) {
			SkuStorageRecorder skuStorageRecorder = new SkuStorageRecorder();
			skuStorageRecorder.setGoodsStorge(goodsStorge);
			skuStorageRecorder.setSkuId(skuId);
			skuStorageRecorder.setOperator(SessionContextHolder.getCurrentLoginName());
			skuStorageRecorderMapper.insertSelective(skuStorageRecorder);
		}
		return updateCount;
	}

	@Override
	public PageResultBean<SkuStorageRecorder> getSkuStorageRecorders(Long skuId, PageBean pageBean) {
		PageResultBean<SkuStorageRecorder> pageResultBean = new PageResultBean<>();
		SkuStorageRecorderCondition condition = new SkuStorageRecorderCondition();
		condition.createCriteria().andSkuIdEqualTo(skuId);
		int count = (int) skuStorageRecorderMapper.countByExample(condition);
		pageResultBean.setCount(count);
		pageResultBean.setPageSize(pageBean.getPageSize());
		pageResultBean.setPage(pageBean.getPage());
		if (count == 0 || pageBean.getOffset() >= count) {
			pageResultBean.setList(Collections.emptyList());
		} else {
			condition.setOffset(pageBean.getOffset());
			condition.setLimit(pageBean.getPageSize());
			condition.setOrderByClause("id DESC");
			pageResultBean.setList(skuStorageRecorderMapper.selectByExample(condition));
		}
		return pageResultBean;
	}

}
