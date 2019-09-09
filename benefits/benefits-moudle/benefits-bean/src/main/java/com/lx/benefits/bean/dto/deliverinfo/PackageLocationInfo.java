package com.lx.benefits.bean.dto.deliverinfo;

public class PackageLocationInfo {
	private String context;// 内容
	private String ftime;// 格式化后时间

	public PackageLocationInfo() {

	}

	public PackageLocationInfo(String context, String ftime) {
		this.context = context;
		this.ftime = ftime;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getFtime() {
		return ftime;
	}

	public void setFtime(String ftime) {
		this.ftime = ftime;
	}

}
