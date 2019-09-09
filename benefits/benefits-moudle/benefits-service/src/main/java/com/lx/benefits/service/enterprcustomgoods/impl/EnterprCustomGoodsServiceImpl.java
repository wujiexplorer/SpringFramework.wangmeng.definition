package com.lx.benefits.service.enterprcustomgoods.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.apollo.common.exception.BusinessException;
import com.apollo.common.utils.money.MoneyUtil;
import com.lx.benefits.bean.constants.RedisCacheKeyConstant;
import com.lx.benefits.bean.dto.admin.customized.GoodsModuleInfoDto;
import com.lx.benefits.bean.dto.order.QueryFreightVO;
import com.lx.benefits.bean.entity.agent.AgentBlackGoods;
import com.lx.benefits.bean.entity.enterprcustomgoods.EnterprCustomGoods;
import com.lx.benefits.bean.entity.enterprcustomgoods.EnterprCustomGoodsExample;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacket;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.bean.entity.product.BrandEntity;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.ProductTopicCondition;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.param.order.SkuParam;
import com.lx.benefits.bean.util.JsonUtils;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionShopInfo;
import com.lx.benefits.bean.vo.order.JDAddress;
import com.lx.benefits.bean.vo.order.ProductMsg;
import com.lx.benefits.config.properties.ThirdPlaformSeller;
import com.lx.benefits.dao.enterprcustomgoods.EnterprCustomGoodsDao;
import com.lx.benefits.mapper.enterprcustomgoods.EnterprCustomGoodsMapper;
import com.lx.benefits.service.agent.AgentBlackGoodService;
import com.lx.benefits.service.basedistrictinfo.impl.DistrictInfoServiceImpl;
import com.lx.benefits.service.creditoperateinfo.CreditOperateInfoService;
import com.lx.benefits.service.enterprcustomgoods.EnterprCustomGoodsService;
import com.lx.benefits.service.enterprfestival.EnterprFestivalService;
import com.lx.benefits.service.jdOrder.IJDOrderService;
import com.lx.benefits.service.order.impl.OrderInfoValidateServiceImpl;
import com.lx.benefits.service.product.BrandService;
import com.lx.benefits.service.product.ProductTopicService;
import com.lx.benefits.service.yxOrder.IYXOrderService;
import com.lx.benefits.utils.RedisUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author by yingcai on 2018/12/22.
 */
@Service
@Slf4j
public class EnterprCustomGoodsServiceImpl implements EnterprCustomGoodsService {

    @Autowired
    private EnterprCustomGoodsDao enterprCustomGoodsDao;

    @Autowired
    private EnterprCustomGoodsMapper enterprCustomGoodsMapper;
    @Autowired
    private AgentBlackGoodService agentBlackGoodService;
    @Autowired
    private ProductTopicService productTopicService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
	private BrandService brandService;
    
    @Resource
    private IJDOrderService ijdOrderService;
    @Resource
    private IYXOrderService iyxOrderService;
    @Resource
    private ThirdPlaformSeller thirdPlaformSeller;
    @Resource
    private OrderInfoValidateServiceImpl orderInfoValidateService;
    @Resource
    private DistrictInfoServiceImpl districtInfoService;
    @Resource
    private EnterprFestivalService enterprFestivalService;
    @Resource
    private CreditOperateInfoService creditOperateInfoService;
    
    @Override
    public Long insertGoods(GoodsModuleInfoDto req) {
        Long enterprId = req.getEnterprId();
        // 热门商品
        List<Long> hotGoodsIdList = req.getHotGoodsIdList();
        List<Long> topicIdsList = req.getTopicIdsList();
        List<Long> brandIdsList = req.getBrandIdsList();
        List<Long> categoryIdsList = req.getCategoryIdsList();
        List<Long> supplierIdsList = req.getSupplierIdsList();

        EnterprCustomGoods enterprCustomGoods = new EnterprCustomGoods();
        Long customId = req.getCustomId();
        enterprCustomGoods.setCustomId(customId);
        enterprCustomGoods.setEnterprId(enterprId);
        enterprCustomGoods.setHotGoodsIdList(JSON.toJSONString(hotGoodsIdList));
        enterprCustomGoods.setTopicIdsList(JSON.toJSONString(topicIdsList));
        enterprCustomGoods.setBrandIdsList(JSON.toJSONString(brandIdsList));
        enterprCustomGoods.setCategoryIdsList(JSON.toJSONString(categoryIdsList));
        enterprCustomGoods.setSupplierIdsList(JSON.toJSONString(supplierIdsList));
        if (null == customId || customId < 1) {
            return enterprCustomGoodsDao.insert(enterprCustomGoods);    
        }
        int rowCount = enterprCustomGoodsDao.updateByPrimaryKeySelective(enterprCustomGoods);
        if (rowCount > 0) {
			log.info("分类列表缓存刷新，企业定制设置刷新---enterId={}", enterprId);
			redisUtils.expire(String.format(RedisCacheKeyConstant.GOODS_MODULE_CACHE_KEY, enterprId), 5, TimeUnit.SECONDS);
			redisUtils.expire(String.format(RedisCacheKeyConstant.NAV_CACHE_KEY, enterprId), 6, TimeUnit.SECONDS);
        }
        return (long)rowCount;
    }

