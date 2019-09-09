package com.lx.benefits.mapper.product;


import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.entity.product.BrandEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 品牌 商品品牌 数据层
 * 
 * @author ruoyi
 * @date 2019-01-29
 */
public interface BrandMapper 
{
	/**
     * 查询品牌 商品品牌信息
     * 
     * @param id 品牌 商品品牌ID
     * @return 品牌 商品品牌信息
     */
	BrandEntity selectBrandById(Long id);

	BrandEntity selectByName(String name);
	
	/**
     * 查询品牌 商品品牌列表
     * 
     * @param brand 品牌 商品品牌信息
     * @return 品牌 商品品牌集合
     */
	List<BrandEntity> selectBrandList(BrandEntity brand);
	
	/**
     * 新增品牌 商品品牌
     * 
     * @param brand 品牌 商品品牌信息
     * @return 结果
     */
	int insertBrand(BrandEntity brand);
	
	/**
     * 修改品牌 商品品牌
     * 
     * @param brand 品牌 商品品牌信息
     * @return 结果
     */
	int updateBrand(BrandEntity brand);
	
	/**
     * 删除品牌 商品品牌
     * 
     * @param id 品牌 商品品牌ID
     * @return 结果
     */
	int deleteBrandById(Integer id);
	
	/**
     * 批量删除品牌 商品品牌
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteBrandByIds(String[] ids);


	List<BrandEntity> queryByParam(Map<String, Object> params);

	Integer selectCount(Map<String, Object> params);

	List<BrandEntity> selectPageList(Map<String, Object> params);

	int countWithName(@Param("name") String name);

	List<BrandEntity> selectWithName(@Param("name") String name, @Param("pageBean") PageBean pageBean);

	List<BrandEntity> getNameByIds(List<Long> brandIds);
	
}