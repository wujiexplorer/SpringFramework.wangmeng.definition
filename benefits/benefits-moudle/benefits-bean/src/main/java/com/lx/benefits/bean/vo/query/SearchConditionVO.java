package com.lx.benefits.bean.vo.query;


import com.lx.benefits.bean.base.dto.BaseVO;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * 搜索结果 - 筛选条件
 * @author zhuss
 * @2016年3月2日 下午3:28:15
 */
public class SearchConditionVO implements BaseVO {

	private static final long serialVersionUID = 2678211490082901362L;

	private String id;
	private String code;
	private String name;
	private Integer count;
	private List<SearchConditionElementVO> element;
	
	public SearchConditionVO() {
		super();
	}
	public SearchConditionVO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SearchConditionElementVO> getElement() {
		return element;
	}

	public void setElement(List<SearchConditionElementVO> element) {
		this.element = element;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