    @Override
    public GoodsModuleInfoDto findById(Long enterprId) {
        EnterprCustomGoodsExample enterprCustomGoodsExample = new EnterprCustomGoodsExample();
        enterprCustomGoodsExample.createCriteria().andEnterprIdEqualTo(enterprId);
        GoodsModuleInfoDto goodsModuleInfoDto = new GoodsModuleInfoDto();
        List<EnterprCustomGoods> enterprCustomGoodsList = enterprCustomGoodsDao.find(enterprCustomGoodsExample);
        if(null != enterprCustomGoodsList && enterprCustomGoodsList.size() == 1) {
            EnterprCustomGoods enterprCustomGoods = enterprCustomGoodsList.get(0);
            goodsModuleInfoDto.setCustomId(enterprCustomGoods.getCustomId());
            goodsModuleInfoDto.setEnterprId(enterprCustomGoods.getEnterprId());
            goodsModuleInfoDto.setSupplierIdsList(JsonUtils.getList(enterprCustomGoods.getSupplierIdsList(), new TypeReference<List<Long>>(){}));
            goodsModuleInfoDto.setHotGoodsIdList(JsonUtils.getList(enterprCustomGoods.getHotGoodsIdList(), new TypeReference<List<Long>>(){}));
            goodsModuleInfoDto.setTopicIdsList(JsonUtils.getList(enterprCustomGoods.getTopicIdsList(), new TypeReference<List<Long>>(){}));
            List<Long> brandIdsList = JsonUtils.getList(enterprCustomGoods.getBrandIdsList(), new TypeReference<List<Long>>(){});
            goodsModuleInfoDto.setBrandIdsList(brandIdsList);
            List<BrandEntity> brandEntityList = getBrandEntitys(brandIdsList);
            goodsModuleInfoDto.setBrandEntityList(brandEntityList);
            goodsModuleInfoDto.setCategoryIdsList(JsonUtils.getList(enterprCustomGoods.getCategoryIdsList(), new TypeReference<List<Long>>(){}));
            goodsModuleInfoDto.setLowestPrice(enterprCustomGoods.getLowestPrice());
        }
        return goodsModuleInfoDto;
    }

    private List<BrandEntity> getBrandEntitys(List<Long> brandIds){
    	List<BrandEntity> list = new ArrayList<>();
    	for(Long brandId : brandIds){
    		BrandEntity brandEntity = brandService.selectBrandById(brandId);
    		list.add(brandEntity);
		}
    	return list;
	}

    @Override
    public List<EnterprCustomGoods> selectListByParam(EnterprCustomGoods record) {
        return enterprCustomGoodsMapper.selectListByParam(record);
    }

