package com.lx.benefits.bean.dto.yianapi;


import com.lx.benefits.bean.dto.order.OrderDetailVO;

public class YianOrderDetailVO extends OrderDetailVO {

	private String deliveryname;

	private String deliverycompanycode;

	private String deliverycode;

	private String vno;

	private String vpwd;

	private String vintro;

	public String getDeliveryname() {
		return deliveryname;
	}

	public void setDeliveryname(String deliveryname) {
		this.deliveryname = deliveryname;
	}

	public String getDeliverycompanycode() {
		return deliverycompanycode;
	}

	public void setDeliverycompanycode(String deliverycompanycode) {
		this.deliverycompanycode = deliverycompanycode;
	}

	public String getDeliverycode() {
		return deliverycode;
	}

	public void setDeliverycode(String deliverycode) {
		this.deliverycode = deliverycode;
	}

	public String getVno() {
		return vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}

	public String getVpwd() {
		return vpwd;
	}

	public void setVpwd(String vpwd) {
		this.vpwd = vpwd;
	}

	public String getVintro() {
		return vintro;
	}

	public void setVintro(String vintro) {
		this.vintro = vintro;
	}
}
