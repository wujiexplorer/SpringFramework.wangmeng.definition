package com.lx.benefits.service.operation.impl;


import com.lx.benefits.bean.entity.operation.ActivityEntity;
import com.lx.benefits.mapper.operation.ActivityMapper;
import com.lx.benefits.service.operation.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 活动位 服务层实现
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
@Service
public class ActivityServiceImpl implements ActivityService {

	@Resource
	private ActivityMapper activityMapper;

	/**
     * 查询活动位信息
     * 
     * @param id 活动位ID
     * @return 活动位信息
     */
    @Override
	public ActivityEntity selectById(Integer id) {
    	return activityMapper.selectById(id);
	}
	
	/**
     * 查询活动位列表
     * 
     * @param activity 活动位信息
     * @return 活动位集合
     */
	@Override
	public List<ActivityEntity> selectList(ActivityEntity activity) {
        return activityMapper.selectList(activity);
	}
	
    /**
     * 新增活动位
     * 
     * @param activity 活动位信息
     * @return 结果
     */
	@Override
	public int insert(ActivityEntity activity) {
        activity.setCreatedTime(new Date());
        activity.setUpdatedTime(new Date());
	    return activityMapper.insert(activity);
	}
	
	/**
     * 修改活动位
     * 
     * @param activity 活动位信息
     * @return 结果
     */
	@Override
	public int update(ActivityEntity activity) {
        activity.setUpdatedTime(new Date());
	    return activityMapper.update(activity);
	}

	/**
     * 删除活动位对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteByIds(String ids) {
		return activityMapper.deleteByIds(ids.split(","));
	}

    /**
	* 条件查询
	* @param params
	* @return
	*/
    @Override
    public List<ActivityEntity> queryByParam(Map<String, Object> params){
        return activityMapper.queryByParam(params);
	}

    /**
    * 查询总记录
	* @param params
	* @return
	*/
    @Override
    public Integer selectCount(Map<String, Object> params){
        return activityMapper.selectCount(params);
	}

    /**
    * 分页查询列表
	* @param params
	* @return
	*/
    @Override
    public List<ActivityEntity> selectPageList(Map<String, Object> params) {
        return activityMapper.selectPageList(params);
	}
	
}
