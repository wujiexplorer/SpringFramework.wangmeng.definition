package com.lx.benefits.service.product;

import com.lx.benefits.bean.entity.product.LabelEntity;

import java.util.List;
import java.util.Map;

/**
 * 标签 服务层
 * 
 * @author ruoyi
 * @date 2019-01-30
 */
public interface LabelService {

	/**
     * 查询标签信息
     * 
     * @param id 标签ID
     * @return 标签信息
     */
	LabelEntity selectById(Long id);
	
	/**
     * 查询标签列表
     * 
     * @param label 标签信息
     * @return 标签集合
     */
	List<LabelEntity> selectList(LabelEntity label);
	
	/**
     * 新增标签
     * 
     * @param label 标签信息
     * @return 结果
     */
	int insert(LabelEntity label);
	
	/**
     * 修改标签
     * 
     * @param label 标签信息
     * @return 结果
     */
	int update(LabelEntity label);
		
	/**
     * 删除标签信息
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
    List<LabelEntity> queryByParam(Map<String, Object> params);

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
    List<LabelEntity> selectPageList(Map<String, Object> params);
	
}
