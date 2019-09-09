package com.lx.benefits.bean.vo.query;


import com.lx.benefits.bean.base.dto.BaseVO;

/**
 * 搜索结果 - 筛选条件
 * @author zhuss
 * @2016年3月2日 下午3:28:15
 */
public class SearchConditionElementVO implements BaseVO {

	private static final long serialVersionUID = 2678211490082901362L;

	private String key;
	private String value;
	private String valueEn;

	public SearchConditionElementVO(String key, String value, String valueEn) {
		this.key = key;
		this.value = value;
		this.valueEn = valueEn;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueEn() {
		return valueEn;
	}

	public void setValueEn(String valueEn) {
		this.valueEn = valueEn;
	}
	
}
