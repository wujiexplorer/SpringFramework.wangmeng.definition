package com.lx.benefits.service.operation;

import com.lx.benefits.bean.entity.operation.BannerEntity;

import java.util.List;
import java.util.Map;

/**
 * banner 服务层
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
public interface BannerService
{
	/**
     * 查询banner信息
     * 
     * @param id bannerID
     * @return banner信息
     */
	BannerEntity selectById(Integer id);
	
	/**
     * 查询banner列表
     * 
     * @param banner banner信息
     * @return banner集合
     */
	List<BannerEntity> selectList(BannerEntity banner);
	
	/**
     * 新增banner
     * 
     * @param banner banner信息
     * @return 结果
     */
	int insert(BannerEntity banner);
	
	/**
     * 修改banner
     * 
     * @param banner banner信息
     * @return 结果
     */
	int update(BannerEntity banner);
		
	/**
     * 删除banner信息
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
    List<BannerEntity> queryByParam(Map<String, Object> params);

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
    List<BannerEntity> selectPageList(Map<String, Object> params);
	
}
