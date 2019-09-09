package com.lx.benefits.bean.dto.deliverinfo;

import java.util.List;

public class PackageDeliverInfo {
	private Integer state;// 快递单当前签收状态，包括0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单等7个状态
	private String com;// 快递公司
	private String nu;// 单号
	private List<PackageLocationInfo> data;
	private String message;// 消息体

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getNu() {
		return nu;
	}

	public void setNu(String nu) {
		this.nu = nu;
	}

	public List<PackageLocationInfo> getData() {
		return data;
	}

	public void setData(List<PackageLocationInfo> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
