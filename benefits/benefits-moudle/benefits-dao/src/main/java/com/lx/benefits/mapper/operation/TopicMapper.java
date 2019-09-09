package com.lx.benefits.mapper.operation;


import com.lx.benefits.bean.entity.operation.TopicEntity;

import java.util.List;
import java.util.Map;

/**
 * 专题 专题 数据层
 *
 * @author ruoyi
 * @date 2019-01-31
 */
public interface TopicMapper
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
     * 删除专题 专题
     *
     * @param id 专题 专题ID
     * @return 结果
     */
	 int deleteById(Integer id);

	/**
     * 批量删除专题 专题
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteByIds(String[] ids);

    /**
    * 条件查询
	* @param params
	* @return
	*/
    List<TopicEntity> queryByParam(Map<String, Object> params);

    /**
    * 查询总记录
	* @param params
	* @return
	*/
    Integer selectCount(Map<String, Object> params);

    /**
    * 分页查询列表
	* @param params
	* @return
	*/
    List<TopicEntity> selectPageList(Map<String, Object> params);

	List<TopicEntity> getNameByIds(List<Integer> topicIds);

}