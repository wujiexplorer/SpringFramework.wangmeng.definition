package com.lx.benefits.service.product;

import com.lx.benefits.bean.entity.product.CategoryEntity;
import com.lx.benefits.bean.vo.product.CategoryTree;

import java.util.List;
import java.util.Map;

/**
 * 分类 商品分类 服务层
 * 
 * @author ruoyi
 * @date 2019-01-29
 */
public interface CategoryService {

	/**
     * 查询分类 商品分类信息
     * 
     * @param id 分类 商品分类ID
     * @return 分类 商品分类信息
     */
	CategoryEntity selectById(Long id);
	
	/**
     * 查询分类 商品分类列表
     * 
     * @param category 分类 商品分类信息
     * @return 分类 商品分类集合
     */
	List<CategoryEntity> selectList(CategoryEntity category);
	
	/**
     * 新增分类 商品分类
     * 
     * @param category 分类 商品分类信息
     * @return 结果
     */
	int insert(CategoryEntity category);
	
	/**
     * 修改分类 商品分类
     * 
     * @param category 分类 商品分类信息
     * @return 结果
     */
	int update(CategoryEntity category);
		
	/**
     * 删除分类 商品分类信息
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
    List<CategoryEntity> queryByParam(Map<String, Object> params);

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
    List<CategoryEntity> selectPageList(Map<String, Object> params);

    List<CategoryEntity> queryCategoryId(Long categoryId);
    
    /**
     * 获取产品分类树
     * @return
     */
	List<CategoryTree> getCategoryTrees();
}
