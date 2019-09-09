package com.lx.benefits.bean.dto.order;


import com.lx.benefits.bean.base.dto.BaseVO;
import com.lx.benefits.bean.dto.pay.PaywayVO;

import java.util.List;

/**
 * 提交订单页面信息
 * @author zhuss
 * @2016年1月14日 下午2:03:15
 */
public class SubmitOrderInfoVO implements BaseVO {

	private static final long serialVersionUID = 6274396638191798513L;

	private String price;//总价(包括所有的金额之后的实付金额)
	private String itemsprice;//商品总价
	private String freight;//运费
	private String taxes;//进口税
	private String disprice;//优惠金额
	private String isfirstminus; //是否是首次下单0否1是
	private String firstcoupon;//首单优惠金额
	private String cid;//优惠券ID(多个id用,号分隔)
	private String totalcoupon;//优惠券总金额
	private String totalpoint;//总积分
	private String usedpoint;//可使用积分
	private String usedpointsign;//使用标识
	private String itemnum;//订单商品总数
	private String discountamount;
	private String discountrate;
	private String discountdesc;
	
	/**积分类型  默认xg*/
	private String pointType="xg";
	/**接收手机号码*/
	private String receiveTel;
	//private List<ProductWithWarehouseVO> productinfo;
	private List<PaywayVO> paywaylist;//支付方式集合
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getItemsprice() {
		return itemsprice;
	}
	public void setItemsprice(String itemsprice) {
		this.itemsprice = itemsprice;
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
	public String getDisprice() {
		return disprice;
	}
	public void setDisprice(String disprice) {
		this.disprice = disprice;
	}
	public String getIsfirstminus() {
		return isfirstminus;
	}
	public void setIsfirstminus(String isfirstminus) {
		this.isfirstminus = isfirstminus;
	}
	//public List<ProductWithWarehouseVO> getProductinfo() {
	//	return productinfo;
	//}
	//public void setProductinfo(List<ProductWithWarehouseVO> productinfo) {
	//	this.productinfo = productinfo;
	//}
	public String getFirstcoupon() {
		return firstcoupon;
	}
	public void setFirstcoupon(String firstcoupon) {
		this.firstcoupon = firstcoupon;
	}
	public String getTotalcoupon() {
		return totalcoupon;
	}
	public void setTotalcoupon(String totalcoupon) {
		this.totalcoupon = totalcoupon;
	}
	public List<PaywayVO> getPaywaylist() {
		return paywaylist;
	}
	public void setPaywaylist(List<PaywayVO> paywaylist) {
		this.paywaylist = paywaylist;
	}
	public String getTotalpoint() {
		return totalpoint;
	}
	public void setTotalpoint(String totalpoint) {
		this.totalpoint = totalpoint;
	}
	public String getUsedpoint() {
		return usedpoint;
	}
	public void setUsedpoint(String usedpoint) {
		this.usedpoint = usedpoint;
	}
	public String getUsedpointsign() {
		return usedpointsign;
	}
	public void setUsedpointsign(String usedpointsign) {
		this.usedpointsign = usedpointsign;
	}
	public String getReceiveTel() {
		return receiveTel;
	}
	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel;
	}
	public String getPointType() {
		return pointType;
	}
	public void setPointType(String pointType) {
		this.pointType = pointType;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getItemnum() {
		return itemnum;
	}
	public void setItemnum(String itemnum) {
		this.itemnum = itemnum;
	}

	public String getDiscountamount() {
		return discountamount;
	}

	public void setDiscountamount(String discountamount) {
		this.discountamount = discountamount;
	}

	public String getDiscountrate() {
		return discountrate;
	}

	public void setDiscountrate(String discountrate) {
		this.discountrate = discountrate;
	}

	public String getDiscountdesc() {
		return discountdesc;
	}

	public void setDiscountdesc(String discountdesc) {
		this.discountdesc = discountdesc;
	}
}
