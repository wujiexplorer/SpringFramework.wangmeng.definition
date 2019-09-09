package com.lx.benefits.service.operation;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.operation.TopicEntity;

import java.util.List;
import java.util.Map;

/**
 * 专题 专题 服务层
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
public interface TopicService
{
	/**
     * 查询专题 专题信息
     * 
     * @param id 专题 专题ID
     * @return 专题 专题信息
     */
	TopicEntity selectById(Integer id);
	
	/**
     * 查询专题 专题列表
     * 
     * @param topic 专题 专题信息
     * @return 专题 专题集合
     */
	List<TopicEntity> selectList(TopicEntity topic);

	List<TopicEntity> selectListBySpu(String spu);
	
	/**
     * 新增专题 专题
     * 
     * @param topic 专题 专题信息
     * @return 结果
     */
	int insert(TopicEntity topic);
	
	/**
     * 修改专题 专题
     * 
     * @param topic 专题 专题信息
     * @return 结果
     */
	int update(TopicEntity topic);
		
	/**
     * 删除专题 专题信息
     * 
     * @param topicId 需要删除的数据ID
     * @return 结果
     */
	int deleteById(Integer topicId);

    /**
    * 条件查询
	* @param params
	* @return
	*/
    List<TopicEntity> queryByParam(Map<String, Object> params);

	/**
	 * 获取主题列表
	 * 
	 * @param pageBean
	 * @param id 
	 * @param name 
	 * @return
	 */
	PageResultBean<TopicEntity> getTopicsByPage(PageBean pageBean, Integer id, String name);

	List<TopicEntity> getTopics(List<Integer> topicIds);

}
