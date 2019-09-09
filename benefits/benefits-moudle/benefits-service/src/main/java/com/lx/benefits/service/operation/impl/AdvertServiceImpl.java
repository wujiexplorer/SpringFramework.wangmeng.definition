package com.lx.benefits.service.operation.impl;

import com.lx.benefits.bean.entity.operation.AdvertEntity;
import com.lx.benefits.mapper.operation.AdvertMapper;
import com.lx.benefits.service.operation.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 广告位  服务层实现
 * 
 * @author ruoyi
 * @date 2019-01-30
 */
@Service
public class AdvertServiceImpl implements AdvertService
{
	@Autowired
	private AdvertMapper advertMapper;

	/**
     * 查询广告位 信息
     * 
     * @param id 广告位 ID
     * @return 广告位 信息
     */
    @Override
	public AdvertEntity selectById(Integer id) {
        return advertMapper.selectById(id);
	}
	
	/**
     * 查询广告位 列表
     * 
     * @param advert 广告位 信息
     * @return 广告位 集合
     */
	@Override
	public List<AdvertEntity> selectList(AdvertEntity advert) {
        return advertMapper.selectList(advert);
	}
	
    /**
     * 新增广告位 
     * 
     * @param advert 广告位 信息
     * @return 结果
     */
	@Override
	public int insert(AdvertEntity advert) {
        advert.setCreatedTime(new Date());
        advert.setUpdatedTime(new Date());
	    return advertMapper.insert(advert);
	}
	
	/**
     * 修改广告位 
     * 
     * @param advert 广告位 信息
     * @return 结果
     */
	@Override
	public int update(AdvertEntity advert) {
        advert.setUpdatedTime(new Date());
	    return advertMapper.update(advert);
	}

	/**
     * 删除广告位 对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteByIds(String ids) {
		return advertMapper.deleteByIds(ids.split(","));
	}

    /**
	* 条件查询
	* @param params
	* @return
	*/
    @Override
    public List<AdvertEntity> queryByParam(Map<String, Object> params){
        return advertMapper.queryByParam(params);
	}

    /**
    * 查询总记录
	* @param params
	* @return
	*/
    @Override
    public Integer selectCount(Map<String, Object> params){
        return advertMapper.selectCount(params) == null ? 0 : advertMapper.selectCount(params);
	}

    /**
    * 分页查询列表
	* @param params
	* @return
	*/
    @Override
    public List<AdvertEntity> selectPageList(Map<String, Object> params) {
        return advertMapper.selectPageList(params);
	}
	
}
