package com.lx.benefits.bean.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-06 02:10.
 */
@ApiModel("列表分页通用参数")
public class PageBean {
	public static int DEFAULT_PAGE_SIZE = 10;
	public static int MAX_PAGE_SIZE = 50;// 请勿随意修改此值，如有需要可通过匿名内部类的方式重写getPageSize方法来调整页面大小
	@ApiModelProperty(value = "页码")
	private Integer page;
	@ApiModelProperty(value = "每页数据条数")
	private Integer pageSize;

	public Integer getPage() {
		return null == this.page || this.page < 1 ? 1 : this.page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		if (pageSize == null || pageSize < 1) {
			return DEFAULT_PAGE_SIZE;
		} else if (pageSize > MAX_PAGE_SIZE) {
			return MAX_PAGE_SIZE;
		}
		return pageSize;
	}

	public Integer getPageSizeExtend() {

		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffset() {
		return (this.getPage() - 1) * this.getPageSize();
	}

	public int getOffsetExtend() {
		return (this.getPage() - 1) * this.getPageSizeExtend();
	}

	@Override
	public String toString() {
		return "PageBean{" + "page=" + page + ", pageSize=" + pageSize + '}';
	}
}
