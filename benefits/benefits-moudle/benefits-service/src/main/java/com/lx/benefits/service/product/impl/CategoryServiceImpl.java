package com.lx.benefits.service.product.impl;

import com.lx.benefits.bean.entity.product.CategoryEntity;
import com.lx.benefits.bean.vo.product.CategoryTree;
import com.lx.benefits.mapper.product.CategoryMapper;
import com.lx.benefits.mapper.product.ProductMapper;
import com.lx.benefits.service.cache.CacheService;
import com.lx.benefits.service.product.CategoryService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;


/**
 * 分类 商品分类 服务层实现
 * 
 * @author ruoyi
 * @date 2019-01-29
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Resource
	private CategoryMapper categoryMapper;

	@Resource
	private ProductMapper productMapper;

	@Resource
	private CacheService cacheService;

	/**
     * 查询分类 商品分类信息
     * 
     * @param id 分类 商品分类ID
     * @return 分类 商品分类信息
     */
    @Override
	public CategoryEntity selectById(Long id) {
	    return categoryMapper.selectById(id);
	}
	
	/**
     * 查询分类 商品分类列表
     * 
     * @param category 分类 商品分类信息
     * @return 分类 商品分类集合
     */
	@Override
	public List<CategoryEntity> selectList(CategoryEntity category) {
		return  categoryMapper.selectList(category);
	}
	
    /**
     * 新增分类 商品分类
     * 
     * @param category 分类 商品分类信息
     * @return 结果
     */
	@Override
	public int insert(CategoryEntity category) {
		category.setCreatedTime(new Date());
		category.setUpdatedTime(new Date());
		cacheService.delNavCache();
	    return categoryMapper.insert(category);
	}
	
	/**
     * 修改分类 商品分类
     * 
     * @param category 分类 商品分类信息
     * @return 结果
     */
	@Override
	public int update(CategoryEntity category) {
		category.setUpdatedTime(new Date());
		cacheService.delNavCache();
	    return categoryMapper.update(category);
	}

	/**
     * 删除分类 商品分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteByIds(String ids) {
		cacheService.delNavCache();
		return categoryMapper.deleteByIds(ids.split(","));
	}

    /**
	* 条件查询
	* @param params
	* @return
	*/
    @Override
    public List<CategoryEntity> queryByParam(Map<String, Object> params){
		return categoryMapper.queryByParam(params);
	}

    /**
    * 查询总记录
	* @param params
	* @return
	*/
    @Override
    public Integer selectCount(Map<String, Object> params){
		return categoryMapper.selectCount(params) == null ? 0 : categoryMapper.selectCount(params);

	}

    /**
    * 分页查询列表
	* @param params
	* @return
	*/
    @Override
    public List<CategoryEntity> selectPageList(Map<String, Object> params) {
        return categoryMapper.selectPageList(params);
	}

	@Override
	public List<CategoryEntity> queryCategoryId(Long id) {
		List<CategoryEntity> categoryEntityList = null;
		CategoryEntity categoryEntity = null;
		int count = 0;
		categoryEntityList = new ArrayList<>();
		categoryEntity = categoryMapper.selectById(id);
		categoryEntityList.add(categoryEntity);
		id = categoryEntity.getParentId();
		while(count < 10) {
			if(categoryEntity.getParentId() != 0) {
				categoryEntity = categoryMapper.selectById(id);
				categoryEntityList.add(categoryEntity);
				id = categoryEntity.getParentId();
				count++;
			}else {
				break;
			}
		}
		
		return categoryEntityList;
	}

	@Override
	public List<CategoryTree> getCategoryTrees() {
		return categoryMapper.getCategoryTrees();
	}
}
