package com.lx.benefits.bean.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	// 
	private int start;
	// 每页条数
	private int limit;

	public Query(Map<String, Object> params) {
		this.putAll(params);
		// 分页参数
		if (params.containsKey("page") && params.get("page") != null) {
			this.start = Integer.parseInt(params.get("page").toString());
			if (this.start == 0) {
				this.start = 1;
			}
		}
		if (params.containsKey("pageSize") && params.get("pageSize") != null) {
			this.limit = Integer.parseInt(params.get("pageSize").toString());
		}
		this.put("start", (start-1)*limit);
		this.put("limit", limit);
		this.put("page",(start-1)*limit);
		this.put("pageSize",limit);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.put("start", start);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
