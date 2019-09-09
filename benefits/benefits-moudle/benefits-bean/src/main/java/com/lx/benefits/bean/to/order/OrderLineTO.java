package com.lx.benefits.bean.to.order;

import com.lx.benefits.bean.base.dto.BaseTO;

import java.util.List;

/**
 * 订单行
 * @author zhuss
 * @2016年1月7日 下午6:38:24
 */
public class OrderLineTO implements BaseTO {

	private static final long serialVersionUID = 6903164098400003575L;

	private String lineid;//订单子项ID
	private String sku;
	private String tid;
	private String name;
	private String price;
	private String lineprice;
	private String imgurl;
	private String count;
	private List<String> specs;
	private String isreturn;//是否有退货 0否1是
	private String commision;	// 佣金
	private Integer ifcan;
	private String payamount;//实付金额
	private String itemstyle;//0-实物商品1-虚拟商品
	private String cdkey;//兑换码
	private String qrcode;//二维码
	private String codestatus;//兑换码状态
	private String servicedes;//服务说明
	private String discountamount;//折扣金额

	public String getDiscountamount() {
		return discountamount;
	}

	public void setDiscountamount(String discountamount) {
		this.discountamount = discountamount;
	}

	public String getServicedes() {
		return servicedes;
	}
	public void setServicedes(String servicedes) {
		this.servicedes = servicedes;
	}
	public String getCodestatus() {
		return codestatus;
	}
	public void setCodestatus(String codestatus) {
		this.codestatus = codestatus;
	}
	public String getItemstyle() {
		return itemstyle;
	}
	public void setItemstyle(String itemstyle) {
		this.itemstyle = itemstyle;
	}
	public String getCdkey() {
		return cdkey;
	}
	public void setCdkey(String cdkey) {
		this.cdkey = cdkey;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	public String getPayamount() {
		return payamount;
	}
	public void setPayamount(String payamount) {
		this.payamount = payamount;
	}
	public Integer getIfcan() {
		return ifcan;
	}
	public void setIfcan(Integer ifcan) {
		this.ifcan = ifcan;
	}
	public String getCommision() {
		return commision;
	}
	public void setCommision(String commision) {
		this.commision = commision;
	}
	public String getLineid() {
		return lineid;
	}
	public void setLineid(String lineid) {
		this.lineid = lineid;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLineprice() {
		return lineprice;
	}
	public void setLineprice(String lineprice) {
		this.lineprice = lineprice;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public List<String> getSpecs() {
		return specs;
	}
	public void setSpecs(List<String> specs) {
		this.specs = specs;
	}
	public String getIsreturn() {
		return isreturn;
	}
	public void setIsreturn(String isreturn) {
		this.isreturn = isreturn;
	}
}
