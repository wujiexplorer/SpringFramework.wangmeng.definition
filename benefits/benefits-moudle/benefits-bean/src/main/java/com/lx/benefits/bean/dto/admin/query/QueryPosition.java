package com.lx.benefits.bean.dto.admin.query;

import com.alibaba.fastjson.JSON;
import com.lx.benefits.bean.base.dto.BaseQuery;

public class QueryPosition extends BaseQuery {

	private static final long serialVersionUID = 1560486820313769171L;

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "QueryPosition" + JSON.toJSONString(this);
	}
}
