package com.lx.benefits.bean.dto.spec;

import java.util.List;

import lombok.Data;

@Data
public class ProductSpecValueBean {

	private Integer specNameId;
	private String specName;
	private List<SpectValueBean> specValues;

}
