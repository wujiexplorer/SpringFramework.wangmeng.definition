package com.lx.benefits.mapper.product;


import com.lx.benefits.bean.dto.admin.category.ThreaLevelCategory;
import com.lx.benefits.bean.entity.product.CategoryEntity;
import com.lx.benefits.bean.vo.product.CategoryTree;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 分类 商品分类 数据层
 * 
 * @author ruoyi
 * @date 2019-01-29
 */
public interface CategoryMapper 
{
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
     * 删除分类 商品分类
     * 
     * @param id 分类 商品分类ID
     * @return 结果
     */
	int deleteById(Long id);
	
	/**
     * 批量删除分类 商品分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteByIds(String[] ids);

	List<CategoryEntity> queryByCategoryIds(List<Long> categoryIds);

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

	CategoryEntity queryByName(String name);

	CategoryEntity queryByNameAndPid(@Param("name") String name, @Param("parentId") Long parentId);

	List<CategoryEntity> selectByCategoryId(Map<String, Object> params);

	List<CategoryEntity> getNameByIds(List<Long> categoryIds);

	/**
	 * 获取三级分类信息
	 * @param categoryId3 第三级分类ID
	 * @return
	 */
	ThreaLevelCategory getThreaLevelCategory(Integer categoryId3);

	/**
	 * 供应商的二级分类ID
	 * @param catogoryId2
	 * @param intValue
	 * @return
	 */
	ThreaLevelCategory getThreaLevelCategoryByThirdCatId(@Param("catId")Long catId, @Param("supplierId")Integer supplierId);

	List<CategoryTree> getCategoryTrees();
	
}