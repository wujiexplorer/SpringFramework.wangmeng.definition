package com.lx.benefits.bean.dto.order;


import com.lx.benefits.bean.dto.groupbuy.OrderRedeemItemVo;

import java.util.List;

public class OrderDetailVO extends OrderVO{
	private static final long serialVersionUID = 3560609727126939561L;
	private String address;//收货人地址
	private String tel;//收货人手机号
	private String name;//收货人姓名
	private String disprice;//优惠金额
	private String baseprice;//实付金额
	private String leftMoney;//可退金额（线下团购用）
	private String freight;//运费
	private String taxes;//税金
	private String remark;//买家下单留言
	private String itemprice;//商品原始总价
	private String paytime;//付款时间
	private String discountamount;//折扣金额

	public String getDiscountamount() {
		return discountamount;
	}

	public void setDiscountamount(String discountamount) {
		this.discountamount = discountamount;
	}

	/**接收手机号码*/
	private String receiveTel;
    private List<OrderRedeemItemVo> orderRedeemItemList;//兑换码
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisprice() {
		return disprice;
	}
	public void setDisprice(String disprice) {
		this.disprice = disprice;
	}
	public String getBaseprice() {
		return baseprice;
	}
	public void setBaseprice(String baseprice) {
		this.baseprice = baseprice;
	}
	public String getFreight() {
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}
	public String getTaxes() {
		return taxes;
	}
	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}
	public List<OrderRedeemItemVo> getOrderRedeemItemList() {
		return orderRedeemItemList;
	}
	public void setOrderRedeemItemList(List<OrderRedeemItemVo> orderRedeemItemList) {
		this.orderRedeemItemList = orderRedeemItemList;
	}
	public String getLeftMoney() {
		return leftMoney;
	}
	public void setLeftMoney(String leftMoney) {
		this.leftMoney = leftMoney;
	}
	public String getReceiveTel() {
		return receiveTel;
	}
	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getItemprice() {
		return itemprice;
	}
	public void setItemprice(String itemprice) {
		this.itemprice = itemprice;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	
	
	
    
	
}
