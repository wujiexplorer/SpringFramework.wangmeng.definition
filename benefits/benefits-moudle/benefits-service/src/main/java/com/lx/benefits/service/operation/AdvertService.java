package com.lx.benefits.service.operation;

import com.lx.benefits.bean.entity.operation.AdvertEntity;

import java.util.List;
import java.util.Map;

/**
 * 广告位  服务层
 * 
 * @author ruoyi
 * @date 2019-01-30
 */
public interface AdvertService
{
	/**
     * 查询广告位 信息
     * 
     * @param id 广告位 ID
     * @return 广告位 信息
     */
	AdvertEntity selectById(Integer id);
	
	/**
     * 查询广告位 列表
     * 
     * @param advert 广告位 信息
     * @return 广告位 集合
     */
	List<AdvertEntity> selectList(AdvertEntity advert);
	
	/**
     * 新增广告位 
     * 
     * @param advert 广告位 信息
     * @return 结果
     */
	int insert(AdvertEntity advert);
	
	/**
     * 修改广告位 
     * 
     * @param advert 广告位 信息
     * @return 结果
     */
	int update(AdvertEntity advert);
		
	/**
     * 删除广告位 信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteByIds(String ids);

    /**
    * 条件查询
	* @param params
	* @return
	*/
    List<AdvertEntity> queryByParam(Map<String, Object> params);

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
    List<AdvertEntity> selectPageList(Map<String, Object> params);
	
}
