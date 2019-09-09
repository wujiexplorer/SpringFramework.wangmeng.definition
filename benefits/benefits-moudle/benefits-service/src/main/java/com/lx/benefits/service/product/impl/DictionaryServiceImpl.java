package com.lx.benefits.service.product.impl;


import com.lx.benefits.bean.entity.product.DictionaryEntity;
import com.lx.benefits.mapper.product.DictionaryMapper;
import com.lx.benefits.service.product.DictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 商品产地 服务层实现
 * 
 * @author ruoyi
 * @date 2019-01-30
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Resource
	private DictionaryMapper placeOriginMapper;

	/**
     * 查询商品产地信息
     * 
     * @param id 商品产地ID
     * @return 商品产地信息
     */
    @Override
	public DictionaryEntity selectById(Long id) {
        return placeOriginMapper.selectById(id);
	}
	
	/**
     * 查询商品产地列表
     * 
     * @param placeOrigin 商品产地信息
     * @return 商品产地集合
     */
	@Override
	public List<DictionaryEntity> selectList(DictionaryEntity placeOrigin) {
        return placeOriginMapper.selectList(placeOrigin);
	}
	
    /**
     * 新增商品产地
     * 
     * @param placeOrigin 商品产地信息
     * @return 结果
     */
	@Override
	public int insert(DictionaryEntity placeOrigin) {
        placeOrigin.setCreatedTime(new Date());
        placeOrigin.setUpdatedTime(new Date());
	    return placeOriginMapper.insert(placeOrigin);
	}
	
	/**
     * 修改商品产地
     * 
     * @param placeOrigin 商品产地信息
     * @return 结果
     */
	@Override
	public int update(DictionaryEntity placeOrigin) {
        placeOrigin.setUpdatedTime(new Date());
	    return placeOriginMapper.update(placeOrigin);
	}

	/**
     * 删除商品产地对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteByIds(String ids) {
		return placeOriginMapper.deleteByIds(ids.split(","));
	}

    /**
	* 条件查询
	* @param params
	* @return
	*/
    @Override
    public List<DictionaryEntity> queryByParam(Map<String, Object> params){
        return placeOriginMapper.queryByParam(params);
	}

    /**
    * 查询总记录
	* @param params
	* @return
	*/
    @Override
    public Integer selectCount(Map<String, Object> params){
		return placeOriginMapper.selectCount(params) == null ? 0 : placeOriginMapper.selectCount(params);
	}

    /**
    * 分页查询列表
	* @param params
	* @return
	*/
    @Override
    public List<DictionaryEntity> selectPageList(Map<String, Object> params) {
        return placeOriginMapper.selectPageList(params);
	}
	
}
