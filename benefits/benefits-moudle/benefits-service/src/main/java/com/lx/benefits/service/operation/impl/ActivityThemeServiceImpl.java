package com.lx.benefits.service.operation.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.admin.operation.ActivityThemeProducts;
import com.lx.benefits.bean.dto.product.ProductSearchBean;
import com.lx.benefits.bean.entity.operation.ActivityThemeEntity;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.mapper.operation.ActivityThemeMapper;
import com.lx.benefits.service.operation.ActivityThemeService;
import com.lx.benefits.service.product.ProductService;

/**
 * 专题活动 专题活动 服务层实现
 * 
 * @author ruoyi
 * @date 2019-02-01
 */
@Service
public class ActivityThemeServiceImpl implements ActivityThemeService {

	@Resource
	private ActivityThemeMapper activityThemeMapper;
	@Resource
	private ProductService productService;

	/**
	 * 查询专题活动 专题活动信息
	 * 
	 * @param id
	 *            专题活动 专题活动ID
	 * @return 专题活动 专题活动信息
	 */
	@Override
	public ActivityThemeEntity selectById(Integer id) {
		return activityThemeMapper.selectById(id);
	}

	/**
	 * 查询专题活动 专题活动列表
	 * 
	 * @param activityTheme
	 *            专题活动 专题活动信息
	 * @return 专题活动 专题活动集合
	 */
	@Override
	public List<ActivityThemeEntity> selectList(ActivityThemeEntity activityTheme) {
		return activityThemeMapper.selectList(activityTheme);
	}

	/**
	 * 新增专题活动 专题活动
	 * 
	 * @param activityTheme
	 *            专题活动 专题活动信息
	 * @return 结果
	 */
	@Override
	public int insert(ActivityThemeEntity activityTheme) {
		activityTheme.setStatus(2);//默认下架
		activityTheme.setCreatedUser(SessionContextHolder.getCurrentLoginName());
		Date now = new Date();
		activityTheme.setCreatedTime(now);
		activityTheme.setUpdatedTime(now);
		return activityThemeMapper.insert(activityTheme);
	}

	/**
	 * 修改专题活动 专题活动
	 * 
	 * @param activityTheme
	 *            专题活动 专题活动信息
	 * @return 结果
	 */
	@Override
	public int update(ActivityThemeEntity activityTheme) {
		activityTheme.setUpdatedTime(new Date());
		activityTheme.setUpdateUser(SessionContextHolder.getCurrentLoginName());
		return activityThemeMapper.update(activityTheme);
	}

	/**
	 * 删除专题活动 专题活动对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteByIds(String ids) {
		return activityThemeMapper.deleteByIds(ids.split(","));
	}

	/**
	 * 条件查询
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public List<ActivityThemeEntity> queryByParam(Map<String, Object> params) {
		return activityThemeMapper.queryByParam(params);
	}

	/**
	 * 查询总记录
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public Integer selectCount(Map<String, Object> params) {
		return activityThemeMapper.selectCount(params);
	}

	/**
	 * 分页查询列表
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public List<ActivityThemeEntity> selectPageList(Map<String, Object> params) {
		if(params==null) {
			params=new HashMap<>();
		}
		params.put("orderByClause", "id desc");
		return activityThemeMapper.selectPageList(params);
	}

	@Override
	public List<ActivityThemeProducts> getThemeProducts(PageBean pageBean) {
		Long enterprId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
		Map<String,Object> params = new HashMap<>();
		params.put("status", 1);
		List<ActivityThemeEntity> activityThemes = activityThemeMapper.queryByParam(params);
		if (CollectionUtils.isEmpty(activityThemes)) {
			return Collections.emptyList();
		}
		if (activityThemes.size() == 1) {
			ActivityThemeProducts activityThemeProducts = this.getActivityThemeProducts(enterprId, activityThemes.get(0), pageBean);
			return activityThemeProducts == null ? Collections.emptyList() : Arrays.asList(activityThemeProducts);
		} else {
			return activityThemes.parallelStream().map(activityTheme -> this.getActivityThemeProducts(enterprId, activityTheme, pageBean))
					.filter(Objects::nonNull).sorted(Comparator.comparing(ActivityThemeProducts::getId).reversed()).collect(Collectors.toList());
		}
	}

	private ActivityThemeProducts getActivityThemeProducts(Long enterprId, ActivityThemeEntity activityTheme, PageBean pageBean) {
		if (activityTheme.getTopicId() == null) {
			return null;
		}
		ProductSearchBean productSearchBean = new ProductSearchBean();
		productSearchBean.setTopicId(activityTheme.getTopicId().intValue());
		PageResultBean<ProductEntity> pageResultBean = productService.getProductsByPage(enterprId, productSearchBean, pageBean);
		if (CollectionUtils.isEmpty(pageResultBean.getList())) {
			return null;
		}
		ActivityThemeProducts activityThemeProducts = new ActivityThemeProducts();
		activityThemeProducts.setId(activityTheme.getId());
		activityThemeProducts.setTopicId(activityTheme.getTopicId().intValue());
		activityThemeProducts.setShowType(activityTheme.getShowType());
		activityThemeProducts.setThemeName(activityTheme.getThemeName());
		activityThemeProducts.setThemeUrl(activityTheme.getThemeUrl());
		activityThemeProducts.setProducts(pageResultBean);
		return activityThemeProducts;
	}

}
