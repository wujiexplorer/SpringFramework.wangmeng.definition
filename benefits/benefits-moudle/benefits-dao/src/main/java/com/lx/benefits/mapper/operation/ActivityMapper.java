package com.lx.benefits.mapper.operation;

import com.lx.benefits.bean.entity.operation.ActivityEntity;

import java.util.List;
import java.util.Map;

/**
 * 活动位 数据层
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
public interface ActivityMapper 
{
	/**
     * 查询活动位信息
     * 
     * @param id 活动位ID
     * @return 活动位信息
     */
	 ActivityEntity selectById(Integer id);
	
	/**
     * 查询活动位列表
     * 
     * @param activity 活动位信息
     * @return 活动位集合
     */
	 List<ActivityEntity> selectList(ActivityEntity activity);
	
	/**
     * 新增活动位
     * 
     * @param activity 活动位信息
     * @return 结果
     */
	 int insert(ActivityEntity activity);
	
	/**
     * 修改活动位
     * 
     * @param activity 活动位信息
     * @return 结果
     */
	 int update(ActivityEntity activity);
	
	/**
     * 删除活动位
     * 
     * @param id 活动位ID
     * @return 结果
     */
	 int deleteById(Integer id);
	
	/**
     * 批量删除活动位
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
    List<ActivityEntity> queryByParam(Map<String, Object> params);

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
    List<ActivityEntity> selectPageList(Map<String, Object> params);
	
}