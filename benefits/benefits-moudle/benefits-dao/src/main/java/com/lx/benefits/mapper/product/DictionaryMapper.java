package com.lx.benefits.mapper.product;


import com.lx.benefits.bean.entity.product.DictionaryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品产地 数据层
 * 
 * @author ruoyi
 * @date 2019-01-30
 */
public interface DictionaryMapper
{
	/**
     * 查询商品产地信息
     * 
     * @param id 商品产地ID
     * @return 商品产地信息
     */
	 DictionaryEntity selectById(Long id);
	
	/**
     * 查询商品产地列表
     * 
     * @param placeOrigin 商品产地信息
     * @return 商品产地集合
     */
	 List<DictionaryEntity> selectList(DictionaryEntity placeOrigin);
	
	/**
     * 新增商品产地
     * 
     * @param placeOrigin 商品产地信息
     * @return 结果
     */
	int insert(DictionaryEntity placeOrigin);
	
	/**
     * 修改商品产地
     * 
     * @param placeOrigin 商品产地信息
     * @return 结果
     */
	int update(DictionaryEntity placeOrigin);
	
	/**
     * 删除商品产地
     * 
     * @param id 商品产地ID
     * @return 结果
     */
	int deleteById(Long id);
	
	/**
     * 批量删除商品产地
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
    List<DictionaryEntity> queryByParam(Map<String, Object> params);

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
    List<DictionaryEntity> selectPageList(Map<String, Object> params);
	
}