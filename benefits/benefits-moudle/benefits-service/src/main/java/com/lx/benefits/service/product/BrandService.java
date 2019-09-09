package com.lx.benefits.service.product;


import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.entity.product.BrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌 商品品牌 服务层
 * 
 * @author ruoyi
 * @date 2019-01-29
 */
public interface BrandService {
	/**
     * 查询品牌 商品品牌信息
     * 
     * @param id 品牌 商品品牌ID
     * @return 品牌 商品品牌信息
     */
	BrandEntity selectBrandById(Long id);

	BrandEntity selectBrandByName(String name);
	
	/**
     * 查询品牌 商品品牌列表
     * 
     * @param brand 品牌 商品品牌信息
     * @return 品牌 商品品牌集合
     */
	List<BrandEntity> selectBrandList(BrandEntity brand);

	List<BrandEntity>  queryByParam(Map<String, Object> params);
	
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
     * 删除品牌 商品品牌信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteBrandByIds(String ids);

	Integer selectCount(Map<String, Object> params);

	List<BrandEntity> selectPageList(Map<String, Object> params);

	List<BrandEntity> selectListBrand(List<Long> ids, Integer status);

	PageResultBean<BrandEntity> selectBrandList(String name, PageBean pageBean);
	
}
