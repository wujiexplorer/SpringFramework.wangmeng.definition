package com.lx.benefits.service.agent.impl;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.lx.benefits.bean.constants.RedisCacheKeyConstant;
import com.lx.benefits.bean.entity.agent.AgentBlackGoods;
import com.lx.benefits.bean.entity.agent.AgentBlackGoodsCondition;
import com.lx.benefits.bean.vo.agent.AgentBlackGoodsBean;
import com.lx.benefits.mapper.agent.AgentBlackGoodsMapper;
import com.lx.benefits.mapper.operation.TopicMapper;
import com.lx.benefits.mapper.product.BrandMapper;
import com.lx.benefits.mapper.product.CategoryMapper;
import com.lx.benefits.service.agent.AgentBlackGoodService;
import com.lx.benefits.utils.RedisUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AgentBlackGoodServiceImpl implements AgentBlackGoodService {

	@Autowired
	private AgentBlackGoodsMapper blackGoodsMapper;
	@Autowired
	private BrandMapper brandMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private TopicMapper topicMapper;
	@Autowired
	private RedisUtils redisUtils;

	@Override
	public AgentBlackGoods getBlackGoods(Integer rebateType) {
		AgentBlackGoodsCondition example = new AgentBlackGoodsCondition();
		example.createCriteria().andRebateTypeEqualTo(rebateType);
		List<AgentBlackGoods> agentBlackGoods = blackGoodsMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(agentBlackGoods)) {
			return null;
		}
		return agentBlackGoods.get(0);
	}

	@Override
	public AgentBlackGoods getBlackGoodsByEnterprId(Long enterprId) {
		return blackGoodsMapper.getBlackGoodsByEnterprId(enterprId);
	}

	@Override
	public AgentBlackGoodsBean getBlackGoodsWithName(Integer rebateType) {
		AgentBlackGoodsBean agentBlackGoodsBean = new AgentBlackGoodsBean();
		AgentBlackGoods blackGoods = this.getBlackGoods(rebateType);
		if (blackGoods == null) {
			agentBlackGoodsBean.setBrandList(Collections.emptyList());
			agentBlackGoodsBean.setCategoryList(Collections.emptyList());
			agentBlackGoodsBean.setGoodList(Collections.emptyList());
		} else {
			// 处理品牌黑名单
			String brandIds = blackGoods.getBrandIds();
			if (!StringUtils.isEmpty(brandIds)) {
				List<Long> collect = Stream.of(brandIds.split(",")).map(item -> Long.parseLong(item)).collect(Collectors.toList());
				agentBlackGoodsBean.setBrandList(brandMapper.getNameByIds(collect));
			} else {
				agentBlackGoodsBean.setBrandList(Collections.emptyList());
			}
			// 处理分类黑名单
			String categoryIds = blackGoods.getCategoryIds();
			if (!StringUtils.isEmpty(categoryIds)) {
				List<Long> collect = Stream.of(categoryIds.split(",")).map(item -> Long.parseLong(item)).collect(Collectors.toList());
				agentBlackGoodsBean.setCategoryList(categoryMapper.getNameByIds(collect));
			} else {
				agentBlackGoodsBean.setCategoryList(Collections.emptyList());
			}
			// 处理商品黑名单
			String goodIds = blackGoods.getGoodIds();
			if (!StringUtils.isEmpty(goodIds)) {
				List<Integer> collect = Stream.of(goodIds.split(",")).map(Integer::valueOf).collect(Collectors.toList());
				agentBlackGoodsBean.setGoodList(topicMapper.getNameByIds(collect));
			} else {
				agentBlackGoodsBean.setGoodList(Collections.emptyList());
			}
		}
		return agentBlackGoodsBean;
	}

	@Override
	public void updateBlackGoods(Integer rebateType, AgentBlackGoodsBean agentBlackGoodsBean) {
		AgentBlackGoods record = new AgentBlackGoods();
		List<Long> brandIdsList = agentBlackGoodsBean.getBrandIdsList();
		if (CollectionUtils.isEmpty(brandIdsList)) {
			record.setBrandIds("");
		} else {
			record.setBrandIds(brandIdsList.stream().map(Object::toString).collect(Collectors.joining(",")));
		}
		List<Long> categoryIdsList = agentBlackGoodsBean.getCategoryIdsList();
		if (CollectionUtils.isEmpty(categoryIdsList)) {
			record.setCategoryIds("");
		} else {
			record.setCategoryIds(categoryIdsList.stream().map(Object::toString).collect(Collectors.joining(",")));
		}
		List<Long> goodIdsList = agentBlackGoodsBean.getGoodIdsList();
		if (CollectionUtils.isEmpty(goodIdsList)) {
			record.setGoodIds("");
		} else {
			record.setGoodIds(goodIdsList.stream().map(Object::toString).collect(Collectors.joining(",")));
		}
		AgentBlackGoodsCondition example = new AgentBlackGoodsCondition();
		example.createCriteria().andRebateTypeEqualTo(rebateType);
		List<AgentBlackGoods> dbAgentBlackGoods = blackGoodsMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(dbAgentBlackGoods)) {
			blackGoodsMapper.updateByExampleSelective(record, example);
		} else {
			record.setRebateType(rebateType);
			blackGoodsMapper.insertSelective(record);
		}
		this.delNavCache(rebateType);
	}

	private void delNavCache(Integer rebateType) {
		log.info("分类列表缓存刷新，企业定制设置刷新---start");
		List<Long> enterprIds = blackGoodsMapper.getEnterprIdsByRebateType(rebateType);
		if (!CollectionUtils.isEmpty(enterprIds)) {
			for (Long enterprId : enterprIds) {// 分类列表缓存刷新，企业定制设置刷新
				redisUtils.expire(String.format(RedisCacheKeyConstant.GOODS_MODULE_CACHE_KEY, enterprId), 30, TimeUnit.SECONDS);
				redisUtils.expire(String.format(RedisCacheKeyConstant.NAV_CACHE_KEY, enterprId), 32, TimeUnit.SECONDS);
			}
		}
		log.info("分类列表缓存刷新，企业定制设置刷新---end");
	}

}