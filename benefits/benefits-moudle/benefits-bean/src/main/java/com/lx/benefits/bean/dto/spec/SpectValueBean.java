package com.lx.benefits.bean.dto.spec;

import lombok.Data;

@Data
public class SpectValueBean {

	private Integer specValueId;
	private String specValue;
	private String specImage;

	public SpectValueBean() {
	}

	public SpectValueBean(Integer specValueId, String specValue) {
		this.specValueId = specValueId;
		this.specValue = specValue;
	}

	public SpectValueBean(Integer specValueId, String specValue, String specImage) {
		this.specValueId = specValueId;
		this.specValue = specValue;
		this.specImage = specImage;
	}
	
}
