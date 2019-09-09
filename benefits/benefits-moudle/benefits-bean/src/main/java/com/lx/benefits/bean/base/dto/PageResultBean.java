package com.lx.benefits.bean.base.dto;

import java.util.List;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-06 02:10.
 */
@ApiModel("列表分页通用参数")
public class PageResultBean<T> {

	@ApiModelProperty(value = "页码")
	private Integer page;

	@ApiModelProperty(value = "每页数据条数")
	private Integer pageSize;

	@ApiModelProperty(value = "总共条数")
	private Integer count;

	private List<T> list;

	public PageResultBean() {

	}

	public PageResultBean(Integer page, Integer pageSize, Integer count, List<T> list) {
		this.page = page;
		this.pageSize = pageSize;
		this.count = count;
		this.list = list;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	public void setPageInfo(PageBean pageBean) {
		if (pageBean == null) {
			return;
		}
		this.setPage(pageBean.getPage());
		this.setPageSize(pageBean.getPageSize());
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + JSON.toJSONString(this);
	}
}