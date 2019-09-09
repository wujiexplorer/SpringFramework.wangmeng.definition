package com.lx.benefits.service.operation.impl;

import com.lx.benefits.bean.entity.operation.BannerEntity;
import com.lx.benefits.mapper.operation.BannerMapper;
import com.lx.benefits.service.operation.BannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * banner 服务层实现
 * 
 * @author ruoyi
 * @date 2019-01-31
 */
@Service
public class BannerServiceImpl implements BannerService
{
	@Resource
	private BannerMapper bannerMapper;

	/**
     * 查询banner信息
     * 
     * @param id bannerID
     * @return banner信息
     */
    @Override
	public BannerEntity selectById(Integer id) {
        return bannerMapper.selectById(id);
	}
	
	/**
     * 查询banner列表
     * 
     * @param banner banner信息
     * @return banner集合
     */
	@Override
	public List<BannerEntity> selectList(BannerEntity banner) {
        return  bannerMapper.selectList(banner);
	}
	
    /**
     * 新增banner
     * 
     * @param banner banner信息
     * @return 结果
     */
	@Override
	public int insert(BannerEntity banner) {
        banner.setCreatedTime(new Date());
        banner.setUpdatedTime(new Date());
	    return bannerMapper.insert(banner);
	}
	
	/**
     * 修改banner
     * 
     * @param banner banner信息
     * @return 结果
     */
	@Override
	public int update(BannerEntity banner) {
        banner.setUpdatedTime(new Date());
	    return bannerMapper.update(banner);
	}

	/**
     * 删除banner对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteByIds(String ids) {
		return bannerMapper.deleteByIds(ids.split(","));
	}

    /**
	* 条件查询
	* @param params
	* @return
	*/
    @Override
    public List<BannerEntity> queryByParam(Map<String, Object> params){
        return bannerMapper.queryByParam(params);
	}

    /**
    * 查询总记录
	* @param params
	* @return
	*/
    @Override
    public Integer selectCount(Map<String, Object> params){
        return bannerMapper.selectCount(params);
	}

    /**
    * 分页查询列表
	* @param params
	* @return
	*/
    @Override
    public List<BannerEntity> selectPageList(Map<String, Object> params) {
        return bannerMapper.selectPageList(params);
	}
	
}
