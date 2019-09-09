package com.lx.benefits.service.operation.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.operation.TopicEntity;
import com.lx.benefits.bean.entity.product.ProductTopicCondition;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.mapper.operation.TopicMapper;
import com.lx.benefits.mapper.product.ProductTopicMapper;
import com.lx.benefits.service.operation.TopicService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专题 专题 服务层实现
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
@Service
public class TopicServiceImpl implements TopicService {

	@Resource
	private TopicMapper topicMapper;

	@Resource
	private ProductTopicMapper productTopicMapper;

	/**
     * 查询专题 专题信息
     * 
     * @param id 专题 专题ID
     * @return 专题 专题信息
     */
    @Override
	public TopicEntity selectById(Integer id) {
		return topicMapper.selectById(id);
	}
	
	/**
     * 查询专题 专题列表
     * 
     * @param topic 专题 专题信息
     * @return 专题 专题集合
     */
	@Override
	public List<TopicEntity> selectList(TopicEntity topic) {
		return topicMapper.selectList(topic);
	}

	@Override
	public List<TopicEntity> selectListBySpu(String spu) {
		return topicMapper.selectListBySpu(spu);
	}

	/**
     * 新增专题 专题
     * 
     * @param topic 专题 专题信息
     * @return 结果
     */
	@Override
	public int insert(TopicEntity topic) {
		String name = topic.getName();
		if (name == null || "".equals(name = name.trim())) {
			throw new BusinessException("专题名称不能为空!");
		}
		TopicEntity recorder = new TopicEntity();
		recorder.setName(name);
		recorder.setCreatedTime(new Date());
		recorder.setCreatedUser(SessionContextHolder.getCurrentLoginName());
		recorder.setDescription(topic.getDescription());
		try {
			return topicMapper.insert(recorder);
		} catch (DuplicateKeyException e) {
			throw new BusinessException("专题名称重复!");
		}
	}

	/**
	 * 修改专题 专题
	 * 
	 * @param topic
	 *            专题 专题信息
	 * @return 结果
	 */
	@Override
	public int update(TopicEntity topic) {
		Long topicId = topic.getId();
		if(topicId==null) {
			throw new BusinessException("专题ID不能为空!");
		}
		TopicEntity recorder = new TopicEntity();
		recorder.setId(topicId);
		recorder.setName(topic.getName());
		recorder.setDescription(topic.getDescription());
		recorder.setUpdatedTime(new Date());
		recorder.setUpdateUser(SessionContextHolder.getCurrentLoginName());
		try {
			return topicMapper.update(recorder);
		} catch (DuplicateKeyException e) {
			throw new BusinessException("专题名称重复!");
		}
	}

	/**
     * 删除专题 专题对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteById(Integer topicId) {
		int del =  topicMapper.deleteById(topicId);
		if ( del > 0) {
			ProductTopicCondition productTopicCondition=new ProductTopicCondition();
			productTopicCondition.createCriteria().andTopicIdEqualTo(topicId);
			productTopicMapper.deleteByExample(productTopicCondition);
		}
		return del;
	}

    /**
	* 条件查询
	* @param params
	* @return
	*/
    @Override
    public List<TopicEntity> queryByParam(Map<String, Object> params){
		return topicMapper.queryByParam(params);
	}

	@Override
	public PageResultBean<TopicEntity> getTopicsByPage(PageBean pageBean, Integer id, String name) {
		PageResultBean<TopicEntity> pageResultBean = new PageResultBean<>();
		Map<String, Object> params = new HashMap<>();
		if (id != null) {
			params.put("id", id);
		}
		if (!StringUtils.isEmpty(name)) {
			params.put("name", name.trim());
		}
		Integer count = topicMapper.selectCount(params);
		pageResultBean.setCount(count);
		pageResultBean.setPage(pageBean.getPage());
		pageResultBean.setPageSize(pageBean.getPageSize());
		if (count == 0 || pageBean.getOffset() >= count) {
			pageResultBean.setList(Collections.emptyList());
		} else {
			params.put("orderByClause", "id DESC");
			params.put("start", pageBean.getOffset());
			params.put("limit", pageBean.getPageSize());
			pageResultBean.setList(topicMapper.selectPageList(params));
		}
		return pageResultBean;
	}

	@Override
	public List<TopicEntity> getTopics(List<Integer> topicIds) {
		if(CollectionUtils.isEmpty(topicIds)) {
			return Collections.emptyList();
		}
		return topicMapper.getNameByIds(topicIds);
	}

}
