package com.lx.benefits.service.product.impl;


import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.constants.Constant;
import com.lx.benefits.bean.entity.product.BrandEntity;
import com.lx.benefits.mapper.product.BrandMapper;
import com.lx.benefits.service.product.BrandService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 品牌 商品品牌 服务层实现
 * 
 * @author ruoyi
 * @date 2019-01-29
 */
@Service
public class BrandServiceImpl implements BrandService {

	@Resource
	private BrandMapper brandMapper;

	/**
     * 查询品牌 商品品牌信息
     * 
     * @param id 品牌 商品品牌ID
     * @return 品牌 商品品牌信息
     */
    @Override
	public BrandEntity selectBrandById(Long id) {
    	return brandMapper.selectBrandById(id);
	}

	@Override
	public BrandEntity selectBrandByName(String name) {
		return brandMapper.selectByName(name);
	}

	/**
     * 查询品牌 商品品牌列表
     * 
     * @param brand 品牌 商品品牌信息
     * @return 品牌 商品品牌集合
     */
	@Override
	public List<BrandEntity> selectBrandList(BrandEntity brand) {
		return brandMapper.selectBrandList(brand);
	}

	@Override
	public List<BrandEntity> queryByParam(Map<String, Object> params) {
		return  brandMapper.queryByParam(params);
	}

	/**
     * 新增品牌 商品品牌
     * 
     * @param insertBrand 品牌 商品品牌信息
     * @return 结果
     */
	@Override
	public int insertBrand(BrandEntity insertBrand) {
		insertBrand.setCreatedTime(new Date());
		insertBrand.setUpdatedTime(new Date());
	    return brandMapper.insertBrand(insertBrand);
	}
	
	/**
     * 修改品牌 商品品牌
     * 
     * @param brand 品牌 商品品牌信息
     * @return 结果
     */
	@Override
	public int updateBrand(BrandEntity brand) {
		brand.setUpdatedTime(new Date());
	    return brandMapper.updateBrand(brand);
	}

	/**
     * 删除品牌 商品品牌对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBrandByIds(String ids) {
		return brandMapper.deleteBrandByIds(ids.split(","));
	}

	@Override
	public Integer selectCount(Map<String, Object> params) {
		return brandMapper.selectCount(params);
	}

	@Override
	public List<BrandEntity> selectPageList(Map<String, Object> params) {
		return brandMapper.selectPageList(params);
	}

	@Override
	public List<BrandEntity> selectListBrand(List<Long> ids, Integer status) {
		if(CollectionUtils.isEmpty(ids)){
			return null;
		}
		List<BrandEntity> brandDOs = new ArrayList<BrandEntity>();
		List<BrandEntity> listBrand=new ArrayList<BrandEntity>();
		for (int i = 0; i < ids.size(); i++) {
			BrandEntity brandDO = this.selectBrandById(ids.get(i));
			if(brandDO!=null){
				listBrand.add(brandDO);
			}
		}
		for (BrandEntity brandDO : listBrand) {
			Integer bool =brandDO.getStatus();
			if (Constant.ENABLED.YES==bool) {
				switch (status) {
					case 0:
						break;
					case 1:
						brandDOs.add(brandDO);
						break;
					case 2:
						brandDOs.add(brandDO);
						break;
					default:
						break;
				}
			} else {
				switch (status) {
					case 0:
						brandDOs.add(brandDO);
						break;
					case 1:
						break;
					case 2:
						brandDOs.add(brandDO);
						break;
					default:
						break;
				}
			}
		}
		return brandDOs;
	}

	@Override
	public PageResultBean<BrandEntity> selectBrandList(String name, PageBean pageBean) {
		if (name != null && "".equals(name = name.trim())) {
			name = null;
		}
		int count = brandMapper.countWithName(name);
		Integer page = pageBean.getPage();
		Integer pageSize = pageBean.getPageSize();
		List<BrandEntity> list;
		if (count == 0) {
			list = Collections.emptyList();
		} else {
			list = brandMapper.selectWithName(name, pageBean);
		}
		return new PageResultBean<>(page, pageSize, count, list);
	}
}