	@Override
	public GoodsModuleInfoDto findByIdWithAgent(Long enterprId) {
		String cachKey = String.format(RedisCacheKeyConstant.GOODS_MODULE_CACHE_KEY, enterprId);
		GoodsModuleInfoDto goodsModuleInfoDto = redisUtils.get(cachKey, GoodsModuleInfoDto.class);
		if (goodsModuleInfoDto != null) {
			return goodsModuleInfoDto;
		} else {
			synchronized (Byte.valueOf(enterprId.byteValue())) {
				goodsModuleInfoDto = redisUtils.get(cachKey, GoodsModuleInfoDto.class);
				if (goodsModuleInfoDto != null) {
					return goodsModuleInfoDto;
				}
			}
		}
		log.info("GOODS_MODULE_INFO_LOAD_FROM_DB...");
		goodsModuleInfoDto = this.findById(enterprId);
		if(goodsModuleInfoDto==null) {
			goodsModuleInfoDto=new GoodsModuleInfoDto();
		}
		AgentBlackGoods agentBlackGoods = agentBlackGoodService.getBlackGoodsByEnterprId(enterprId);
		if (agentBlackGoods != null) {
			String brandIds = agentBlackGoods.getBrandIds();
			String categoryIds = agentBlackGoods.getCategoryIds();
			String goodIds = agentBlackGoods.getGoodIds();
			if (!StringUtils.isEmpty(brandIds)) {
				List<Long> collect = Stream.of(brandIds.split(",")).map(Long::valueOf).collect(Collectors.toList());
				List<Long> brandIdsList = goodsModuleInfoDto.getBrandIdsList();
				if (brandIdsList == null) {
					goodsModuleInfoDto.setBrandIdsList(collect);
				} else {
					brandIdsList.addAll(collect);
				}
			}
			if (!StringUtils.isEmpty(categoryIds)) {
				List<Long> collect = Stream.of(categoryIds.split(",")).map(Long::valueOf).collect(Collectors.toList());
				List<Long> categoryIdsList = goodsModuleInfoDto.getCategoryIdsList();
				if (categoryIdsList == null) {
					goodsModuleInfoDto.setCategoryIdsList(collect);
				} else {
					categoryIdsList.addAll(collect);
				}
			}
			if (!StringUtils.isEmpty(goodIds)) {
				List<Long> collect = Stream.of(goodIds.split(",")).map(Long::valueOf).collect(Collectors.toList());
				List<Long> topicIdsList = goodsModuleInfoDto.getTopicIdsList();
				if (topicIdsList == null) {
					goodsModuleInfoDto.setTopicIdsList(collect);
				} else {
					topicIdsList.addAll(collect);
				}
			}
		}

		if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getTopicIdsList()) && !CollectionUtils.isEmpty(goodsModuleInfoDto.getHotGoodsIdList())) {
			goodsModuleInfoDto.getHotGoodsIdList().removeAll(goodsModuleInfoDto.getTopicIdsList());
		}
		redisUtils.set(cachKey, goodsModuleInfoDto, 60*60*24);//缓存一天
		return goodsModuleInfoDto;
	}

	@Override
	public GoodsModuleInfoDto findByIdWithAgentNoCache(Long enterprId) {
		GoodsModuleInfoDto goodsModuleInfoDto = this.findById(enterprId);
		if(goodsModuleInfoDto==null) {
			goodsModuleInfoDto=new GoodsModuleInfoDto();
		}
		AgentBlackGoods agentBlackGoods = agentBlackGoodService.getBlackGoodsByEnterprId(enterprId);
		if (agentBlackGoods != null) {
			String brandIds = agentBlackGoods.getBrandIds();
			String categoryIds = agentBlackGoods.getCategoryIds();
			String goodIds = agentBlackGoods.getGoodIds();
			if (!StringUtils.isEmpty(brandIds)) {
				List<Long> collect = Stream.of(brandIds.split(",")).map(Long::valueOf).collect(Collectors.toList());
				List<Long> brandIdsList = goodsModuleInfoDto.getBrandIdsList();
				if (brandIdsList == null) {
					goodsModuleInfoDto.setBrandIdsList(collect);
				} else {
					brandIdsList.addAll(collect);
				}
			}
			if (!StringUtils.isEmpty(categoryIds)) {
				List<Long> collect = Stream.of(categoryIds.split(",")).map(Long::valueOf).collect(Collectors.toList());
				List<Long> categoryIdsList = goodsModuleInfoDto.getCategoryIdsList();
				if (categoryIdsList == null) {
					goodsModuleInfoDto.setCategoryIdsList(collect);
				} else {
					categoryIdsList.addAll(collect);
				}
			}
			if (!StringUtils.isEmpty(goodIds)) {
				List<Long> collect = Stream.of(goodIds.split(",")).map(Long::valueOf).collect(Collectors.toList());
				List<Long> topicIdsList = goodsModuleInfoDto.getTopicIdsList();
				if (topicIdsList == null) {
					goodsModuleInfoDto.setTopicIdsList(collect);
				} else {
					topicIdsList.addAll(collect);
				}
			}
		}

		if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getTopicIdsList()) && !CollectionUtils.isEmpty(goodsModuleInfoDto.getHotGoodsIdList())) {
			goodsModuleInfoDto.getHotGoodsIdList().removeAll(goodsModuleInfoDto.getTopicIdsList());
		}
		return goodsModuleInfoDto;
	}

	@Override
	public boolean checkAvailable(Long campaginId, Long enterprId, ProductEntity productEntity, SkuEntity skuEntity) {
		if (campaginId != null && campaginId > 0) {
			if (enterprFestivalService.isExist(campaginId, productEntity.getSpuCode())) {
				SessionShopInfo sessionShopInfo = SessionContextHolder.getSessionEmployeeInfo();
				if (sessionShopInfo != null && !creditOperateInfoService.isAuthority(campaginId, sessionShopInfo.getEmployeeId())) {
					throw new BusinessException("抱歉,您没有权限购买此商品!");
				}
				EnterprFestivalPacket enterprFestivalPacket = enterprFestivalService.getById(campaginId);
				if (enterprFestivalPacket.getIsWhitelist()) {
					return true;
				}
			} else {
				return false;
			}
		}
		GoodsModuleInfoDto goodsModuleInfoDto = this.findByIdWithAgent(enterprId);
		if (goodsModuleInfoDto != null) {// 根据企业定制来判断是否可购
			Long brandId = productEntity.getBrandId();
			List<Long> brandIdsList;
			if (brandId != null && !CollectionUtils.isEmpty(brandIdsList = goodsModuleInfoDto.getBrandIdsList())) {// 处理品牌黑名单
				if (brandIdsList.contains(productEntity.getBrandId())) {
					return false;
				}
			}

			List<Long> categoryIds;
			Long categoryId = productEntity.getCategoryId();
			if (categoryId != null && !CollectionUtils.isEmpty(categoryIds = goodsModuleInfoDto.getCategoryIdsList())) {// 处理分类黑名单
				if (categoryIds.contains(productEntity.getCategoryId())) {
					return false;
				}
			}
			List<Long> supplierIdsList;
			Long supplierId = productEntity.getSupplierId();
			if (supplierId != null && !CollectionUtils.isEmpty(supplierIdsList = goodsModuleInfoDto.getSupplierIdsList())) {
				if (supplierIdsList.contains(supplierId)) {
					return false;
				}
			}
			if (!CollectionUtils.isEmpty(goodsModuleInfoDto.getTopicIdsList())) {// 处理商品黑名单
				List<Integer> topicIds=new ArrayList<>();
				for (Long item : goodsModuleInfoDto.getTopicIdsList()) {
					topicIds.add(item.intValue());
				}
				ProductTopicCondition productTopicCondition = new ProductTopicCondition();
				productTopicCondition.createCriteria().andSpuCodeEqualTo(productEntity.getSpuCode()).andTopicIdIn(topicIds);
				long count = productTopicService.countByCondition(productTopicCondition);
				if (count > 0) {
					return false;
				}
			}
			// 处理最低价商品限制
			if (goodsModuleInfoDto.getLowestPrice() != null && goodsModuleInfoDto.getLowestPrice().compareTo(skuEntity.getGoodsPrice()) > 0) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean checkAvailable(Long enterprId, ProductEntity productEntity, SkuEntity skuEntity) {
		return this.checkAvailable(null, enterprId, productEntity, skuEntity);
	}

	@Override
	public JSONObject queryStockAndFreight(QueryFreightVO qeueryFreightVO) {
		JSONObject stockAndfreight = new JSONObject();
		List<SkuParam> skuParamLists = new ArrayList<>();
    	SkuParam skuParam = new SkuParam();
    	skuParam.setSkuId(qeueryFreightVO.getSku());
    	skuParam.setQuantity(qeueryFreightVO.getNum());
    	skuParamLists.add(skuParam);
    	List<ProductMsg> stock = null;
        if(thirdPlaformSeller.getJd().getId().equals(qeueryFreightVO.getSupplierId())){//京东
        	ConsigneeAdress address = new ConsigneeAdress();
        	address.setProvinceid(qeueryFreightVO.getProvinceId());
        	address.setCityid(qeueryFreightVO.getCityId());
        	address.setDistrictid(qeueryFreightVO.getDistrictId());
        	address.setStreetid(qeueryFreightVO.getStreetId());
        	JDAddress jdAddress = districtInfoService.getJdAddress(address);
        	stock = orderInfoValidateService.validateJdOrderInfo(skuParamLists, null, jdAddress);
            Double jdFreight = ijdOrderService.freight(qeueryFreightVO);
            stockAndfreight.put("freight",BigDecimal.valueOf(jdFreight));
        }else if(thirdPlaformSeller.getYx().getId().equals(qeueryFreightVO.getSupplierId())){//严选
        	stock = orderInfoValidateService.validateYxOrderInfo(skuParamLists);
            Integer yxFreight = iyxOrderService.freight(Long.valueOf(MoneyUtil.changeY2F(qeueryFreightVO.getPrice())));
            stockAndfreight.put("freight",MoneyUtil.changeF2Y(yxFreight));

        }
        if(CollectionUtils.isEmpty(stock)) {
        	stockAndfreight.put("stock","有货");
        }else {
        	stockAndfreight.put("stock","无货");
        }
		return stockAndfreight;
	}
}
