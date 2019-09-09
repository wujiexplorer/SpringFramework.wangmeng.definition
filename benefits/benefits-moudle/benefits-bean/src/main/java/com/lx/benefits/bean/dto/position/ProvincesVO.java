package com.lx.benefits.bean.dto.position;


import com.lx.benefits.bean.base.dto.BaseTO;

import java.util.List;

/**
 * 省份
 * @author zhuss
 *
 */
public class ProvincesVO implements BaseTO {

	private static final long serialVersionUID = -4353190289523891908L;

	private String region;//大区：华北等
	private String regcode;//大区代码
	
	private List<ProvinceTO> info;

	
	public ProvincesVO() {
		super();
	}
	public ProvincesVO(String region, String regcode, List<ProvinceTO> info) {
		super();
		this.region = region;
		this.regcode = regcode;
		this.info = info;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegcode() {
		return regcode;
	}

	public void setRegcode(String regcode) {
		this.regcode = regcode;
	}
	public List<ProvinceTO> getInfo() {
		return info;
	}
	public void setInfo(List<ProvinceTO> info) {
		this.info = info;
	}
}
