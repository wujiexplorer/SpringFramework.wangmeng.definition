package com.lx.benefits.service.product.impl;


import com.lx.benefits.bean.entity.product.LabelEntity;
import com.lx.benefits.mapper.product.LabelMapper;
import com.lx.benefits.service.product.LabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 标签 服务层实现
 * 
 * @author ruoyi
 * @date 2019-01-30
 */
@Service
public class LabelServiceImpl implements LabelService {

	@Resource
	private LabelMapper labelMapper;

	/**
     * 查询标签信息
     * 
     * @param id 标签ID
     * @return 标签信息
     */
    @Override
	public LabelEntity selectById(Long id) {
        return labelMapper.selectById(id);
	}
	
	/**
     * 查询标签列表
     * 
     * @param label 标签信息
     * @return 标签集合
     */
	@Override
	public List<LabelEntity> selectList(LabelEntity label) {
        return labelMapper.selectList(label);
	}
	
    /**
     * 新增标签
     * 
     * @param label 标签信息
     * @return 结果
     */
	@Override
	public int insert(LabelEntity label) {
        label.setCreatedTime(new Date());
        label.setUpdatedTime(new Date());
	    return labelMapper.insert(label);
	}
	
	/**
     * 修改标签
     * 
     * @param label 标签信息
     * @return 结果
     */
	@Override
	public int update(LabelEntity label) {
        label.setUpdatedTime(new Date());
	    return labelMapper.update(label);
	}

	/**
     * 删除标签对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteByIds(String ids) {
		return labelMapper.deleteByIds(ids.split(","));
	}

    /**
	* 条件查询
	* @param params
	* @return
	*/
    @Override
    public List<LabelEntity> queryByParam(Map<String, Object> params){
        return labelMapper.queryByParam(params);
	}

    /**
    * 查询总记录
	* @param params
	* @return
	*/
    @Override
    public Integer selectCount(Map<String, Object> params){
		return labelMapper.selectCount(params) == null ? 0 : labelMapper.selectCount(params);
	}

    /**
    * 分页查询列表
	* @param params
	* @return
	*/
    @Override
    public List<LabelEntity> selectPageList(Map<String, Object> params) {
        return labelMapper.selectPageList(params);
	}
	
}
