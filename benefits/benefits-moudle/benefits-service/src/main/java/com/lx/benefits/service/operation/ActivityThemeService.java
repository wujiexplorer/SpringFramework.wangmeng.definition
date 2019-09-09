package com.lx.benefits.service.operation;

import java.util.List;
import java.util.Map;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.dto.admin.operation.ActivityThemeProducts;
import com.lx.benefits.bean.entity.operation.ActivityThemeEntity;

/**
 * 专题活动 专题活动 服务层
 * 
 * @author ruoyi
 * @date 2019-02-01
 */
public interface ActivityThemeService {

	/**
     * 查询专题活动 专题活动信息
     * 
     * @param id 专题活动 专题活动ID
     * @return 专题活动 专题活动信息
     */
	ActivityThemeEntity selectById(Integer id);
	
	/**
     * 查询专题活动 专题活动列表
     * 
     * @param activityTheme 专题活动 专题活动信息
     * @return 专题活动 专题活动集合
     */
	List<ActivityThemeEntity> selectList(ActivityThemeEntity activityTheme);
	
	/**
     * 新增专题活动 专题活动
     * 
     * @param activityTheme 专题活动 专题活动信息
     * @return 结果
     */
	int insert(ActivityThemeEntity activityTheme);
	
	/**
     * 修改专题活动 专题活动
     * 
     * @param activityTheme 专题活动 专题活动信息
     * @return 结果
     */
	int update(ActivityThemeEntity activityTheme);
		
	/**
     * 删除专题活动 专题活动信息
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
    List<ActivityThemeEntity> queryByParam(Map<String, Object> params);

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
    List<ActivityThemeEntity> selectPageList(Map<String, Object> params);

	List<ActivityThemeProducts> getThemeProducts(PageBean pageBean);
	
}
